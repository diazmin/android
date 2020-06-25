package com.example.myapplication_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText nm1,nm2;
    private TextView res;
    private RadioButton sm,rs,mul,dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm1 = (EditText)findViewById(R.id.num1);
        nm2 = (EditText)findViewById(R.id.num2);
        res = (TextView)findViewById(R.id.rest);

        sm = (RadioButton)findViewById((R.id.sum));
        rs = (RadioButton)findViewById(R.id.res);
        mul = (RadioButton)findViewById(R.id.mult);
        dv = (RadioButton)findViewById(R.id.div);



    }

    public void calcular(View v){

        int n1 = Integer.parseInt((nm1.getText().toString()));
        int n2 = Integer.parseInt((nm2.getText().toString()));
        float r = 0.0f;

        if(sm.isChecked()){
            r = n1 + n2;
        }else if(rs.isChecked()){
            r = n2 - n1;
        }else if(mul.isChecked()){
            r = n2 * n1;
        }else if(dv.isChecked()){
            if (n2 > n1){
                r = n2/n1;
            }else{
                r = n1/n2;
            }

        }

        res.setText(Float.toString(r));
    }
}
