package com.example.changeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChangeMoney extends AppCompatActivity {
    TextView tv;
    String dolar1, euro1, yen1, checks1;

    //Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_money);
        convertir();

    }


    public void convertir()
    {
        checks1 = getIntent().getStringExtra("checks2");

        if(checks1.equals("1"))
        {
            dolar1 = getIntent().getStringExtra("dolar");
            euro1 = getIntent().getStringExtra("euro");
            yen1 = getIntent().getStringExtra("yen");

            tv = findViewById(R.id.resultado);;
            tv.setText("Dolar: " + dolar1 + " pesos\n" + "Euro: " + euro1 + " pesos\n" + "Yen: " + yen1 + " pesos");
        }
         if(checks1.equals("2"))
        {
            dolar1 = getIntent().getStringExtra("dolar");
            euro1 = getIntent().getStringExtra("euro");

            tv = findViewById(R.id.resultado);;
            tv.setText("Dolar: " + dolar1 + " pesos\n" + "Euro: " + euro1 + " pesos\n");
        }
        else if (checks1.equals("3"))
        {
            dolar1 = getIntent().getStringExtra("dolar");
            yen1 = getIntent().getStringExtra("yen");

            tv = findViewById(R.id.resultado);;
            tv.setText("Dolar: " + dolar1 + " pesos\n" + "Yen: " + yen1 + " pesos");
        }
        else if (checks1.equals("4"))
        {
            euro1 = getIntent().getStringExtra("euro");
            yen1 = getIntent().getStringExtra("yen");

            tv = findViewById(R.id.resultado);;
            tv.setText("Euro: " + euro1 + " pesos\n" + "Yen: " + yen1 + " pesos");
        }
        else if (checks1.equals("5"))
        {
            dolar1 = getIntent().getStringExtra("dolar");
            tv = findViewById(R.id.resultado);;
            tv.setText("Dolar: " + dolar1 + " pesos\n");
        }
        else if (checks1.equals("6"))
        {
            euro1 = getIntent().getStringExtra("euro");
            tv = findViewById(R.id.resultado);;
            tv.setText("Euro: " + euro1 + " pesos\n");
        }
        else if (checks1.equals("7"))
        {
            yen1 = getIntent().getStringExtra("yen");
            tv = findViewById(R.id.resultado);;
            tv.setText("Yen: " + yen1 + " pesos");
        }
        else if(checks1.equals(""))
        {
            tv = findViewById(R.id.resultado);;
            tv.setText("No hay selección de opción alguna.");
        }

    }

    public void change(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
