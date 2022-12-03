package com.booktree.ui.home.friendsList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.booktree.API.FBDatabase;
import com.booktree.common.MutableListLiveData;
import com.booktree.model.Friend;
import com.booktree.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class FollowerListViewModel extends ViewModel {

    private MutableListLiveData<Users> mFollowerList;
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
        mFollowerList=new MutableListLiveData<Users>();
        mFriendList=new MutableListLiveData<Friend>();
    }

    public void refreshFriendList(){
        FBDatabase.getInstance().getFriend((list)->{
            mFriendList.clear(false);
            mFriendList.addAll(list);
        });
    }

    public void showFollowerList(FBDatabase.FBCallbackWithArray<Users> callback){
        FBDatabase.getInstance().getFriend((list) ->{
            friendArrayList.clear();
            friendArrayList.addAll(list);
        } );
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        for(Friend friend:friendArrayList) {
           if(friend.UserId==user.getUid()){
               myfriendList.add(friend);
           }
        }
        for(Friend friend:myfriendList){
            var res = new ArrayList<Users>();
            database.collection("Users").whereEqualTo("Uid",friend.Followers).get()
                    .addOnCompleteListener((task)->{
                        if(task.isSuccessful()){
                            var userFBList = task.getResult().getDocuments();
                            userFBList.forEach((item)->{
                                res.add(item.toObject(Users.class));
                            });
                            callback.onGetSuccess(res);
                        }});
        }
    }
    public void refreshFollowList(){
        getInstance().showFollowerList((list)->{
            mFollowerList.clear(false);
            mFollowerList.addAll(list);
        });
    }

    public LiveData<ArrayList<Friend>> getFriendList() {return mFriendList;}
    public LiveData<ArrayList<Users>> getFollowerList() {return mFollowerList;}
}
