package com.example.proyecto;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etNombre,etCel,etDir;
    Spinner spDia, spMes;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre= findViewById(R.id.edtNombre);
        etCel= findViewById(R.id.edtCelular);
        etDir= findViewById(R.id.edtDireccion);
        spDia = findViewById(R.id.spDia);
        spMes = findViewById(R.id.spMes);
        imagen = findViewById(R.id.img);


        //Creando un ArrayAdapter, el arreglo de cadenas y el layout del Spinner
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.mesList, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> dataAdapter2 = ArrayAdapter.createFromResource(this, R.array.diaList, android.R.layout.simple_spinner_item);

        //Especificando el comportamiento del Sppiner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Aplicando el Adapter al spinner
        spMes.setAdapter(dataAdapter);
        spDia.setAdapter(dataAdapter2);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public void foto(View v)
    {



        //Permisos para acceder a la cámara
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }


        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = new File(getExternalFilesDir(null), etNombre.getText().toString());
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
        startActivity(intento1);
        Bitmap bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null)+"/"+etNombre.getText().toString());
        imagen.setImageBitmap(bitmap1);

    }

    public void galeria(View v)
    {
        Bitmap bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null)+"/"+etNombre.getText().toString());
        imagen.setImageBitmap(bitmap1);
//
//        Intent intento1=new Intent(this,image.class);
//        startActivity(intento1);
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin;
        admin = new AdminSQLiteOpenHelper(this, "bdd", null, 1);
        SQLiteDatabase bdd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();
        String cel = etCel.getText().toString();
        String dir = etDir.getText().toString();
        String dia = spDia.getSelectedItem().toString();
        String mes = spMes.getSelectedItem().toString();

        if(!nombre.isEmpty() && !cel.isEmpty() && !dir.isEmpty() && !dia.isEmpty() && !mes.isEmpty())
        {
            ContentValues registros = new ContentValues();

            //Guardar valores
            registros.put("nombre", nombre);
            registros.put("celular", cel);
            registros.put("direccion", dir);
            registros.put("dia", dia);
            registros.put("mes", mes);

            //Insertarlos en la bdd
            bdd.insert("contactos", null, registros);

            //Cerrar bdd
            bdd.close();

            //Limpiar variables
            etNombre.setText("");
            etCel.setText("");
            etDir.setText("");
//            spDia.setAdapter(null);
//            spMes.setAdapter(null);



            Toast.makeText(this, "Contacto registrado, yay", Toast.LENGTH_SHORT).show();
            change(v);
        }
        else
        {
            Toast.makeText(this, "Ups, hay campos vacios", Toast.LENGTH_SHORT).show();
        }

    }

    public void change(View v)
    {
        //Cambiar de Activity
        Intent in = new Intent(this, Intro.class);
        startActivity(in);
    }

    public void buscar(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bdd", null, 1);
        SQLiteDatabase bdd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();

        if(!nombre.isEmpty())
        {
            Cursor fila = bdd.rawQuery("SELECT nombre, celular, direccion, dia, mes FROM contactos WHERE nombre = '" + nombre+ "'", null);

            if(fila.moveToFirst())
            {
                etNombre.setText(fila.getString(0));
                etCel.setText(fila.getString(1));
                etDir.setText(fila.getString(2));
                Bitmap bitmap1 = BitmapFactory.decodeFile(getExternalFilesDir(null)+"/"+fila.getString(0));
                imagen.setImageBitmap(bitmap1);

//              spDia.setText(fila.getString(3));
//              spDia.setText(fila.getString(3));
                //fila.close();

                bdd.close();
            }
            else
                {
                    Toast.makeText(this, "No existe ese contacto", Toast.LENGTH_SHORT).show();
                    //fila.close();
                    bdd.close();
                }
        }
        else
        {
            Toast.makeText(this, "Busca por nombre", Toast.LENGTH_SHORT).show();
        }

    }

    public void editar(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bdd", null, 1);
        SQLiteDatabase bdd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();
        String cel = etCel.getText().toString();
        String dir = etDir.getText().toString();
        String dia = spDia.getSelectedItem().toString();
        String mes = spMes.getSelectedItem().toString();

        if(!nombre.isEmpty() && !cel.isEmpty() && !dir.isEmpty() && !dia.isEmpty() && !mes.isEmpty())
        {
            ContentValues registros = new ContentValues();

            //Guardar valores
            registros.put("nombre", nombre);
            registros.put("celular", cel);
            registros.put("dir", dir);
            registros.put("dia", dia);
            registros.put("mes", mes);

            int cant = bdd.update("contactos", registros, "nombre = " + nombre , null);
            bdd.close();

            if(cant == 1)
            {
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
            }
            else
                {
                    Toast.makeText(this, "No existe contacto", Toast.LENGTH_SHORT).show();
                }
        }
        else
            {
                Toast.makeText(this, "Ups, hay campos vacíos", Toast.LENGTH_SHORT).show();
            }

    }

    public void eliminar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bdd", null, 1);
        SQLiteDatabase bdd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();
        if(!nombre.isEmpty())
        {
            int cant = bdd.delete("contactos", "nombre = '" + nombre + "'", null);
            bdd.close();

            //Limpiar variables
            etNombre.setText("");
            etCel.setText("");
            etDir.setText("");
            spDia.setAdapter(null);
            spMes.setAdapter(null);

            if(cant == 1)
            {
                Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "No existe contacto", Toast.LENGTH_SHORT).show();
            }

        }
        else
            {
                Toast.makeText(this, "Escribe el nombre del contacto a eliminar", Toast.LENGTH_SHORT).show();
            }
    }
}
