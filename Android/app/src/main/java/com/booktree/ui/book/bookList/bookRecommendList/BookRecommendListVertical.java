package com.booktree.ui.book.bookList.bookRecommendList;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.common.RecyclerViewList.RecyclerViewListVertical;

public abstract class BookRecommendListVertical extends RecyclerViewListVertical<BookRecommendAdapter> {

  public BookRecommendListVertical(RecyclerView recyclerView,
      Context context) {
    super(recyclerView, context);
  }
}
