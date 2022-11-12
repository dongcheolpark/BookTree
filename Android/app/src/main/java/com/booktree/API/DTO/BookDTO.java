package com.booktree.API.DTO;

import com.google.gson.annotations.SerializedName;

public class BookDTO {
  @SerializedName("meta")
  public BookMetaDTO meta;
  @SerializedName("documents")
  public Doucuments[] documents;
}
