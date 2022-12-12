package com.booktree.ui.home;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.booktree.API.FBDatabase;
import com.booktree.MainActivity;
import com.booktree.R;

import com.booktree.ui.home.friendsList.FollowerActivity;
import com.booktree.ui.home.friendsList.FollowingActivity;


import com.booktree.databinding.FragmentHomeBinding;
import com.booktree.ui.home.CalendarfeedList.CalendarFeedRecyclerList;
import com.bumptech.glide.Glide;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.booktree.model.User;
import com.booktree.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.github.sundeepk.compactcalendarview.domain.Event;

public class HomeFragment extends Fragment {

//  private static final String TAG = "HomeFragment";
  private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
  private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;
  private User user;
  private FirebaseAuth mAuth;
  private Date today;

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
    mAuth=FirebaseAuth.getInstance();
    final FirebaseUser curuser = mAuth.getCurrentUser();
    FBDatabase.getInstance().getUser(curuser.getUid(),(user)->{
      Glide.with(this).load(user.profileImg).into(profile_image);
      textView_username.setText(user.name);
    });

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
        Event ev1 = new Event(Color.BLACK, dateClicked.getTime()); //날짜
        compactCalendarView.addEvent(ev1);
        textView_result.setText(dateClicked.toString());
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
    var feedList = new CalendarFeedRecyclerList(feedListView,getActivity());
    homeViewModel.getCalendarFeedList().observe(getViewLifecycleOwner(), (list) -> {
      feedList.getAdapter().setList(list);
    });

    homeViewModel.refreshCalendarFeedList(today,this::stopShimmer);
    //1. 데이터 생성
//    BarDataSet barDataSet1 = new BarDataSet(data1(), "Data1");
//    //2. 바 데이터 생성
//    BarData barData = new BarData();
//    //3. 바 데이터에 데이터셋 추가
//    barData.addDataSet(barDataSet1);
//    //4. 바차트에 바데이터 등록
//    barChart.setData(barData);

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
  }
//
//  private ArrayList<BarEntry> data1(){
//    ArrayList<BarEntry> dataList = new ArrayList<>();
//
//    dataList.add(new BarEntry(0,3));
//    dataList.add(new BarEntry(1,6));
//    dataList.add(new BarEntry(2,10));
//    dataList.add(new BarEntry(3,15));
//    return dataList;
//  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}