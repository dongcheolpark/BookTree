package com.booktree.ui.feed.feedDetail;

import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.databinding.ActivityFeedDetailBinding;
import com.booktree.model.Feed;
import com.bumptech.glide.Glide;

public class FeedDetailActivity extends AppCompatActivity {
  private ActivityFeedDetailBinding binding;
  private FeedDetailViewModel viewModel;

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityFeedDetailBinding.inflate(getLayoutInflater());
    viewModel = new ViewModelProvider(this).get(FeedDetailViewModel.class);
    setContentView(binding.getRoot());
  }

  @Override
  protected void onStart() {
    super.onStart();

    if (VERSION.SDK_INT >= 33) {
      viewModel.setFeed(getIntent().getSerializableExtra("feed", Feed.class));
    }
    else {
      viewModel.setFeed((Feed) getIntent().getSerializableExtra("feed"));
    }
    setContents();
  }

  private void setContents() {
    viewModel.getFeed().observe(this,(feed) -> {
      binding.feedAuthor.setText(feed.author);
      binding.feedContent.setText(feed.contents);
      Glide.with(this).load(feed.imageUrl).into(binding.feedImage);
    });
  }
}
