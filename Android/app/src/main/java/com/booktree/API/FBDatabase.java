package com.booktree.API;

import android.net.Uri;

import com.booktree.model.Feed;
import com.booktree.model.Friend;
import com.booktree.model.Users;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FBDatabase {
  private FirebaseFirestore database;
  private static FBDatabase instance = null;
  private StorageReference storageRef;

  public static FBDatabase getInstance() {
    if(instance == null) instance = new FBDatabase();
    return instance;
  }

  private FBDatabase() {
    database = FirebaseFirestore.getInstance();
    storageRef = FirebaseStorage.getInstance().getReference();
  }

  public Task<DocumentReference> createUser (Users user){
    return database.collection("Users").add(user);
  }

  public void getUser(FBCallbackWithArray<Users> callback, ArrayList<Friend> friendArrayList){
    var res = new ArrayList<Users>();
    database.collection("Users").get()
            .addOnCompleteListener((task)->{
              if(task.isSuccessful()){
                var userFBList = task.getResult().getDocuments();
                userFBList.forEach((item)->{
                  res.add(item.toObject(Users.class));
                });
                callback.onGetSuccess(res);
              }});
  }

  public Task<DocumentReference> createFriend(Friend friend){
    return database.collection("Friends").add(friend);
  }

  public void getFriend(FBCallbackWithArray<Friend> callback){
    var res = new ArrayList<Friend>();
    database.collection("Friends").get()
            .addOnCompleteListener((task)->{
              if(task.isSuccessful()){
                var friendFBList=task.getResult().getDocuments();
                friendFBList.forEach((item)->{
                  res.add(item.toObject(Friend.class));
                });
                callback.onGetSuccess(res);
              }});
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

  public void uploadImage(Uri uri, FBCallbackUploadImage callback) {
    try {
      var imageRef = storageRef.child(uri.getLastPathSegment());
      var inputStream = new FileInputStream(new File(uri.getPath()));
      //imageRef.putStream(inputStream).onSuccessTask();
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
