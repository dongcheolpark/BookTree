package com.booktree.ui.feed.feedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.R;
import com.booktree.model.Feed;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {

  private ArrayList<Feed> feeds;
  private final Context context;

  public FeedListAdapter(Context context) {
    feeds = new ArrayList<>();
    this.context = context;
  }

  public void setList(ArrayList<Feed> feedArrayList) {
    feeds = feedArrayList;
    notifyDataSetChanged();
  }

  @NonNull
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.feed_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
    holder.setContents(context,feeds.get(position));
  }

  @Override
  public int getItemCount() {
    return feeds.size();
  }
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView author;
    private TextView content;
    private ImageView image;

    public ViewHolder(@NonNull @NotNull View view) {
      super(view);
      author = view.findViewById(R.id.feedAuthor);
      content = view.findViewById(R.id.feedContent);
      image = view.findViewById(R.id.feedImage);
    }

    public void setContents(Context context,Feed feed) {
      author.setText(feed.author);
      content.setText(feed.contents);
      Glide.with(context).load(feed.imageUrl).into(image);
    }
  }
}
