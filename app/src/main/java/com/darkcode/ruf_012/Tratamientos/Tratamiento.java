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
    int costo;
    int id_plan;
    int id_p_tratamiento;
    int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_p_tratamiento() {
        return id_p_tratamiento;
    }

    public void setId_p_tratamiento(int id_p_tratamiento) {
        this.id_p_tratamiento = id_p_tratamiento;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
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
