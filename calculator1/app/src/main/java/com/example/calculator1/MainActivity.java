package com.example.calculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private EditText E;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        E = findViewById(R.id.enter);
        E.setShowSoftInputOnFocus(false);
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(E.getText().toString()))
                    E.setText("");
            }
        });
    }
    private void updateText(String s){
        String os = E.getText().toString();
        int curPos = E.getSelectionStart();
        String ls = os.substring(0 , curPos);
        String rs = os.substring(curPos);
        if(getString(R.string.display).equals(E.getText().toString())) {
            E.setText(s);
            E.setSelection(curPos + 1);
        }
        else {
            E.setText(String.format("%s%s%s", ls, s, rs));
            E.setSelection(curPos + 1);
        }


    }
    public void zeroBtn(View view){
        updateText("0");

    }
    public void oneBtn(View view){
        updateText("1");

    }
    public void twoBtn(View view){
        updateText("2");

    }
    public void threeBtn(View view){
        updateText("3");

    }
    public void fourBtn(View view){
        updateText("4");

    }
    public void fiveBtn(View view){
        updateText("5");

    }
    public void sixBtn(View view){
        updateText("6");

    }
    public void sevenBtn(View view){
        updateText("7");

    }
    public void eightBtn(View view){
        updateText("8");

    }
    public void nineBtn(View view){
        updateText("9");

    }
    public void addBtn(View view){
        updateText("+");

    }
    public void subBtn(View view){
        updateText("-");

    }
    public void mulBtn(View view){
        updateText("×");

    }
    public void divBtn(View view){
        updateText("÷");

    }
    public void eqBtn(View view){
        String userExpr = E.getText().toString();
        userExpr = userExpr.replaceAll("÷" , "/");
        userExpr = userExpr.replaceAll("×" , "*");

        Expression e = new Expression(userExpr);
        String result = String.valueOf(e.calculate());
        E.setText(result);
        E.setSelection(result.length());

    }
    public void pointBtn(View view){
        updateText(".");

    }
    public void plusMinusBtn(View view){
        updateText("-");

    }
    public void clearBtn(View view){
        E.setText("");

    }
    public void parenBtn(View view){
        int curPos = E.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = E.getText().length();
        int i;
        for(i = 0; i < curPos; i++){
            if(E.getText().toString().substring(i , i+1).equals("(")){
                openPar += 1;
            }
            if(E.getText().toString().substring(i , i+1).equals(")")){
                closePar += 1;
            }
        }
        if(openPar == closePar || E.getText().toString().substring(textLen-1 , textLen).equals("(")){
            updateText("(");
            E.setSelection(curPos + 1);
        }
        else if(closePar < openPar && ! E.getText().toString().substring(textLen-1 , textLen).equals("(")){
            updateText(")");
            E.setSelection(curPos + 1);
        }


    }
    public void powerBtn(View view){
        updateText("^");

    }
    public void backBtn(View view){
        int curPos = E.getSelectionStart();
        int textLen = E.getText().length();
        if(curPos != 0 && textLen != 0){
            SpannableStringBuilder sl = (SpannableStringBuilder) E.getText();
            sl.replace(curPos-1 , curPos , "");
            E.setText(sl);
            E.setSelection(curPos-1);
        }

    }
}