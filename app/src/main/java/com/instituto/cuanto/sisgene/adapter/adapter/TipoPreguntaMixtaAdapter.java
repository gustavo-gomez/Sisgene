package com.instituto.cuanto.sisgene.adapter.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaMixtaAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMixtaItem> myListPreguntaMixta = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMixtaAdapter tipoPreguntaMixtaAdapter;
    int numMaxChequeados;
    int importancia;

    public TipoPreguntaMixtaAdapter(Context context, ArrayList<TipoPreguntaMixtaItem> myListPreguntaMixta,
                                    int numMaxChequeados, int importancia) {
        this.myListPreguntaMixta = myListPreguntaMixta;
        this.tipoPreguntaMixtaAdapter = this;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.numMaxChequeados = numMaxChequeados;
        this.importancia = importancia;
    }

    public void limpiarLista() {
        int dim = myListPreguntaMixta.size();
        System.out.println("dim myListPreguntaMixta:" + myListPreguntaMixta.size());
        for (int i = 0; i < dim; i++) {
            myListPreguntaMixta.remove(0);
        }
        this.tipoPreguntaMixtaAdapter.notifyDataSetChanged();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tipo_pregunta_mixta_layout, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.linear = (LinearLayout) convertView.findViewById(R.id.linear);
            mViewHolder.etPreguntaMixta = (EditText) convertView.findViewById(R.id.etPreguntaMixta);
            convertView.setTag(mViewHolder);
            notifyDataSetChanged();
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaMixtaItem currentTipoPreguntaMixtaItem = getItem(position);
        System.out.println("position "+ position );
        System.out.println("myListPreguntaMixta.size()" + myListPreguntaMixta.size());

        mViewHolder.tvTitle.setText(currentTipoPreguntaMixtaItem.getTitle());
        mViewHolder.linear.removeAllViews();
        if (!currentTipoPreguntaMixtaItem.getHasView()) {
            currentTipoPreguntaMixtaItem.lvLayout = new LinearLayout(context);
            currentTipoPreguntaMixtaItem.lvLayout.setOrientation(LinearLayout.VERTICAL);

            final ArrayList<String> alternativas = currentTipoPreguntaMixtaItem.getAlternativas();
            final ArrayList<String> respuestas = currentTipoPreguntaMixtaItem.getRespuestas();
            for (int i = 0; i < alternativas.size(); i++) {
                final CheckBox checkbox = new CheckBox(context);
                checkbox.setText(alternativas.get(i));
                checkbox.setChecked(false);
                checkbox.setId(i);
                checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        System.out.println("cantChequeados antes: " + respuestas.size());
                        System.out.println("usuario: "+position);
                        if (respuestas.size() < numMaxChequeados || respuestas.contains(checkbox.getText())) {
                            if (checkbox.isChecked()) {
                                System.out.println("Checked" + checkbox.getText());
                                respuestas.add(checkbox.getText().toString());
                                if (checkbox.getId() == (alternativas.size()-1)) {
                                    mViewHolder.etPreguntaMixta.setEnabled(true);
                                    mViewHolder.etPreguntaMixta.setVisibility(View.VISIBLE);
                                    mViewHolder.etPreguntaMixta.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {
                                            currentTipoPreguntaMixtaItem.setPreguntaMixta(s.toString());
                                        }
                                    });
                                }
                            } else {
                                System.out.println("Un-Checked" + checkbox.getText());
                                int id = respuestas.indexOf(checkbox.getText().toString());
                                System.out.println("elemento a borrar" + id);
                                respuestas.remove(id);
                                System.out.println("total de elementos"+respuestas.size());
                                if (checkbox.getId() == (alternativas.size()-1)) {
                                    mViewHolder.etPreguntaMixta.setEnabled(false);
                                    mViewHolder.etPreguntaMixta.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            System.out.println(respuestas.size()+" is checked:"+isChecked);
                            checkbox.setChecked(false);
                        }
                        System.out.println("cantChequeados despues: " + respuestas.size());
                    }
                });
                currentTipoPreguntaMixtaItem.lvLayout.addView(checkbox);
            }
            currentTipoPreguntaMixtaItem.setHasView(true);
            System.out.println("Posicion: " + position + " linear vertical insertado");
        }
        mViewHolder.linear.addView(currentTipoPreguntaMixtaItem.lvLayout);

        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        LinearLayout linear;
        EditText etPreguntaMixta;
    }


}
