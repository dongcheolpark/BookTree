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
import com.booktree.model.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.ViewHolder> {

    private ArrayList<User> friendinfo;
    private final Context context;

    public FollowerListAdapter(Context context) {
        friendinfo = new ArrayList<>();
        this.context = context;
    }

    public void setFriendsList(ArrayList<User> friendArrayList){
        friendinfo=friendArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_list,parent,false);
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

        private final ImageView iv_followerprofile;
        private final TextView tv_followername;

        public ViewHolder(@NonNull View view) {
            super(view);
            iv_followerprofile=view.findViewById(R.id.iv_followerprofile);
            tv_followername=view.findViewById(R.id.tv_userid);
        }

        public void setContents(Context context, User user){
            tv_followername.setText(user.name);
            Glide.with(context).load(user.profileImg).into(iv_followerprofile);
        }
    }
}
