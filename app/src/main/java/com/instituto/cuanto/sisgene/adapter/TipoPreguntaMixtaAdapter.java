package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizItem;
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
    private Boolean mixta;
    int numMaxChequeados;
    boolean importancia;

    public TipoPreguntaMixtaAdapter(Context context, ArrayList<TipoPreguntaMixtaItem> myListPreguntaMixta, Boolean mixta,
                                    int numMaxChequeados, boolean importancia) {
        this.myListPreguntaMixta = myListPreguntaMixta;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.mixta = mixta;
        this.numMaxChequeados = numMaxChequeados;
        this.importancia = importancia;
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
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tipo_pregunta_mixta_layout, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.linear = (LinearLayout) convertView.findViewById(R.id.linear);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaMixtaItem currentTipoPreguntaMixtaItem = getItem(position);

        mViewHolder.tvTitle.setText(currentTipoPreguntaMixtaItem.getTitle());
        mViewHolder.linear.removeAllViews();
        if(!currentTipoPreguntaMixtaItem.getHasView()){
            currentTipoPreguntaMixtaItem.lvLayout = new LinearLayout(context);
            fillLinearLayout(currentTipoPreguntaMixtaItem);
            currentTipoPreguntaMixtaItem.setHasView(true);
            System.out.println("Posicion: "+position+" linear vertical insertado");
        }
        mViewHolder.linear.addView(currentTipoPreguntaMixtaItem.lvLayout);

        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        LinearLayout linear;
    }

    private void fillLinearLayout(final TipoPreguntaMixtaItem currentTipoPreguntaMixtaItem) {
        ArrayList<String> alternativas = currentTipoPreguntaMixtaItem.getAlternativas();
        final ArrayList<String> respuestas = currentTipoPreguntaMixtaItem.getRespuestas();
        for (int i=0;i<alternativas.size();i++){
            final CheckBox checkbox = new CheckBox(context);
            checkbox.setText(alternativas.get(i));
            checkbox.setChecked(false);
            checkbox.setId(i);
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (checkbox.isChecked()) {
                        System.out.println("Checked"+checkbox.getId());
                        respuestas.add(checkbox.getText().toString());
                    } else {
                        System.out.println("Un-Checked" + checkbox.getId());
                        int id = respuestas.indexOf(checkbox.getText().toString());
                        respuestas.remove(id);
                    }
                }
            });
            currentTipoPreguntaMixtaItem.lvLayout.addView(checkbox);
        }
    }
}
