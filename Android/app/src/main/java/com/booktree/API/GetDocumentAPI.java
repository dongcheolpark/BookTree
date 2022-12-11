package com.booktree.API;

import android.util.Log;
import com.booktree.API.DTO.BookDTO;
import com.booktree.API.FBDatabase.FBCallbackWithArray;
import com.booktree.common.ResultCallBack;
import com.booktree.model.Documents;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDocumentAPI {

  private static GetDocumentAPI instance = null;
  public static GetDocumentAPI getInstance() {
    if (instance == null) instance = new GetDocumentAPI();
    return instance;
  }
  private KakaoAPI kakaoAPI;
  private GetDocumentAPI() {
    kakaoAPI = APIClient.getInstance().getKakaoAPI();
  }

  public void getDocumentWithISBN(String isbn, ResultCallBack<Documents> callBack) {
    APIClient.getInstance().getKakaoAPI().getBookInfo(isbn,
        "accuracy",
        1, 10).enqueue(
        new Callback<BookDTO>() {
          @Override
          public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
            callBack.func(response.body().documents[0]);
          }
          @Override
          public void onFailure(Call<BookDTO> call, Throwable t) {
          }
        }
    );
  }
  public void getDocumentsWithISBN(List<String> isbnList, FBCallbackWithArray<Documents> callback) {
    var size = isbnList.size();
    var countDownLatch = new CountDownLatch(size);
    var res = new ArrayList<Documents>();
    var executorService = Executors.newFixedThreadPool(1);
    executorService.execute(() -> {
        isbnList.forEach((isbn) -> {
            getDocumentWithISBN(isbn,(book) -> {
              res.add(book);
              countDownLatch.countDown();
            });
        });
        try {
          countDownLatch.await(5, TimeUnit.SECONDS);
          callback.onGetSuccess(res);
        } catch (InterruptedException e) {
          Log.e("ERROR : ", e.getMessage());
        }
      });
  }
}
