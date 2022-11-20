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
import com.booktree.model.Documents;
import com.booktree.BookDetailActivity;
import com.booktree.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

  private ArrayList<Documents> documents;
  private final Context context;

  public BookListAdapter(Context context) {
    documents = new ArrayList<>();
    this.context = context;
  }

  public void addDocuments(Documents[] doc) {
    documents.addAll(Arrays.asList(doc)) ;
  }

  public void clearDocuments() {
    int size = documents.size();
    documents.clear();
    notifyItemRangeRemoved(0,size);
  }

  @NonNull
  @NotNull
  @Override
  public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.book_info_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull BookListAdapter.ViewHolder holder, int position) {
    holder.setContents(context, documents.get(position));
  }

  @Override
  public int getItemCount() {
    return documents.size();
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
