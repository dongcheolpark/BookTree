package com.booktree.ui.home;

import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.booktree.API.FBDatabase;
import com.booktree.common.MutableListLiveData;
import com.booktree.common.VoidCallback;
import com.booktree.model.Feed;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;

public class HomeViewModel extends ViewModel {

  private MutableListLiveData<Feed> mFeedList;
  private MutableListLiveData<Feed> mFeedList2;
  private MutableListLiveData<Date> eventsDateList;


  public HomeViewModel() {
    mFeedList = new MutableListLiveData<Feed>();
    mFeedList2 = new MutableListLiveData<Feed>();
    eventsDateList = new MutableListLiveData<Date>();
  }

  public void refreshCalendarFeedList(Date date, VoidCallback callback) {
    FBDatabase.getInstance().getFeedInCalendar(date,(list) -> {
      mFeedList.clear(false);
      if(!list.isEmpty())
        mFeedList.addAll(list);
      else
        mFeedList.clear(true);
      callback.func();
    });
  }

  public void refreshCalendarEvents(VoidCallback callback){
    FBDatabase.getInstance().getFeed((list)->{
      mFeedList2.clear(false);
      if(!list.isEmpty())
        mFeedList2.addAll(list);
      long count =  mFeedList2.getValue().stream().count(); //mFeedList 피드 수
      for(int i=0;i<count;i++){
        eventsDateList.add(mFeedList2.getValue().get(i).uploadDate); //eventsDateList에 각피드의 uploadDate를 Date타입으로 저장
      }
      callback.func();
//      for(int i=0;i<count;i++)
//        Log.d("refreshTest", String.valueOf(eventsDateList.getValue()));
//        Log.d("refreshTest","진행됨"+count);
    });
  }

  public LiveData<ArrayList<Feed>> getCalendarFeedList(){return mFeedList;}
  public LiveData<ArrayList<Date>> getCalendarEvents(){return eventsDateList;}
}