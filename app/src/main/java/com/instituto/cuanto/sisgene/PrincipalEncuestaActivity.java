package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;
import com.instituto.cuanto.sisgene.util.Util;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principalencuesta);

        horaValor = (TextView) findViewById(R.id.txHoraValor);
        fechaValor = (TextView) findViewById(R.id.txFechaValor);
        encuestadorValor = (TextView) findViewById(R.id.txEncuestadorValor);
        supervisorValor = (TextView) findViewById(R.id.txSupervisorValor);
        btnNuevaEncuesta = (Button) findViewById(R.id.btnNuevaEncuesta);
        btnSalir = (Button) findViewById(R.id.btnResumen);

        fechaValor.setText(Util.obtenerFecha());
        r = new RefreshClock();
        iniReloj = new Thread(r);
        iniReloj.start();

        encuestadorValor.setText("Jesus Cahuana Auquipuma");
        supervisorValor.setText("Juan Arango Pineda");

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
            Intent intent = new Intent(PrincipalEncuestaActivity.this, DatosEncuestadosActivity.class);
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
        temp.put(Constants.THIRD_COLUMN, "FE. DESARROLLO");
        temp.put(Constants.FOURTH_COLUMN, "ESTADO");
        temp.put(Constants.FIVE_COLUMN, "AP. PATERNO");
        temp.put(Constants.SIX_COLUMN, "AP. MATERNO");
        temp.put(Constants.SEVEN_COLUMN, "NOMBRES");
        list.add(temp);

        HashMap<String, String> temp2 = new HashMap<String, String>();
        temp2.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp2.put(Constants.SECOND_COLUMN, "101");
        temp2.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp2.put(Constants.FOURTH_COLUMN, "Completo");
        temp2.put(Constants.FIVE_COLUMN, "Lopez");
        temp2.put(Constants.SIX_COLUMN, "Arias");
        temp2.put(Constants.SEVEN_COLUMN, "Juan");
        list.add(temp2);

        HashMap<String, String> temp3 = new HashMap<String, String>();
        temp3.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp3.put(Constants.SECOND_COLUMN, "102");
        temp3.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp3.put(Constants.FOURTH_COLUMN, "Completo");
        temp3.put(Constants.FIVE_COLUMN, "Perez");
        temp3.put(Constants.SIX_COLUMN, "Yucra");
        temp3.put(Constants.SEVEN_COLUMN, "Juan");
        list.add(temp3);

        HashMap<String, String> temp4 = new HashMap<String, String>();
        temp4.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp4.put(Constants.SECOND_COLUMN, "103");
        temp4.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp4.put(Constants.FOURTH_COLUMN, "Completo");
        temp4.put(Constants.FIVE_COLUMN, "Cahuana");
        temp4.put(Constants.SIX_COLUMN, "Auquipuma");
        temp4.put(Constants.SEVEN_COLUMN, "Michel");
        list.add(temp4);


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
