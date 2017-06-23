package com.darkcode.ruf_012.Diagrama;

/**
 * Created by NativoLink on 18/6/17.
 */

public class Diagrama {

    String fecha_reg;

    int id_paciente;
    int dientes_tratados;
    int id_consulta;

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getDientes_tratados() {
        return dientes_tratados;
    }

    public void setDientes_tratados(int dientes_tratados) {
        this.dientes_tratados = dientes_tratados;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }


}
