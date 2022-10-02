package com.example.saored;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn_capture, btn_copy;
    TextView tv_data;
    private static final int REQUEST_CAMERA_CODE = 101;
    Bitmap bitmap;
    TextView b1, b2, b3, b4, b5, b6;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn_capture = findViewById(R.id.button_capture);
        btn_copy = findViewById(R.id.button_copy);
        tv_data = findViewById(R.id.text_data);

        b1 = (TextView) findViewById(R.id.bang1);
        b2 = (TextView) findViewById(R.id.bang2);
        b3 = (TextView) findViewById(R.id.bang3);
        b4 = (TextView) findViewById(R.id.bang4);
        b5 = (TextView) findViewById(R.id.bang5);
        b6 = (TextView) findViewById(R.id.bang6);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CAMERA }, REQUEST_CAMERA_CODE);
        }
        btn_capture.setOnClickListener(view -> CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(MainActivity.this));
        
        btn_copy.setOnClickListener(view -> {
            String scanned_text = tv_data.getText().toString();
            MainActivity.this.copyToClickBoard(scanned_text);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                intent.putExtra()
//            }
//        });
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

            String s = stringBuilder.toString();
            String[] string = s.split(" ");
            for (int i=0;i<string.length;i++){
                if(i==0){
                    b1.setText(string[0]);
                }
                else if(i==1){
                    b2.setText(string[1]);
                }
                else if(i==2){
                    b3.setText(string[2]);
                }
                else if(i==3){
                    b4.setText(string[3]);
                }
                else if(i==4){
                    b5.setText(string[4]);
                }
                else if(i==5){
                    b6.setText(string[5]);
                }
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //lấy hình ảnh từ máy ảnh

        super.onActivityResult(requestCode, resultCode, data);
        String result1 = data.getStringExtra("t1");
        b1.setText(result1);
        String result2 = data.getStringExtra("t2");
        b2.setText(result2);
        String result3 = data.getStringExtra("t3");
        b3.setText(result3);
        String result4 = data.getStringExtra("t4");
        b4.setText(result4);
        String result5 = data.getStringExtra("t5");
        b5.setText(result5);
        String result6 = data.getStringExtra("t6");
        b6.setText(result6);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK) {
                assert result != null;
                Uri resultUri = result.getUri();
                 try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                        getTextFromImage(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
    StringBuilder stringBuilder = new StringBuilder();
    private void getTextFromImage(Bitmap bitmap){
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if(!recognizer.isOperational()){
            Toast.makeText(MainActivity.this, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            for(int i=0;i<textBlockSparseArray.size();i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
            }
            tv_data.setText(stringBuilder.toString());
            btn_capture.setText("Retake");
            btn_copy.setVisibility(View.VISIBLE);
        }
    }

//    public void Themgiatri(StringBuilder stringBuilder){
//        String s = stringBuilder.toString();
//        String[] string = s.split(" ");
//        for (int i=0;i<string.length;i++){
//            if(i==0){
//                    b1.setText(string[0], TextView.BufferType.EDITABLE);
//            }
//            else if(i==1){
//                    b2.setText(string[1], TextView.BufferType.EDITABLE);
//            }
//            else if(i==2){
//                    b3.setText(string[2], TextView.BufferType.EDITABLE);
//            }
//            else if(i==3){
//                    b4.setText(string[3], TextView.BufferType.EDITABLE);
//            }
//            else if(i==4){
//                    b5.setText(string[4], TextView.BufferType.EDITABLE);
//            }
//            else if(i==5){
//                    b6.setText(string[5], TextView.BufferType.EDITABLE);
//            }
//        }
//    }

    private void copyToClickBoard(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied to clipboard!", Toast.LENGTH_SHORT).show();
    }


    public void btnNext(View view) {
        Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent1, 6);

    }
}