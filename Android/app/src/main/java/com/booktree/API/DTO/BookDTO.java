package com.booktree.API.DTO;

import com.booktree.model.Documents;
import com.google.gson.annotations.SerializedName;

public class BookDTO {
  @SerializedName("meta")
  public BookMetaDTO meta;
  @SerializedName("documents")
  public Documents[] documents;
}
