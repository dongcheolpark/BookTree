package com.booktree.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.booktree.R;
import com.booktree.ui.book.BarcodeScanActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.booktree.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    BottomNavigationView navView = binding.navView;
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home, R.id.navigation_dashboard,  R.id.navigation_book, R.id.navigation_setting)
        .build();
    NavController navController = Navigation.findNavController(this,
        R.id.nav_host_fragment_activity_main);
    NavigationUI.setupWithNavController(binding.navView, navController);
  }

}