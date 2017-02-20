package com.darkcode.ruf_012;

/**
 * Created by NativoLink on 24/1/17.
 */
public interface Communicator {
    public void regPacienteComm(String nombre,String sexo,int edad,String direccion,String telefono,String ocupacion, String DireccionOcu, String TelefonoOcu,String Allegado);
    public  void editDiente(int posicionDiente,String pared,String estado);
    public void guardarDiagrama(int id_paciente);
}