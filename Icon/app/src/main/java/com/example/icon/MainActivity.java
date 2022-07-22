package com.example.icon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button  button;
    private Button  button2;
    private Button  button3;
    private Button  button4;
    private EditText editTextNumber3;
    private EditText editTextNumber4;
    private EditText editTextNumber5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextNumber5 = findViewById(R.id.editTextNumber5);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double num1 = Double.parsedouble(editTextNumber3.getText().toString());
//                double num2 = Double.parsedouble(editTextNumber4.getText().toString());
//                double sum = num1 + num2;
//                editTextNumber5.setText("" + sum);
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double num3 = Double.parsedouble(editTextNumber3.getText().toString());
//                double num4 = Double.parsedouble(editTextNumber4.getText().toString());
//                double minus = num3 - num4;
//                editTextNumber5.setText("" + minus);
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double num5 = Double.parsedouble(editTextNumber3.getText().toString());
//                double num6 = Double.parsedouble(editTextNumber4.getText().toString());
//                double mult = num5 * num6;
//                editTextNumber5.setText("" + mult);
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double num7 = Double.parsedouble(editTextNumber3.getText().toString());
//                double num8 = Double.parsedouble(editTextNumber4.getText().toString());
//                double divide = num7 / num8;
//                editTextNumber5.setText("" + divide);
//            }
//        });
//
   }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                double num1 = Double.parseDouble(editTextNumber3.getText().toString());
                double num2 = Double.parseDouble(editTextNumber4.getText().toString());
                double sum = num1 + num2;
                editTextNumber5.setText("" + String.format("%.4f" , sum));
                break;
            case R.id.button2:
                double num3 = Double.parseDouble(editTextNumber3.getText().toString());
                double num4 = Double.parseDouble(editTextNumber4.getText().toString());
                double minus = num3 - num4;
                editTextNumber5.setText("" + String.format("%.4f" , minus));
                break;
            case R.id.button3:
                double num5 = Double.parseDouble(editTextNumber3.getText().toString());
                double num6 = Double.parseDouble(editTextNumber4.getText().toString());
                double mult = num5 * num6;
                editTextNumber5.setText("" + String.format("%.4f" , mult));
                break;
            case R.id.button4:
                double num7 = Double.parseDouble(editTextNumber3.getText().toString());
                double num8 = Double.parseDouble(editTextNumber4.getText().toString());
                double div = num7 / num8;
                editTextNumber5.setText("" + String.format("%.4f" , div));
                break;
        }
    }
}
// To round off in double , use decimalformat , String.format() , bigdecimal