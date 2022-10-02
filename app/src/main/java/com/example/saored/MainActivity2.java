package com.example.saored;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    Button button;
    EditText B1, B2, B3, B4, B5, B6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        B1 = findViewById(R.id.bangone);
        B2 = findViewById(R.id.bangtwo);
        B3 = findViewById(R.id.bangthree);
        B4 = findViewById(R.id.bangfour);
        B5 = findViewById(R.id.bangfive);
        B6 = findViewById(R.id.bangsix);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Intent intent1 = new Intent();
            String text1 = B1.getText().toString();
            String text2 = B2.getText().toString();
            String text3 = B3.getText().toString();
            String text4 = B4.getText().toString();
            String text5 = B5.getText().toString();
            String text6 = B6.getText().toString();
            intent1.putExtra("t1", text1);
            intent1.putExtra("t2", text2);
            intent1.putExtra("t3", text3);
            intent1.putExtra("t4", text4);
            intent1.putExtra("t5", text5);
            intent1.putExtra("t6", text6);
            setResult(1, intent1);
//            setResult(1, intent1);
////            setResult(3, intent3);
////            setResult(4, intent4);
////            setResult(5, intent5);
////            setResult(6, intent6);
            finish();
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1){
//            if(resultCode == RESULT_OK){
//                String result = data.getStringExtra("t1");
//                b1.setText(result);
//            }
//            if(resultCode == RESULT_CANCELED){
//                b1.setText("0");
//            }
//        }
//    }

//    public void btnBack(View view) {
//        Intent intent1 = new Intent();
//        Intent intent2 = new Intent();
//        Intent intent3 = new Intent();
//        Intent intent4 = new Intent();
//        Intent intent5 = new Intent();
//        Intent intent6 = new Intent();
//        String text1 = B1.getText().toString();
//        String text2 = B2.getText().toString();
//        String text3 = B3.getText().toString();
//        String text4 = B4.getText().toString();
//        String text5 = B5.getText().toString();
//        String text6 = B6.getText().toString();
//        intent1.putExtra("t1", text1);
//        intent2.putExtra("t2", text2);
//        intent3.putExtra("t3", text3);
//        intent4.putExtra("t4", text4);
//        intent5.putExtra("t5", text5);
//        intent6.putExtra("t6", text6);
//        setResult(1, intent1);
//        setResult(2, intent2);
//        setResult(3, intent3);
//        setResult(4, intent4);
//        setResult(5, intent5);
//        setResult(6, intent6);
//        finish();
//    }
}