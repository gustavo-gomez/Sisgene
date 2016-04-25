package com.instituto.cuanto.sisgene;

import com.instituto.cuanto.sisgene.forms.ValidarAdministradorResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;


/**
 * Created by Jesus on 12/11/2015.
 */
public interface ClienteService {

    //@GET("/validaAdministrador/{cadenaJSON}")
    //@FormUrlEncoded
    //@POST("/valida")
    @POST("/validaAdministrador/")
    void repositorySync(@Body String cadenaJSON, Callback<ValidarAdministradorResponse> callback);
    //void repositorySync(@Path("cadenaJSON") String cadenaJSON, Callback<ValidarAdministradorResponse> callback);
    //void repositorySync(@Body String cadenaJSON, Callback<String> callback);
}
