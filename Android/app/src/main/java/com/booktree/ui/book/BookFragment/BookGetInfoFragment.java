package com.booktree.ui.book.BookFragment;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.ui.book.bookList.BookListAdapter;
import com.booktree.ui.book.bookList.BookRecyclerList;
import com.booktree.ui.book.bookList.Viewholder.getBookInfoViewholder;
import org.jetbrains.annotations.NotNull;

public class BookGetInfoFragment extends BookFragment {

  @Override
  protected BookRecyclerList getList(RecyclerView bookInfoList) {
    return new BookRecyclerList(bookInfoList, getActivity(), new BookListAdapter<getBookInfoViewholder>(getActivity()) {
      @NonNull
      @NotNull
      @Override
      public getBookInfoViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
          int viewType) {
        return new getBookInfoViewholder(createView(parent));
      }
    });
  }
}
