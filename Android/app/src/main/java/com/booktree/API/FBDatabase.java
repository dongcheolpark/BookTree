package com.booktree.API;

import com.booktree.model.Feed;
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
