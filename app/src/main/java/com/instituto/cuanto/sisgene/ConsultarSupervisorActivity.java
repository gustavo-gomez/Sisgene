package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 18/10/2015.
 */
public class ConsultarSupervisorActivity extends AppCompatActivity {

    Button btnSalir,btnResumen, btnBuscar;
    private ArrayList<HashMap<String, String>> list;
    TextView txtSupervisor;
    ListView listView;
    Spinner spnEncuestadores;

    String rolUsu;
    String nombreUsu;
    String userUsu;
    String listEncuestadores;

    ArrayAdapter<String> lstEnc;

    String estadoEnvi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultarsupervisor);

        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(btnBuscarsetOnClickListener);

        //btnResumen = (Button)findViewById(R.id.btnResumen);
        //btnResumen.setOnClickListener(btnSalirsetOnClickListener);

        txtSupervisor = (TextView)findViewById(R.id.txtSupervisor);
        spnEncuestadores = (Spinner) findViewById(R.id.spnEstados);

        //Recargando lista
        listView=(ListView)findViewById(R.id.lstEncuestas);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {

                if(posicion != 0){

                    CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
                    List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuesta(ConsultarSupervisorActivity.this,estadoEnvi);

                    System.out.println("POSICION PRU : "+posicion);
                    System.out.println("listaCAB : "+listaCabeceraResp.size());

                    CabeceraRespuesta cabeceraResp = listaCabeceraResp.get(posicion-1);

                    if(cabeceraResp.getEstado().equals("1")){
                        Toast.makeText(ConsultarSupervisorActivity.this, "NO SE PUEDE EDITAR ENCUESTAS YA ENVIADAS", Toast.LENGTH_LONG).show();
                    }else {
                        System.out.println("POSITION: " + posicion);
                        Intent intent = new Intent(ConsultarSupervisorActivity.this, ModificarEncuestaActivity.class);
                        intent.putExtra("posicion", posicion + "");
                        intent.putExtra("estadoEnvi", estadoEnvi);
                        startActivity(intent);
                    }
                }
            }
        });

        populateList();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        userUsu = pref.getString("user", null);
        nombreUsu = pref.getString("nombres", null);
        rolUsu = pref.getString("rol", null);

        txtSupervisor.setText(nombreUsu);

    }

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ConsultarSupervisorActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnBuscarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            listView =(ListView)findViewById(R.id.lstEncuestas);

            String estEnviado = spnEncuestadores.getSelectedItem().toString();

            if(estEnviado.equals("ENVIADO")){
                estadoEnvi = "1";
            }
            if(estEnviado.equals("NO ENVIADO")){
                estadoEnvi = "0";
            }
            if(estEnviado.equals("SELECCIONE")){
                estadoEnvi = "";
            }

            System.out.println("ESTADO ENVIADO : "+estadoEnvi);

            populateList();
        }
    };

    @Override
    public void onBackPressed() {
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
        List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuesta(ConsultarSupervisorActivity.this,estadoEnvi);

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
            if(cabeceraResp.getEstado().equals("0")) estado = "NO ENVIADO";
            if(cabeceraResp.getEstado().equals("1")) estado = "ENVIADO";
            temp2.put(Constants.EIGHT_COLUMN, estado);
            list.add(temp2);
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

    }
}
