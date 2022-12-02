package com.booktree.ui.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.databinding.FragmentFeedBinding;
import com.booktree.ui.feed.feedCreate.FeedCreateActivity;
import com.booktree.ui.feed.feedList.FeedRecyclerList;

public class FeedFragment extends Fragment {

  private FeedViewModel feedViewModel;
  private FragmentFeedBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    feedViewModel =
        new ViewModelProvider(this).get(FeedViewModel.class);

    binding = FragmentFeedBinding.inflate(inflater, container, false);

    binding.shimmerFeedList.startShimmer();
    binding.shimmerFeedList.setVisibility(View.VISIBLE);
    binding.feedList.setVisibility(View.INVISIBLE);

    final var feedListView = binding.feedList;
    var feedList = new FeedRecyclerList(feedListView,getActivity());
    feedViewModel.getFeedList().observe(getViewLifecycleOwner(), (list) -> {
      feedList.getAdapter().setList(list);
    });

    feedViewModel.refreshFeedList(this::stopShimmer);

    final var createFeedBtn = binding.createFeedBtn;
    createFeedBtn.setOnClickListener((view) -> {
      Intent intent = new Intent(getActivity(), FeedCreateActivity.class);
      startActivity(intent);
    });

    View root = binding.getRoot();

    return root;
  }

  private void stopShimmer() {
    binding.shimmerFeedList.stopShimmer();
    binding.feedList.setVisibility(View.VISIBLE);
    binding.shimmerFeedList.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onResume() {
    super.onResume();
    feedViewModel.refreshFeedList(this::stopShimmer);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}