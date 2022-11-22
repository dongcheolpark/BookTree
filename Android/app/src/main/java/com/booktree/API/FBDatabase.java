package com.booktree.API;

import com.booktree.model.Feed;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
}
