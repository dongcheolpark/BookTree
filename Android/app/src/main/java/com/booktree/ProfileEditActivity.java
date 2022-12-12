package com.booktree;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.booktree.API.FBDatabase;
import com.booktree.MainActivity;
import com.booktree.R;
import com.booktree.common.VoidCallback;
import com.booktree.databinding.ActivityProfileeditBinding;
import com.booktree.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;

public class ProfileEditActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityProfileeditBinding binding;
    private ImageView Ed_profileImage;
    private ActivityResultLauncher<Intent> launcher;
    private User userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding=ActivityProfileeditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth=FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();

        Ed_profileImage= binding.profileImageview;
        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result-> {
                    if (result.getResultCode() == RESULT_OK) {
                        try {
                            Uri uri = result.getData().getData();
                            Ed_profileImage.setImageURI(uri);
                            //String imagePath=getRealPathFromURI(uri);
                            userInfo.profileImg = uri.toString();
                        } catch (Exception e) {
                        }
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
                    }
                });
        Ed_profileImage.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launcher.launch(intent);
        });

        final EditText Ed_username=binding.profileTextviewName;
        final TextView UserName;
        Button Ed_Btn=binding.profileButton;
        Ed_username.setOnClickListener(view -> userInfo.name=Ed_username.getText().toString());

        userInfo.uid=user.getUid();

        FBDatabase.getInstance().createUser(userInfo,()->{});

        Ed_Btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }}
    /*private String getRealPathFromURI(Uri contentUri) {
        if (contentUri.getPath().startsWith("/storage")) {
            return contentUri.getPath();
        }
        String id = DocumentsContract.getDocumentId(contentUri).split(":")[1];
        String[] columns = { MediaStore.Files.FileColumns.DATA };
        String selection = MediaStore.Files.FileColumns._ID + " = " + id;
        Cursor cursor = this.getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            int columnIndex = cursor.getColumnIndex(columns[0]);
            if (cursor.moveToFirst())
            { return cursor.getString(columnIndex);
            }
        } finally {
            cursor.close();
        } return null;
    }

}*/
