package com.booktree.ui.book.BookFragment;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.booktree.R;
import com.booktree.databinding.FragmentBookBinding;
import com.booktree.ui.book.BarcodeScanActivity;
import com.booktree.ui.book.BookViewModel;
import com.booktree.ui.book.bookList.BookListAdapter;
import com.booktree.ui.book.bookList.BookRecyclerList;
import com.booktree.ui.book.bookList.Viewholder.ToBookInfoViewHolder;
import org.jetbrains.annotations.NotNull;

public abstract class BookFragment extends Fragment {

  private BookViewModel bookViewModel;
  private FragmentBookBinding binding;

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
    setSearchText();
    setBookList();
    setBarcodeBtn();
  }

  private void setBookList() {
    bookViewModel.setBookInfoList(getList(binding.bookInfoList));
    bookViewModel.getQueryString().observe(getViewLifecycleOwner(), bookViewModel.getBookInfoList().getInitialListItems());
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