package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;
import com.instituto.cuanto.sisgene.mwtools.ExpandableHeightListview;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaMatrizMultipleAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMatrizItem> myListPreguntaMatriz = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMatrizMultipleAdapter tipoPreguntaMatrizAdapter;
    private Boolean mixta;

    public TipoPreguntaMatrizMultipleAdapter(Context context, ArrayList<TipoPreguntaMatrizItem> myListPreguntaMatriz) {
        this.myListPreguntaMatriz = myListPreguntaMatriz;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.mixta = mixta;
    }

    public void limpiarLista() {

        int dim = myListPreguntaMatriz.size();
        System.out.println("dim myListPreguntaMixta:" + myListPreguntaMatriz.size());
            for (int i = 0; i < dim; i++) {
                myListPreguntaMatriz.remove(0);
            }
        tipoPreguntaMatrizAdapter.notifyDataSetChanged();
        System.out.println("dim myListPreguntaMixta despues:" + myListPreguntaMatriz.size());
    }

    @Override
    public int getCount() {
        return myListPreguntaMatriz.size();
    }

    @Override
    public TipoPreguntaMatrizItem getItem(int position) {
        return myListPreguntaMatriz.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tipo_pregunta_mat_multiple, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.grid = (GridView) convertView.findViewById(R.id.grid);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaMatrizItem currentTipoPreguntaMatrizItem = getItem(position);

        mViewHolder.tvTitle.setText(currentTipoPreguntaMatrizItem.getTitle());
        mViewHolder.grid.setNumColumns(currentTipoPreguntaMatrizItem.getHorizontal().size()+1);
        mViewHolder.grid.setAdapter(new GridAdapter(context,currentTipoPreguntaMatrizItem));
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        GridView grid;
    }

}
