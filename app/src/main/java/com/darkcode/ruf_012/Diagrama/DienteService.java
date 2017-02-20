package com.darkcode.ruf_012.Diagrama;


import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by NativoLink on 16/2/17.
 */

public interface DienteService {
    @FormUrlEncoded
    @POST("/WebSites/Tesis/Diagrama/regDientes.php")
    public void guardarDiente(@Field("id_paciente") int id_paciente,
                          @Field("posicion_diente") int posicion_diente,
                          @Field("pared") String pared,
                          @Field("estado") String estado,
                          Callback<String> callback);
}
