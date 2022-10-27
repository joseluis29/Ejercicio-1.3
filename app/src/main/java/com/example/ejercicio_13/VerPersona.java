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

public class VerPersona extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo, direccion, codigo;
    Button botonActualizar, botonEliminar, botonRegresar;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_persona);

        codigo = (EditText) findViewById(R.id.txtCodigo);
        nombres = (EditText) findViewById(R.id.eptxtnombre);
        apellidos = (EditText) findViewById(R.id.eptxtapellidos);
        edad = (EditText) findViewById(R.id.eptxtedad);
        correo = (EditText) findViewById(R.id.eptxtcorreo);
        direccion = (EditText) findViewById(R.id.eptxtdireccion);

        botonActualizar = (Button) findViewById(R.id.btnActualizar);
        botonEliminar = (Button) findViewById(R.id.btnEliminar);
        botonRegresar = (Button) findViewById(R.id.btnRegresar) ;

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edad.setText(getIntent().getStringExtra("edad"));
        correo.setText(getIntent().getStringExtra("correo"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarPersona();

            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPersona();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Listar.class);
                startActivity(intent);
            }
        });
    }

    private void actualizarPersona() {
        Conexion conexion = new Conexion(this, Operaciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put(Operaciones.nombres, nombres.getText().toString());
        valores.put(Operaciones.apellidos, apellidos.getText().toString());
        valores.put(Operaciones.edad, edad.getText().toString());
        valores.put(Operaciones.correo, correo.getText().toString());
        valores.put(Operaciones.direccion, direccion.getText().toString());

        db.update(Operaciones.tablapersonas, valores , Operaciones.id +" = "+obtenerCodigo, null);
        db.close();

        Intent intent = new Intent(getApplicationContext(),Listar.class);
        startActivity(intent);

    }

    private void eliminarPersona() {
        Conexion conexion = new Conexion(this, Operaciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        db.delete(Operaciones.tablapersonas,Operaciones.id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();
        db.close();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}