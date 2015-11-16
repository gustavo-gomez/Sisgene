package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizSimpleItem;

import java.util.ArrayList;

/**
 * Created by Luis Benavides on 27/10/2015.
 */
public class TipoPreguntaMatrizSimpleAdapter extends BaseAdapter {

    public static ArrayList<TipoPreguntaMatrizSimpleItem> myListPreguntaMatriz = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    public static TipoPreguntaMatrizSimpleAdapter tipoPreguntaMatrizAdapter;

    public TipoPreguntaMatrizSimpleAdapter(Context context, ArrayList<TipoPreguntaMatrizSimpleItem> myListPreguntaMatriz) {
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
    public TipoPreguntaMatrizSimpleItem getItem(int position) {
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

        final TipoPreguntaMatrizSimpleItem currentTipoPreguntaMatrizSimpleItem = getItem(position);
        mViewHolder.tvTitle.setText(currentTipoPreguntaMatrizSimpleItem.getTitle());
        mViewHolder.grid.removeAllViews();
        if(!currentTipoPreguntaMatrizSimpleItem.getHasView()){
            currentTipoPreguntaMatrizSimpleItem.tbLayout = new TableLayout(context);
            fillTableLayout(currentTipoPreguntaMatrizSimpleItem);
            currentTipoPreguntaMatrizSimpleItem.setHasView(true);
            System.out.println("Posicion: "+position+" tabla insertada");
        }
        mViewHolder.grid.addView(currentTipoPreguntaMatrizSimpleItem.tbLayout);
        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle;
        TableLayout grid;
    }

    private void fillTableLayout(final TipoPreguntaMatrizSimpleItem currentTipoPreguntaMatrizItem){
        for (int i = 0; i < currentTipoPreguntaMatrizItem.getVertical().size()+1; i++) {
            TableRow tableRow = new TableRow(context);
            final RadioGroup group = new RadioGroup(context);
            final LinearLayout linear= new LinearLayout(context);

            for (int j = 0; j < currentTipoPreguntaMatrizItem.getHorizontal().size(); j++) {
                if (i!=0 && j != 0) {
                    final RadioButton radio = new RadioButton(context);
                    radio.setText(currentTipoPreguntaMatrizItem.getHorizontal().get(j - 1));
                    radio.setChecked(false);
                    radio.setPadding(40, 20, 20, 20);
                    group.setId(i-1);
                    group.addView(radio);
                    if(group.getChildCount()+1==currentTipoPreguntaMatrizItem.getHorizontal().size()){
                        group.setOrientation(RadioGroup.HORIZONTAL);
                        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                        {
                            public void onCheckedChanged(RadioGroup rGroup, int checkedId)
                            {
                                RadioButton radio = (RadioButton)rGroup.findViewById(checkedId);
                                ArrayList<String> respuestas = currentTipoPreguntaMatrizItem.getRespuestas();
                                if(radio.isChecked()){
                                    respuestas.set(rGroup.getId(),radio.getText().toString());
                                    System.out.println("fila:"+ rGroup.getId() + "Checked:" + radio.getText());
                                }else{
                                    System.out.println("fila:"+ rGroup.getId() + "Unchecked:" + radio.getText());
                                    respuestas.remove(rGroup.getId());
                                }
                            }
                        });
                        tableRow.addView(group);
                    }
                } else {
                    TextView textView = new TextView(context);
                    if (i == 0 && j == 0) {
                        textView.setText("Tipo de Servicio");
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
