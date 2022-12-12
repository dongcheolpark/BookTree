package com.booktree.component;

import static androidx.core.content.FileProvider.getUriForFile;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.booktree.R;
import com.booktree.common.ResultCallBack;
import com.booktree.common.VoidCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;

public class ChooseImageBottomDialog {

  public static BottomSheetDialog Create(AppCompatActivity activity,LayoutInflater layoutInflater,
     File fileDir, ResultCallBack<Uri> callBack) {
    var file = new File(fileDir,"tempFile.png");
    var cameraUri = getUriForFile(activity,activity.getPackageName() + ".fileProvider",file);

    var dialogView = layoutInflater.inflate(R.layout.dialog_choose_image_source, null);
    var dialog = new BottomSheetDialog(activity,R.style.NewDialog);
    dialog.setContentView(dialogView);

    var takePhoto = activity.registerForActivityResult(new ActivityResultContracts.TakePicture(),(result) -> {
      if(result)
        callBack.func(cameraUri);
    });

    var chooseImage = activity.registerForActivityResult(new ActivityResultContracts.GetContent(),(uri) -> {
      callBack.func(uri);
    });

    dialogView.findViewById(R.id.TakeCamera).setOnClickListener((view) -> {
      takePhoto.launch(cameraUri);
      dialog.dismiss();
    });
    dialogView.findViewById(R.id.ChooseImage).setOnClickListener((view) -> {
      chooseImage.launch("image/*");
      dialog.dismiss();
    });
    return dialog;
  }
}
