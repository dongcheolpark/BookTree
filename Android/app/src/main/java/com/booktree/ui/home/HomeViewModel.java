package com.booktree.ui.home;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.APIClient;

public class HomeViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public HomeViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is home fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }

  public void ChangeValue(Context context) {
  }
}