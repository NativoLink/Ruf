package com.darkcode.ruf_012.Diagrama;

/**
 * Created by NativoLink on 2/3/17.
 */

public class DienteDB {

    int id_diente;
    int posDiente;
    String estadoDB;
    String area;
    String cuadrante;

    public int getId_diente() {
        return id_diente;
    }

    public void setId_diente(int id_diente) {
        this.id_diente = id_diente;
    }

    public int getPosDiente() {
        return posDiente;
    }

    public void setPosDiente(int posDiente) {
        this.posDiente = posDiente;
    }

    public String getEstadoDB() {
        return estadoDB;
    }

    public void setEstadoDB(String estadoDB) {
        this.estadoDB = estadoDB;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCuadrante() {
        return cuadrante;
    }

    public void setCuadrante(String cuadrante) {
        this.cuadrante = cuadrante;
    }
}
