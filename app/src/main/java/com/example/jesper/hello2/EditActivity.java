package com.example.jesper.hello2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnCancel;
    Button btnPicture;
    ImageView mImageView;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnPicture = (Button) findViewById(R.id.takePicture);
        mImageView = (ImageView) findViewById(R.id.imageView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView field = (TextView) findViewById(R.id.editText);
                String str = String.valueOf(field.getText());

                if(str != null && !str.isEmpty()){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", str);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             Intent returnIntent = new Intent();
                setResult(EditActivity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
}
