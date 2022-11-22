package com.booktree.ui.book.BookFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.R;
import com.booktree.ui.book.bookList.BookListAdapter;
import com.booktree.ui.book.bookList.BookListAdapter.Type;
import com.booktree.ui.book.bookList.BookRecyclerList;
import com.booktree.ui.book.bookList.Viewholder.ToBookInfoViewHolder;
import org.jetbrains.annotations.NotNull;

public class BookToInfoFragment extends BookFragment {

  @Override
  protected BookRecyclerList getList(RecyclerView bookInfoList) {
    return new BookRecyclerList(bookInfoList,getActivity(), Type.toBook);
  }
}
