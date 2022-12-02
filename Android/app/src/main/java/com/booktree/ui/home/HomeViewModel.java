package com.booktree.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.booktree.API.FBDatabase;
import com.booktree.common.MutableListLiveData;
import com.booktree.model.Feed;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;

public class HomeViewModel extends ViewModel {

  private MutableListLiveData<Feed> mFeedList;
//  private Timestamp timestamp;

  public HomeViewModel() {
    mFeedList = new MutableListLiveData<Feed>();
  }

  public void refreshCalendarFeedList(Date date) {
    FBDatabase.getInstance().getCalendarFeed(date,(list) -> {
      mFeedList.clear(false);
      mFeedList.addAll(list);
    });
  }

//  public void setDate(Date selectedDate) { this.date = selectedDate;}
//  public Date getDate(){return this.date;}

  public LiveData<ArrayList<Feed>> getCalendarFeedList(){return mFeedList;}
}