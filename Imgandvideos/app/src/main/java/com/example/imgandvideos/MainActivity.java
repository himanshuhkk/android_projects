package com.example.imgandvideos;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;
    Button bnimg , bnvid , bnfs;
    ImageView image1;
    LinearLayout llvid , llimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        bnimg = findViewById(R.id.bnimg);
        bnvid = findViewById(R.id.bnvid);
        bnfs = findViewById(R.id.bnfulvid);
        image1 = findViewById(R.id.image1);
        llimg = findViewById(R.id.llimage);
        llvid = findViewById(R.id.llvideo);
        bnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llimg.setVisibility(View.VISIBLE);
                llvid.setVisibility(View.GONE);
            }
        });
        bnvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llimg.setVisibility(View.GONE);
                llvid.setVisibility(View.VISIBLE);
                ShowNormalVideo();
            }
        });
        bnfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llimg.setVisibility(View.GONE);
                llvid.setVisibility(View.VISIBLE);
                ShowFullScreenVideo();
            }
        });
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bird));

    }
    private void ShowNormalVideo(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params =
                (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
        params.width = 800;
        params.height = 500;
        params.leftMargin = 0;
        videoView.setLayoutParams(params);
        videoView.start();
    }
    private void ShowFullScreenVideo(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params =
                (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        videoView.setLayoutParams(params);
        videoView.start();
    }
}