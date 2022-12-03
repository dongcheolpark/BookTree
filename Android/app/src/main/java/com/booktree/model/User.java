package com.booktree.model;

import java.util.Objects;

public class User {
  public String name;
  public String uid;
  public String profileImg;

  public User() {}

  public User(String name,String uid, String profileImg) {
    this.name = name;
    this.uid = uid;
    this.profileImg = profileImg;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(name, user.name) && Objects.equals(uid, user.uid)
        && Objects.equals(profileImg, user.profileImg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, uid, profileImg);
  }
}
