package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizItem;

import java.util.ArrayList;

/**
 * Created by Luis Benavides on 27/10/2015.
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
            convertView = inflater.inflate(R.layout.tipo_pregunta_matriz, parent, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            mViewHolder.grid = (TableLayout) convertView.findViewById(R.id.tlPreguntas);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        final TipoPreguntaMatrizItem currentTipoPreguntaMatrizItem = getItem(position);
        mViewHolder.tvTitle.setText(currentTipoPreguntaMatrizItem.getTitle());
        mViewHolder.grid.removeAllViews();
        if(!currentTipoPreguntaMatrizItem.getHasView()){
            currentTipoPreguntaMatrizItem.tbLayout = new TableLayout(context);
            fillTableLayout(currentTipoPreguntaMatrizItem);
            currentTipoPreguntaMatrizItem.setHasView(true);
            System.out.println("Posicion: "+position+" tabla insertada");
        }
        mViewHolder.grid.addView(currentTipoPreguntaMatrizItem.tbLayout);
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        TableLayout grid;
    }

    private void fillTableLayout(final TipoPreguntaMatrizItem currentTipoPreguntaMatrizItem){
        for (int i = 0; i <currentTipoPreguntaMatrizItem.getVertical().size()+1 ; i++) {
            TableRow tableRow = new TableRow(context);
            for (int j = 0; j < currentTipoPreguntaMatrizItem.getHorizontal().size(); j++) {
                if (i != 0 && j != 0) {
                    final CheckBox checkbox = new CheckBox(context);
                    checkbox.setText("");
                    checkbox.setChecked(false);
                    checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            if(checkbox.isChecked()){
                                System.out.println("Checked");
                            }else{
                                System.out.println("Un-Checked");
                            }
                        }
                    });
                    tableRow.addView(checkbox);
                } else {
                    TextView textView = new TextView(context);
                    if (i == 0 && j == 0) {
                        textView.setText("Tipo de Servicio");
                        textView.setPadding(20, 20, 20, 20);
                        tableRow.addView(textView);
                    }
                    if (i == 0 && j != 0) {
                        textView.setText(currentTipoPreguntaMatrizItem.getHorizontal().get(j - 1));
                        textView.setPadding(20, 20, 20, 20);
                        tableRow.addView(textView);
                    }
                    if (i != 0 && j == 0) {
                        textView.setText(currentTipoPreguntaMatrizItem.getVertical().get(i - 1));
                        textView.setPadding(20, 20, 20, 20);
                        tableRow.addView(textView);
                    }
                }
            }
            currentTipoPreguntaMatrizItem.tbLayout.addView(tableRow);
        }
    }
}
