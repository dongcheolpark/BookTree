package com.booktree.ui.feed.feedDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.model.Feed;

public class FeedDetailViewModel extends ViewModel {
  private MutableLiveData<Feed> mFeed;

  public FeedDetailViewModel() {
    mFeed = new MutableLiveData<>();
  }

  public void setFeed(Feed feed) {
    mFeed.setValue(feed);
  }
  public LiveData<Feed> getFeed() {
    return mFeed;
  }
}
