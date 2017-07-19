package com.darkcode.ruf_012.Tratamientos;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
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

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Consulta/listDetalleConsulta.php")
    void getDetalleConsulta(@Query("id_paciente") int id_paciente,
                            @Query("id_consulta") int id_consulta, Callback<List<Tratamiento>> callback);


    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Tratamiento/listTratamientosDeUnPlan.php")
    void getTratsDeUnPlan(@Query("id_paciente") int id_paciente,
                          @Query("id_plan") int id_plan,Callback<List<Tratamiento>> callback);


    @Headers("Cache-Control: max-age=1")
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Tratamiento/regPlanTratamiento.php")
    void regTratsDeUnPlan(@Field("id_plan") int id_plan,
                          @Field("id_tratamiento") int id_tratamiento,
                          @Field("cantidad") int cantidad,
                          @Field("costo") int costo,
                          @Field("descripcion") String descripcion,
                          Callback<String> callback);


    @Headers("Cache-Control: max-age=1")
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Tratamiento/regTratamientos.php")
    void regTrat(@Field("nombre") String nombre,
                  @Field("tipo") String tipo,
                  Callback<String> callback);

    @Headers("Cache-Control: max-age=1")
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Tratamiento/regPlan.php")
    void regPlan(@Field("id_paciente") int id_paciente,
                  @Field("descripcion") String descripcion,
                          Callback<Integer> callback);


}
