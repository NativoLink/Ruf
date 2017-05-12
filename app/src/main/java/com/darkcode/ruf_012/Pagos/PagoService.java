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
    
}
