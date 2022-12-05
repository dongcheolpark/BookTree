package com.booktree.ui.book.bookDetail;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.common.RecyclerViewList.RecyclerViewListVertical;

public class BookReviewListVertical extends RecyclerViewListVertical<BookReviewAdapter> {
  public BookReviewListVertical(RecyclerView recyclerView,
      Context context) {
    super(recyclerView, context);
    adapter = new BookReviewAdapter(this.context);
    recyclerView.setAdapter(adapter);
  }
}
