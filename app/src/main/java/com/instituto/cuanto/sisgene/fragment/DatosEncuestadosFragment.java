package com.instituto.cuanto.sisgene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.instituto.cuanto.sisgene.R;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class DatosEncuestadosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_datos_entrevistado, container, false);
        //tilNombresApellidos = (TextInputLayout) view.findViewById(R.id.tilNombresApellidos);
        //tilDireccion = (TextInputLayout) view.findViewById(R.id.tilDireccion);

        //String dato = this.getArguments().getString("message");

        //tilNombresApellidos.getEditText().setHint(dato);
        ////tilDireccion.getEditText().setText(dato);


        return view;
    }
}
