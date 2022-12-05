package com.booktree.ui.home;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

import android.content.Intent;
import android.net.Uri;
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

public class HomeFragment extends Fragment {

//  private static final String TAG = "HomeFragment";
  private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
  private SimpleDateFormat dateFormatForDay = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

  private HomeViewModel homeViewModel;
  private FragmentHomeBinding binding;
  private User user;
  private FirebaseAuth mAuth;
  public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

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

    textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth())); //위에 MMM yyyy 표시
    compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);  //일요일을 시작으로


    // 날짜 클릭 이벤트 관련 코드
    compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
      @Override
      public void onDayClick(Date dateClicked) {
        String date = dateFormatForDay.format(dateClicked); //날짜 나오기
        textView_result.setText(date);
      }



      @Override
      public void onMonthScroll(Date firstDayOfNewMonth) {
        textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
      }



//        // 해당날짜에 이벤트가 있으면
//        if (events.size() > 0) {
//          textView_result.setText(events.get(0).getData().toString());
//        }
//        // 해당날짜에 이벤트가 없으면
//        else {
//          SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
//          String clickDate = simpleDate.format(dateClicked);
//
//          EditText editText = new EditText(HomeFragment.this.getContext());
//          AlertDialog.Builder builder = new AlertDialog.Builder(HomeFragment.this.getContext());
//          AlertDialog dialogText = builder.setTitle("추가할 일정을 입력해 주세요.")
//                  // .setMessage("메시지 입력")
//                  .setView(editText)
//                  .setPositiveButton("저장하기", new DialogInterface.OnClickListener() {
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                      String dateClicked_st = simpleDate.format(dateClicked);
//                      Log.d(TAG, "태그 dateClicked_st : " + dateClicked_st);
//
//                      Date currentDay = null;
//                      try {
//                        // .parse 함수 : Parses text from a string to produce a Date (문자열에서 텍스트를 분석하여 날짜 생성)
//                        currentDay = simpleDate.parse(dateClicked_st);
//                        Log.d(TAG, "태그 currentDay : " + currentDay);
//
//                      } catch (ParseException e) {
//                        e.printStackTrace();
//                      }
//                      Long currentLong = currentDay.getTime();
//                      Log.d(TAG, "태그 currentLong : " + currentLong);
//
//                      Event ev1 = new Event(Color.GREEN, currentLong, clickDate + " : " + editText.getText().toString());
//                      compactCalendarView.addEvent(ev1);
//                      textView_result.setText(clickDate + " : " + editText.getText().toString());
//                      Toast.makeText(HomeFragment.this.getContext(), "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                  })
//                  .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                      // 취소 클릭 시 실행할 거 작성
//                      Toast.makeText(HomeFragment.this.getContext(), "일정입력 취소되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                  })
//                  .create();
//          dialogText.show();
//        }
//      }

//      @Override
//      public void onMonthScroll(Date firstDayOfNewMonth) {
//        textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
//        Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
//      }
    });

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}