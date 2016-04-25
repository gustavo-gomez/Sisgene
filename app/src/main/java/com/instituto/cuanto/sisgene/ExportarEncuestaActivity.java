package com.instituto.cuanto.sisgene;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.EnviarEncuestaDAO;
import com.instituto.cuanto.sisgene.entidad.Allegado;
import com.instituto.cuanto.sisgene.entidad.CabeceraEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DetalleEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DireccionViviendaEncuestada;
import com.instituto.cuanto.sisgene.entidad.PersonaEncuestada;
import com.instituto.cuanto.sisgene.forms.GuardarEncuestaRequest;
import com.instituto.cuanto.sisgene.forms.GuardarEncuestaResponse;
import com.instituto.cuanto.sisgene.util.DateDialog;
import com.instituto.cuanto.sisgene.util.EnvioServiceUtil;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jesus on 18/10/2015.
 */
public class ExportarEncuestaActivity extends AppCompatActivity {

    Button btnSalir, btnBuscar, btnEnviar;
    private ArrayList<HashMap<String, String>> list;
    String sFIni="",sFFin="";
    ListView listView;
    EditText txtDateIni,txtDateFin;
    CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
    Gson gson = new Gson();

    String idCabTemp = "";
    boolean estado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportarencuesta);

        txtDateIni = (EditText) findViewById(R.id.etFechaIni);
        txtDateFin = (EditText) findViewById(R.id.etFechaFin);
/*
        txtDateIni.setOnClickListener(tdFechaIniOnClickListener);
        txtDateFin.setOnClickListener(tdFechaFinOnClickListener);
*/
        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(btnBuscarsetOnClickListener);

        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(btnEnviarsetOnClickListener);

        //Recargando lista
        listView =(ListView)findViewById(R.id.lstEncuestas);
        populateList();


    }

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ExportarEncuestaActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnBuscarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            listView =(ListView)findViewById(R.id.lstEncuestas);

            sFIni = txtDateIni.getText().toString().trim();
            sFFin = txtDateFin.getText().toString().trim();
            populateList();
        }
    };

    View.OnClickListener btnEnviarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            sFIni = txtDateIni.getText().toString().trim();
            sFFin = txtDateFin.getText().toString().trim();

            try {
                CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
                List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuestas(ExportarEncuestaActivity.this, sFIni, sFFin);

                EnvioServiceUtil envioServiceUtil = new EnvioServiceUtil();

                for (CabeceraRespuesta cabeceraResp : listaCabeceraResp) {
                    /*System.out.println("MANDAAAAANDOOOOOOOOOOOOO 1 ");
                    envioServiceUtil.enviarEncuestaEjecutada(ExportarEncuestaActivity.this, cabeceraResp.getIdCabeceraEnc() + "");
                    System.out.println("MANDAAAAANDOOOOOOOOOOOOO 2 ");*/

                    idCabTemp = cabeceraResp.getIdCabeceraEnc() + "";
                    new RestCosumeAsyncTask().execute();

                }

                Intent intent = new Intent(ExportarEncuestaActivity.this, PrincipalActivity.class);
                startActivity(intent);

                finish();
            }catch(Exception e){
                System.out.println("ERROR EXC : "+e.getMessage());
            }
        }
    };



    @Override
    public void onBackPressed() {
    }
/*
    View.OnClickListener tdFechaIniOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog dialog = new DateDialog();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            txtDateIni.setText(dialog.getArguments().getString("hora"));
            dialog.show(ft, "DatePicker");
        }
    };

    View.OnClickListener tdFechaFinOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog dialog = new DateDialog();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            txtDateFin.setText(dialog.getArguments().getString("hora"));
            dialog.show(ft, "DatePicker");
        }
    };
*/
    private void populateList() {
        // TODO Auto-generated method stub

        list = new ArrayList<HashMap<String,String>>();

        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put(Constants.FIRST_COLUMN, "ENCUESTADOR");
        temp.put(Constants.SECOND_COLUMN, "N° ENCUESTA");
        temp.put(Constants.THIRD_COLUMN, "FE. DESAR");
        temp.put(Constants.FOURTH_COLUMN, "ENCUESTADO");
        temp.put(Constants.FIVE_COLUMN, "H. INICIO");
        temp.put(Constants.SIX_COLUMN, "H. TERMINO");
        temp.put(Constants.SEVEN_COLUMN, "TIEMPO");
        temp.put(Constants.EIGHT_COLUMN, "ESTADO");
        list.add(temp);

        CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
        List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuestas(ExportarEncuestaActivity.this,sFIni,sFFin);

        for(CabeceraRespuesta cabeceraResp: listaCabeceraResp){
            HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put(Constants.FIRST_COLUMN, cabeceraResp.getUserEncuestador());
            temp2.put(Constants.SECOND_COLUMN, cabeceraResp.getNumEncuesta());
            temp2.put(Constants.THIRD_COLUMN, cabeceraResp.getFechaDesarrollo());
            temp2.put(Constants.FOURTH_COLUMN, cabeceraResp.getNombreEncuestado()+" "+cabeceraResp.getApMaternoEncuestado()+" "+cabeceraResp.getApPaternoEncuestado());
            temp2.put(Constants.FIVE_COLUMN, cabeceraResp.getHoraInicio());
            temp2.put(Constants.SIX_COLUMN, cabeceraResp.getHoraFin());
            temp2.put(Constants.SEVEN_COLUMN, cabeceraResp.getTiempo());
            String valorEnvi = "";
            if(cabeceraResp.getEstado().equals("0")) valorEnvi = "NO ENVIADO";
            if(cabeceraResp.getEstado().equals("1")) valorEnvi = "ENVIADO";

            temp2.put(Constants.EIGHT_COLUMN, valorEnvi);
            list.add(temp2);
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

    }

    private class RestCosumeAsyncTask extends AsyncTask<String, Void, Void> {
        ProgressDialog progressDialog2;

        @Override
        protected Void doInBackground(String... params) {

            EnviarEncuestaDAO enviarEncuestaDAO = new EnviarEncuestaDAO();
            GuardarEncuestaRequest guardarRequest = new GuardarEncuestaRequest();

            PersonaEncuestada persona = enviarEncuestaDAO.obtenerPersonaEncuestada(ExportarEncuestaActivity.this,idCabTemp);
            DireccionViviendaEncuestada direccion = enviarEncuestaDAO.obtenerDireccionEncuestada(ExportarEncuestaActivity.this, idCabTemp);
            CabeceraEncuestaRpta cabeceraEncuestaRpta = enviarEncuestaDAO.obtenerCabEncRtpaEncuestada(ExportarEncuestaActivity.this, idCabTemp);

            List<DetalleEncuestaRpta> detalleEncuestaRptas = enviarEncuestaDAO.obtenerDenEncEncuestada(ExportarEncuestaActivity.this, idCabTemp);
            System.out.println("TAMAÑO CAB : "+detalleEncuestaRptas.size());

            /*for(DetalleEncuestaRpta prueba : detalleEncuestaRptas){
                System.out.println("1.- "+prueba.getPreg_id() + " - "+prueba.getValor_respuesta());
                if(prueba.getPreg_id().equals("183")){
                    prueba.setValor_respuesta("[01]Empleado$Trabajador no remunerado\\u0026[02]");
                }
            }*/

            System.out.println("-------------------------------------------------------------------------");
            for(DetalleEncuestaRpta prueba : detalleEncuestaRptas){
                System.out.println("1.- "+prueba.getPreg_id() + " - "+prueba.getValor_respuesta());
            }

            //System.out.println("PRUEBAAAAA JASON ENVIAR : {\"cab_enc_rpta\":{\"categoría_centropoblado\":\"\",\"codigo_centropoblado\":\"\",\"codigo_digitador\":\"\",\"codigo_informante\":\"0\",\"condicion\":\"0\",\"estado\":\"C\",\"fecha\":\"2016/01/16 11:57:52\",\"fecha_digitacion\":\"\",\"fecha_envio\":\"\",\"fecha_visita\":\"\",\"hora_fin\":\"2016/01/16 11:58:25\",\"hora_inicio\":\"2016/01/16 11:57:52\",\"id_usuario_encuestador\":\"119\",\"maquina_digitador\":\"\",\"nombre_centropoblado\":\"\",\"num_area\":\"0\",\"num_conglomerado\":\"\",\"num_hogar\":\"\",\"num_manzana\":\"\",\"num_vivienda\":\"\",\"num_zona\":\"\",\"numero_encuesta\":\"1\",\"obervacion_supervisor\":\"\",\"observaciones\":\"jdksksoskdmdl kwksk\",\"tiempo\":\"00:01\"},\"direccion_viviendaencuestada\":{\"etapa\":\"\",\"grupo\":\"\",\"interior\":\"\",\"km\":\"\",\"lote\":\"\",\"manzana\":\"\",\"num_puerta\":\"\",\"piso\":\"\",\"sector\":\"\",\"tipo_ubicacion\":\"lsuhludhdlus\"},\"lista_allegados\":[],\"lista_det_enc_rpta\":[{\"preg_id\":\"177\",\"valor_respuesta\":\"[01]ldldls\"},{\"preg_id\":\"178\",\"valor_respuesta\":\"[01]Sí\"},{\"preg_id\":\"179\",\"valor_respuesta\":\"[01]Quehaceres del hogar$Anciano/Invalido\"},{\"preg_id\":\"180\",\"valor_respuesta\":\"[01]Empleado$Trabajador familiar no remunerado\\u0026[02]\"},{\"preg_id\":\"183\",\"valor_respuesta\":\"[01]Recibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecida%Gas Natural$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión " +
              //      "del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecido%Diesel Biodiesel$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustiblenull$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Calidad del combustible%Diesel Biodiesel$Precio diferente al ofrecidonull$Variaciones en la presión del suministronull$Bajo rendimientonullRecibió una cantidad menor a la ofrecidanull$Mala Ca JESUSI JESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSIJESUSI");

            List<Allegado> listAllegado = enviarEncuestaDAO.obtenerAllegadoEncuestada(ExportarEncuestaActivity.this, idCabTemp);

            System.out.println("TAMANO ALLEGADO : "+listAllegado.size());
            System.out.println("TAMANO cabeceraEncuestaRpta : "+cabeceraEncuestaRpta.getId_usuario_encuestador());

            guardarRequest.setPersona_encuestada(persona);
            guardarRequest.setDireccion_viviendaencuestada(direccion);
            guardarRequest.setCab_enc_rpta(cabeceraEncuestaRpta);
            guardarRequest.setLista_det_enc_rpta(detalleEncuestaRptas);
            guardarRequest.setLista_allegados(listAllegado);

            String jsonEnviar = gson.toJson(guardarRequest);
            System.out.println("JSON ENVIAR : " + jsonEnviar + "\n\n\n\n");

            Log.i("Json a a Enviar: ", jsonEnviar);

            try {
                String a = URLEncoder.encode(jsonEnviar, "UTF-8");
                System.out.println("\n\nJSON CODIFICADO : " + a);

                jsonEnviar = a;
                Log.i("Json CODIFICADO: ", jsonEnviar);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //PROVISIONAL
            /*String ip="192.168.1.35";
            String puerto="8081";*/

            /*String ip="192.168.1.39";
            String puerto="8080";*/

            //CUANTO
            String ip="190.40.162.59";
            String puerto="8085";

            /*String ip="190.40.162.59";
            String puerto="8085";*/

            /*String ip="192.168.1.33";
            String puerto="8085";*/


            //String ip="192.168.1.117";
            //String puerto="8089";

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://"+ip+":"+puerto+"/WSSisgene/WebServiceSISGENE")
                    //.setEndpoint("http://"+ip+":"+puerto+"/resources/WebServiceSISGENE")
                    .build();


            EnvioService service = restAdapter.create(EnvioService.class);

            System.out.println("ENVIARAAAAAAAAAAA");

            service.repository2Sync(jsonEnviar, new Callback<GuardarEncuestaResponse>() {
                @Override
                public void success(GuardarEncuestaResponse guardarEncuestaResponse, Response response) {
                    System.out.println("1.- "+guardarEncuestaResponse.getCodigo_respuesta());
                    System.out.println("2.- "+guardarEncuestaResponse.getMensaje());
                    if (guardarEncuestaResponse.getCodigo_respuesta().equals("01")){
                        progressDialog2.hide();
                        Toast.makeText(ExportarEncuestaActivity.this, guardarEncuestaResponse.getMensaje(), Toast.LENGTH_LONG).show();
                    }else {
                        progressDialog2.hide();
                        cabeceraRespDAO.actualizarCabEncEstadoEnviado(ExportarEncuestaActivity.this, Integer.parseInt(idCabTemp));
                        Toast.makeText(ExportarEncuestaActivity.this, "Envio de Encuesta con éxito", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    System.out.println("EEROR : "+error.toString() + " - "+ error.getMessage());
                    System.out.println("OCURRIO UN ERROR");
                    Toast.makeText(ExportarEncuestaActivity.this, "Ocurrio un error en el envio de Información, verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                    progressDialog2.hide();
                }
            });

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog2 = new ProgressDialog(ExportarEncuestaActivity.this);
            //progressDialog2.show();
            progressDialog2.setMessage("Enviando Encuesta...");

        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println("POSTEXCECUTE");
            //progressDialog.hide();
        }
    }
}
