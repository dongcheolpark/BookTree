package com.booktree.ui.home.CalendarfeedList;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.model.Feed;
import com.booktree.ui.home.CalendarfeedList.CalendarFeedListAdapter;

import java.util.ArrayList;

public class CalendarFeedRecyclerList {
    private final RecyclerView calendarFeedList;
    private final Context context;
    private final CalendarFeedListAdapter adapter;
    private LinearLayoutManager layoutManager;



    public CalendarFeedRecyclerList(RecyclerView list, Context context) {
        calendarFeedList = list;
        this.context = context;
        adapter = new CalendarFeedListAdapter(this.context);
        layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        calendarFeedList.setAdapter(adapter);
        calendarFeedList.setLayoutManager(layoutManager);
        // 리스트 기본 설정
    }

    public void setCalendarFeedList(ArrayList<Feed> list) {
        adapter.setList(list);
    }

    public void setCalendarFeedList(Feed feed) {
    }
}