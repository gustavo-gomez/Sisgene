package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaUnicaAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaAbiertaItem> myListPreguntaUnica = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaUnicaAdapter tipoPreguntaAbiertaAdapter;
    public static String[] arrayvaloresAlternativas = new String[]{};

    public TipoPreguntaUnicaAdapter() {
    }

    public TipoPreguntaUnicaAdapter(Context context, ArrayList<TipoPreguntaAbiertaItem> myListPreguntaUnica) {
        this.myListPreguntaUnica = myListPreguntaUnica;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void limpiarLista() {

        int dim = myListPreguntaUnica.size();
        System.out.println("dim myListPreguntaUnica:" + myListPreguntaUnica.size());
        //for (int i = 0; i < dim; i++)
        myListPreguntaUnica.remove(0);

        tipoPreguntaAbiertaAdapter.notifyDataSetChanged();
        System.out.println("dim myListPreguntaUnica despues:" + myListPreguntaUnica.size());
    }

    @Override
    public int getCount() {
        return myListPreguntaUnica.size();
    }

    @Override
    public TipoPreguntaAbiertaItem getItem(int position) {
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
            convertView = inflater.inflate(R.layout.tipopreguntaabiertaitem_layout, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.spRespuesta = (Spinner) convertView.findViewById(R.id.spRespuestaPreguntaEncuestado);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaAbiertaItem currentTipoPreguntaAbiertaItem = getItem(position);

        mViewHolder.tvTitle.setText(currentTipoPreguntaAbiertaItem.getTitle());
        //mViewHolder.tvDesc.setText(currentTipoPreguntaAbiertaItem.getDescription());
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        Spinner spRespuesta;

        public MyViewHolder(View item) {

        }
    }

}
