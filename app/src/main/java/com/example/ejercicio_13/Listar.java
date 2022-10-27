package com.example.ejercicio_13;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_13.Config.Conexion;
import com.example.ejercicio_13.Config.Operaciones;
import com.example.ejercicio_13.Config.Persona;

import java.util.ArrayList;


public class Listar extends AppCompatActivity {

    Conexion conexion;
    ListView lista;
    ArrayList<Persona> listaPersona;
    ArrayList <String> ArregloPersona;
    Button Volver;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lista = (ListView) findViewById(R.id.listar);

        conexion = new Conexion(this, Operaciones.NameDatabase,null,1);

        obtenerlistaPeersona();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersona);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ObtenerPersona(i);

            }
        });

        Volver = (Button) findViewById(R.id.btRegresar);
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ObtenerPersona(int id) {
        Persona persona = listaPersona.get(id);

        Intent intent = new Intent(getApplicationContext(),VerPersona.class);
        intent.putExtra("codigo", persona.getId()+"");
        intent.putExtra("nombre", persona.getNombres());
        intent.putExtra("apellidos", persona.getApellidos());
        intent.putExtra("edad", persona.getEdad()+"");
        intent.putExtra("correo", persona.getCorreo());
        intent.putExtra("direccion", persona.getDireccion());

        startActivity(intent);
    }

    private void obtenerlistaPeersona() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Persona list_person = null;
        listaPersona = new ArrayList<Persona>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Operaciones.tablapersonas, null);

        while (cursor.moveToNext())
        {
            list_person = new Persona();
            list_person.setId(cursor.getInt(0));
            list_person.setNombres(cursor.getString(1));
            list_person.setApellidos(cursor.getString(2));
            list_person.setEdad(cursor.getString(3));
            list_person.setCorreo(cursor.getString(4));
            list_person.setDireccion(cursor.getString(5));
            listaPersona.add(list_person);
        }
        cursor.close();
        llenarlista();
    }
    private void llenarlista()
    {
        ArregloPersona = new ArrayList<String>();

        for (int i=0; i<listaPersona.size();i++)
        {
            ArregloPersona.add(listaPersona.get(i).getId()+"|"+
                    listaPersona.get(i).getNombres()+"|"+
                    listaPersona.get(i).getApellidos()+"|"+
                    listaPersona.get(i).getEdad()+"|"+
                    listaPersona.get(i).getCorreo()+"|"+
                    listaPersona.get(i).getDireccion());

        }
    }
}