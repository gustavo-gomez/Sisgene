package com.instituto.cuanto.sisgene;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.TipoPreguntaDAO;

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

        btnEjecutar = (Button) findViewById(R.id.btnEjecutar);
        btnExportar = (Button) findViewById(R.id.btnExportar);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);

        btnEjecutar.setOnClickListener(btnEjecutarsetOnClickListener);
        btnExportar.setOnClickListener(btnExportarsetOnClickListener);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);
        btnConsultar.setOnClickListener(btnConsultarSupsetOnClickListener);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        userUsu = pref.getString("user", null);
        nombreUsu = pref.getString("nombres", null);
        rolUsu = pref.getString("rol", null);

        if (rolUsu.equals("SUPERVISOR")) {
            btnExportar.setVisibility(View.VISIBLE);
            btnConsultar.setVisibility(View.VISIBLE);
            btnEjecutar.setVisibility(View.INVISIBLE);
        }else{
            btnEjecutar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
    }

    View.OnClickListener btnEjecutarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int ultiNumEncuesta = 0;
            int hastaNumEnc = 0;

            //Se obtiene el ultimo numero de encuesta (caer_num_encuesta)
            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();
            ultiNumEncuesta = cabeceraRespuestaDAO.obtenerUltimoNumeroEncuestaCabecera(PrincipalActivity.this);
            System.out.println("ultiNumEncuesta: " + ultiNumEncuesta);

            Intent intent = new Intent(PrincipalActivity.this, PrincipalEncuestaActivity.class);
            startActivity(intent);
        /*
            if (ultiNumEncuesta != -1) {
                //obtener el id del usuario de las preferencias
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                String usu_id = pref.getString("user", "");

                //se obtiene el valor del campo hasta_num_enc de la tabla Usuario_persona
                hastaNumEnc = cabeceraRespuestaDAO.obtenerHastaNumEnc(PrincipalActivity.this, usu_id);
                System.out.println("hastaNumEnc: " + hastaNumEnc);
                System.out.println("usu_id: " + usu_id);

                if (hastaNumEnc != -1) {
                    if (ultiNumEncuesta <= hastaNumEnc) {
                        //aun puede ejecutar encuestas
                        Intent intent = new Intent(PrincipalActivity.this, PrincipalEncuestaActivity.class);
                        startActivity(intent);
                    } else {
                        new android.support.v7.app.AlertDialog.Builder(PrincipalActivity.this).setTitle("Mensaje")
                                .setMessage("Ya ejecutó todas las encuestas asignadas")
                                .setNeutralButton("Aceptar", alertaAceptarOnClickListener)
                                .setCancelable(false).show();
                    }
                } else
                    new android.support.v7.app.AlertDialog.Builder(PrincipalActivity.this).setTitle("Alerta")
                            .setMessage("Ocurrio un error. Comuníquese con el administrador del sistema..")
                            .setNeutralButton("Aceptar", alertaAceptarOnClickListener)
                            .setCancelable(false).show();
            } else
                new android.support.v7.app.AlertDialog.Builder(PrincipalActivity.this).setTitle("Alerta")
                        .setMessage("Ocurrio un error. Comuníquese con el administrador del sistema")
                        .setNeutralButton("Aceptar", alertaAceptarOnClickListener)
                        .setCancelable(false).show();
            */
        }
    };
    DialogInterface.OnClickListener alertaAceptarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
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

    public void cerrarConMensaje() {
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
            finish();
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
