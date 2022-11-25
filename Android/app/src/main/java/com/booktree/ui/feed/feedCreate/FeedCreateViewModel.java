package com.booktree.ui.feed.feedCreate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.FBDatabase;
import com.booktree.model.Documents;
import com.booktree.model.Feed;
import java.util.Objects;

public class FeedCreateViewModel extends ViewModel {
  private MutableLiveData<Documents> mDocument;
  private MutableLiveData<String> mContents;

  public FeedCreateViewModel() {
    mDocument = new MutableLiveData<>();
    mContents = new MutableLiveData<>();
  }

  public void setDocument(Documents document) {
    mDocument.postValue(document);
  }

  public void setContents(String value) {
    mContents.postValue(value);
  }

  public LiveData<Documents> getDocument() {
    return mDocument;
  }

  private boolean validate() {
    if(mDocument.getValue() == null) return false;
    if(mDocument.getValue().isbn.equals("")) return false;
    if(mContents.getValue() == null) return false;
    return true;
  }

  public boolean createFeed() {
    if(!validate()) return false;
    var feed = new Feed(mDocument.getValue().getIsbn(),
        "test",
        mContents.getValue(),
        "https://i.stack.imgur.com/GsDIl.jpg");
    FBDatabase.getInstance().createFeed(feed);
    return true;
  }
}
