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
    EditText b1, b2, b3, b4, b5, b6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_capture = findViewById(R.id.button_capture);
        btn_copy = findViewById(R.id.button_copy);
        tv_data = findViewById(R.id.text_data);

        b1 = (EditText) findViewById(R.id.bang1);
        b2 = (EditText) findViewById(R.id.bang2);
        b3 = (EditText) findViewById(R.id.bang3);
        b4 = (EditText) findViewById(R.id.bang4);
        b5 = (EditText) findViewById(R.id.bang5);
        b6 = (EditText) findViewById(R.id.bang6);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CAMERA }, REQUEST_CAMERA_CODE);
        }
        btn_capture.setOnClickListener(view -> CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(MainActivity.this));
        
        btn_copy.setOnClickListener(view -> {
            String scanned_text = tv_data.getText().toString();
            MainActivity.this.copyToClickBoard(scanned_text);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //lấy hình ảnh từ máy ảnh

        super.onActivityResult(requestCode, resultCode, data);
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

    public void Themgiatri(StringBuilder stringBuilder){
        String s = stringBuilder.toString();
        String[] string = s.split(" ");
        for (int i=0;i<string.length;i++){
            if(i==0){
                if(string[i].isEmpty()){
                    b1.setText("0", TextView.BufferType.EDITABLE);
                }
                else{
                    b1.setText(string[0], TextView.BufferType.EDITABLE);
                }
            }
            else if(i==1){
                if(string[i].isEmpty()){
                    b2.setText("0", TextView.BufferType.EDITABLE);
                }
                else{
                    b2.setText(string[1], TextView.BufferType.EDITABLE);
                }
            }
            else if(i==2){
                if(string[i].isEmpty()){
                    b3.setText("0", TextView.BufferType.EDITABLE);
                }
                else{
                    b3.setText(string[2], TextView.BufferType.EDITABLE);
                }
            }
            else if(i==3){
                if(string[i].isEmpty()){
                    b4.setText("0", TextView.BufferType.EDITABLE);
                }
                else{
                    b4.setText(string[3], TextView.BufferType.EDITABLE);
                }
            }
            else if(i==4){
                if(string[i].isEmpty()){
                    b5.setText("0", TextView.BufferType.EDITABLE);
                }
                else{
                    b5.setText(string[4], TextView.BufferType.EDITABLE);
                }
            }
            else if(i==5){
                if(string[i].isEmpty()){
                    b6.setText("0", TextView.BufferType.EDITABLE);
                }
                else{
                    b6.setText(string[5], TextView.BufferType.EDITABLE);
                }
            }
        }
    }

    private void copyToClickBoard(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied to clipboard!", Toast.LENGTH_SHORT).show();
    }
}