package com.booktree.ui.book.bookList.bookSearchList.Viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.R;
import com.booktree.model.Documents;
import com.bumptech.glide.Glide;

public class BasicViewHolder extends RecyclerView.ViewHolder {
  private final TextView titleText;
  private final TextView contentText;
  private final ImageView thumbNailContents;
  protected final ConstraintLayout bookInfoLayout;

  public BasicViewHolder(View view) {
    super(view);
    // Define click listener for the ViewHolder's View
    titleText = view.findViewById(R.id.bookInfoTitle);
    contentText = view.findViewById(R.id.bookInfoContents);
    thumbNailContents = view.findViewById(R.id.Thumbnail);
    bookInfoLayout = view.findViewById(R.id.bookInfoLayout);
  }

  public void setContents(Context context, Documents item) {
    titleText.setText(item.title);
    contentText.setText(item.publisher);
    Glide.with(context).load(item.thumbnail).into(thumbNailContents);
  }
}
