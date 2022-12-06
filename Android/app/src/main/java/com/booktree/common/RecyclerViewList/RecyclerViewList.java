package com.booktree.common.RecyclerViewList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public abstract class RecyclerViewList<T extends Adapter> {
  protected T adapter;
  protected final RecyclerView recyclerView;
  protected final Context context;
  protected LinearLayoutManager layoutManager;

  public RecyclerViewList(RecyclerView recyclerView, Context context) {
    this.recyclerView = recyclerView;
    this.context = context;
  }

  public T getAdapter() {
    return adapter;
  }

}
