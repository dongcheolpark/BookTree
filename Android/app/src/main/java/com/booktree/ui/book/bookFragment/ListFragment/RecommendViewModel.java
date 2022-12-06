package com.booktree.ui.book.bookFragment.ListFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.GetDocumentAPI;
import com.booktree.common.MutableListLiveData;
import com.booktree.model.Documents;
import java.util.ArrayList;

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
    GetDocumentAPI.getInstance().getDocumentWithISBN("9791159171772",(doc) -> {
      mDocList.add(doc);
      mDocList.add(doc);
      mDocList.add(doc);
      mDocList.notifyChange();
    });
  }
}
