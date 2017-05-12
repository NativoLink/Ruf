package com.darkcode.ruf_012.Pagos;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by usuario on 07/05/2017.
 */

public interface PagoService {
    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/listConsultasPendientes.php")
    void getPagos(@Query("id_paciente") int id_paciente, Callback<List<ConsultasPendientes>> callback);

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/regDetallePago.php")
    void getDetallePago(@Field("id_consulta") int id_consulta,
                        @Field("id_pago") int id_pago,
                        @Field("pago") int pago,
                        @Field("pendiente") int pendiente,
                        @Field("estado") String estado,
                        Callback<List<RegDetallePago>> callback);

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Pagos/regPagos.php")
    void getRegPagos(@Field("total") int total,
                     @Field("nota") String nota,
                     @Field("estadop") String estadop,
                     Callback<List<RegDetallePago>> callback);// no si crear una clase para esto o colocarlo en reg detalle pago

}
