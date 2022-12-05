package com.booktree.ui.book.bookList.bookSearchList.Viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.booktree.ui.book.bookDetail.BookDetailActivity;
import com.booktree.model.Documents;

public class ToBookInfoViewHolder extends BasicViewHolder {

  public ToBookInfoViewHolder(View view) {
    super(view);
  }

  @Override
  public void setContents(Context context, Documents item) {
    super.setContents(context, item);
    bookInfoLayout.setOnClickListener((View view) -> {
      Intent intent = new Intent(context, BookDetailActivity.class);
      intent.putExtra("document",item);
      context.startActivity(intent);
    });
  }
}
