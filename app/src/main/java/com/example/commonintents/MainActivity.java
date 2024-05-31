package com.example.commonintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button initiateCallButton = findViewById(R.id.initiate_call_button);
        Button capturePictureButton = findViewById(R.id.capture_picture_button);
        Button playMediaButton = findViewById(R.id.play_media_button);


        initiateCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("0818297676");
            }
        });

        capturePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto("newphoto");
            }
        });


        playMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMedia();
            }
        });
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    
    static final Uri locationForPhotos = Uri.parse("/Internal storage/DCIM/Camera/");

    public void capturePhoto(String targetFilename) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.withAppendedPath(locationForPhotos, targetFilename));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }


    

    private void playMedia() {
        Uri uri = Uri.parse("/Internal storage/Download/About Love.mp3");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "audio/*"); // or "audio/*" for audio files
        startActivity(intent);
    }

}
