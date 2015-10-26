package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jesus on 18/10/2015.
 */
public class ConsultarSupervisorActivity extends AppCompatActivity {

    Button btnSalir,btnResumen;
    private ArrayList<HashMap<String, String>> list;
    TextView txtSupervisor;
    Spinner spnEncuestadores;

    String rolUsu;
    String nombreUsu;
    String userUsu;
    String listEncuestadores;

    ArrayAdapter<String> lstEnc;
    String [] arrEncuestadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultarsupervisor);

        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);

        btnResumen = (Button)findViewById(R.id.btnResumen);
        //btnResumen.setOnClickListener(btnSalirsetOnClickListener);

        txtSupervisor = (TextView)findViewById(R.id.txtSupervisor);
        spnEncuestadores = (Spinner) findViewById(R.id.spnEncustadores);

        //Recargando lista
        ListView listView=(ListView)findViewById(R.id.lstEncuestas);
        populateList();
        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        userUsu = pref.getString("user", null);
        nombreUsu = pref.getString("nombres", null);
        rolUsu = pref.getString("rol", null);
        listEncuestadores = pref.getString("lstEncuestadores", null);

        arrEncuestadores = obtenerListaEncuestadores(listEncuestadores);
        lstEnc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrEncuestadores);

        spnEncuestadores.setAdapter(lstEnc);

        txtSupervisor.setText(nombreUsu);

    }

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ConsultarSupervisorActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    };

    public String[] obtenerListaEncuestadores(String cadena){

        String [] response = cadena.split(",");

        return response;
    }

    @Override
    public void onBackPressed() {
    }

    private void populateList() {
        // TODO Auto-generated method stub

        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(Constants.FIRST_COLUMN, "ENCUESTADOR");
        temp.put(Constants.SECOND_COLUMN, "N° ENCUESTA");
        temp.put(Constants.THIRD_COLUMN, "FE. DESARROLLO");
        temp.put(Constants.FOURTH_COLUMN, "ESTADO");
        temp.put(Constants.FIVE_COLUMN, "AP. PATERNO");
        temp.put(Constants.SIX_COLUMN, "AP. MATERNO");
        temp.put(Constants.SEVEN_COLUMN, "NOMBRES");
        list.add(temp);

        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp2.put(Constants.SECOND_COLUMN, "101");
        temp2.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp2.put(Constants.FOURTH_COLUMN, "No enviado");
        temp2.put(Constants.FIVE_COLUMN, "Lopez");
        temp2.put(Constants.SIX_COLUMN, "Arias");
        temp2.put(Constants.SEVEN_COLUMN, "Juan");
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp3.put(Constants.SECOND_COLUMN, "102");
        temp3.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp3.put(Constants.FOURTH_COLUMN, "No Enviado");
        temp3.put(Constants.FIVE_COLUMN, "Perez");
        temp3.put(Constants.SIX_COLUMN, "Yucra");
        temp3.put(Constants.SEVEN_COLUMN, "Juan");
        list.add(temp3);

        HashMap<String,String> temp4=new HashMap<String, String>();
        temp4.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp4.put(Constants.SECOND_COLUMN, "103");
        temp4.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp4.put(Constants.FOURTH_COLUMN, "No enviado");
        temp4.put(Constants.FIVE_COLUMN, "Cahuana");
        temp4.put(Constants.SIX_COLUMN, "Auquipuma");
        temp4.put(Constants.SEVEN_COLUMN, "Michel");
        list.add(temp4);


    }
}
