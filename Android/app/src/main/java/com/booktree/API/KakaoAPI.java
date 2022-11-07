package com.booktree.API;

import android.os.Build;
import com.booktree.API.DTO.BookDTO;
import com.booktree.BuildConfig;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoAPI {
  @Headers({"Authorization: KakaoAK " + BuildConfig.KAKAO_API_KEY})
  @GET("/v3/search/book")
  Call<BookDTO> getBookInfo(
      @Query("query") String query,
      @Query("sort") String sort,
      @Query("page") Integer page,
      @Query("size") Integer size
      );
}

