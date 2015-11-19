package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.UsuarioDAO;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;
import com.instituto.cuanto.sisgene.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Jesus on 17/10/2015.
 */
public class PrincipalEncuestaActivity extends AppCompatActivity {

    TextView horaValor;
    TextView fechaValor;
    TextView supervisorValor;
    TextView encuestadorValor;
    Button btnNuevaEncuesta;
    int hora = 0, minuto = 0, segundo = 0;
    Thread iniReloj = null;
    Runnable r;
    String min, hor, seg;
    String curTime;
    boolean isUpdate = false;

    private ArrayList<HashMap<String, String>> list;

    Button btnSalir;

    String rolUsu;
    String nombreUsu;
    String userUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalencuesta);

        horaValor = (TextView) findViewById(R.id.txHoraValor);
        fechaValor = (TextView) findViewById(R.id.txFechaValor);
        encuestadorValor = (TextView) findViewById(R.id.txEncuestadorValor);
        supervisorValor = (TextView) findViewById(R.id.txSupervisorValor);
        btnNuevaEncuesta = (Button) findViewById(R.id.btnNuevaEncuesta);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        fechaValor.setText(Util.obtenerFecha());
        r = new RefreshClock();
        iniReloj = new Thread(r);
        iniReloj.start();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        userUsu = pref.getString("user", null);
        nombreUsu = pref.getString("nombres", null);
        rolUsu = pref.getString("rol", null);

        if(rolUsu.equals("ENCUESTADOR")){
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String idSupervisor = usuarioDAO.obtenerIDSupervisorXEncuestador(PrincipalEncuestaActivity.this, userUsu);
            System.out.println("ID_SUPERVISOR : "+idSupervisor);
            String nombreSupervisor = usuarioDAO.obtenerNombreSupervisor(PrincipalEncuestaActivity.this,idSupervisor);
            System.out.println("NOM SUPER : "+nombreSupervisor);
            encuestadorValor.setText(nombreUsu);
            supervisorValor.setText(nombreSupervisor);
        }
        if(rolUsu.equals("SUPERVISOR")){
            encuestadorValor.setText("");
            supervisorValor.setText(nombreUsu);
        }

        ListView listView = (ListView) findViewById(R.id.lvListaEncuesta);
        populateList();
        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);


        btnSalir.setOnClickListener(btnSalirsetOnClickListener);
        btnNuevaEncuesta.setOnClickListener(btnNuevaEncuestasetOnClickListener);
    }

    @Override
    public void onBackPressed() {
    }

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalEncuestaActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnNuevaEncuestasetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PrincipalEncuestaActivity.this, DatosCabeceraActivity.class);
            startActivity(intent);
        }
    };

    private void initClock() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {

                    if (isUpdate) {
                        settingNewClock();
                    } else {
                        updateTime();
                    }

                    curTime = hor + hora + min + minuto + seg + segundo;
                    horaValor.setText(curTime);

                } catch (Exception e) {
                    System.out.println("ERROR : " + e.getMessage());
                }
            }
        });
    }

    private void updateTime() {

        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);
        segundo = c.get(Calendar.SECOND);
        setZeroClock();

    }

    private void settingNewClock() {
        segundo += 1;

        setZeroClock();

        if (segundo >= 0 & segundo <= 59) {

        } else {
            segundo = 0;
            minuto += 1;
        }
        if (minuto >= 0 & minuto <= 59) {

        } else {
            minuto = 0;
            hora += 1;
        }
        if (hora >= 0 & hora <= 24) {

        } else {
            hora = 0;
        }

    }

    private void setZeroClock() {
        if (hora >= 0 & hora <= 9) {
            hor = "0";
        } else {
            hor = "";
        }

        if (minuto >= 0 & minuto <= 9) {
            min = ":0";
        } else {
            min = ":";
        }

        if (segundo >= 0 & segundo <= 9) {
            seg = ":0";

        } else {
            seg = ":";
        }
    }

    private void populateList() {
        // TODO Auto-generated method stub

        list = new ArrayList<HashMap<String, String>>();

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
        List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuestas(PrincipalEncuestaActivity.this);

        for(CabeceraRespuesta cabeceraResp: listaCabeceraResp){
            HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put(Constants.FIRST_COLUMN, cabeceraResp.getUserEncuestador());
            temp2.put(Constants.SECOND_COLUMN, cabeceraResp.getNumEncuesta());
            temp2.put(Constants.THIRD_COLUMN, cabeceraResp.getFechaDesarrollo());
            temp2.put(Constants.FOURTH_COLUMN, cabeceraResp.getNombreEncuestado()+" "+cabeceraResp.getApMaternoEncuestado()+" "+cabeceraResp.getApPaternoEncuestado());
            temp2.put(Constants.FIVE_COLUMN, cabeceraResp.getHoraInicio());
            temp2.put(Constants.SIX_COLUMN, cabeceraResp.getHoraFin());
            temp2.put(Constants.SEVEN_COLUMN, cabeceraResp.getTiempo());

            String estado="";
            if(cabeceraResp.getEstado().equals("P")) estado = "PENDIENTE";
            if(cabeceraResp.getEstado().equals("C")) estado = "COMPLETO";
            if(cabeceraResp.getEstado().equals("I")) estado = "INCOMPLETO";
            if(cabeceraResp.getEstado().equals("R")) estado = "RECHAZADO";

            temp2.put(Constants.EIGHT_COLUMN, estado);
            list.add(temp2);
        }

    }

    class RefreshClock implements Runnable {
        // @Override
        @SuppressWarnings("unused")
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    initClock();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }
}
