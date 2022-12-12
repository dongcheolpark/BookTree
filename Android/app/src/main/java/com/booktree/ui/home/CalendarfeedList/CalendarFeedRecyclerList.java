package com.booktree.ui.home.CalendarfeedList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.booktree.common.RecyclerViewList.RecyclerViewList;
import com.booktree.common.RecyclerViewList.RecyclerViewListVertical;
import com.booktree.model.Feed;
import com.booktree.ui.feed.feedList.FeedListAdapter;
import com.booktree.ui.home.CalendarfeedList.CalendarFeedListAdapter;

import java.util.ArrayList;

public class CalendarFeedRecyclerList extends RecyclerViewListVertical<CalendarFeedListAdapter> {
    public CalendarFeedRecyclerList(RecyclerView recyclerView, Context context){
        super(recyclerView,context);
        adapter = new CalendarFeedListAdapter(this.context);
        recyclerView.setAdapter(adapter);
    }
}