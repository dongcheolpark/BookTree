package com.booktree.ui.book.bookList.bookSearchList.Viewholder;

import android.content.Context;
import android.view.View;
import com.booktree.model.Documents;
import com.booktree.ui.feed.feedCreate.DocumentEventListener;

public class getBookInfoViewholder extends BasicViewHolder {

  public getBookInfoViewholder(View view) {
    super(view);
  }

  @Override
  public void setContents(Context context, Documents item) {
    super.setContents(context, item);
    bookInfoLayout.setOnClickListener((view) -> {
      if(context instanceof DocumentEventListener) {
        ((DocumentEventListener) context).Event(item);
      }
    });
  }
}
