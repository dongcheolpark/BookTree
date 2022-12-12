package com.booktree.ui.book;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.ui.book.bookList.bookSearchList.BookRecyclerList;
import java.util.Objects;

public class BookViewModel extends ViewModel {

  private MutableLiveData<String> mQueryString;
  private BookRecyclerList bookInfoList;

  public BookViewModel() {
    mQueryString = new MutableLiveData<>();
  }

  public void setBookInfoList(BookRecyclerList bookInfoList) {
    this.bookInfoList = bookInfoList;
  }
  public BookRecyclerList getBookInfoList() {
    return bookInfoList;
  }

  public LiveData<String> getQueryString() {
    return mQueryString;
  }
  public void setQueryString(String value) {
    if(mQueryString.getValue() != null && mQueryString.getValue().equals(value)) return;
    mQueryString.setValue(value);
  }
}