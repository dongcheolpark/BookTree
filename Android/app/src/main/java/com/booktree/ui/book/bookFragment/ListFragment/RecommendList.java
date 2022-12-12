package com.booktree.ui.book.bookFragment.ListFragment;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.booktree.common.RecyclerViewList.RecyclerViewListHorizontal;

public class RecommendList extends RecyclerViewListHorizontal<RecommendAdapter> {

  public RecommendList(RecyclerView recyclerView,
      Context context) {
    super(recyclerView, context);
    adapter = new RecommendAdapter(context);
    recyclerView.setAdapter(adapter);
  }
}
