package com.booktree.ui.feed.feedCreate;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.R;
import com.booktree.databinding.FragmentBookSelectBinding;
import com.booktree.ui.book.BookFragment;

public class FeedBookSelectActivity extends AppCompatActivity {

  private FragmentBookSelectBinding binding;

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = FragmentBookSelectBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    var fragment = new BookFragment();
    var manager = getSupportFragmentManager();
    var fragmentTransaction = manager.beginTransaction();
    fragmentTransaction.add(R.id.frame,fragment);
    fragmentTransaction.commit();
  }
}
