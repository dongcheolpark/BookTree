package com.booktree.API;

import com.booktree.model.Feed;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.sql.Array;
import java.util.ArrayList;

public class FBDatabase {
  private FirebaseFirestore database;
  private static FBDatabase instance = null;

  public static FBDatabase getInstance() {
    if(instance == null) instance = new FBDatabase();
    return instance;
  }

  private FBDatabase() {
    database = FirebaseFirestore.getInstance();
  }

  public Task<DocumentReference> createFeed(Feed feed) {
    return database.collection("Feeds").add(feed);
  }

  public void getFeed(FBCallback<Feed> callback) {
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

  public interface FBCallback<T> {
    public void onGetSuccess(ArrayList<T> list);
  }
}
