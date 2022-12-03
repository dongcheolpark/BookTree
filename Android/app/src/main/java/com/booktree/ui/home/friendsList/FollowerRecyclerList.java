package com.booktree.ui.home.friendsList;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.booktree.model.User;
import java.util.ArrayList;

public class FollowerRecyclerList {
    private final RecyclerView friendList;
    private final Context context;
    private final FollowerListAdapter adapter;
    private LinearLayoutManager layoutManager;

    public FollowerRecyclerList(RecyclerView list, Context context) {
        friendList = list;
        this.context = context;
        adapter = new FollowerListAdapter(this.context);
        layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        friendList.setAdapter(adapter);
        friendList.setLayoutManager(layoutManager);
    }

    public void setFriendList(ArrayList<User> list){adapter.setFriendsList(list);}
}
