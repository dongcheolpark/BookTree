package com.booktree.API;

import android.util.Log;

import androidx.annotation.NonNull;

import com.booktree.model.Feed;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

    var minTime = date.getTime()/1000;
    var maxTime = date.getTime()/1000+86400;


//    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//    var eggRef = rootRef.child("Feeds").child("2JyzcW2HZby9NG7vWpIO").child("uploadDate");
//
//    eggRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//      @Override
//      public void onComplete(@NonNull Task<DataSnapshot> task) {
//        if(task.isSuccessful()){
//          String value = task.getResult().getValue(String.class);
//          Log.d("timeTest",value);
//        } else{
//          Log.d("timeTest",task.getException().getMessage());
//        }
//      }
//    });

//    Log.d("timeTest",date.toString());
//    Log.d("timeTest", String.valueOf(minTime));
//    Log.d("timeTest", String.valueOf(maxTime));
//    Log.d("timeTest", String.valueOf(database.collection("Feeds").document("2JyzcW2HZby9NG7vWpIO").collection("uploadDate").get().addOnCompleteListener((task)->{
//      var tempList = task.getResult().getvalues();})));
//    Log.d("timeTest",database.collection("Feeds").document("2JyzcW2HZby9NG7vWpIO").collection("uploadDate").toString());

    database.collection("Feeds").get()
            .addOnCompleteListener((task)-> {
              if(task.isSuccessful()) {
//                for(QueryDocumentSnapshot document : task.getResult()){
//                  Log.d("timeTest",document.getId() + "=>" + document.getData());
//                  }
                var feedFBList = task.getResult().getDocuments();
                feedFBList.forEach((item) -> {
                  res.add(item.toObject(Feed.class));
                });
                callback.onGetSuccess(res);
              }});
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

  public interface FBCallbackWithArray<T> {
    public void onGetSuccess(ArrayList<T> list);
  }
  public interface FBCallbackUploadImage {
    public void func(String url);
  }
}
