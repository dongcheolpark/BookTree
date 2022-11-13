package com.booktree.ui.book.bookList;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.API.DTO.Doucuments;
import com.booktree.R;
import com.bumptech.glide.Glide;
import org.jetbrains.annotations.NotNull;

public class bookListAdapter extends RecyclerView.Adapter<bookListAdapter.ViewHolder> {

  private Doucuments[] documents;
  private final Context context;

  public bookListAdapter(Context context) {
    documents = new Doucuments[0];
    this.context = context;
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
    var item = documents[position];
    holder.getTextView().setText(item.title);
    holder.getContentText().setText(item.publisher);
    Glide.with(context).load(item.thumbnail).into(holder.getThumbNailContents());
    holder.bookInfoLayout.setOnClickListener((View view) -> {
      Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show();
    });
  }

  @Override
  public int getItemCount() {
    return documents.length;
  }
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleText;
    private final TextView contentText;
    private final ImageView thumbNailContents;
    private final ConstraintLayout bookInfoLayout;

    public ViewHolder(View view) {
      super(view);
      // Define click listener for the ViewHolder's View
      titleText = view.findViewById(R.id.bookInfoTitle);
      contentText = view.findViewById(R.id.bookInfoContents);
      thumbNailContents = view.findViewById(R.id.Thumbnail);
      bookInfoLayout = view.findViewById(R.id.bookInfoLayout);
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

    public ConstraintLayout getBookInfoLayout() {
      return bookInfoLayout;
    }
  }
}
