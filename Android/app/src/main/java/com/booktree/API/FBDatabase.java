package com.booktree.API;

import android.util.Log;

import androidx.annotation.NonNull;

import com.booktree.model.Feed;
import com.google.android.gms.tasks.OnCompleteListener;
import android.os.AsyncTask;
import android.util.Log;
import com.booktree.common.ResultCallBack;
import com.booktree.common.VoidCallback;
import com.booktree.model.Feed;
import com.booktree.model.Friend;
import com.booktree.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FBDatabase {
  private FirebaseFirestore database;
  private static FBDatabase instance = null;
  private StorageReference storageRef;
  private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

  public static FBDatabase getInstance() {
    if(instance == null) instance = new FBDatabase();
    return instance;
  }

  private FBDatabase() {
    database = FirebaseFirestore.getInstance();
    storageRef = FirebaseStorage.getInstance().getReference();
  }

  public Task<DocumentReference> createFeed(Feed feed) {
    return database.collection("Feeds").add(feed);
  }

  public void getFeed(FBCallbackWithArray<Feed> callback) {
    var res = new ArrayList<Feed>();
    database.collection("Feeds").get()
        .addOnCompleteListener((task)-> {
          if(task.isSuccessful()) {
            var feedFBList = task.getResult().getDocuments();
            feedFBList.forEach((item) -> {
              res.add(item.toObject(Feed.class));
            });
            callback.onGetSuccess(res);
          }});
  }

  public void getFeedWithIsbn(String isbn,FBCallbackWithArray<Feed> callback) {
    var res = new ArrayList<Feed>();
    database.collection("Feeds").whereEqualTo("book",isbn).get()
        .addOnCompleteListener((task)-> {
          if(task.isSuccessful()) {
            var feedFBList = task.getResult().getDocuments();
            feedFBList.forEach((item) -> {
              res.add(item.toObject(Feed.class));
            });
            callback.onGetSuccess(res);
          }});
  }

  public void getFeedInCalendar(Date date, FBCallbackWithArray<Feed> callback) {
    var res = new ArrayList<Feed>();

    var timestamp = new Timestamp(date);
    var tomorrow = new Date(date.getTime()+(long)(1000*60*60*24));
    var timestamp2 = new Timestamp(tomorrow);

    database.collection("Feeds").whereLessThan("uploadDate",timestamp2.toDate()).whereGreaterThanOrEqualTo("uploadDate",timestamp.toDate()).get()
            .addOnCompleteListener((task)-> {
              if(task.isSuccessful()) {
//                for(QueryDocumentSnapshot document : task.getResult()){
//                  Log.d("timeTest",document.getId() + "=>" + document.getData());
//                  }
                var feedFBList = task.getResult().getDocuments();
                if(!feedFBList.isEmpty()){
                  feedFBList.forEach((item) -> {
                    res.add(item.toObject(Feed.class));
                  });} else{
                  Log.d("timeTest","피드 없음");
                }
                callback.onGetSuccess(res);
              }}
            );
  }

  public void uploadImage(File file, FBCallbackUploadImage callback) {
    try {
      var imageRef = storageRef.child(file.getName());
      var inputStream = new FileInputStream(file);
      imageRef.putStream(inputStream).addOnCompleteListener((snapshot)-> {
        if(snapshot.isSuccessful()) {
          imageRef.getDownloadUrl().addOnCompleteListener((resTask) -> {
            if(resTask.isSuccessful()) {
              callback.func(resTask.getResult().toString());
            }
          });
        }
      });
    } catch (FileNotFoundException e) {
      return;
    } catch (Exception e) {
      return;
    }
  }

  public void createUser(User user, VoidCallback callback) {
    database.collection("User").whereEqualTo("uid",user.uid).get()
        .addOnCompleteListener((task) -> {
          if(task.isSuccessful()) {
            if(task.getResult().size() == 0) {
              database.collection("User").add(user);
              callback.func();
            }
          }
        });
  }

  public void getUser(String userUid, ResultCallBack<User> callBack) {
    database.collection("Users").whereEqualTo("uid",userUid).get()
        .addOnCompleteListener(task -> {
          if(task.isSuccessful()) {
            var res = task.getResult().getDocuments().get(0).toObject(User.class);
            callBack.func(res);
          }
        });
  }

  public void createFollow(Friend friend,VoidCallback callback) {
    database.collection("Friends")
        .whereEqualTo("Follower",friend.Follower)
        .whereEqualTo("Following",friend.Following).get()
        .addOnCompleteListener((task) -> {
          if(task.isSuccessful()) {
            if(task.getResult().size() == 0) {
              database.collection("Friends").add(friend);
              callback.func();
            }
          }
        });
  }
  public void getFollowing(String uid,ResultCallBack<List<User>> callBack) {
    getFollow(uid,Follow.Following,callBack);
  }

  public void getFollower(String uid,ResultCallBack<List<User>> callBack) {
    getFollow(uid,Follow.Follower,callBack);
  }

  private void getFollow(String uid,Follow follow,ResultCallBack<List<User>> callBack) {
    database.collection("Friends")
        .whereEqualTo(follow == Follow.Follower ? "Following" : "Follower",uid).get()
        .addOnCompleteListener(task -> {
          if(task.isSuccessful()) {
            var friendList = task.getResult()
                .getDocuments()
                .stream()
                .map(item -> {
                      var obj = item.toObject(Friend.class);
                      return follow == Follow.Follower ? obj.Follower : obj.Following;
                    })
                .collect(Collectors.toList());
            int size = friendList.size();
            var executorService = Executors.newFixedThreadPool(size+1);
            executorService.execute(() -> {
              try {
                var res = Collections.synchronizedList(new ArrayList<User>());
                var countLatch = new CountDownLatch(size);
                friendList.forEach(item -> {
                    executorService.execute(() -> {
                      getUser(item, (user) -> {
                        res.add(user);
                        countLatch.countDown();
                      });
                    });
                });
                countLatch.await(5, TimeUnit.SECONDS);
                callBack.func(res);
              } catch (InterruptedException e) {
                Log.e("Error", e.getMessage(), null);
              }
            });
          }
        });
  }

  public enum Follow{
    Follower,
    Following
  }

  public interface FBCallbackWithArray<T> {
    public void onGetSuccess(ArrayList<T> list);
  }
  public interface FBCallbackUploadImage {
    public void func(String url);
  }
}
