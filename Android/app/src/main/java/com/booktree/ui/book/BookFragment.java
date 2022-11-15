package com.booktree.ui.book;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.API.APIClient;
import com.booktree.API.DTO.BookDTO;
import com.booktree.databinding.FragmentBookBinding;
import com.booktree.ui.book.bookList.BookRecyclerList;
import com.booktree.ui.book.bookList.bookListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFragment extends Fragment {

  private BookViewModel bookViewModel;
  private FragmentBookBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {

    bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

    binding = FragmentBookBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    final var searchBookInfo = binding.searchBookInfo;
    searchBookInfo.setOnKeyListener((v, keyCode, event) -> {
      if (keyCode == KeyEvent.KEYCODE_ENTER) {
        bookViewModel.setQueryString(searchBookInfo.getText().toString());
      }
      return true;
    });

    var bookInfoList = binding.bookInfoList;
    var list = new BookRecyclerList(bookInfoList,getActivity());
    bookViewModel.getQueryString().observe(getViewLifecycleOwner(), list.getInitialListItems());

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
