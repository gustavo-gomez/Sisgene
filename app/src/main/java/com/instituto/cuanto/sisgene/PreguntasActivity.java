package com.instituto.cuanto.sisgene;

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
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.adapter.TipoPreguntaAbiertaAdapter;
import com.instituto.cuanto.sisgene.entities.PreguntaOpcion;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;

import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    Button btnSiguiente;
    LinearLayout lyFragmentoListaPreguntas;
    //android.app.FragmentManager fragmentManager;
    ListView lvRespuestas_tipoUnica;
    static ArrayList<String> arrayList;
    TipoPreguntaAbiertaAdapter tipoPreguntaAbiertaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        arrayList = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoUnica = (ListView) findViewById(R.id.lvRespuestas_tipoUnica);

        //Se lee la primera pregunta que retorna un objeto de tipo PreguntaOpcion
        PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
        //preguntar a las base de datos

        //Obteniendo el tipo de pregunta
        //String tipoPregunta = preguntaOpcion.getPre_idPadre().getPre_tipo_rpta();
        String tipoPregunta = "UN";

        //obteniendo numero maximo de personas que deben responder las preguntas
        int numEncuestado = preguntaOpcion.getPre_idPadre().getPre_num_max_allegados();

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
            obtenerNombresEncuestados(15);
        }
/*
        for (int i = 0; i < title.length; i++) {
            // Create a new object for each list item
            TipoPreguntaAbiertaItem ld = new TipoPreguntaAbiertaItem();
            ld.setTitle(Nombres[i]);
            myList.add(ld);

        }
      */
    }

    public void obtenerNombresEncuestados(int num) {

        ArrayList<String> nombresEncuestados = null;
        final ArrayList<EditText> editTexts = null;
        ArrayList<TextView> textViews = null;

        final AlertDialog.Builder alert = new AlertDialog.Builder(PreguntasActivity.this);
        final LinearLayout lyinput = new LinearLayout(PreguntasActivity.this);
        lyinput.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i <= num; i++) {

            final TextView tvinput = new TextView(PreguntasActivity.this);
            tvinput.setText("Nombre " + i + ":  ");
            tvinput.setTextSize(20);

            final EditText etinput = new EditText(PreguntasActivity.this);
            etinput.setWidth(1000);

            textViews.add(tvinput);
            editTexts.add(etinput);
        }

        for (int i = 0; i <= num; i++) {
            lyinput.addView(textViews.get(i));
            lyinput.addView(editTexts.get(i));
        }
        alert.setTitle("Ingrese el nombre de las "+num+" personas");
        alert.setView(lyinput);


        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = editTexts.get(3).getText().toString().trim();
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.setCancelable(false).show();

        //return nombresEncuestados;
    }

}
