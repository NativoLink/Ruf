package com.darkcode.ruf_012.Tratamientos;

/**
 * Created by NativoLink on 22/2/17.
 */

public class Tratamiento {


    int id_tratamiento;
    String nombre;
    String tipo;

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
}
