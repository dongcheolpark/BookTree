package com.booktree;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.model.Documents;
import com.bumptech.glide.Glide;

public class BookDetailActivity extends AppCompatActivity {
  Documents doc;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    doc = (Documents)getIntent().getSerializableExtra("document");
    setContentView(R.layout.activity_book_detail);

    setTopViewGroup();

  }

  protected void setTopViewGroup() {
    ImageView Thumbnail = findViewById(R.id.bookDetailThumbnail);
    TextView title = findViewById(R.id.bookDetailTitle);
    TextView author = findViewById(R.id.bookDetailAuthor);
    TextView date = findViewById(R.id.bookDetailDate);
    TextView publisher = findViewById(R.id.bookDetailPublisher);

    title.setText(doc.title);
    author.setText(doc.authorString());
    date.setText(doc.getDateString());
    publisher.setText(doc.publisher);
    Glide.with(this).load(doc.thumbnail).into(Thumbnail);
  }
}
