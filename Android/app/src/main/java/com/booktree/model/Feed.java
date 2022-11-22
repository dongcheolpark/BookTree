package com.booktree.model;

import java.io.Serializable;

public class Feed implements Serializable {
  public String book;
  public String author;
  public String contents;
  public String imageUrl;

  public Feed(String book,String author,String contents, String ImageUrl) {
    this.book = book;
    this.author = author;
    this.contents = contents;
    this.imageUrl = ImageUrl;
  }
}
