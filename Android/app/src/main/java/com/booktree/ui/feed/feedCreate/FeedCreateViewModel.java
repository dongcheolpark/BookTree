package com.booktree.ui.feed.feedCreate;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.FBDatabase;
import com.booktree.model.Documents;
import com.booktree.model.Feed;
import java.io.File;

public class FeedCreateViewModel extends ViewModel {
  private MutableLiveData<Documents> mDocument;
  private MutableLiveData<String> mContents;
  private MutableLiveData<Uri> mImage;

  public FeedCreateViewModel() {
    mDocument = new MutableLiveData<>();
    mContents = new MutableLiveData<>();
    mImage = new MutableLiveData<>();

  }
  public void setImage(Uri uri) {
    mImage.setValue(uri);
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
  public LiveData<Uri> getUri() {return mImage;}

  private boolean validate() {
    if(mDocument.getValue() == null) return false;
    if(mDocument.getValue().isbn.equals("")) return false;
    if(mContents.getValue() == null) return false;
    return true;
  }
  private boolean uploadImage() {


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
