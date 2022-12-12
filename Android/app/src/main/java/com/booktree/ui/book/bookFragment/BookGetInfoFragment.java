package com.booktree.ui.book.bookFragment;

import androidx.recyclerview.widget.RecyclerView;
import com.booktree.ui.book.bookList.bookSearchList.BookListAdapter.Type;
import com.booktree.ui.book.bookList.bookSearchList.BookRecyclerList;

public class BookGetInfoFragment extends BookFragment {

  @Override
  protected BookRecyclerList getList(RecyclerView bookInfoList) {
    return new BookRecyclerList(bookInfoList, getActivity(), Type.getBook);
  }
}
