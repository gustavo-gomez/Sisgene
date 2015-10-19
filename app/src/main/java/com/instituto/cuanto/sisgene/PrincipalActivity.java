package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jesus on 17/10/2015.
 */
public class PrincipalActivity extends AppCompatActivity {

    Button btnEjecutar;
    Button btnExportar;
    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityprincipal);

        btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        btnExportar = (Button)findViewById(R.id.btnExportar);
        btnSalir    = (Button)findViewById(R.id.btnSalir);

        btnEjecutar.setOnClickListener(btnEjecutarsetOnClickListener);
        btnExportar.setOnClickListener(btnExportarsetOnClickListener);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);

    }

    @Override
    public void onBackPressed() {
    }

    View.OnClickListener btnEjecutarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalActivity.this, PrincipalEncuestaActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnExportarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalActivity.this, ExportarEncuestaActivity.class);
            startActivity(intent);
        }
    };
}
