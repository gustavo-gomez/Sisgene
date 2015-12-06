package com.instituto.cuanto.sisgene;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.constantes.Constants;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.util.DateDialog;
import com.instituto.cuanto.sisgene.util.EnvioServiceUtil;
import com.instituto.cuanto.sisgene.util.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by Jesus on 18/10/2015.
 */
public class ExportarEncuestaActivity extends AppCompatActivity {

    Button btnSalir, btnBuscar, btnEnviar;
    private ArrayList<HashMap<String, String>> list;
    String sFIni="",sFFin="";
    ListView listView;
    EditText txtDateIni,txtDateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportarencuesta);

        txtDateIni = (EditText) findViewById(R.id.etFechaIni);
        txtDateFin = (EditText) findViewById(R.id.etFechaFin);
/*
        txtDateIni.setOnClickListener(tdFechaIniOnClickListener);
        txtDateFin.setOnClickListener(tdFechaFinOnClickListener);
*/
        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(btnSalirsetOnClickListener);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(btnBuscarsetOnClickListener);

        btnEnviar = (Button)findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(btnEnviarsetOnClickListener);

        //Recargando lista
        listView =(ListView)findViewById(R.id.lstEncuestas);
        populateList();


    }

    View.OnClickListener btnSalirsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ExportarEncuestaActivity.this, PrincipalActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener btnBuscarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            listView =(ListView)findViewById(R.id.lstEncuestas);

            sFIni = txtDateIni.getText().toString().trim();
            sFFin = txtDateFin.getText().toString().trim();
            populateList();
        }
    };

    View.OnClickListener btnEnviarsetOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            sFIni = txtDateIni.getText().toString().trim();
            sFFin = txtDateFin.getText().toString().trim();

            try {
                CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
                List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuestas(ExportarEncuestaActivity.this, sFIni, sFFin);

                EnvioServiceUtil envioServiceUtil = new EnvioServiceUtil();

                for (CabeceraRespuesta cabeceraResp : listaCabeceraResp) {

                    boolean estTemp = envioServiceUtil.enviarEncuestaEjecutada(ExportarEncuestaActivity.this, cabeceraResp.getIdCabeceraEnc() + "");

                    if(estTemp == true) {
                        boolean estadoTemp = cabeceraRespDAO.actualizarCabEncEstadoEnviado(ExportarEncuestaActivity.this, cabeceraResp.getIdCabeceraEnc());

                        if (estadoTemp != false) {
                            return;
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("ERROR EXC : "+e.getMessage());
            }
        }
    };



    @Override
    public void onBackPressed() {
    }
/*
    View.OnClickListener tdFechaIniOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog dialog = new DateDialog();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            txtDateIni.setText(dialog.getArguments().getString("hora"));
            dialog.show(ft, "DatePicker");
        }
    };

    View.OnClickListener tdFechaFinOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog dialog = new DateDialog();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            txtDateFin.setText(dialog.getArguments().getString("hora"));
            dialog.show(ft, "DatePicker");
        }
    };
*/
    private void populateList() {
        // TODO Auto-generated method stub

        list = new ArrayList<HashMap<String,String>>();

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
        List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuestas(ExportarEncuestaActivity.this,sFIni,sFFin);

        for(CabeceraRespuesta cabeceraResp: listaCabeceraResp){
            HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put(Constants.FIRST_COLUMN, cabeceraResp.getUserEncuestador());
            temp2.put(Constants.SECOND_COLUMN, cabeceraResp.getNumEncuesta());
            temp2.put(Constants.THIRD_COLUMN, cabeceraResp.getFechaDesarrollo());
            temp2.put(Constants.FOURTH_COLUMN, cabeceraResp.getNombreEncuestado()+" "+cabeceraResp.getApMaternoEncuestado()+" "+cabeceraResp.getApPaternoEncuestado());
            temp2.put(Constants.FIVE_COLUMN, cabeceraResp.getHoraInicio());
            temp2.put(Constants.SIX_COLUMN, cabeceraResp.getHoraFin());
            temp2.put(Constants.SEVEN_COLUMN, cabeceraResp.getTiempo());
            String valorEnvi = "";
            if(cabeceraResp.getEstado().equals("0")) valorEnvi = "NO ENVIADO";
            if(cabeceraResp.getEstado().equals("1")) valorEnvi = "ENVIADO";

            temp2.put(Constants.EIGHT_COLUMN, valorEnvi);
            list.add(temp2);
        }

        ListViewAdapter adapter=new ListViewAdapter(this, list);
        listView.setAdapter(adapter);

    }
}
