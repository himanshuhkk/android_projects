package com.example.graphics_primitives;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating a Bitmap
        Bitmap bg = Bitmap.createBitmap( 720 ,  1280 , Bitmap.Config.ARGB_4444);
        //setting the bitmap as background for the image view
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageBitmap(bg);
        //creating the canvas object
        Canvas canvas = new Canvas(bg);
        //creating the paint object and set its color and size
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setTextSize(50);
        //To draw a circle
        canvas.drawText("circle" , 170 , 600 , paint1);
        canvas.drawCircle(225 , 450 , 100 , paint);
        //To draw a square
        paint.setColor(Color.GRAY);
        canvas.drawText("square" , 170 , 1100 , paint1);
        canvas.drawRect(150 , 850 , 350 , 1050 , paint);
        //To draw a rectangle
        paint.setColor(Color.MAGENTA);
        canvas.drawText("Rectangle" , 470 , 610 , paint1);
        canvas.drawRect(500 , 360 , 650 , 560 , paint);
        //To draw a line
        paint.setColor(Color.RED);
        canvas.drawText("Line" , 480 , 800 , paint1);
        canvas.drawLine(550 , 850 , 550 , 1050 , paint);

        //To draw an arc and face
        paint.setColor(Color.YELLOW);
        canvas.drawText("Arc and Face" , 70 , 200 , paint1);
        RectF rectF = new RectF(50 , 220 , 300 , 450);
        rectF = new RectF(150 , 220 , 300 , 550);
        canvas.drawArc(rectF , 180 , 180 , false, paint);
        paint.setColor(Color.DKGRAY);
        canvas.drawCircle(190 , 290 , 10 , paint);
        canvas.drawCircle(250 , 290 , 10 , paint);
        canvas.drawLine(220 , 300 , 220 , 370 ,  paint);
        canvas.drawLine(180 , 370 , 270 , 370 ,  paint);

        //ROTATE TEXT
        paint.setColor(Color.BLUE);
        canvas.rotate(-50);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Rotate Text" , 60 , 550 , paint);





    }
}