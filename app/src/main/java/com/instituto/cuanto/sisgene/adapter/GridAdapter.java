package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.MixtaAlternativa;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizItem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by USUARIO on 08/11/2015.
 */
public class GridAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    public TipoPreguntaMatrizItem myListaGrid;
    private Context context;

    public GridAdapter(Context context, TipoPreguntaMatrizItem myListaGrid) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.myListaGrid = myListaGrid;
    }

    @Override
    public int getCount() {
        return myListaGrid.getVertical().size()*(myListaGrid.getHorizontal().size()+1);
    }

    @Override
    public String getItem(int position) {
        String data;
        int fila = position/(myListaGrid.getHorizontal().size()+1);

        if(position==0){
            data = myListaGrid.getTitle();
        }else {
            if(position%(myListaGrid.getHorizontal().size()+1)==0){
                data = myListaGrid.getVertical().get(fila);
            }else{
                if(fila==0){
                    data = myListaGrid.getHorizontal().get(position-1);
                }else{
                    int columna = position%(myListaGrid.getHorizontal().size()+1);
                    data = null;
                }
            }
        }
        return data;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            mViewHolder = new MyViewHolder();
            convertView = layoutInflater.inflate(R.layout.tipo_pregunta_mat_multiple_item, parent, false);
            convertView.setTag(mViewHolder);
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.textView);
            mViewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBox);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        int fila = position/(myListaGrid.getHorizontal().size()+1);

        if(position==0){
            mViewHolder.tvTitle.setVisibility(View.VISIBLE);
            mViewHolder.tvTitle.setText(getItem(position));
            mViewHolder.checkbox.setVisibility(View.GONE);
        }else {
            if(position%(myListaGrid.getHorizontal().size()+1)==0){
                mViewHolder.tvTitle.setVisibility(View.VISIBLE);
                mViewHolder.tvTitle.setText(getItem(position));
                mViewHolder.checkbox.setVisibility(View.GONE);
            }else{
                if(fila==0){
                    mViewHolder.tvTitle.setVisibility(View.VISIBLE);
                    mViewHolder.tvTitle.setText(getItem(position));
                    mViewHolder.checkbox.setVisibility(View.GONE);
                }else{
                    mViewHolder.checkbox.setVisibility(View.VISIBLE);
                    mViewHolder.tvTitle.setVisibility(View.GONE);
                }
            }
        }
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        CheckBox checkbox;
    }
}
