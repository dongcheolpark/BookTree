package com.booktree.ui.feed.feedCreate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.R;
import com.booktree.databinding.FragmentBookSelectBinding;
import com.booktree.model.Documents;
import com.booktree.ui.book.BookFragment.BookGetInfoFragment;
import com.booktree.ui.book.BookFragment.BookToInfoFragment;

public class FeedBookSelectActivity extends AppCompatActivity implements DocumentEventListener {

  private FragmentBookSelectBinding binding;

  @Override
  public void Event(Documents document) {
    Intent intent = new Intent();
    intent.putExtra("document", document);
    setResult(Activity.RESULT_OK,intent);
    finish();
  }

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = FragmentBookSelectBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    var fragment = new BookGetInfoFragment();
    var manager = getSupportFragmentManager();
    var fragmentTransaction = manager.beginTransaction();
    fragmentTransaction.add(R.id.frame,fragment);
    fragmentTransaction.commit();
  }
}
