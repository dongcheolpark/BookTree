package com.booktree.ui.feed.feedList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.model.Feed;
import java.util.ArrayList;

public class FeedRecyclerList {
  private final RecyclerView feedList;
  private final Context context;
  private final FeedListAdapter adapter;
  private LinearLayoutManager layoutManager;



  public FeedRecyclerList(RecyclerView list, Context context) {
    feedList = list;
    this.context = context;
    adapter = new FeedListAdapter(this.context);
    layoutManager = new LinearLayoutManager(this.context);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    feedList.setAdapter(adapter);
    feedList.setLayoutManager(layoutManager);
    // 리스트 기본 설정
  }

  public void setFeedList(ArrayList<Feed> list) {
    adapter.setList(list);
  }
}
