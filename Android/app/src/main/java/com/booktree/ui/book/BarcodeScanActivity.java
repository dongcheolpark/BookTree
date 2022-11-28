package com.booktree.ui.book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanActivity extends AppCompatActivity  {

    private BookViewModel bookViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
//        setContentView(R.layout.activity_main);
        startBarcodeReader();
    }

    public void startBarcodeReader(){
//        Toast.makeText(this,"들어오긴 함",Toast.LENGTH_SHORT).show();
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents() != null){ //바코드를 제대로 읽은 경우
                Toast.makeText(this, "scanned: "+result.getContents(), Toast.LENGTH_LONG).show();
                bookViewModel.setQueryString(result.getContents());
            }else{ //사용자가 이전으로 가기 원할떄
                Toast.makeText(this, "cancelled",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}