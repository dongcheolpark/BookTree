package com.booktree.model;

import java.io.Serializable;
import java.util.Objects;

public class Friend implements Serializable {
    public String Follower;
    public String Following;

    public Friend() {}
    public Friend(String Follower,String Following) {
        if(Follower.equals(Following)) throw new IllegalArgumentException("Follower and Following is same uid");
        this.Follower = Follower;
        this.Following = Following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Friend friend = (Friend) o;
        return Objects.equals(Follower, friend.Follower) && Objects.equals(Following,
            friend.Following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Follower, Following);
    }
}

