package com.booktree.component;

import android.net.Uri;
import android.view.LayoutInflater;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.R;
import com.booktree.common.VoidCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ChooseImageBottomDialog {
  public static BottomSheetDialog Create(AppCompatActivity activity,LayoutInflater layoutInflater,
      Uri uri, VoidCallback callBack) {
    var dialogView = layoutInflater.inflate(R.layout.dialog_choose_image_source, null);
    var takePhoto = activity.registerForActivityResult(new ActivityResultContracts.TakePicture(),(result) -> {
      if(result)
        callBack.func();
    });
    var dialog = new BottomSheetDialog(activity);
    dialog.setContentView(dialogView);
    dialogView.findViewById(R.id.TakeCamera).setOnClickListener((view) -> {
      takePhoto.launch(uri);
      dialog.dismiss();
    });
    return dialog;
  }
}
