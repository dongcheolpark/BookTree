package com.booktree.ui.book.bookDetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.model.Documents;
import com.booktree.ui.feed.feedCreate.FeedCreateActivity;
import com.bumptech.glide.Glide;
import com.booktree.databinding.ActivityBookDetailBinding;

public class BookDetailActivity extends AppCompatActivity {
  private ActivityBookDetailBinding binding;
  private BookDetailViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityBookDetailBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());

    viewModel = new ViewModelProvider(this).get(BookDetailViewModel.class);

    viewModel.getDocument().observe(this,(doc) -> {
      binding.createFeedBtnDetail.setOnClickListener((view) -> {
        var intent = new Intent(this, FeedCreateActivity.class);
        intent.putExtra("document",doc);
        startActivity(intent);
      });
      setTopViewGroup(doc);
    });
    var isbn = getIntent().getStringExtra("isbn");
    if(TextUtils.isEmpty(isbn)) {
      viewModel.setDocument((Documents) getIntent().getSerializableExtra("document"));
    }
    else {
      viewModel.setDocumentWithIsbn(isbn);
    }
    viewModel.getDocument().observe(this,(doc)->{
      viewModel.refreshFeedList(()->{});
    });

    var bookReviewList = new BookReviewList(binding.recyclerView,this);

    viewModel.getFeedList().observe(this,(list) -> {
      bookReviewList.getAdapter().setList(list);
    });
  }

  protected void setTopViewGroup(Documents doc) {
    binding.bookDetailTitle.setText(doc.title);
    binding.bookDetailAuthor.setText(doc.authorString());
    binding.bookDetailDate.setText(doc.getDateString());
    binding.bookDetailPublisher.setText(doc.publisher);
    Glide.with(this).load(doc.thumbnail).into(binding.bookDetailThumbnail);
  }
}
