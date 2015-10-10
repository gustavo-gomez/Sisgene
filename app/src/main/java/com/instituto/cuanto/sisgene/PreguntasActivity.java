package com.instituto.cuanto.sisgene;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.instituto.cuanto.sisgene.fragment.ListViewTipoPregunta1Fragment;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity{

    Button btnSiguiente;
    LinearLayout lyFragmentoListaPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntaslayout);

        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
        lyFragmentoListaPreguntas = (LinearLayout)findViewById(R.id.lyFragmentoListaPreguntas);

        //La primera vez carga la primera pregunta

        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListViewTipoPregunta1Fragment listViewTipoPregunta1Fragment = new ListViewTipoPregunta1Fragment();
        fragmentTransaction.add(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta1Fragment);
        fragmentTransaction.addToBackStack(null).commit();

        //btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);

    }
}
