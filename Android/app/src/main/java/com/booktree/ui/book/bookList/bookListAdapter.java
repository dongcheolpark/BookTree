package com.booktree.ui.book.bookList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.API.DTO.Doucuments;
import com.booktree.R;
import org.jetbrains.annotations.NotNull;

public class bookListAdapter extends RecyclerView.Adapter<bookListAdapter.ViewHolder> {

  private Doucuments[] documents;

  public bookListAdapter() {
    documents = new Doucuments[0];
  }

  public void setDocuments(Doucuments[] doc) {
    documents = doc;
  }

  @NonNull
  @NotNull
  @Override
  public bookListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.book_info_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull bookListAdapter.ViewHolder holder, int position) {
    holder.getTextView().setText(documents[position].title);
  }

  @Override
  public int getItemCount() {
    return documents.length;
  }
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleText;
    private final TextView contentText;
    private final ImageView thumbNailContents;

    public ViewHolder(View view) {
      super(view);
      // Define click listener for the ViewHolder's View
      titleText = view.findViewById(R.id.bookInfoTitle);
      contentText = view.findViewById(R.id.bookInfoContents);
      thumbNailContents = view.findViewById(R.id.Thumbnail);
    }

    public TextView getTextView() {
      return titleText;
    }

    public TextView getContentText() {
      return contentText;
    }

    public ImageView getThumbNailContents() {
      return thumbNailContents;
    }
  }
}
