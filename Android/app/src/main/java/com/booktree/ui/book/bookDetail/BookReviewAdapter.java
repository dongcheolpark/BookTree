package com.booktree.ui.book.bookDetail;

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
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class BookReviewAdapter extends RecyclerView.Adapter<BookReviewAdapter.ViewHolder> {

  private ArrayList<Feed> feedList = new ArrayList<>();
  private final Context context;

  public BookReviewAdapter(Context context) {
    this.context = context;
  }

  public void setList(ArrayList<Feed> feedArrayList) {
    feedList = feedArrayList;
    notifyDataSetChanged();
  }

  @NonNull
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    var view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_book_detail_review, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
    holder.setContents(feedList.get(position));
  }

  @Override
  public int getItemCount() {
    return feedList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView author;
    private TextView contents;
    private ImageView avatar;

    public ViewHolder(
        @NonNull @NotNull View view) {
      super(view);
      author = view.findViewById(R.id.bookDetailReviewAuthor);
      contents = view.findViewById(R.id.bookDetailReviewContents);
      avatar = view.findViewById(R.id.bookDetailReviewAvatar);
    }

    public void setContents(Feed feed) {
      author.setText(feed.author);
      contents.setText(feed.contents);
      //Glide.with(context).load(이미지 URL).into(avatar);
    }
  }
}
