package com.booktree.ui.book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.booktree.ui.book.bookDetail.BookDetailActivity;
import com.booktree.ui.book.bookFragment.BookFragment;
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