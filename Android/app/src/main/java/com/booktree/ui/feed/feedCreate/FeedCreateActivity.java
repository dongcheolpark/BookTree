package com.booktree.ui.feed.feedCreate;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.databinding.ActivityCreateFeedBinding;

public class FeedCreateActivity extends AppCompatActivity {

  private ActivityCreateFeedBinding binding;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityCreateFeedBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    final var selectBookBtn = binding.selectBook;
    selectBookBtn.setOnClickListener((view) -> {
      Intent intent = new Intent(this, FeedBookSelectActivity.class);
      this.startActivity(intent);
    });
  }
}
