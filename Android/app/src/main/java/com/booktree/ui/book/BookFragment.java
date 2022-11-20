package com.booktree.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.R;
import com.booktree.databinding.FragmentBookBinding;
import com.booktree.ui.book.bookList.BookListAdapter;
import com.booktree.ui.book.bookList.BookRecyclerList;
import com.booktree.ui.book.bookList.Viewholder.ToBookInfoViewHolder;
import org.jetbrains.annotations.NotNull;

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
    var list = new BookRecyclerList(bookInfoList,getActivity(),
        new BookListAdapter<ToBookInfoViewHolder>(getActivity()) {
          @NonNull
          @NotNull
          @Override
          public ToBookInfoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
              int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_info_item, parent, false);
            return new ToBookInfoViewHolder(view);
          }
        });

    bookViewModel.getQueryString().observe(getViewLifecycleOwner(), list.getInitialListItems());

    Button barcodeBtn = binding.barcodeBtn;
    barcodeBtn.setOnClickListener((view) -> {
      Intent intent = new Intent(getActivity(), BarcodeScanActivity.class);
      getActivity().startActivity(intent);
    });

    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
