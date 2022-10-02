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
        button = findViewById(R.id.button);

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

    public void btnBack(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        String text = B1.getText().toString();
        intent.putExtra("t1", text);
        setResult(RESULT_OK, intent);
        finish();
        startActivity(intent);
    }
}