package com.booktree.ui.book.bookList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.model.Documents;
import com.booktree.BookDetailActivity;
import com.booktree.R;
import com.booktree.ui.book.bookList.Viewholder.BasicViewHolder;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public abstract class BookListAdapter<T extends BasicViewHolder> extends RecyclerView.Adapter<T> {

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

  @Override
  public void onBindViewHolder(@NonNull @NotNull T holder, int position) {
    holder.setContents(context, documents.get(position));
  }

  @Override
  public int getItemCount() {
    return documents.size();
  }
  
}
