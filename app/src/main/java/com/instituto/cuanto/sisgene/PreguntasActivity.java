package com.instituto.cuanto.sisgene;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.instituto.cuanto.sisgene.fragment.ListViewTipoPregunta1Fragment;
import com.instituto.cuanto.sisgene.fragment.ListViewTipoPregunta2Fragment;
import com.instituto.cuanto.sisgene.fragment.PreguntasFragment;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity{

    Button btnSiguiente;
    LinearLayout lyFragmentoListaPreguntas;
    android.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
        lyFragmentoListaPreguntas = (LinearLayout)findViewById(R.id.lyFragmentoListaPreguntas);

        //La primera vez carga la primera pregunta
        fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListViewTipoPregunta1Fragment listViewTipoPregunta1Fragment = new ListViewTipoPregunta1Fragment();
        fragmentTransaction.add(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta1Fragment);
        fragmentTransaction.commit();

        //btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            System.out.println("sdacsadasdasdvsavfdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
            ListViewTipoPregunta2Fragment listViewTipoPregunta2Fragment = new ListViewTipoPregunta2Fragment();
            fragmentTransaction.replace(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta2Fragment);
            fragmentTransaction.commit();
        }
    };

}
