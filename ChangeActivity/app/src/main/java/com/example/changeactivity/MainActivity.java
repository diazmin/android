package com.example.changeactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    Spinner sp;
    CheckBox cbDolar, cbEuro, cbYen;

    float resultadoDolar = 0.00f;
    float resultadoEuro = 0.00f;
    float resultadoYen = 0.00f;
    String checks = "";
    String stDolar = " ";
    String stEuro = " ";
    String stYen = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = findViewById(R.id.spinner);

        //Creando un ArrayAdapter, el arreglo de cadenas y el layout del Spinner
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.cantidades, android.R.layout.simple_spinner_item);

        //Especificando el comportamiento del Sppiner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Aplicando el Adapter al spinner
        sp.setAdapter(dataAdapter);

        cbDolar = findViewById((R.id.checkBox));
        cbEuro = findViewById((R.id.checkBox2));
        cbYen = findViewById((R.id.checkBox3));


    }


    public void change(View v) {
        Intent in = new Intent(this, ChangeMoney.class);

        String cantidad = sp.getSelectedItem().toString();

        if(cbDolar.isChecked() && cbEuro.isChecked() && cbYen.isChecked())
        {
            resultadoDolar = (float) (19.52 * Float.parseFloat(cantidad));
            resultadoEuro = (float) (21.43 * Float.parseFloat(cantidad));
            resultadoYen = (float) (0.18 * Float.parseFloat(cantidad));
            checks = "1";
        }
        else if(cbDolar.isChecked() && cbEuro.isChecked())
        {
            resultadoDolar = (float) (19.52 * Float.parseFloat(cantidad));
            resultadoEuro = (float) (21.43 * Float.parseFloat(cantidad));
            checks = "2";
        }
        else if(cbDolar.isChecked() && cbYen.isChecked())
        {
            resultadoDolar = (float) (19.52 * Float.parseFloat(cantidad));
            resultadoYen = (float) (0.18 * Float.parseFloat(cantidad));
            checks = "3";
        }
        else if(cbEuro.isChecked() && cbYen.isChecked())
        {
            resultadoEuro = (float) (21.43 * Float.parseFloat(cantidad));
            resultadoYen = (float) (0.18 * Float.parseFloat(cantidad));
            checks = "4";
        }
        else if(cbDolar.isChecked())
        {
            checks = "5";
            resultadoDolar = (float) (21.43 * Float.parseFloat(cantidad));

        }

        else if(cbEuro.isChecked())
        {
            resultadoEuro = (float) (21.43 * Float.parseFloat(cantidad));
            checks = "6";
        }
        else if(cbYen.isChecked())
        {
            resultadoYen = (float) (0.18 * Float.parseFloat(cantidad));
            checks = "7";
        }

        stDolar = Float.toString(resultadoDolar);
        stEuro = Float.toString(resultadoEuro);
        stYen = Float.toString(resultadoYen);


        in.putExtra("dolar", stDolar);
        in.putExtra("euro", stEuro);
        in.putExtra( "yen", stYen);
        in.putExtra("checks2", checks);

        startActivity(in);

    }
}
