package com.booktree.ui.feed.feedCreate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.model.Documents;
import com.booktree.ui.feed.feedList.FeedListAdapter.ViewHolder;

public class FeedCreateViewModel extends ViewModel {
  private MutableLiveData<Documents> Document;

  public FeedCreateViewModel() {
    Document = new MutableLiveData<>();
  }

  public void setDocument(Documents document) {
    Document.setValue(document);
  }

  public LiveData<Documents> getDocument() {
    return Document;
  }
}
