package com.darkcode.ruf_012.Doctor;


import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

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
                          @Field("especialidad") String especialidad,
                          Callback<String> callback);
}
