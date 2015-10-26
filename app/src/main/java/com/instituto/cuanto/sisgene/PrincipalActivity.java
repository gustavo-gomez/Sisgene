package com.instituto.cuanto.sisgene;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    Button btnConsultar;

    String rolUsu;
    String nombreUsu;
    String userUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityprincipal);

        btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        btnExportar = (Button)findViewById(R.id.btnExportar);
        btnSalir    = (Button)findViewById(R.id.btnResumen);
        btnConsultar    = (Button)findViewById(R.id.btnConsultar);

        btnEjecutar.setOnClickListener(btnEjecutarsetOnClickListener);
        btnExportar.setOnClickListener(btnExportarsetOnClickListener);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);
        btnConsultar.setOnClickListener(btnConsultarSupsetOnClickListener);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        userUsu = pref.getString("user", null);
        nombreUsu = pref.getString("nombres", null);
        rolUsu = pref.getString("rol", null);

        if(rolUsu.equals("ENCUESTADOR")){
            btnConsultar.setEnabled(false);
        }

    }

    @Override
    public void onBackPressed() {
        //AlertDialog.Builder msjConfirmacion = new AlertDialog.Builder(this);

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
            //Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
            //startActivity(intent);
            System.out.println("CERRARMMSJEEEEEEEE");
            cerrarConMensaje();
        }
    };

    public void cerrarConMensaje(){
        System.out.println("ENTRO A CERRAR CON MSJE");
        AlertDialog.Builder msjConfirmacion = new AlertDialog.Builder(this);

        msjConfirmacion.setTitle("SISGENE");
        msjConfirmacion.setMessage("¿Esta seguro que desea salir de la aplicación?");
        msjConfirmacion.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        msjConfirmacion.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog confirmacion = msjConfirmacion.create();
        confirmacion.show();
    }

    View.OnClickListener btnExportarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalActivity.this, ExportarEncuestaActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnConsultarSupsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalActivity.this, ConsultarSupervisorActivity.class);
            startActivity(intent);
        }
    };
}
