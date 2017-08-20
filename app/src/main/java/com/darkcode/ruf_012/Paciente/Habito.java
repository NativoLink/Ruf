package com.darkcode.ruf_012.Paciente;

/**
 * Created by NativoLink on 20/8/17.
 */

public class Habito {
    public int id_paciente;
    public int num_cepillar;
    public String enjuague;
    public String hilo_dental;
    public String ultima_revision;
    public String tratamiento_realizado;
    public String fecha;

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getNum_cepillar() {
        return num_cepillar;
    }

    public void setNum_cepillar(int num_cepillar) {
        this.num_cepillar = num_cepillar;
    }

    public String getEnjuague() {
        return enjuague;
    }

    public void setEnjuague(String enjuague) {
        this.enjuague = enjuague;
    }

    public String getHilo_dental() {
        return hilo_dental;
    }

    public void setHilo_dental(String hilo_dental) {
        this.hilo_dental = hilo_dental;
    }

    public String getUltima_revision() {
        return ultima_revision;
    }

    public void setUltima_revision(String ultima_revision) {
        this.ultima_revision = ultima_revision;
    }

    public String getTratamiento_realizado() {
        return tratamiento_realizado;
    }

    public void setTratamiento_realizado(String tratamiento_realizado) {
        this.tratamiento_realizado = tratamiento_realizado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
