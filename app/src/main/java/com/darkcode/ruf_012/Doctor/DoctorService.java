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
}
