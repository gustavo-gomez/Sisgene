package com.instituto.cuanto.sisgene;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.instituto.cuanto.sisgene.forms.LoginRequest;
import com.instituto.cuanto.sisgene.forms.LoginResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button btnAceptar, btnSalir;
    EditText etNombreUsuario, etClave;
    TextView tvNombreUsuarioError, tvClaveError, tvErroLogin;

    String respuestaWS;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnSalir = (Button)findViewById(R.id.btnCerrar);
        etNombreUsuario = (EditText)findViewById(R.id.etNombreUsuario);
        etClave = (EditText)findViewById(R.id.etClave);
        tvNombreUsuarioError = (TextView)findViewById(R.id.tvNombreUsuarioError);
        tvClaveError = (TextView)findViewById(R.id.tvClaveError);


        btnAceptar.setOnClickListener(btnAceptarsetOnClickListener);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);


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
            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
            startActivity(intent);
            //validar usuario y clave con WebService
            /*if(camposOK)
            {
                //new RestCosumeAsyncTask().execute();
            }
            */
        }
    };

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class RestCosumeAsyncTask extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;

        String nomUsu = etNombreUsuario.getText().toString().trim();
        String clvUsu = etClave.getText().toString().trim();

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();

            try{
                LoginRequest log = new LoginRequest();
                log.setUsuario(nomUsu);
                log.setContrasenia(clvUsu);

                String sId = gson.toJson(log);
                String urlTemp = URLEncoder.encode(sId, "UTF-8");

                HttpGet get = new HttpGet("http://192.168.1.43:8081/SISGENE_LOCAL/service/obtenerGSON/"+urlTemp);
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
            progressDialog.setMessage("Obteniendo información");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("POSTEXCECUTE");
            boolean estado = false;
            progressDialog.hide();

            System.out.println("RESPWS : "+respuestaWS);
            LoginResponse loginResponse = gson.fromJson(respuestaWS, LoginResponse.class);

            if(loginResponse.getResponse_code().equals("00")){
                estado = true;
            }else{
                //tvErroLogin.setText(loginResponse.getMessage());
                Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

            if(estado) {
                //////Se llama a fragmengto, cambiar de lugar cuando se defina el orden
                Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                startActivity(intent);
                //////
            }


        }
    }



}
