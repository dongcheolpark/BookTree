package com.booktree.ui.home.friendsList;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booktree.API.FBDatabase;
import com.booktree.API.FBDatabase.Follow;
import com.booktree.R;

import com.booktree.databinding.ActivityFollowingBinding;
import com.booktree.databinding.FollowerListBinding;
import com.booktree.model.Friend;
import com.booktree.ui.home.friendsList.FollowerListViewModel;
import com.booktree.ui.home.friendsList.FollowerRecyclerList;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class FollowingActivity extends AppCompatActivity {

    private FollowingListViewModel followingListviewModel;
    private ActivityFollowingBinding binding;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        followingListviewModel = new ViewModelProvider(this).get(FollowingListViewModel.class);
        binding= ActivityFollowingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final var followingListView=binding.followingList;
        var followingList = new FollowerRecyclerList(followingListView,this);
        followingListviewModel.getFollowerList().observe(this,followingList::setFriendList);
        if(getIntent().getSerializableExtra("follow") == Follow.Follower) {
            followingListviewModel.refreshFollowerList();
        }
        if(getIntent().getSerializableExtra("follow") == Follow.Following) {
            followingListviewModel.refreshFollowingList();
        }

    }


}
