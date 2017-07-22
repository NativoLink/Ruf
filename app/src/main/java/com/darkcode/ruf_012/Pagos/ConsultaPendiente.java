package com.darkcode.ruf_012.Pagos;

/**
 * Created by usuario on 07/05/2017.
 */

public class ConsultaPendiente {



    int id_consulta;
    String fecha;
    int costo;
    int pendiente;
    String estado;
    String tipo = "";

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ConsultaPendiente(){

    }

    public ConsultaPendiente(int id_consulta, int costo, int pagoAbono){

        this.id_consulta = id_consulta;
        this.costo = costo;
        this.pagoAbono = pagoAbono;

    }



    //  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // VARIABLE PARA SABER CUANTO VA A PAGAR O ABONAR A UNA CONSULTA
        int pagoAbono;
    //  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


    public int getPagoAbono() {
        return pagoAbono;
    }

    public void setPagoAbono(int pagoAbono) {
        this.pagoAbono = pagoAbono;
    }


    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPendiente() {
        return pendiente;
    }

    public void setPendiente(int pendiente) {
        this.pendiente = pendiente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
