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
import com.instituto.cuanto.sisgene.entities.RespuestaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizMultipleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizSimpleItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luis Benavides on 27/10/2015.
 */
public class TipoPreguntaMatrizMultipleAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMatrizMultipleItem> myListPreguntaMatriz = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMatrizMultipleAdapter tipoPreguntaMatrizAdapter;

    public TipoPreguntaMatrizMultipleAdapter(Context context, ArrayList<TipoPreguntaMatrizMultipleItem> myListPreguntaMatriz) {
        this.myListPreguntaMatriz = myListPreguntaMatriz;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.tipoPreguntaMatrizAdapter = this;
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
    public TipoPreguntaMatrizMultipleItem getItem(int position) {
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

        final TipoPreguntaMatrizMultipleItem currentTipoPreguntaMatrizMultipleItem = getItem(position);
        mViewHolder.tvTitle.setText(currentTipoPreguntaMatrizMultipleItem.getTitle());
        mViewHolder.grid.removeAllViews();
        if(!currentTipoPreguntaMatrizMultipleItem.getHasView()){
            currentTipoPreguntaMatrizMultipleItem.tbLayout = new TableLayout(context);
            fillTableLayout(currentTipoPreguntaMatrizMultipleItem);
            currentTipoPreguntaMatrizMultipleItem.setHasView(true);
            System.out.println("Posicion: "+position+" tabla insertada");
        }
        mViewHolder.grid.addView(currentTipoPreguntaMatrizMultipleItem.tbLayout);
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        TableLayout grid;
    }

    private void fillTableLayout(final TipoPreguntaMatrizMultipleItem currentTipoPreguntaMatrizMultipleItem){
        for (int i = 0; i < currentTipoPreguntaMatrizMultipleItem.getVertical().size()+1 ; i++) {
            TableRow tableRow = new TableRow(context);
            for (int j = 0; j < currentTipoPreguntaMatrizMultipleItem.getHorizontal().size(); j++) {
                if (i != 0 && j != 0) {
                    final CheckBox checkbox = new CheckBox(context);
                    RespuestaItem rtemp =  new RespuestaItem();
                    rtemp.setRow(i - 1);
                    rtemp.setCol(j - 1);
                    checkbox.setTag(rtemp);
                    checkbox.setText(currentTipoPreguntaMatrizMultipleItem.getHorizontal().get(j - 1));
                    checkbox.setChecked(false);
                    checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            RespuestaItem ritem = (RespuestaItem) checkbox.getTag();
                            if(checkbox.isChecked()){
                                System.out.println("Checked " + checkbox.getText());
                                ritem.setTexto(checkbox.getText().toString());
                                currentTipoPreguntaMatrizMultipleItem.getRespuestas().add(ritem);
                            }else{
                                System.out.println("Un-Checked " + checkbox.getText());
                                int id = currentTipoPreguntaMatrizMultipleItem.buscarRespuestas(checkbox.getText().toString());
                                currentTipoPreguntaMatrizMultipleItem.getRespuestas().remove(id);
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
                    if (i != 0 && j == 0) {
                        textView.setText(currentTipoPreguntaMatrizMultipleItem.getVertical().get(i - 1));
                        textView.setPadding(20, 20, 20, 20);
                        tableRow.addView(textView);
                    }
                }
            }
            currentTipoPreguntaMatrizMultipleItem.tbLayout.addView(tableRow);
        }
    }
}
