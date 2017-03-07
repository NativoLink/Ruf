package com.darkcode.ruf_012.Tratamientos;


import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by NativoLink on 22/2/17.
 */

public interface TratamientoService {

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Tratamiento/listTratamientos.php")
    void getTratamientos(Callback<List<Tratamiento>> callback);


    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Tratamiento/listPlanesPaciente.php")
    void getPlanes(@Query("id_paciente") int id_paciente, Callback<List<Plan>> callback);


}
