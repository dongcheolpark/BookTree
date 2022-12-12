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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

public class FollowerListViewModel extends ViewModel {

    private MutableListLiveData<User> mFollowerList;
    private MutableListLiveData<Friend>mFriendList;
    private FirebaseFirestore database;
    private FirebaseAuth mAuth;
    private ArrayList<Friend> myfriendList;
    private ArrayList<Friend> friendArrayList;
    public static FollowerListViewModel instance = null;

    public static FollowerListViewModel getInstance(){
        if(instance==null) instance = new FollowerListViewModel();
        return instance;
    }

    public FollowerListViewModel(){
        mFollowerList=new MutableListLiveData<User>();
        mFriendList=new MutableListLiveData<Friend>();
    }

    public void refreshFollowList(){
        FirebaseUser user = mAuth.getCurrentUser();
        FBDatabase.getInstance().getFollower(user.getUid(),(list)->{
            mFollowerList.setValue(new ArrayList<>(list));
        });
    }

    public LiveData<ArrayList<Friend>> getFriendList() {return mFriendList;}
    public LiveData<ArrayList<User>> getFollowerList() {return mFollowerList;}
}
