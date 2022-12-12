package com.booktree.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.booktree.API.FBDatabase;
import com.booktree.databinding.FragmentSettingBinding;
import com.booktree.model.User;
import com.booktree.ui.AuthActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class SettingFragment extends Fragment {

  private SettingViewModel notificationsViewModel;
  private FragmentSettingBinding binding;

  @Override
  public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    notificationsViewModel =
        new ViewModelProvider(this).get(SettingViewModel.class);
    super.onCreate(savedInstanceState);
  }
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSettingBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setContent(FBDatabase.getInstance().getUserInfo());
    setLogout();
  }
  public void setContent(User user) {
    binding.SettingProfileName.setText(user.name);
    Glide.with(this).load(user.profileImg).into(binding.SettingProfileAvatar);
  }
  public void setLogout() {
    binding.SettingLogout.setOnClickListener((view) -> {
      FirebaseAuth.getInstance().signOut();
      var intent = new Intent(getActivity(), AuthActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
      startActivity(intent);
    });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}