package com.darkcode.ruf_012.Doctor;


import com.darkcode.ruf_012.Paciente.Especialidad;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by NativoLink on 15/2/17.
 */

public interface DoctorService {
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Login/login.php")
    public void postLogin(@Field("username") String username, @Field("password") String password, Callback<Doctor> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Doctor/regDoctor.php")
    public void regDoctor(@Field("nombre") String nombre,
                          @Field("usuario") String usuario,
                          @Field("clave") String clave,
                          @Field("direccion") String direccion,
                          @Field("telefono") String telefono,
                          @Field("cedula") String cedula,
                          @Field("especialidad") int especialidad,
                          Callback<String> callback);

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Doctor/listEspecialidad.php")
    void getEspecialidaddes(Callback<List<Especialidad>> callback);

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Doctor/listDoctores.php")
    void getDoctores(Callback<List<Doctor>> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Doctor/listBuscarDoctor.php")
    void getBuscarDoctor(@Field("nombre") String nombre, Callback<List<Doctor>> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Doctor/regEspecialidad.php")
    void regEspecialidad(@Field("nombre") String nombre, Callback<String> callback);
}
