package com.instituto.cuanto.sisgene;

import com.instituto.cuanto.sisgene.forms.GuardarEncuestaResponse;
import com.instituto.cuanto.sisgene.forms.ValidarAdministradorResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Jesus on 16/11/2015.
 */
public interface EnvioService {

    @GET("/guardarEncuesta/{cadenaJSON}")
    void repository2Sync(@Path("cadenaJSON") String cadenaJSON, Callback<GuardarEncuestaResponse> callback);
}
