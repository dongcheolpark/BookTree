package com.booktree.ui.feed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.FBDatabase;
import com.booktree.common.MutableListLiveData;
import com.booktree.common.VoidCallback;
import com.booktree.model.Feed;
import java.util.ArrayList;

public class FeedViewModel extends ViewModel {

  private MutableListLiveData<Feed> mFeedList;

  public FeedViewModel() {
    mFeedList = new MutableListLiveData<Feed>();
  }

  public void refreshFeedList(VoidCallback callback) {
    FBDatabase.getInstance().getFeed((list) -> {
      mFeedList.clear(false);
      if(!list.isEmpty())
        mFeedList.addAll(list);
      callback.func();
    });
  }

  public LiveData<ArrayList<Feed>> getFeedList() {
    return mFeedList;
  }
}