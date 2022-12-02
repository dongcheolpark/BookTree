package com.booktree.ui.feed.feedList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.model.Feed;
import com.booktree.common.RecyclerViewList.RecyclerViewList;
import java.util.ArrayList;

public class FeedRecyclerList extends RecyclerViewList<FeedListAdapter> {
  public FeedRecyclerList(RecyclerView recyclerView, Context context) {
    super(recyclerView,context);
    adapter = new FeedListAdapter(this.context);
    recyclerView.setAdapter(adapter);
  }
}
