package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaUnicaAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaUnicaItem> myListPreguntaUnica = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaUnicaAdapter tipoPreguntaUnicaAdapter;

    public TipoPreguntaUnicaAdapter() {
    }

    public TipoPreguntaUnicaAdapter(Context context, ArrayList<TipoPreguntaUnicaItem> myListPreguntaUnica) {
        this.myListPreguntaUnica = myListPreguntaUnica;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void limpiarLista() {

        int dim = myListPreguntaUnica.size();
        System.out.println("dim myListPreguntaUnica:" + myListPreguntaUnica.size());

        for (int i = 0; i < dim; i++) {
            myListPreguntaUnica.remove(0);
        }
        tipoPreguntaUnicaAdapter.notifyDataSetChanged();
        System.out.println("dim myListPreguntaUnica despues:" + myListPreguntaUnica.size());
    }

    @Override
    public int getCount() {
        return myListPreguntaUnica.size();
    }

    @Override
    public TipoPreguntaUnicaItem getItem(int position) {
        return myListPreguntaUnica.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tipopregunta_unica_item_layout, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.spRespuesta = (Spinner) convertView.findViewById(R.id.spRespuestaPreguntaEncuestado);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaUnicaItem currentTipoPreguntaUnicaItem = getItem(position);

        mViewHolder.tvTitle.setText(currentTipoPreguntaUnicaItem.getTitle());
        //se carga el spinnner con los datos de las alternativas
        ArrayAdapter<String> arrayValoresAlternativasAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, currentTipoPreguntaUnicaItem.getAlternativasAsArray());

        arrayValoresAlternativasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mViewHolder.spRespuesta.setAdapter(arrayValoresAlternativasAdapter);

        mViewHolder.spRespuesta.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        currentTipoPreguntaUnicaItem.setRespuesta(parent.getSelectedItem().toString().trim());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        Spinner spRespuesta;
    }

}
