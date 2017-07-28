package com.darkcode.ruf_012.Pagos;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by usuario on 07/05/2017.
 */

public interface PagoService {
    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/listConsultasPendientes.php")
    void getPagos(@Query("id_paciente") int id_paciente, Callback<List<ConsultaPendiente>> callback);


    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/listPagosPacientes.php")
    void getPagosR(@Query("id_paciente")int id_paciente,
                   @Query("fec_ini")String fec_ini,
                   @Query("fecha_fin")String fecha_fin,
                   Callback<List<PagoR>> callback);


    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/listDetallePago.php")
    void getDetallePagosR(@Query("id_paciente")int id_paciente,
                   @Query("id_pago")int fec_ini,
                   Callback<List<DetallePagoR>> callback);

//    @Headers("Cache-Control: max-age=1")
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Pagos/regDetalle.php")
    void regDetallePago(@Field("id_consulta") int id_consulta,
                        @Field("id_paciente") int id_paciente,
                        @Field("id_pago") int id_pago,
                        @Field("pago") int pago,
                        @Field("tipo") String tipo,
                        Callback<String> callback);

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Pagos/regPagos.php")
    void regPagos(@Field("total") int total,
                  @Field("nota") String nota,
                     Callback<String> callback);// no si crear una clase para esto o colocarlo en reg detalle pago


    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/numRecibo.php")
    void getNumRecibo(Callback<String> callback);// no si crear una clase para esto o colocarlo en reg detalle pago



}
