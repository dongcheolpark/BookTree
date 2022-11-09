package com.booktree.API.DTO;

import com.google.gson.annotations.SerializedName;

public class BookMetaDTO {
  @SerializedName("total_count")
  public Integer total_count;	// 검색된 문서 수

  @SerializedName("pageable_count")
  public Integer pageable_count; // 중복된 문서를 제외하고, 처음부터 요청 페이지까지의 노출 가능 문서 수

  @SerializedName("is_end")
  public Boolean is_end; // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
}
