package com.booktree.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.booktree.databinding.FragmentHomeBinding;
import com.booktree.ui.home.CalendarfeedList.CalendarFeedRecyclerList;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.github.sundeepk.compactcalendarview.domain.Event;

public class HomeFragment extends Fragment {

  private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
  private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;
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

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}