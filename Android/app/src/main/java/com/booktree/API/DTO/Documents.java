package com.booktree.API.DTO;

import static java.util.stream.Collectors.joining;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class Documents implements Serializable {

  @SerializedName("title")
  public String title; //도서 제목

  @SerializedName("contents")
  public String contents; //도서 소개

  @SerializedName("url")
  public String url; //도서 상세 URL

  @SerializedName("isbn")
  public String isbn; //ISBN10(10자리) 또는 ISBN13(13자리) 형식의 국제 표준 도서번호(International Standard Book Number)
  // ISBN10 또는 ISBN13 중 하나 이상 포함
  // 두 값이 모두 제공될 경우 공백(' ')으로 구분

  @SerializedName("datetime")
  public Date datetime; // 도서 출판날짜, ISO 8601 형식 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]

  @SerializedName("authors")
  public String[] authors; // 도서 저자 리스트

  @SerializedName("publisher")
  public String publisher; // 도서 출판사

  @SerializedName("translators")
  public String[] translators; // 도서 번역자 리스트

  @SerializedName("price")
  public Integer price;  // 도서 정가

  @SerializedName("sale_price")
  public Integer sale_price; // 도서 판매가

  @SerializedName("thumbnail")
  public String thumbnail;  // 도서 표지 미리보기 URL

  @SerializedName("status")
  public String status;  // 도서 판매 상태 정보 (정상, 품절, 절판 등)
  //상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출 요소로 활용 권장
  public String authorString() {
    return Arrays.stream(authors).collect(joining(", "));
  }
}
