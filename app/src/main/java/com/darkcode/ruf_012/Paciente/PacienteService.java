package com.darkcode.ruf_012.Paciente;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by NativoLink on 15/12/15.
 */
public interface PacienteService {
    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Paciente/listPacientes.php")
    void getPacientes(Callback<List<Paciente>> callback);

    @Headers("Cache-Control: max-age=1")
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Paciente/listBuscarPaciente.php")
    void getPaciente(@Field("nombre") String nombre,Callback<List<Paciente>> callback);


    @GET("/WebSites/Tesis/Consulta/listConsulta.php")
    void getConsultas(@Query("id_paciente") int id_paciente, Callback<List<Consulta>> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Login/login.php")
    public void postLogin(@Field("username") String username, @Field("password") String password, Callback<Paciente> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Consulta/regConsulta.php")
    public void postRegConsulta(
            @Field("id_p_tratamiento") String id_p_tratamiento,
            @Field("estado") String estado,
            @Field("descripcion") String descripcion,
            Callback<String> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Paciente/regPaciente.php")
    public void regPaciente(
            @Field("nombre") String nombre,
            @Field("direccion") String direccion,
            @Field("telefono") String telefono,
            /*
            @Field("edad") String edad,
            @Field("sexo") String sexo,
            @Field("estado_civil") String estado_civil,
            @Field("ocupacion") String ocupacion,
            @Field("direccion_oc") String direccion_oc,
            @Field("telefono_oc") String telefono_oc,
            @Field("responsable") String responsable,
            @Field("fecha") String fecha,
            @Field("estado_salud") String estado_salud,
            @Field("enfermedad") String enfermedad,
            @Field("bajo_tratamiento") String bajo_tratamiento,
            @Field("tratamiento") String tratamiento,
            @Field("medico") String medico,
            @Field("alergia") String alergia,
            @Field("enfermedad_sistematica") String enfermedad_sistematica,*/
            Callback<String> callback);

    @Headers("Cache-Control: max-age=1")
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Paciente/ActualizarCuenta.php")
    public void ActualizarCuenta(@Field("id_cliente") int id_cliente,
                                 @Field("clave_nueva") String clave_nueva,
                                 @Field("clave_vieja") String clave_vieja, Callback<String> callback);




}
