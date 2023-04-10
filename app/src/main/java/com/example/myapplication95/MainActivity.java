package com.example.myapplication95;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
    }

    //Metodo Guardar
    public void Guardar(View view){
        String nombre = et1.getText().toString();
        String contenido = et2.getText().toString();

        try {
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();

            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);

            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            crearArchivo.write(contenido);

            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this, "El archivo se ha guardado correctamente", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
        }catch (IOException e){
            Toast.makeText(this, "No se pudo guardar el archivo", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para consultar
    public void consultar(View view){
        String nombre = et1.getText().toString();

        try{
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            String contenidoCompleto = "";

            while (linea != null) {
                contenidoCompleto += linea + "\n";
                linea = leerArchivo.readLine();
            }
            leerArchivo.close();
            abrirArchivo.close();
            et2.setText(contenidoCompleto);

        }catch (IOException e){
            Toast.makeText(this, "No se ha encontrado el archivo", Toast.LENGTH_SHORT).show();
        }
    }
}