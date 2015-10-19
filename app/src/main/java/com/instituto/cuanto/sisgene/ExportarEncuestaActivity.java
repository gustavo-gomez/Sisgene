package com.instituto.cuanto.sisgene;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.util.DateDialog;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jesus on 18/10/2015.
 */
public class ExportarEncuestaActivity extends AppCompatActivity {

    Button btnSalir;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportarencuesta);

        EditText txtDate = (EditText) findViewById(R.id.etFechaIni);
        EditText txtDate2 = (EditText) findViewById(R.id.etFechaFin);

        txtDate.setOnClickListener(tdFechaIniOnClickListener);
        txtDate2.setOnClickListener(tdFechaFinOnClickListener);

        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);

        //Recargando lista
        ListView listView=(ListView)findViewById(R.id.lstEncuestas);
        populateList();
        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

    }

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ExportarEncuestaActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
    }

    View.OnClickListener tdFechaIniOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog dialog = new DateDialog(v);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.show(ft, "DatePicker");
        }
    };

    View.OnClickListener tdFechaFinOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog dialog = new DateDialog(v);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            dialog.show(ft, "DatePicker");
        }
    };

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
        temp2.put(Constants.FOURTH_COLUMN, "Completo");
        temp2.put(Constants.FIVE_COLUMN, "Lopez");
        temp2.put(Constants.SIX_COLUMN, "Arias");
        temp2.put(Constants.SEVEN_COLUMN, "Juan");
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp3.put(Constants.SECOND_COLUMN, "102");
        temp3.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp3.put(Constants.FOURTH_COLUMN, "Completo");
        temp3.put(Constants.FIVE_COLUMN, "Perez");
        temp3.put(Constants.SIX_COLUMN, "Yucra");
        temp3.put(Constants.SEVEN_COLUMN, "Juan");
        list.add(temp3);

        HashMap<String,String> temp4=new HashMap<String, String>();
        temp4.put(Constants.FIRST_COLUMN, "Jesus Cahuana");
        temp4.put(Constants.SECOND_COLUMN, "103");
        temp4.put(Constants.THIRD_COLUMN, "10/10/2015");
        temp4.put(Constants.FOURTH_COLUMN, "Completo");
        temp4.put(Constants.FIVE_COLUMN, "Cahuana");
        temp4.put(Constants.SIX_COLUMN, "Auquipuma");
        temp4.put(Constants.SEVEN_COLUMN, "Michel");
        list.add(temp4);


    }
}
