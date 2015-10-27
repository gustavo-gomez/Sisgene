package com.instituto.cuanto.sisgene;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    Button btnSiguiente;
    LinearLayout lyFragmentoListaPreguntas;
    //android.app.FragmentManager fragmentManager;
    ListView lvRespuestas_tipoGeneral;
    static ArrayList<String> arrayList;
    TipoPreguntaAbiertaAdapter tipoPreguntaAbiertaAdapter;
    ArrayList<String> nombresEncuestados = null;
    ArrayList<TipoPreguntaAbiertaItem> miListaTipoPreguntaAbiertaAdapter;
    Context context = PreguntasActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        arrayList = new ArrayList<>();
        nombresEncuestados = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoGeneral = (ListView) findViewById(R.id.lvRespuestas_tipoGeneral);
        tipoPreguntaAbiertaAdapter = new TipoPreguntaAbiertaAdapter();
        miListaTipoPreguntaAbiertaAdapter = new ArrayList<>();
        //Se lee la primera pregunta que retorna un objeto de tipo PreguntaOpcion
        PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
        //preguntar a las base de datos

        //Obteniendo el tipo de pregunta
        //String tipoPregunta = preguntaOpcion.getPre_idPadre().getPre_tipo_rpta();
        String tipoPregunta = "UN";

        //obteniendo numero maximo de personas que deben responder las preguntas
        //int numEncuestado = preguntaOpcion.getPre_idPadre().getPre_num_max_allegados();
        int numEncuestado = 15;

        if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaAbierta(numEncuestado);

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        }


        //se comenta el uso de fragmentos
        /*
        //La primera vez carga la primera pregunta
        fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListViewTipoPregunta1Fragment listViewTipoPregunta1Fragment = new ListViewTipoPregunta1Fragment();
        fragmentTransaction.add(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta1Fragment);
        fragmentTransaction.commit();
        */
        //btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(PreguntasActivity.this, PreguntasActivity.class);
            startActivity(intent);
            /*
            fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListViewTipoPregunta2Fragment listViewTipoPregunta2Fragment = new ListViewTipoPregunta2Fragment();
            fragmentTransaction.replace(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta2Fragment);
            fragmentTransaction.commit();*/
            /*
            fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListViewTipoPregunta2Fragment listViewTipoPregunta2Fragment = new ListViewTipoPregunta2Fragment();
            fragmentTransaction.replace(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta2Fragment);
            fragmentTransaction.commit();
            */
        }
    };

    private void poblarLista_TipoPreguntaAbierta(int numEncuestado) {

        String[] Nombres = new String[]{
                "Gustavo", "Jesus", "Miguel", "Jhon",
                "Pedro", "javier"
        };
        if (numEncuestado != 1) {
            obtenerNombresEncuestados(numEncuestado);
        }

        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = new TipoPreguntaAbiertaItem();
            tipoPreguntaAbiertaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaAbiertaItem.setDescription("");
            miListaTipoPreguntaAbiertaAdapter.add(tipoPreguntaAbiertaItem);
        }
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, miListaTipoPreguntaAbiertaAdapter));
    }

    public void obtenerNombresEncuestados(int num) {


        final ArrayList<EditText> editTexts = new ArrayList<>();
        final ArrayList<TextView> textViews = new ArrayList<>();

        final AlertDialog.Builder alert = new AlertDialog.Builder(PreguntasActivity.this);
        ScrollView scrollView = new ScrollView(PreguntasActivity.this);
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
        alert.setTitle("Ingrese el nombre de las " + num + " personas");
        scrollView.addView(lyinput);
        alert.setView(scrollView);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < editTexts.size(); i++) {
                    nombresEncuestados.add(editTexts.get(i).getText().toString().trim());
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.setCancelable(false).show();
    }

}
