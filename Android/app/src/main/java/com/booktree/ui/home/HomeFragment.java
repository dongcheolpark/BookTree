package com.booktree.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.booktree.ui.home.CalendarfeedList.CalendarFeedRecyclerList;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.booktree.databinding.FragmentHomeBinding;
import com.google.firebase.Timestamp;

public class HomeFragment extends Fragment {

  private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
  private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;
  private Date dateDt = new Date();

  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    final var compactCalendarView = binding.compactcalendarView;
    final var textView_month = binding.textViewMonth;
    final var textView_result = binding.textViewResult;
//    Timestamp selectedDateTs = null;


    textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth())); //위에 MMM yyyy 표시
    compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);  //일요일을 시작으로

    // 날짜 클릭 이벤트 관련 코드
    compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
      @Override
      public void onDayClick(Date dateClicked) {
        String date = dateFormatForDay.format(dateClicked); //날짜 나오기
//        homeViewModel.setTimestamp(selectedDateTs);
        dateDt = dateClicked;
        textView_result.setText(date);
      }

      @Override
      public void onMonthScroll(Date firstDayOfNewMonth) {
        textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
      }
    });

    final var calendarFeedListView = binding.calendarFeedList;
    var calendarFeedList = new CalendarFeedRecyclerList(calendarFeedListView,getActivity());
    homeViewModel.getCalendarFeedList().observe(getViewLifecycleOwner(), calendarFeedList::setCalendarFeedList);

    homeViewModel.refreshCalendarFeedList();

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}