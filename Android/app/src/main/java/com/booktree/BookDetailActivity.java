package com.booktree;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.API.APIClient;
import com.booktree.model.Documents;
import com.bumptech.glide.Glide;
import com.booktree.databinding.ActivityBookDetailBinding;
import java.io.IOException;
import java.util.Objects;

public class BookDetailActivity extends AppCompatActivity {
  private ActivityBookDetailBinding binding;
  private BookDetailViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityBookDetailBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());

    viewModel = new ViewModelProvider(this).get(BookDetailViewModel.class);

    viewModel.getDocument().observe(this, this::setTopViewGroup);
    var isbn = getIntent().getStringExtra("isbn");
    if(TextUtils.isEmpty(isbn)) {
      viewModel.setDocument((Documents) getIntent().getSerializableExtra("document"));
    }
    else {
      viewModel.setDocumentWithIsbn(isbn);
    }
  }

  protected void setTopViewGroup(Documents doc) {
    binding.bookDetailTitle.setText(doc.title);
    binding.bookDetailAuthor.setText(doc.authorString());
    binding.bookDetailDate.setText(doc.getDateString());
    binding.bookDetailPublisher.setText(doc.publisher);
    Glide.with(this).load(doc.thumbnail).into(binding.bookDetailThumbnail);
  }
}
