package com.booktree.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.API.FBDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    var mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    if (user != null) {
      FBDatabase.getInstance().setUser(user.getUid(),() -> {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
      });
    }
    else {
      var intent = new Intent(this, AuthActivity.class);
      startActivity(intent);
      finish();
    }
  }
}
