package com.booktree.ui.book.bookFragment.ListFragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.ui.book.bookList.bookSearchList.BookRecyclerList;

public class RecommendViewModel extends ViewModel {
  private MutableLiveData<String> mQueryString;
  private BookRecyclerList bookInfoList;

  public RecommendViewModel() {
    mQueryString = new MutableLiveData<>();
  }

  public void setBookInfoList(BookRecyclerList bookInfoList) {
    this.bookInfoList = bookInfoList;
  }
}
