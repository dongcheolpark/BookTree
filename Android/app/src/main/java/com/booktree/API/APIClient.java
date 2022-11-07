package com.booktree.API;

import com.booktree.BuildConfig;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
  private static APIClient instance = null;
  private static KakaoAPI kakaoAPI = null;
  private static final String url = "https://dapi.kakao.com";

  private APIClient() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    kakaoAPI = retrofit.create(KakaoAPI.class);
  }

  public static APIClient getInstance() {
    if(instance == null) instance = new APIClient();
    return instance;
  }

  public KakaoAPI getKakaoAPI() {
    return kakaoAPI;
  }
}
