package com.booktree.ui.book;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.databinding.FragmentBookBinding;

public class BookFragment extends Fragment {

  private BookViewModel bookViewModel;
  private FragmentBookBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {

    bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

    binding = FragmentBookBinding.inflate(inflater,container,false);
    View root = binding.getRoot();

    final var textView = binding.textBook;
    final var searchBookInfo = binding.searchBookInfo;
    searchBookInfo.setOnKeyListener((v,keyCode,event) -> {
      if(keyCode == KeyEvent.KEYCODE_ENTER) {
        bookViewModel.setText(searchBookInfo.getText().toString());
      }
      return true;
    });

    bookViewModel.getQueryString().observe(getViewLifecycleOwner(), textView::setText);

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
