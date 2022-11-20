package com.booktree.model;

import java.io.Serializable;

public class Feed implements Serializable {
  public String author;
  public String contents;
  public String imageUrl;

  public Feed(String author,String contents, String ImageUrl) {
    this.author = author;
    this.contents = contents;
    this.imageUrl = ImageUrl;
  }
}
