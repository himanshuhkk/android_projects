package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    double first , second ;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Button b0,badd,bsub,bmul,bdiv,beq,bdot,bb,bc,bper,bpm;
    EditText enter;
    boolean add , sub , mul , div , per , pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.one);
        b2=findViewById(R.id.two);
        b3=findViewById(R.id.three);
        b4=findViewById(R.id.four);
        b5=findViewById(R.id.five);
        b6=findViewById(R.id.six);
        b7=findViewById(R.id.seven);
        b8=findViewById(R.id.eight);
        b9=findViewById(R.id.nine);
        b0=findViewById(R.id.zero);
        bc=findViewById(R.id.clear);
        bper=findViewById(R.id.percentage);
        bdiv=findViewById(R.id.divide);
        bb=findViewById(R.id.back);
        bmul=findViewById(R.id.mul);
        bsub=findViewById(R.id.minus);
        badd =findViewById(R.id.add);
        beq=findViewById(R.id.equal);
        bdot=findViewById(R.id.dot);
        bpm=findViewById(R.id.plusminus);
        enter=findViewById(R.id.enter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "1");
//                int curpos = enter.getSelectionStart();
//                enter.setSelection(curpos + 1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + "0");
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText("");
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer s = new StringBuffer(enter.getText());
                if(s.length()>=1){
                    s.deleteCharAt(s.length()-1);
               }
                enter.setText(s);
            }
        });
        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter.setText(enter.getText() + ".");
            }
        });
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = Double.parseDouble(enter.getText().toString());
                add=true;
                enter.setText("");
            }
        });
        bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = Double.parseDouble(enter.getText().toString());
                sub=true;
                enter.setText("");
            }
        });
        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = Double.parseDouble(enter.getText().toString());
                div=true;
                enter.setText("");
            }
        });
        bmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = Double.parseDouble(enter.getText().toString());
                mul=true;
                enter.setText("");
            }
        });
        bpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number = Double.parseDouble(enter.getText().toString());
                enter.setText(number * -1 + "");
            }
        });
        bper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = Double.parseDouble(enter.getText().toString());
                per=true;
                enter.setText("");
            }
        });
        beq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(per==true){
                    first=first/100;
                    enter.setText(first + "");
                    per=false;
                }
                second=Double.parseDouble(enter.getText().toString());
                if(add==true){
                    enter.setText(first + second + "");
                    add=false;
                }
                if(sub==true){
                    enter.setText(first - second + "");
                    sub=false;
                }
                if(mul==true){
                    enter.setText(first * second + "");
                    mul=false;
                }
                if(div==true){
                    enter.setText(first / second + "");
                    div=false;
                }

            }
        });


    }
}