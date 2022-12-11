package com.booktree.ui.feed.feedDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.APIClient;
import com.booktree.API.DTO.BookDTO;
import com.booktree.API.GetDocumentAPI;
import com.booktree.model.Documents;
import com.booktree.model.Feed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDetailViewModel extends ViewModel {
  private MutableLiveData<Feed> mFeed;
  private MutableLiveData<Documents> mDocument;

  public FeedDetailViewModel() {
    mFeed = new MutableLiveData<>();
    mDocument = new MutableLiveData<>();
  }

  public void setDocument(String isbn) {
    GetDocumentAPI.getInstance().getDocumentWithISBN(isbn,(res) -> {
      mDocument.setValue(res);
    });
  }
  public LiveData<Documents> getDocument() {
    return mDocument;
  }

  public void setFeed(Feed feed) {
    mFeed.setValue(feed);
  }
  public LiveData<Feed> getFeed() {
    return mFeed;
  }
}
