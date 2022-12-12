package com.booktree.ui.home.friendsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.booktree.API.FBDatabase;
import com.booktree.common.MutableListLiveData;
import com.booktree.model.Friend;
import com.booktree.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FollowingListViewModel extends ViewModel {
    private MutableListLiveData<User> mFollowingList;
    private MutableListLiveData<Friend>mFriendList;
    private FirebaseFirestore database;
    private FirebaseAuth mAuth;

    public static FollowingListViewModel instance = null;

    public static FollowingListViewModel getInstance(){
        if(instance==null) instance = new FollowingListViewModel();
        return instance;
    }

    public void refreshFollowList(){
        FirebaseUser user = mAuth.getCurrentUser();
        FBDatabase.getInstance().getFollowing(user.getUid(),(list)->{
            mFollowingList.setValue(new ArrayList<>(list));
        });
    }

    public LiveData<ArrayList<Friend>> getFriendList() {return mFriendList;}
    public LiveData<ArrayList<User>> getFollowerList() {return mFollowingList;}
}
