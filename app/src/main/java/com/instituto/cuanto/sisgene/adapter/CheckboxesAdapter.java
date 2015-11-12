package com.instituto.cuanto.sisgene.adapter;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.MixtaAlternativa;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;

/**
 * Created by USUARIO on 08/11/2015.
 */
public class CheckboxesAdapter extends BaseAdapter implements View.OnClickListener{

    private LayoutInflater layoutInflater;
    public ArrayList<MixtaAlternativa> myListaCheckboxes;
    private Context context;
    private Boolean mixta;
    public CheckboxesAdapter(Context context, ArrayList<MixtaAlternativa> myListaCheckboxes, Boolean mixta) {
        this.myListaCheckboxes = myListaCheckboxes;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.mixta = mixta;
    }

    @Override
    public int getCount() {
        return myListaCheckboxes.size();
    }

    @Override
    public MixtaAlternativa getItem(int position) {
        return myListaCheckboxes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.tipo_pregunta_mixta_item, parent, false);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.editText = (EditText) convertView.findViewById(R.id.editText);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final MixtaAlternativa row = getItem(position);
        holder.checkBox.setTag(position);
        holder.checkBox.setText(row.getTitle());
        holder.checkBox.setChecked(row.getValue());
        holder.checkBox.setOnClickListener(this);
        if(position==getCount()-1 && mixta){
            holder.editText.setVisibility(View.VISIBLE);
        }else{
            holder.editText.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        CheckBox checkBox = (CheckBox) v;
        int position = (Integer) v.getTag();
        getItem(position).setValue(checkBox.isChecked());
        System.out.println("selecciono: " + checkBox.getText());
    }

    private static class Holder {
        CheckBox checkBox;
        EditText editText;
    }

}
