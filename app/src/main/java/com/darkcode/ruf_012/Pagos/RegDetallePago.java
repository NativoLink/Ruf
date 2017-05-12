package com.darkcode.ruf_012.Pagos;

/**
 * Created by usuario on 12/05/2017.
 */

public class RegDetallePago {

    int id_consulta;
    int id_pago;
    int pago;
    int pendiente;
    //String estado; no se si se pone
    // reg pago
    int total;
    String nota;
    String estadop;



    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;

    }
    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public int getPendiente() {
        return pendiente;
    }

    public void setPendiente(int pendiente) {
        this.pendiente = pendiente;
    }

    //regpagos


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getEstadop() {
        return estadop;
    }

    public void setEstadop(String estadop) {
        this.estadop = estadop;
    }
}
