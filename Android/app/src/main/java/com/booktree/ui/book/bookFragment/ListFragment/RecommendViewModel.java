package com.booktree.ui.book.bookFragment.ListFragment;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.FBDatabase;
import com.booktree.API.GetDocumentAPI;
import com.booktree.common.MutableListLiveData;
import com.booktree.model.Documents;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RecommendViewModel extends ViewModel {
  private MutableLiveData<String> mQueryString;
  private MutableListLiveData<Documents> mDocList;
  private RecommendList recommendList;

  public RecommendViewModel() {
    mQueryString = new MutableLiveData<>();
    mDocList = new MutableListLiveData<>();
  }

  public void setRecommendList(RecommendList list) {
    this.recommendList = list;
  }
  public LiveData<ArrayList<Documents>> getDocList() {
    return mDocList;
  }
  public RecommendList getRecommendList() {
    return recommendList;
  }
  public void refreshList() {
    if(!mDocList.getValue().isEmpty()) return;
    FBDatabase.getInstance().getFeedCurrentUpload((feedList) -> {
      var isbnList = feedList.stream().map(item -> item.book).collect(Collectors.toList());
      mDocList.clear(false);
      GetDocumentAPI.getInstance().getDocumentsWithISBN(isbnList,(list) -> {
        mDocList.addPostAll(list);
      });
    });
  }
}
