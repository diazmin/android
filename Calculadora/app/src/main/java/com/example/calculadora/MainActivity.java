package com.example.calculadora;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    public EditText txt1, txt2, txtR;
    public RadioButton rbSum, rbRes, rbMult, rbDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (EditText)findViewById(R.id.num1);
        txt2 = (EditText)findViewById(R.id.num2);
        txtR = (EditText) findViewById(R.id.res);

        rbSum = (RadioButton)findViewById((R.id.sumar));
        rbRes = (RadioButton)findViewById(R.id.restar);
        rbMult = (RadioButton)findViewById(R.id.mult);
        rbDiv = (RadioButton)findViewById(R.id.div);

    }

    public void calcular1(View v){

        int n1 = Integer.parseInt((txt1.getText().toString()));
        int n2 = Integer.parseInt((txt2.getText().toString()));
        float r = 0.0f;

        if(rbSum.isChecked()){
            r = n1 + n2;
        }else if(rbRes.isChecked()){
            r = n1 - n2;
        }else if(rbMult.isChecked()){
            r = n2 * n1;
        }else if(rbDiv.isChecked()){
            if (n2 > n1){
                r = n2/n1;
            }else{
                r = n1/n2;
            }

        }

        txtR.setText(Float.toString(r));
    }



}
