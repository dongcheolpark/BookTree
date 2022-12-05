package com.booktree.ui.book.bookFragment.ListFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.databinding.FragmentRecommendBinding;

public class RecommendFragment extends Fragment {
  private RecommendViewModel viewModel;
  private FragmentRecommendBinding binding;

  @Override
  public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(RecommendViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentRecommendBinding.inflate(inflater,container,false);
    return binding.getRoot();
  }

  @Override
  public void onStart() {
    super.onStart();
  }
}
