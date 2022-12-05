package com.booktree.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.booktree.API.FBDatabase;
import com.booktree.common.MutableListLiveData;
import com.booktree.common.VoidCallback;
import com.booktree.model.Feed;

import java.util.ArrayList;
import java.util.Date;

public class HomeViewModel extends ViewModel {

  private MutableListLiveData<Feed> mFeedList;


  public HomeViewModel() {
    mFeedList = new MutableListLiveData<Feed>();
  }

  public void refreshCalendarFeedList(Date date, VoidCallback callback) {
    FBDatabase.getInstance().getFeedInCalendar(date,(list) -> {
      mFeedList.clear(false);
      if(!list.isEmpty())
        mFeedList.addAll(list);
      callback.func();
    });
  }

  public LiveData<ArrayList<Feed>> getCalendarFeedList(){return mFeedList;}
}