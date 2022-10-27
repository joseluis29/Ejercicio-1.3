package com.example.ejercicio_13;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_13.Config.Conexion;
import com.example.ejercicio_13.Config.Operaciones;

public class AgregarPersona extends AppCompatActivity {


    EditText editTextNombres, editTextApellidos, editTextEdad, editTextCorreo, editTextDireccion;
    Button botonGuardar, Volver;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        editTextNombres = (EditText) findViewById(R.id.txtnombres);
        editTextApellidos = (EditText) findViewById(R.id.txtApellidos);
        editTextEdad = (EditText) findViewById(R.id.txtedad);
        editTextCorreo = (EditText) findViewById(R.id.txtcorreo);
        editTextDireccion = (EditText) findViewById(R.id.txtDireccion);
        botonGuardar = (Button) findViewById(R.id.tbnGuardar);
        Volver = (Button) findViewById(R.id.btnRegresar1);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarPersonas();
            }
        });

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void agregarPersonas() {

        Conexion conexion = new Conexion(this, Operaciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Operaciones.nombres, editTextNombres.getText().toString());
        valores.put(Operaciones.apellidos, editTextApellidos.getText().toString());
        valores.put(Operaciones.edad, editTextEdad.getText().toString());
        valores.put(Operaciones.correo, editTextCorreo.getText().toString());
        valores.put(Operaciones.direccion, editTextDireccion.getText().toString());

        Long resultado = db.insert(Operaciones.tablapersonas, Operaciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registrado, Codigo " + resultado.toString()
                ,Toast.LENGTH_LONG).show();

        db.close();

        limpiarPantalla();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    private void limpiarPantalla() {
        editTextNombres.setText("");
        editTextApellidos.setText("");
        editTextEdad.setText("");
        editTextCorreo.setText("");
        editTextDireccion.setText("");
    }

}