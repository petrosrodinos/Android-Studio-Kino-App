package com.example.mykino;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

public class Photoactivity extends AppCompatActivity {

    public Button detectbtn;
    public ImageView imageview;
    public Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("ΚΙΝΟ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button snapbtn = findViewById(R.id.snapbtn);
        detectbtn = findViewById(R.id.detectbtn);
        imageview = findViewById(R.id.imageview);
        //txtview = findViewById(R.id.txtview);


        snapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        detectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectTxt();
            }
        });

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageview.setImageBitmap(imageBitmap);
        }
    }

    private void detectTxt(){
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>(){
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText){
                processTxt(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){

            }
        });
    }

    private void processTxt(FirebaseVisionText text){
        List<FirebaseVisionText.TextBlock> blocks = text.getTextBlocks();
        if(blocks.size()==0){
            Toast.makeText(Photoactivity.this,"No text detected",Toast.LENGTH_LONG).show();
            return;
        }
        for(FirebaseVisionText.TextBlock block : text.getTextBlocks()){
            //String txt = block.getText();
            //txtview.setText(txt);
        }
    }

}