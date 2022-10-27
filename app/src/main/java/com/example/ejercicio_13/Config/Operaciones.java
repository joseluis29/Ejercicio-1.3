package com.example.ejercicio_13.Config;

public class Operaciones {
    public static final String NameDatabase = "PM01DB";

    public static final String tablapersonas = "personas";

    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    public static final String createTablePersonas = "CREATE TABLE " + tablapersonas +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String dropTablePersonas = "DROP TABLE IF EXIST" + tablapersonas;
}
