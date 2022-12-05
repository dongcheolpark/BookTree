package com.booktree.ui.book.bookFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.R;
import com.booktree.databinding.FragmentBookBinding;
import com.booktree.ui.book.BarcodeScanActivity;
import com.booktree.ui.book.BookViewModel;
import com.booktree.ui.book.bookFragment.ListFragment.RecommendFragment;
import com.booktree.ui.book.bookList.bookSearchList.BookRecyclerList;

public abstract class BookFragment extends Fragment {

  private BookViewModel bookViewModel;
  private FragmentBookBinding binding;
  private FragmentManager fragmentManager;
  private Fragment recommendFragment;

  @Override
  public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentBookBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    return root;
  }

  @Override
  public void onStart() {
    super.onStart();
    bookViewModel.setBookInfoList(getList(binding.bookInfoList));
    createRecommendFragment();
    setSearchText();
    setBookSearchQueryObserve();
    setBarcodeBtn();
  }

  private void createRecommendFragment() {
    fragmentManager = getChildFragmentManager();
    recommendFragment = new RecommendFragment();
    var transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.frameLayout,recommendFragment).commitAllowingStateLoss();
  }

  private void setBookSearchQueryObserve() { // 이렇게 짜기 싫은데 시간이 없어서 어쩔 수 없음
    bookViewModel.getQueryString().observe(getViewLifecycleOwner(), (query) -> {
      var transaction = fragmentManager.beginTransaction();
      if(query.isEmpty() || query == "") {
        transaction.replace(R.id.frameLayout,recommendFragment).commitAllowingStateLoss();
        binding.bookInfoList.setVisibility(View.INVISIBLE);
      }
      else {
        binding.bookInfoList.setVisibility(View.VISIBLE);
        transaction.remove(recommendFragment).commitAllowingStateLoss();
        bookViewModel.getBookInfoList().getInitialListItems(query);
      }
    });
  }

  private void setSearchText() {
    final var searchBookInfo = binding.searchBookInfo;
    searchBookInfo.setOnKeyListener((v, keyCode, event) -> {
      if (keyCode == KeyEvent.KEYCODE_ENTER) {
        var imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBookInfo.getWindowToken(),0);
        bookViewModel.setQueryString(searchBookInfo.getText().toString());
      }
      return true;
    });
  }

  private void setBarcodeBtn() {
    binding.barcodeBtn.setOnClickListener((view) -> {
      Intent intent = new Intent(getActivity(), BarcodeScanActivity.class);
      getActivity().startActivity(intent);
    });
  }

  protected abstract BookRecyclerList getList(RecyclerView bookInfoList);

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}