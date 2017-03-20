package com.darkcode.ruf_012.Diagrama;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
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

    @FormUrlEncoded
    @POST("/WebSites/Tesis/Consulta/regConsulta.php")
    public void regConsulta(@Field("id_paciente") int id_paciente,
                            @Field("id_p_tratamiento") int id_p_tratamiento,
                            @Field("estado") String estado,
                            @Field("descripcion") String descripcion,
                            @Field("cantidad") int cantidad,
                              Callback<String> callback);

    @Headers("Cache-Control: max-age=1")
    @GET("/WebSites/Tesis/Diagrama/unDiagrama.php")
    public void unDiagrama(Callback<List<DienteDB>> callback);
}
