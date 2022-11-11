package com.booktree.ui.book;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookViewModel extends ViewModel {

  private MutableLiveData<String> mQueryString;

  public BookViewModel() {
    mQueryString = new MutableLiveData<>();
    mQueryString.setValue("This is book fragment");
  }

  public LiveData<String> getQueryString() {
    return mQueryString;
  }
  public void getQueryString(String value) {
    mQueryString.setValue(value);
  }
}
