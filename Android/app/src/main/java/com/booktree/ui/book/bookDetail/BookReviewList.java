package com.booktree.ui.book.bookDetail;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.common.RecyclerViewList.RecyclerViewList;

public class BookReviewList extends RecyclerViewList<BookReviewAdapter> {
  public BookReviewList(RecyclerView recyclerView,
      Context context) {
    super(recyclerView, context);
    adapter = new BookReviewAdapter(this.context);
    recyclerView.setAdapter(adapter);
  }
}
