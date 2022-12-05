package com.booktree.API;

import com.booktree.API.DTO.BookDTO;
import com.booktree.common.ResultCallBack;
import com.booktree.model.Documents;
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
}
