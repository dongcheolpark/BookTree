package com.booktree;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.booktree.API.APIClient;
import com.booktree.API.DTO.BookDTO;
import com.booktree.model.Documents;
import java.io.IOException;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailViewModel extends ViewModel {
  private MutableLiveData<Documents> mDocument;

  public BookDetailViewModel() {
    mDocument = new MutableLiveData<>();
  }

  public void setDocument(Documents doc) {
    mDocument.setValue(doc);
  }

  public void setDocumentWithIsbn(String isbn) {
    APIClient.getInstance().getKakaoAPI().getBookInfo(isbn,
        "accuracy",
        1, 10).enqueue(
        new Callback<BookDTO>() {
          @Override
          public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
            mDocument.setValue(response.body().documents[0]);
          }
          @Override
          public void onFailure(Call<BookDTO> call, Throwable t) {
          }
        }
    );
  }

  public LiveData<Documents> getDocument() {
    return mDocument;
  }
}
