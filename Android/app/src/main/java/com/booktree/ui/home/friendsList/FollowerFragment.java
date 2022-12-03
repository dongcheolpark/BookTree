package com.booktree.ui.home.friendsList;

/*import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.booktree.API.FBDatabase;
import com.booktree.R;
import com.booktree.model.Friend;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class FollowerActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private RecyclerView followerList;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Friend> arrayList;
    private FollowerListAdapter adapter;

   // private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        followerList=findViewById(R.id.followerList);
        followerList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        followerList.setLayoutManager(layoutManager);

        arrayList=new ArrayList<>();


        db = FirebaseFirestore.getInstance();
        DatabaseReference databaseReference = db.collection("Friends")
       // CollectionReference databaseReference = db.collection("Friends");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arrayList.clear();
                    for(DataSnapshot snapshot : )
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        followerList.setAdapter(adapter);
    }




}*/
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
import com.booktree.R;
import com.booktree.databinding.ActivityFollowerBinding;
import com.booktree.databinding.FollowerListBinding;
import com.booktree.model.Friend;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class FollowerFragment extends Fragment {

    private FollowerListViewModel followerListviewModel;
    private ActivityFollowerBinding binding;
    private ArrayList<Friend> friendArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        followerListviewModel=new ViewModelProvider(this).get(FollowerListViewModel.class);
        binding= ActivityFollowerBinding.inflate(inflater, container, false);

        final var followerListView=binding.followerList;
        var followerList = new FollowerRecyclerList(followerListView,getActivity());
        followerListviewModel.getFollowerList().observe(getViewLifecycleOwner(),followerList::setFriendList);
        followerListviewModel.refreshFollowList();

        View root = binding.getRoot();
        return root;

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding=null;
    }
}
