package com.darkcode.ruf_012.Paciente;

/**
 * Created by NativoLink on 8/3/17.
 */

public class Consulta {
    int id_consulta;
    int id_p_tratamiento;
    String estado;
    String fecha;
    String descripcion;

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_p_tratamiento() {
        return id_p_tratamiento;
    }

    public void setId_p_tratamiento(int id_p_tratamiento) {
        this.id_p_tratamiento = id_p_tratamiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
