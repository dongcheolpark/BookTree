package com.booktree.ui.feed.feedList;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.common.RecyclerViewList.RecyclerViewListVertical;

public class FeedRecyclerList extends RecyclerViewListVertical<FeedListAdapter> {
  public FeedRecyclerList(RecyclerView recyclerView, Context context) {
    super(recyclerView,context);
    adapter = new FeedListAdapter(this.context);
    recyclerView.setAdapter(adapter);
  }
}
