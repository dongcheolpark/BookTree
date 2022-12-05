package com.booktree.common.RecyclerViewList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public abstract class RecyclerViewListHorizontal<T extends Adapter> extends
    RecyclerViewList<T> {
  protected T adapter;
  protected LinearLayoutManager layoutManager;

  public RecyclerViewListHorizontal(RecyclerView recyclerView, Context context) {
    super(recyclerView,context);
    layoutManager = new LinearLayoutManager(context);
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
  }

}
