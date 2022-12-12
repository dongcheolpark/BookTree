package com.booktree.ui.book.bookDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.APIClient;
import com.booktree.API.DTO.BookDTO;
import com.booktree.API.FBDatabase;
import com.booktree.API.GetDocumentAPI;
import com.booktree.common.MutableListLiveData;
import com.booktree.common.VoidCallback;
import com.booktree.model.Documents;
import com.booktree.model.Feed;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailViewModel extends ViewModel {
  private MutableLiveData<Documents> mDocument;
  private MutableListLiveData<Feed> mFeedList;

  public void refreshReviewList(VoidCallback callback) {
    FBDatabase.getInstance().getFeedWithIsbn(mDocument.getValue().getIsbn(),(list) -> {
      mFeedList.clear(false);
      if(!list.isEmpty())
        mFeedList.addAll(list);
      callback.func();
    });
  }

  public LiveData<ArrayList<Feed>> getReviewList() {
    return mFeedList;
  }

  public BookDetailViewModel() {
    mDocument = new MutableLiveData<>();
    mFeedList = new MutableListLiveData<>();
  }

  public void setDocument(Documents doc) {
    mDocument.setValue(doc);
  }

  public void setDocumentWithIsbn(String isbn) {
    GetDocumentAPI.getInstance().getDocumentWithISBN(isbn,(res) -> {
      mDocument.setValue(res);
    });
  }

  public LiveData<Documents> getDocument() {
    return mDocument;
  }
}
