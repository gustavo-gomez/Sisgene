package com.instituto.cuanto.sisgene;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.instituto.cuanto.sisgene.bean.Encuestador;
import com.instituto.cuanto.sisgene.bean.Usuario;
import com.instituto.cuanto.sisgene.dao.UsuarioDAO;
import com.instituto.cuanto.sisgene.forms.LoginRequest;
import com.instituto.cuanto.sisgene.forms.LoginResponse;
import com.instituto.cuanto.sisgene.forms.ValidarAdministradorRequest;
import com.instituto.cuanto.sisgene.forms.ValidarAdministradorResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAceptar, btnCerrar;
    EditText etNombreUsuario, etClave, etCodigoEncuesta;
    TextView tvNombreUsuarioError, tvClaveError, tvErroLogin;
    Spinner listaRol;
    LinearLayout lyCodigo;
  //  private DataBaseHelper dataBaseHelper;
    String respuestaWS;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnCerrar = (Button)findViewById(R.id.btnCerrar);
        etNombreUsuario = (EditText)findViewById(R.id.etNombreUsuario);
        etClave = (EditText)findViewById(R.id.etClave);
        etCodigoEncuesta = (EditText)findViewById(R.id.etCodigoEncuesta);
        tvNombreUsuarioError = (TextView)findViewById(R.id.tvNombreUsuarioError);
        tvClaveError = (TextView)findViewById(R.id.tvClaveError);
        listaRol = (Spinner)findViewById(R.id.spRol);
        lyCodigo = (LinearLayout) findViewById(R.id.lyNombreEncuesta);

        listaRol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 2){
                    lyCodigo.setVisibility(View.VISIBLE);
                }else{
                    lyCodigo.setVisibility(View.INVISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        btnAceptar.setOnClickListener(btnAceptarsetOnClickListener);
        btnCerrar.setOnClickListener(btnCerrarsetOnClickListener);


    }

    View.OnClickListener btnAceptarsetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean camposOK = true;
            boolean loginOK = false;

            tvNombreUsuarioError.setText("");
            tvClaveError.setText("");

            if(etNombreUsuario.getText().toString().trim().length() == 0)
            {
                tvNombreUsuarioError.setText(getResources().getString(R.string.login_user_error));
                camposOK = false;
            }
            if(etClave.getText().toString().trim().length() == 0)
            {
                tvClaveError.setText(getResources().getString(R.string.login_pass_error));
                camposOK = false;
            }

            String rolAcceso = listaRol.getSelectedItem().toString();
            UsuarioDAO usuarioDAO = new UsuarioDAO();


            if(camposOK)
            {
                int cantidadData = usuarioDAO.obtenerCantidadUsuarios(MainActivity.this);

                if(cantidadData == 0){
                    if(rolAcceso.equals("ADMINISTRADOR")){
                        new RestCosumeAsyncTask().execute();
                    }else{
                        Toast.makeText(MainActivity.this, "Tablet no cargada, porfavor utilizar un usuario ADMINISTRADOR", Toast.LENGTH_LONG).show();
                    }

                }else{
                    String nomUsu = etNombreUsuario.getText().toString().trim();
                    String clvUsu = etClave.getText().toString().trim();

                    Usuario usuario = usuarioDAO.obtenerUsuario(MainActivity.this,nomUsu,clvUsu,rolAcceso);

                    if(usuario != null) {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("user", nomUsu);
                        editor.putString("nombres", usuario.getNombre() + " " + usuario.getAp_paterno() + " " + usuario.getAp_materno());
                        editor.putString("rol", rolAcceso);
                        editor.commit();

                        Toast.makeText(MainActivity.this, "BIENVENIDO "+usuario.getNombre() + " " + usuario.getAp_paterno() + " " + usuario.getAp_materno(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };

    View.OnClickListener btnCerrarsetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private class RestCosumeAsyncTask extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;

        String nomUsu = etNombreUsuario.getText().toString().trim();
        String clvUsu = etClave.getText().toString().trim();
        String codEncuesta = etCodigoEncuesta.getText().toString().trim();

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();

            try{

                ValidarAdministradorRequest validarRequest = new ValidarAdministradorRequest();
                validarRequest.setUsuario(nomUsu);
                validarRequest.setClave(clvUsu);
                validarRequest.setCodigo_encuesta(codEncuesta);

                String jsonEnviar = gson.toJson(validarRequest);
                String urlTemp = URLEncoder.encode(jsonEnviar, "UTF-8");

                HttpGet get = new HttpGet("http://192.168.1.35:8081/SISGENE_LOCAL/service/obtenerGSON/"+urlTemp);
                get.setHeader("Content-type", "application/json");

                HttpResponse resp = httpClient.execute(get);
                String respString = EntityUtils.toString(resp.getEntity());

                System.out.println("RESPSTRING : "+respString);

                respuestaWS = respString;

                return respuestaWS;

            }catch(Exception ex){
                System.out.println("EERROR LOGIN : "+ex.getMessage());
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("PREEXECUTE");
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Validando datos de ENCUESTADOR");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("POSTEXCECUTE");
            boolean estado = false;
            progressDialog.setMessage("Cargando configuración de Encuesta en Tablet");

            if(respuestaWS != null) {

                ValidarAdministradorResponse validarResponse = gson.fromJson(respuestaWS, ValidarAdministradorResponse.class);

                if (validarResponse.getCodigo_respuesta().equals("00")) {
                    estado = true;
                } else {
                    progressDialog.hide();
                    Toast.makeText(MainActivity.this, validarResponse.getMensaje(), Toast.LENGTH_LONG).show();
                    return;
                }

                if (estado) {
                    //////Se llama a fragmengto, cambiar de lugar cuando se defina el orden
                    /*Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                    finish();*/
                    //////
                    cargarEnBD(validarResponse);
                    progressDialog.hide();
                }

            }else{
                progressDialog.hide();
                Toast.makeText(MainActivity.this, "El dispositivo no cuenta con conexión a INTERNET", Toast.LENGTH_LONG).show();
                return;
            }

        }
    }

    public void cargarEnBD(ValidarAdministradorResponse validarResponse){


    }

        public String juntar(List<Encuestador> encuestador){
            String response = "";

            for(Encuestador enc: encuestador){
                response = response + enc.getUserEncuestador()+",";
            }
            return response;
        }
}
