package com.instituto.cuanto.sisgene;

import com.instituto.cuanto.sisgene.forms.GuardarEncuestaRequest;
import com.instituto.cuanto.sisgene.forms.GuardarEncuestaResponse;
import com.instituto.cuanto.sisgene.forms.ValidarAdministradorResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Jesus on 16/11/2015.
 */
public interface EnvioService {

    @POST("/guardarEncuesta/")
    void repository2Sync(@Body String cadenaJSON, Callback<GuardarEncuestaResponse> callback);

   // @POST("/guardarEncuesta")
    //void repository2Sync(@Body GuardarEncuestaRequest guardarEncuestaResponse, Callback<GuardarEncuestaResponse> callback);
}
