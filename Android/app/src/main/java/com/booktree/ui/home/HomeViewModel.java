package com.booktree.ui.home;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.APIClient;
import java.io.IOException;

public class HomeViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public HomeViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }

  public void ChangeValue() {
    try {
      var data = APIClient.getInstance().getKakaoAPI().getBookInfo(
          "어린왕자",
          "accuracy",
          1,
          10
      ).execute();
      mText.setValue(data.body().documents[0].title);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}