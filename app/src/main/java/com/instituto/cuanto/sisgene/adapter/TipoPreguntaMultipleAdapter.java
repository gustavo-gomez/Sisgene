package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMultipleItem;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaMultipleAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMultipleItem> myListPreguntaMultiple = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMultipleAdapter tipoPreguntaMultipleAdapter;

    int cantChequeados = 0;

    public TipoPreguntaMultipleAdapter(Context context, ArrayList<TipoPreguntaMultipleItem> myListPreguntaMultiple) {
        this.myListPreguntaMultiple = myListPreguntaMultiple;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.tipoPreguntaMultipleAdapter = this;
    }

    public void limpiarLista() {
        int dim = myListPreguntaMultiple.size();
        System.out.println("dim myListPreguntaMultiple:" + myListPreguntaMultiple.size());
        for (int i = 0; i < dim; i++) {
            myListPreguntaMultiple.remove(0);
        }
        tipoPreguntaMultipleAdapter.notifyDataSetChanged();
        System.out.println("dim myListPreguntaMultiple despues:" + myListPreguntaMultiple.size());
    }

    @Override
    public int getCount() {
        return myListPreguntaMultiple.size();
    }

    @Override
    public TipoPreguntaMultipleItem getItem(int position) {
        return myListPreguntaMultiple.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tipo_pregunta_multiple_layout, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.linear = (LinearLayout) convertView.findViewById(R.id.linear);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaMultipleItem tipoPreguntaMultipleItem = getItem(position);

        mViewHolder.tvTitle.setText(tipoPreguntaMultipleItem.getTitle());
        mViewHolder.linear.removeAllViews();
        if (!tipoPreguntaMultipleItem.getHasView()) {
            tipoPreguntaMultipleItem.lvLayout = new LinearLayout(context);
            fillLinearLayout(tipoPreguntaMultipleItem);
            tipoPreguntaMultipleItem.setHasView(true);
            System.out.println("Posicion: " + position + " linear vertical insertado");
        }
        mViewHolder.linear.addView(tipoPreguntaMultipleItem.lvLayout);

        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        LinearLayout linear;
    }

    private void fillLinearLayout(final TipoPreguntaMultipleItem tipoPreguntaMultipleItem) {
        ArrayList<String> alternativas = tipoPreguntaMultipleItem.getAlternativas();
        final ArrayList<String> respuestas = tipoPreguntaMultipleItem.getRespuestas();
        for (int i = 0; i < alternativas.size(); i++) {
            final CheckBox checkbox = new CheckBox(context);
            checkbox.setText(alternativas.get(i));
            checkbox.setChecked(false);
            checkbox.setId(i);
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (checkbox.isChecked()) {
                        System.out.println("Checked" + checkbox.getId());
                        respuestas.add(checkbox.getText().toString());
                    } else {
                        System.out.println("Un-Checked" + checkbox.getId());
                        int id = respuestas.indexOf(checkbox.getText().toString());
                        respuestas.remove(id);
                    }
                }
            });
            tipoPreguntaMultipleItem.lvLayout.addView(checkbox);
        }
    }
}
