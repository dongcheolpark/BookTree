package com.booktree.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.booktree.MainActivity;
import com.booktree.R;
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

    bookViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

    View view = inflater.inflate(R.layout.fragment_book,container,false);
    Button barcodeBtn = (Button)view.findViewById(R.id.barcodeBtn);
    barcodeBtn.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){((MainActivity)getActivity()).startBarcodeMain();}});
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
