package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;
import com.instituto.cuanto.sisgene.fragment.ListViewTipoPregunta1Fragment;
import com.instituto.cuanto.sisgene.fragment.ListViewTipoPregunta2Fragment;
import com.instituto.cuanto.sisgene.fragment.PreguntasFragment;

import java.util.ArrayList;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity{

    Button btnSiguiente;
    LinearLayout lyFragmentoListaPreguntas;
    //android.app.FragmentManager fragmentManager;
    ListView lvRespuestas_tipoUnica;
    static ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);
        arrayList = new ArrayList<>();
        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
        lyFragmentoListaPreguntas = (LinearLayout)findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoUnica = (ListView)findViewById(R.id.lvRespuestas_tipoUnica);

        //Se lee la primera pregunta que retorna un objeto de tipo Pregunta
        String tipoPregunta="UN";

        if(tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaUnica)))
        {
            arrayList.clear();
            poblarLista_TipoPreguntaUnica();
        }
        else
            if(tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple)))
            {

            }
        else
            if(tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple)))
            {

            }
        else
            if(tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple)))
            {

            }
        else
            if(tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple)))
            {

            }
        else
            if(tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple)))
            {

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

    private void poblarLista_TipoPreguntaUnica() {


        for (int i = 0; i < title.length; i++) {
            // Create a new object for each list item
            TipoPreguntaUnicaItem ld = new TipoPreguntaUnicaItem();
            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }

}
