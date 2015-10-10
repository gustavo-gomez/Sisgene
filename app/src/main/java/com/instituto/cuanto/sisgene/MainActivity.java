package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.fragment.ListViewTipoPregunta1Fragment;

public class MainActivity extends AppCompatActivity {

    Button btnAceptar, btnSalir;
    EditText etNombreUsuario, etClave;
    TextView tvNombreUsuarioError, tvClaveError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnSalir = (Button)findViewById(R.id.btnSalir);
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

            //validar usuario y clave con WebService
       /*     if(camposOK)
            {

            }
        */
            //////Se llama a fragmengto, cambiar de lugar cuando se defina el orden
            Intent intent = new Intent(MainActivity.this, PreguntasActivity.class);
            startActivity(intent);
            //////

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
}
