package com.booktree.ui.home.friendsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booktree.R;
import com.booktree.model.Friend;
import com.bumptech.glide.Glide;
import com.booktree.model.Users;

import java.util.ArrayList;


public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.ViewHolder> {

    private ArrayList<Users> friendinfo;
    private final Context context;

    public FollowerListAdapter(Context context) {
        friendinfo = new ArrayList<>();
        this.context = context;
    }

    public void setFriendsList(ArrayList<Users> friendArrayList){
        friendinfo=friendArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setContents(context,friendinfo.get(position));

    }

    @Override
    public int getItemCount() {
        return friendinfo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_followerid;
        private final ImageView iv_followerprofile;
        private final TextView tv_followername;

        public ViewHolder(@NonNull View view) {
            super(view);
            iv_followerprofile=view.findViewById(R.id.iv_followerprofile);
            tv_followerid=view.findViewById(R.id.tv_followerid);
            tv_followername=view.findViewById(R.id.tv_userid);
        }

        public void setContents(Context context, Users user){
            tv_followername.setText(user.userName);
            tv_followerid.setText(user.id);
            Glide.with(context).load(user.profile).into(iv_followerprofile);
        }
    }
}
