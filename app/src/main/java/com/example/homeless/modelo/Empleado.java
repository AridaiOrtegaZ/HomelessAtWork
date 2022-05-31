package com.example.homeless.modelo;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Empleado implements Serializable {
    private String nombre,edad,ubicacion,url;
    @Exclude
    private String key;

    public Empleado(){}

    public Empleado(String nombre, String edad, String ubicacion, String url) {
        this.nombre = nombre;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
