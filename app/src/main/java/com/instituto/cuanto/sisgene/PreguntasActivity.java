package com.instituto.cuanto.sisgene;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.adapter.TipoPreguntaAbiertaAdapter;
import com.instituto.cuanto.sisgene.entities.Pregunta;
import com.instituto.cuanto.sisgene.entities.PreguntaOpcion;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    Button btnSiguiente;
    //LinearLayout lyFragmentoListaPreguntas;
    //android.app.FragmentManager fragmentManager;
    ListView lvRespuestas_tipoGeneral;
    static ArrayList<String> arrayList;
    TipoPreguntaAbiertaAdapter tipoPreguntaAbiertaAdapter;
    static ArrayList<String> nombresEncuestados;
    ArrayList<TipoPreguntaAbiertaItem> miListaTipoPreguntaAbiertaAdapter;
    Context context = PreguntasActivity.this;
    static boolean dataLista = false;
    AlertDialog.Builder alert;
    int numPregunta = -1;
    final ArrayList<EditText> editTexts = new ArrayList<>();
    final ArrayList<TextView> textViews = new ArrayList<>();
    ScrollView scrollView;
    TextView tvEnunciadoPregunta;
    TextView tvOpcionesPregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        arrayList = new ArrayList<>();
        nombresEncuestados = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        //lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoGeneral = (ListView) findViewById(R.id.lvRespuestas_tipoGeneral);
        tvEnunciadoPregunta = (TextView) findViewById(R.id.tvEnunciadoPregunta);
        tvOpcionesPregunta = (TextView) findViewById(R.id.tvOpcionesPregunta);
        tipoPreguntaAbiertaAdapter = new TipoPreguntaAbiertaAdapter();
        miListaTipoPreguntaAbiertaAdapter = new ArrayList<>();
        scrollView = new ScrollView(PreguntasActivity.this);
        alert = new AlertDialog.Builder(this);

        //Se lee la informacion de la primera pregunta
        String tipoPregunta="AB";
        int numEncuestado = 0;

        //Tipo de pregunta Unica
        if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();


            //Tipo de pregunta Multiple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();


            //Tipo de pregunta abierta
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaAbierta(numEncuestado);
            //Tipo de pregunta Matriz Simple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Matriz Multiple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Mixta
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();
        }

        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            poblarLista_TipoPreguntaAbierta(12);
        }
    };

    public void obtenerNombresEncuestados(int num) {


        /*
        final LinearLayout lyinput = new LinearLayout(PreguntasActivity.this);
        lyinput.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < num; i++) {

            final TextView tvinput = new TextView(PreguntasActivity.this);
            tvinput.setText("Nombre " + (i + 1) + ":  ");
            tvinput.setTextSize(20);

            final EditText etinput = new EditText(PreguntasActivity.this);
            etinput.setWidth(1000);

            textViews.add(tvinput);
            editTexts.add(etinput);
        }

        for (int i = 0; i < num; i++) {
            lyinput.addView(textViews.get(i));
            lyinput.addView(editTexts.get(i));
        }
        //alert.setTitle("Ingrese el nombre de las " + num + " personas");
        scrollView.addView(lyinput);
        //alert.setView(scrollView);
        //new ProgressAsyncTask().execute();


        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                System.out.println("campos: " + editTexts.size());
                for (int i = 0; i < editTexts.size(); i++) {
                    nombresEncuestados.add(editTexts.get(i).getText().toString().trim());
                }
                System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
                for (int i = 0; i < nombresEncuestados.size(); i++) {
                    System.out.println("campo: " + nombresEncuestados.get(i));
                }
                System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
                dataLista = true;
                dialog.dismiss();
            }

        });

        //alert.setCancelable(false).setView(scrollView).show();
        */
    }


    private void poblarLista_TipoPreguntaAbierta(int numEncuestado) {

        if (numEncuestado != 1) {
            obtenerNombresEncuestados(numEncuestado);
            System.out.println("len nombresEncuestados: ");
        }
        System.out.println("cargar datos");
        System.out.println("datos: " + nombresEncuestados.size());

        if (numPregunta == 0) {
            for (int i = 0; i < nombresEncuestados.size(); i++) {

                TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = new TipoPreguntaAbiertaItem();
                tipoPreguntaAbiertaItem.setTitle(nombresEncuestados.get(i));

                miListaTipoPreguntaAbiertaAdapter.add(tipoPreguntaAbiertaItem);
            }

        }


        //colocar las aternativas
        //tvOpcionesPregunta.setText(datosOpc);

        //colocar el enunciado de la preguna
        //tvEnunciadoPregunta.setText();

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, miListaTipoPreguntaAbiertaAdapter));
        System.out.println("num pregunta: " + numPregunta);
        tipoPreguntaAbiertaAdapter.limpiarLista();
    }
}
