package com.booktree.ui.feed.feedCreate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.GetContent;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.databinding.ActivityCreateFeedBinding;
import com.booktree.model.Documents;

public class FeedCreateActivity extends AppCompatActivity {

  private ActivityCreateFeedBinding binding;
  private FeedCreateViewModel viewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityCreateFeedBinding.inflate(getLayoutInflater());

    viewModel = new ViewModelProvider(this).get(FeedCreateViewModel.class);
    setContentView(binding.getRoot());
    final var selectBookBtn = binding.selectBook;

    var getResult = registerForActivityResult(new StartActivityForResult(),
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

    selectBookBtn.setOnClickListener((view) -> {
      Intent intent = new Intent(this, FeedBookSelectActivity.class);
      getResult.launch(intent);
    });

    viewModel.getDocument().observe(this,(data) -> {
      Toast.makeText(this, data.title, Toast.LENGTH_SHORT).show();
    });
  }
}
