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

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {

    bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

    binding = FragmentBookBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override
  public void onStart() {
    super.onStart();

    final var searchBookInfo = binding.searchBookInfo;
    searchBookInfo.setOnKeyListener((v, keyCode, event) -> {
      if (keyCode == KeyEvent.KEYCODE_ENTER) {
        var imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBookInfo.getWindowToken(),0);
        bookViewModel.setQueryString(searchBookInfo.getText().toString());
      }
      return true;
    });

    var bookInfoList = binding.bookInfoList;
    bookViewModel.setBookInfoList(getList(bookInfoList));

    bookViewModel.getQueryString().observe(getViewLifecycleOwner(), bookViewModel.getBookInfoList().getInitialListItems());

    var barcodeBtn = binding.barcodeBtn;
    barcodeBtn.setOnClickListener((view) -> {
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