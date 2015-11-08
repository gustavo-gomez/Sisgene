package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;
import com.instituto.cuanto.sisgene.mwtools.ExpandableHeightListview;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaMixtaAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMixtaItem> myListPreguntaMixta = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMixtaAdapter tipoPreguntaMixtaAdapter;

    public TipoPreguntaMixtaAdapter() {
    }

    public TipoPreguntaMixtaAdapter(Context context, ArrayList<TipoPreguntaMixtaItem> myListPreguntaMixta) {
        this.myListPreguntaMixta = myListPreguntaMixta;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public void limpiarLista() {

        int dim = myListPreguntaMixta.size();
        System.out.println("dim myListPreguntaMixta:" + myListPreguntaMixta.size());
            for (int i = 0; i < dim; i++) {
                myListPreguntaMixta.remove(0);
            }
        tipoPreguntaMixtaAdapter.notifyDataSetChanged();
        System.out.println("dim myListPreguntaMixta despues:" + myListPreguntaMixta.size());
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
            convertView = inflater.inflate(R.layout.tipo_pregunta_mixta_layout, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.listRespuesta = (ExpandableHeightListview) convertView.findViewById(R.id.listView);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaMixtaItem currentTipoPreguntaMixtaItem = getItem(position);

        mViewHolder.tvTitle.setText(currentTipoPreguntaMixtaItem.getTitle());
        //se carga el listview con los datos de las alternativas
        mViewHolder.listRespuesta.setAdapter(new CheckboxesAdapter(context, currentTipoPreguntaMixtaItem.getAlternativasAsArrayList()));
        mViewHolder.listRespuesta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(context,
                        myListPreguntaMixta.get(position).getTitle(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        ExpandableHeightListview listRespuesta;
    }

}
