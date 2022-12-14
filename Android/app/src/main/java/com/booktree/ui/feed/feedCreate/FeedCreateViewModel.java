package com.booktree.ui.feed.feedCreate;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.FBDatabase;
import com.booktree.common.VoidCallback;
import com.booktree.model.Documents;
import com.booktree.model.Feed;
import java.io.File;
import java.util.Date;

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
    if(mImage == null) return false;
    return true;
  }

  public void createFeed(VoidCallback success, VoidCallback fail) {
    if(!validate()) {
      fail.func();
      return;
    }

    FBDatabase.getInstance().uploadImage(mImage.getValue(),(uri) -> {
      var feed = new Feed(mDocument.getValue().getIsbn(),
          FBDatabase.getInstance().getUserInfo().uid,
          mContents.getValue(),
          uri,
          new Date());
      FBDatabase.getInstance().createFeed(feed).addOnCompleteListener(task -> {
        if(task.isSuccessful()) {
          success.func();
        }
        else {
          fail.func();
        }
      });
    });
  }
}
