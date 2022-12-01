package com.booktree.model;

import java.io.Serializable;
import java.util.Date;

public class Feed implements Serializable {
  public String book;
  public String author;
  public String contents;
  public String imageUrl;
  public Date uploadDate;

  public Feed() {}

  public Feed(String book,String author,String contents, String ImageUrl,Date uploadDate) {
    this.book = book;
    this.author = author;
    this.contents = contents;
    this.imageUrl = ImageUrl;
    this.uploadDate = uploadDate;
  }
}
