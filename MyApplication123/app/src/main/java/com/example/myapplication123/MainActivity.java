package com.example.myapplication123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txt1, txt2, txtR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txt1 = (EditText) findViewById(R.id.editText1);
        txt2 = (EditText) findViewById(R.id.editText2);
        txtR = (EditText) findViewById(R.id.editTextR);
    }

    public void sumar(View v){
        int val1 = Integer.parseInt(txt1.getText().toString());
        int val2 = Integer.parseInt(txt2.getText().toString());
        int suma = val1 + val2;

        txtR.setText(Integer.toString(suma));
    }
}
