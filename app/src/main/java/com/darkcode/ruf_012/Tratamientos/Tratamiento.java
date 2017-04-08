package com.darkcode.ruf_012.Tratamientos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NativoLink on 22/2/17.
 */

public class Tratamiento {

    public Tratamiento(Parcel input){

    }


    int id_tratamiento;
    String nombre;
    String tipo;
    String costo;
    int id_plan;

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public int getId_tratamiento() {
        return id_tratamiento;
    }

    public void setId_tratamiento(int id_tratamiento) {
        this.id_tratamiento = id_tratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    Tratamiento(String nombre){
        setNombre(nombre);
    }

    public static final Parcelable.Creator<Tratamiento>CREATOR
            = new Parcelable.Creator<Tratamiento>() {

        @Override
        public Tratamiento createFromParcel(Parcel source) {
            return new Tratamiento(source);
        }

        @Override
        public Tratamiento[] newArray(int size) {
            return new Tratamiento[size];
        }
    };
}
