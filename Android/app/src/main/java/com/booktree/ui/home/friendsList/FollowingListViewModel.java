package com.booktree.ui.home.friendsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.booktree.API.FBDatabase;
import com.booktree.API.FBDatabase.Follow;
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
    private FBDatabase.Follow follow = Follow.Follower;
    public FollowingListViewModel() {
        mFollowingList = new MutableListLiveData<>();
        mFriendList = new MutableListLiveData<>();
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
    public void refreshFollowerList(){
        FBDatabase.getInstance().getFollower(FBDatabase.getInstance().getUserInfo().uid,(list)->{
            mFollowingList.postValue(new ArrayList<>(list));
        });
    }
    public void refreshFollowingList(){
        FBDatabase.getInstance().getFollowing(FBDatabase.getInstance().getUserInfo().uid,(list)->{
            mFollowingList.postValue(new ArrayList<>(list));
        });
    }

    public LiveData<ArrayList<Friend>> getFriendList() {return mFriendList;}
    public LiveData<ArrayList<User>> getFollowerList() {return mFollowingList;}
}
