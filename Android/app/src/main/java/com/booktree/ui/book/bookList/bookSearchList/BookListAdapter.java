package com.booktree.ui.book.bookList.bookSearchList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.model.Documents;
import com.booktree.R;
import com.booktree.ui.book.bookList.bookSearchList.Viewholder.BasicViewHolder;
import com.booktree.ui.book.bookList.bookSearchList.Viewholder.ToBookInfoViewHolder;
import com.booktree.ui.book.bookList.bookSearchList.Viewholder.getBookInfoViewholder;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class BookListAdapter extends RecyclerView.Adapter<BasicViewHolder> {

  public enum Type {
    basic(0),
    getBook(1),
    toBook(2);
    private final int value;
    Type(int i) {
      this.value = i;
    }

    public int getValue() {
      return value;
    }
  };
  private ArrayList<Documents> documents;
  private Type type;
  private final Context context;

  public BookListAdapter(Context context,Type type) {
    documents = new ArrayList<>();
    this.context = context;
    this.type = type;
  }

  private View createView(ViewGroup parent) {
    return LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_book_info, parent, false);
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
  public int getItemViewType(int position) {
    return type.getValue();
  }

  @NonNull
  @NotNull
  @Override
  public BasicViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    var type = Type.values()[viewType];
    var view = createView(parent);
    switch (type) {
      case basic:
        return new BasicViewHolder(view);
      case getBook:
        return new getBookInfoViewholder(view);
      case toBook:
        return new ToBookInfoViewHolder(view);
    }
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull BasicViewHolder holder, int position) {
    holder.setContents(context, documents.get(position));
  }

  @Override
  public int getItemCount() {
    return documents.size();
  }
  
}
