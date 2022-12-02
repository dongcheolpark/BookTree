package com.booktree.ui.feed.feedCreate;

import static androidx.core.content.FileProvider.getUriForFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.databinding.ActivityCreateFeedBinding;
import com.booktree.model.Documents;
import com.booktree.ui.book.bookList.Viewholder.BasicViewHolder;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public class FeedCreateActivity extends AppCompatActivity {

  private ActivityCreateFeedBinding binding;
  private FeedCreateViewModel viewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityCreateFeedBinding.inflate(getLayoutInflater());
    viewModel = new ViewModelProvider(this).get(FeedCreateViewModel.class);
  }

  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public View onCreateView(@NonNull @NotNull String name, @NonNull @NotNull Context context,
      @NonNull @NotNull AttributeSet attrs) {
    setContentView(binding.getRoot());
    return super.onCreateView(name, context, attrs);
  }

  @Override
  protected void onStart() {
    super.onStart();

    getDocument();
    setDocumentHolder();
    setImageBtn();
    setFeedContents();
    createFeedBtn();

  }

  private void getDocument() {
    Documents doc;
    if (android.os.Build.VERSION.SDK_INT >= 33) {
      doc = getIntent().getSerializableExtra("document",Documents.class);
    }
    else {
      doc = (Documents) getIntent().getSerializableExtra("document");
    }
    if(doc != null) viewModel.setDocument(doc);
  }

  private void setDocumentHolder() {
    final var viewHolder = new BasicViewHolder(binding.createFeedBookInfo.getRoot());
    var getBookInfo = registerForActivityResult(new StartActivityForResult(),
        result -> {
          if(result.getResultCode() == Activity.RESULT_OK) {
            if (VERSION.SDK_INT >= 33) {
              viewModel.setDocument(result.getData().getSerializableExtra("document", Documents.class));
            }
            else {
              viewModel.setDocument((Documents) result.getData().getSerializableExtra("document")); // android 33 밑으로는 이 코드를 써야 한다.
            }
          }
        });
    binding.selectBook.setOnClickListener((view) -> {
      Intent intent = new Intent(this, FeedBookSelectActivity.class);
      getBookInfo.launch(intent);
    });
    viewModel.getDocument().observe(this,(data) -> {
      binding.selectBookLayout.setVisibility(View.INVISIBLE);
      binding.createFeedBookInfo.getRoot().setVisibility(View.VISIBLE);
      viewHolder.setContents(this,data);
      Toast.makeText(this, data.title, Toast.LENGTH_SHORT).show();
    });
  }

  private void setImageBtn() {
    viewModel.getUri().observe(this,(data) -> {
      binding.feedImage.setImageURI(data);
    });

    var takePhoto = registerForActivityResult(new ActivityResultContracts.TakePicture(),(result) -> {});

    binding.feedImage.setOnClickListener((view) -> {
      viewModel.setImageFile(new File(getFilesDir(),"tempFile.png"));
      var uri = getUriForFile(this,getApplicationContext().getPackageName() + ".fileProvider",viewModel.getFile().getValue());
      takePhoto.launch(uri);
      viewModel.setImage(uri);
    });
  }

  private void setFeedContents() {
    binding.feedContent.setOnKeyListener((v, keyCode, event) -> {
      viewModel.setContents(binding.feedContent.getText().toString());
      return true;
    });
  }

  private void createFeedBtn() {
    binding.createFeedBtn.setOnClickListener((view)-> {
      viewModel.createFeed(() -> {
        finish();
      },() -> {
        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
      });
    });

  }
}
