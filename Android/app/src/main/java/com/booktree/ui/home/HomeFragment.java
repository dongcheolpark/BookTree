package com.booktree.ui.home;

import static android.widget.Toast.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.booktree.API.FBDatabase;
import com.booktree.ui.MainActivity;
import com.booktree.R;

import com.booktree.ui.feed.feedList.FeedRecyclerList;
import com.booktree.ui.home.friendsList.FollowerActivity;
import com.booktree.ui.home.friendsList.FollowingActivity;


import com.booktree.common.MutableListLiveData;
import com.booktree.databinding.FragmentHomeBinding;
import com.bumptech.glide.Glide;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.booktree.model.User;
import com.booktree.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.github.sundeepk.compactcalendarview.domain.Event;

import javax.crypto.spec.GCMParameterSpec;

public class HomeFragment extends Fragment {

//  private static final String TAG = "HomeFragment";
  private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
  private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;
  private User user;
  private FirebaseAuth mAuth;
  private Date today;
  private Event ev;
  private long count;

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);

    binding.shimmerFeedList.startShimmer();
    binding.shimmerFeedList.setVisibility(View.VISIBLE);
    binding.calendarFeedList.setVisibility(View.INVISIBLE);

    final var compactCalendarView = binding.compactcalendarView;
    final var textView_month = binding.textViewMonth;
    final var textView_result = binding.textViewResult;
    final var profile_image=binding.profileiv;
    final var textView_username=binding.usernametv;
    final var follower_Btn=binding.followerbtn;
    final var following_Btn=binding.followingbtn;

    var user = FBDatabase.getInstance().getUserInfo();
    Glide.with(this).load(user.profileImg).into(profile_image);
    textView_username.setText(user.name);

    //팔로워 리스트 불러오기
    follower_Btn.setOnClickListener(view -> {
      Intent intent=new Intent(getActivity(), FollowerActivity.class);
      startActivity(intent);
    });

    //팔로잉 리스트 불러오기
    following_Btn.setOnClickListener(view->{
      Intent intent=new Intent(getActivity(), FollowingActivity.class);
      startActivity(intent);
    });
//    final var barChart = binding.barChart;
    today = new Date();

    textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth())); //위에 MMM yyyy 표시
    compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);  //일요일을 시작으로

    // 날짜 클릭 이벤트 관련 코드
    compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
      @Override
      public void onDayClick(Date dateClicked) {
        String date = dateFormatForDay.format(dateClicked); //날짜 나오기
        homeViewModel.refreshCalendarFeedList(dateClicked,this::stopShimmer);
        textView_result.setText(dateFormatForDay.format(dateClicked));
      }

      private void stopShimmer() {
        binding.shimmerFeedList.stopShimmer();
        binding.calendarFeedList.setVisibility(View.VISIBLE);
        binding.shimmerFeedList.setVisibility(View.INVISIBLE);
      }

      @Override
      public void onMonthScroll(Date firstDayOfNewMonth) {
        textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
      }

    });

    final var feedListView = binding.calendarFeedList;
    var feedList = new FeedRecyclerList(feedListView,getActivity());
    homeViewModel.getCalendarFeedList().observe(getViewLifecycleOwner(), (list) -> {
      feedList.getAdapter().setList(list);
    });

    homeViewModel.refreshCalendarFeedList(today,this::stopShimmer);
    homeViewModel.refreshCalendarEvents(this::showEvents);

    View root = binding.getRoot();

    return root;
  }

  protected void stopShimmer() {
    binding.shimmerFeedList.stopShimmer();
    binding.calendarFeedList.setVisibility(View.VISIBLE);
    binding.shimmerFeedList.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onResume() {
    super.onResume();
    homeViewModel.refreshCalendarFeedList(today,this::stopShimmer);
    homeViewModel.refreshCalendarEvents(this::showEvents);
  }

  public void showEvents() {
    final var compactCalendarView = binding.compactcalendarView;
    count = homeViewModel.getCalendarEvents().getValue().stream().count(); //왜 얘가 자꾸 커지지
    for (int i = 0; i < count; i++) {
      if(compactCalendarView.getEvents(homeViewModel.getCalendarEvents().getValue().get(i).getTime()).isEmpty()) {
        ev = new Event(Color.BLACK, homeViewModel.getCalendarEvents().getValue().get(i).getTime());
        compactCalendarView.addEvent(ev);
      }
    }
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}