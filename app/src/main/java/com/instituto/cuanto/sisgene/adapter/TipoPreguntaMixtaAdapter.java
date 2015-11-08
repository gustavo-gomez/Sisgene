package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaMixtaAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMixtaItem> myListPreguntaMixta = new ArrayList<TipoPreguntaAbiertaItem>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMixtaAdapter tipoPreguntaMixtaAdapter;

    public TipoPreguntaMixtaAdapter() {
    }

    public TipoPreguntaMixtaAdapter(Context context, ArrayList<TipoPreguntaAbiertaItem> myListPreguntaMixta) {
        this.myListPreguntaMixta = myListPreguntaMixta;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void limpiarLista() {
        int dim = myListPreguntaMixta.size();
        System.out.println("dim myListPreguntaAbierta:" + myListPreguntaMixta.size());
        for (int i = 0; i < dim; i++){
            myListPreguntaMixta.remove(0);
        }
        tipoPreguntaMixtaAdapter.notifyDataSetChanged();
        System.out.println("dim myListPreguntaAbierta despues:" + myListPreguntaMixta.size());
    }

    @Override
    public int getCount() {
        return myListPreguntaMixta.size();
    }

    @Override
    public TipoPreguntaMixtaItem getItem(int position) {
        return myListPreguntaMixta.get(position);
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
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.etRpta = (EditText) convertView.findViewById(R.id.etRespuestaPreguntaEncuestado);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaAbiertaItem currentTipoPreguntaAbiertaItem = getItem(position);

        mViewHolder.tvTitle.setText(currentTipoPreguntaAbiertaItem.getTitle());
        mViewHolder.etRpta.setHint(currentTipoPreguntaAbiertaItem.getDescription());

        final int i = position;
        mViewHolder.etRpta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //((ListItem) myItems.get(i)).caption = s.toString();
                currentTipoPreguntaAbiertaItem.setDescription(s.toString());
            }
        });

        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        EditText etRpta;

    }

}
