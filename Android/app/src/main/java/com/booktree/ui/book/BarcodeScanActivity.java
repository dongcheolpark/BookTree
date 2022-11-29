package com.booktree.ui.book;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;


import com.booktree.BookDetailActivity;
import com.booktree.BookDetailViewModel;
import com.booktree.R;
import com.booktree.databinding.FragmentBookBinding;
import com.booktree.model.Documents;
import com.booktree.ui.book.BookFragment.BookFragment;
import com.booktree.ui.book.bookList.BookRecyclerList;
import com.booktree.ui.book.bookList.Viewholder.BasicViewHolder;
import com.booktree.ui.feed.feedCreate.DocumentEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startBarcodeReader();
    }

    public void startBarcodeReader(){
//        Toast.makeText(this,"들어오긴 함",Toast.LENGTH_SHORT).show();
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); //휴대폰 방향에 따라 가로,세로 변경
        qrScan.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents() != null) { //바코드를 제대로 읽은 경우
                Toast.makeText(this, "scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("isbn",result.getContents());
                startActivity(intent);
            }else{ //사용자가 이전으로 가기 원할떄
                Intent intent = new Intent(this, BookFragment.class);
                startActivity(intent);
                Toast.makeText(this, "cancelled",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}