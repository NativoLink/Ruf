package com.darkcode.ruf_012.Paciente;

/**
 * Created by NativoLink on 20/8/17.
 */

public class HistorialMed {


    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getEstado_salud() {
        return estado_salud;
    }

    public void setEstado_salud(String estado_salud) {
        this.estado_salud = estado_salud;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getBajo_tratamiento() {
        return bajo_tratamiento;
    }

    public void setBajo_tratamiento(String bajo_tratamiento) {
        this.bajo_tratamiento = bajo_tratamiento;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getTipo_alergia() {
        return tipo_alergia;
    }

    public void setTipo_alergia(String tipo_alergia) {
        this.tipo_alergia = tipo_alergia;
    }

    public String getEnfermedad_sistematica() {
        return enfermedad_sistematica;
    }

    public void setEnfermedad_sistematica(String enfermedad_sistematica) {
        this.enfermedad_sistematica = enfermedad_sistematica;
    }

    public String getTipo_enfermedad_sist() {
        return tipo_enfermedad_sist;
    }

    public void setTipo_enfermedad_sist(String tipo_enfermedad_sist) {
        this.tipo_enfermedad_sist = tipo_enfermedad_sist;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public int id_paciente;
    public String estado_salud;
    public String enfermedad;
    public String bajo_tratamiento;
    public String tratamiento;
    public String medico;
    public String alergico;
    public String tipo_alergia;
    public String enfermedad_sistematica;
    public String tipo_enfermedad_sist;
    public String fecha_reg;
}
