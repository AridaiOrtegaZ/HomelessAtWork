package com.example.homeless.modelo;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Contrato implements Serializable {
    String nombreContratado, nombreDelContratador, domicilio, oficio;
    @Exclude
    private String key;

    public Contrato() {

    }

    public Contrato(String nombreContratado, String nombreDelContratador, String domicilio, String oficio) {
        this.nombreContratado = nombreContratado;
        this.nombreDelContratador = nombreDelContratador;
        this.domicilio = domicilio;
        this.oficio = oficio;
    }

    public String getNombreContratado() {
        return nombreContratado;
    }

    public void setNombreContratado(String nombreContratado) {
        this.nombreContratado = nombreContratado;
    }

    public String getNombreDelContratador() {
        return nombreDelContratador;
    }

    public void setNombreDelContratador(String nombreDelContratador) {
        this.nombreDelContratador = nombreDelContratador;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
