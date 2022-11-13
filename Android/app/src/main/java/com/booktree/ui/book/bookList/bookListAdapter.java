package com.booktree.ui.book.bookList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.API.DTO.Documents;
import com.booktree.BookDetailActivity;
import com.booktree.R;
import com.bumptech.glide.Glide;
import org.jetbrains.annotations.NotNull;

public class bookListAdapter extends RecyclerView.Adapter<bookListAdapter.ViewHolder> {

  private Documents[] documents;
  private final Context context;

  public bookListAdapter(Context context) {
    documents = new Documents[0];
    this.context = context;
  }

  public void setDocuments(Documents[] doc) {
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
    holder.setContents(context,documents[position]);
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

    public void setContents(Context context, Documents item) {
      titleText.setText(item.title);
      contentText.setText(item.publisher);
      Glide.with(context).load(item.thumbnail).into(thumbNailContents);
      bookInfoLayout.setOnClickListener((View view) -> {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("document", item);
        context.startActivity(intent);
      });
    }
  }
}
