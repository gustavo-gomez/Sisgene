package com.instituto.cuanto.sisgene;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.instituto.cuanto.sisgene.fragment.DatosEncuestadosFragment;
import com.instituto.cuanto.sisgene.fragment.PreguntasFragment;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class DatosEncuestadosActivity extends AppCompatActivity {

    LinearLayout lyPreguntasEncuestados;
    Spinner spGradoInstruccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_encuestados);

        lyPreguntasEncuestados = (LinearLayout)findViewById(R.id.lyPreguntasEncuestados);
        spGradoInstruccion = (Spinner)findViewById(R.id.spGradoInstruccion);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DatosEncuestadosFragment datosEncuestadosFragment = new DatosEncuestadosFragment();
        fragmentTransaction.add(lyPreguntasEncuestados.getId(), datosEncuestadosFragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
