package com.instituto.cuanto.sisgene.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.instituto.cuanto.sisgene.EnvioService;
import com.instituto.cuanto.sisgene.ExportarEncuestaActivity;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.EnviarEncuestaDAO;
import com.instituto.cuanto.sisgene.entidad.Allegado;
import com.instituto.cuanto.sisgene.entidad.CabeceraEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DetalleEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DireccionViviendaEncuestada;
import com.instituto.cuanto.sisgene.entidad.PersonaEncuestada;
import com.instituto.cuanto.sisgene.forms.GuardarEncuestaRequest;
import com.instituto.cuanto.sisgene.forms.GuardarEncuestaResponse;
import com.instituto.cuanto.sisgene.forms.ValidarAdministradorResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.net.URLEncoder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.FormUrlEncoded;

/**
 * Created by Jesus on 19/11/2015.
 */
public class EnvioServiceUtil {

    Context context;
    String idCabEnc = "",estadoWS = "",ip="",puerto="";
    Gson gson = new Gson();
    CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();

    public EnvioServiceUtil(){}

    public boolean enviarEncuestaEjecutada(Context context, String idCabeceraEncuesta){

        System.out.println("IDCAB : "+idCabeceraEncuesta);

        this.context = context;
        this.idCabEnc = idCabeceraEncuesta;

        try {
            /*LeerProperties leerProperties = new LeerProperties();
            String ipWS = leerProperties.leerIPWS();
            String puertoWS = leerProperties.leerPUERTOWS();
            ip = ipWS;
            puerto = puertoWS;
*/
            //if(ipWS != null && puertoWS != null){
                new RestCosumeAsyncTask().execute();
            //}else{
             //   Toast.makeText(context, "No se encuentra el archivo de configuracion", Toast.LENGTH_LONG).show();
            //}

            System.out.println("ESTADOOOO WS : "+estadoWS);

            if (estadoWS.equals("00")) {
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            System.out.println("ERROR EN ENVIO DE INF : "+e.getMessage());
            return false;
        }
    }

    public boolean guardarSDEncuestaEjecutada(Context context, String idCabeceraEncuesta){

        System.out.println("IDCAB : "+idCabeceraEncuesta);

        this.context = context;
        this.idCabEnc = idCabeceraEncuesta;

        try {
            /*LeerProperties leerProperties = new LeerProperties();
            String ipWS = leerProperties.leerIPWS();
            String puertoWS = leerProperties.leerPUERTOWS();
            ip = ipWS;
            puerto = puertoWS;
*/
            //if(ipWS != null && puertoWS != null){
            new RestCosumeAsyncTask2().execute();
            //}else{
            //   Toast.makeText(context, "No se encuentra el archivo de configuracion", Toast.LENGTH_LONG).show();
            //}

            System.out.println("ESTADOOOO WS : "+estadoWS);

            if (estadoWS.equals("00")) {
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            System.out.println("ERROR EN ENVIO DE INF : "+e.getMessage());
            return false;
        }
    }

    private class RestCosumeAsyncTask extends AsyncTask<String, Void, Void> {
        ProgressDialog progressDialog2;

        @Override
        protected Void doInBackground(String... params) {

            EnviarEncuestaDAO enviarEncuestaDAO = new EnviarEncuestaDAO();
            GuardarEncuestaRequest guardarRequest = new GuardarEncuestaRequest();

            PersonaEncuestada persona = enviarEncuestaDAO.obtenerPersonaEncuestada(context,idCabEnc);
            DireccionViviendaEncuestada direccion = enviarEncuestaDAO.obtenerDireccionEncuestada(context, idCabEnc);

            CabeceraEncuestaRpta cabeceraEncuestaRpta = enviarEncuestaDAO.obtenerCabEncRtpaEncuestada(context, idCabEnc);
            List<DetalleEncuestaRpta> detalleEncuestaRptas = enviarEncuestaDAO.obtenerDenEncEncuestada(context, idCabEnc);
            List<Allegado> listAllegado = enviarEncuestaDAO.obtenerAllegadoEncuestada(context, idCabEnc);
            System.out.println("TAMANO ALLEGADO 2: "+listAllegado.size());
            System.out.println("TAMANO cabeceraEncuestaRpta : "+cabeceraEncuestaRpta.getId_usuario_encuestador());

            guardarRequest.setPersona_encuestada(persona);
            guardarRequest.setDireccion_viviendaencuestada(direccion);
            guardarRequest.setCab_enc_rpta(cabeceraEncuestaRpta);
            guardarRequest.setLista_det_enc_rpta(detalleEncuestaRptas);
            guardarRequest.setLista_allegados(listAllegado);

            String jsonEnviar = gson.toJson(guardarRequest);
            Log.i("Json Enviar si codif: ","jsonEnviar");

            System.out.println("JASON ENVIAR : " + jsonEnviar);

            try {
                String a = URLEncoder.encode(jsonEnviar, "UTF-8");
                Log.i("Json Enviar codif: ","a");
                System.out.println("\n\nJSON CODIFICADO : "+a);
                jsonEnviar = a;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //PROVISIONAL
            /*ip="192.168.1.35";
            puerto="8081";*/

            /*ip="192.168.1.39";
            puerto="8080";*/

            //CUANTO
            ip="190.40.162.59";
            puerto="8085";

            /*ip="190.40.162.59";
            puerto="8085";*/

            /*ip="192.168.1.33";
            puerto="8085";*/

            //ip="192.168.1.117";
            //puerto="8089";

            RestAdapter restAdapter = new RestAdapter.Builder()
                    //.setEndpoint("http://"+ip+":"+puerto+"/WSSisgene/resources/WebServiceSISGENE")
                    //.setEndpoint("http://"+ip+":"+puerto+"/resources/WebServiceSISGENE")
                    .setEndpoint("http://"+ip+":"+puerto+"/WSSisgene/WebServiceSISGENE")
                    .build();

            EnvioService service = restAdapter.create(EnvioService.class);

            service.repository2Sync(jsonEnviar, new Callback<GuardarEncuestaResponse>() {
                @Override
                public void success(GuardarEncuestaResponse guardarEncuestaResponse, Response response) {
                    System.out.println("1.- "+guardarEncuestaResponse.getCodigo_respuesta());
                    System.out.println("2.- "+guardarEncuestaResponse.getMensaje());
                    if (guardarEncuestaResponse.getCodigo_respuesta().equals("01")){
                        progressDialog2.hide();
                        Toast.makeText(context, guardarEncuestaResponse.getMensaje(), Toast.LENGTH_LONG).show();
                    }else {
                        progressDialog2.hide();
                        estadoWS = "00";
                        System.out.println("------->>>>>>"+Integer.parseInt(idCabEnc));
                        cabeceraRespDAO.actualizarCabEncEstadoEnviado(context, Integer.parseInt(idCabEnc));
                        Toast.makeText(context, "Envio de Encuesta con éxito", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(context, "Ocurrio un error en el envio de Información, verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                    progressDialog2.hide();
                }
            });

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("PREEXECUTE");
            progressDialog2 = new ProgressDialog(context);
            progressDialog2.setMessage("Enviando información");
            //progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println("POSTEXCECUTE");
            //progressDialog.hide();
        }
    }


    private class RestCosumeAsyncTask2 extends AsyncTask<String, Void, Void> {
        ProgressDialog progressDialog2;

        @Override
        protected Void doInBackground(String... params) {

            EnviarEncuestaDAO enviarEncuestaDAO = new EnviarEncuestaDAO();
            GuardarEncuestaRequest guardarRequest = new GuardarEncuestaRequest();

            PersonaEncuestada persona = enviarEncuestaDAO.obtenerPersonaEncuestada(context,idCabEnc);
            DireccionViviendaEncuestada direccion = enviarEncuestaDAO.obtenerDireccionEncuestada(context, idCabEnc);
            CabeceraEncuestaRpta cabeceraEncuestaRpta = enviarEncuestaDAO.obtenerCabEncRtpaEncuestada(context, idCabEnc);
            List<DetalleEncuestaRpta> detalleEncuestaRptas = enviarEncuestaDAO.obtenerDenEncEncuestada(context, idCabEnc);
            List<Allegado> listAllegado = enviarEncuestaDAO.obtenerAllegadoEncuestada(context, idCabEnc);

            guardarRequest.setPersona_encuestada(persona);
            guardarRequest.setDireccion_viviendaencuestada(direccion);
            guardarRequest.setCab_enc_rpta(cabeceraEncuestaRpta);
            guardarRequest.setLista_det_enc_rpta(detalleEncuestaRptas);
            guardarRequest.setLista_allegados(listAllegado);


            String jsonEnviar = gson.toJson(guardarRequest);
            Log.i("Json Enviar sin codif: ",jsonEnviar);

            System.out.println("JASON ENVIAR : " + jsonEnviar);

            try {
                String a = URLEncoder.encode(jsonEnviar, "UTF-8");
                Log.i("Json Enviar codif: ",a);
                System.out.println("\n\nJSON CODIFICADO : "+a);
                jsonEnviar = a;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //para guardar en SD
            String nobreArchivo = "EncuestaDNI_"+persona.getNum_documento();
        try{
            File tarjeta = Environment.getExternalStorageDirectory();
            File file = new File(tarjeta.getAbsolutePath(), nobreArchivo);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            osw.write(jsonEnviar);
            osw.flush();
            osw.close();
            //Toast.makeText(, "Los datos fueron grabados correctamente", Toast.LENGTH_SHORT).show();
            Log.i("TAG INFO", "SE GUARDO ARCHIVO");
        } catch (IOException ioe) {
            //Toast.makeText(this, "Error los datos no se guardaron, ocurrio un error ",Toast.LENGTH_SHORT).show();
             Log.e("ERROR SD", "NO SE GRABO NADA "+ioe.getMessage());


        }




        //PROVISIONAL
            //ip="192.168.1.41";
            //puerto="8085";

            /*ip="192.168.1.39";
            puerto="8080";*/

            //CUANTO
            ip="190.40.162.59";
            puerto="8085";

            /*ip="190.40.162.59";
            puerto="8085";*/

            /*ip="192.168.1.33";
            puerto="8085";*/

            //ip="192.168.1.117";
            //puerto="8089";

         //   RestAdapter restAdapter = new RestAdapter.Builder()
                    //.setEndpoint("http://"+ip+":"+puerto+"/WSSisgene/resources/WebServiceSISGENE")
                    //.setEndpoint("http://"+ip+":"+puerto+"/resources/WebServiceSISGENE")
         //           .setEndpoint("http://"+ip+":"+puerto+"/WSSisgene/WebServiceSISGENE")
          //          .build();

           // EnvioService service = restAdapter.create(EnvioService.class);



            return null;
        }


    }
}
