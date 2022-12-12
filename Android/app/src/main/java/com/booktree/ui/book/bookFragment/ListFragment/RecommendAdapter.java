package com.booktree.ui.book.bookFragment.ListFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.booktree.R;
import com.booktree.model.Documents;
import com.booktree.model.Feed;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class RecommendAdapter extends Adapter<RecommendAdapter.ViewHolder> {

  private ArrayList<Documents> doc;
  private Context context;

  public RecommendAdapter(Context context) {
    doc = new ArrayList<>();
    this.context = context;
  }

  public void setList(ArrayList<Documents> doc) {
    this.doc = doc;
    notifyDataSetChanged();
  }

  @NonNull
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_book, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
    holder.setContents(doc.get(position));
  }

  @Override
  public int getItemCount() {
    return doc.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public ViewHolder(
        @NonNull @NotNull View view) {
      super(view);
      imageView = view.findViewById(R.id.Thumbnail);
    }

    public void setContents(Documents doc) {
      Glide.with(context).load(doc.thumbnail).into(imageView);
    }
  }

}
