package com.instituto.cuanto.sisgene.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.entities.ListViewTipoPregunta1Item;

import java.util.List;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class ListViewTipoPregunta1Adapter extends ArrayAdapter<ListViewTipoPregunta1Item> {

    public ListViewTipoPregunta1Adapter(Context context, List<ListViewTipoPregunta1Item> items) {
        super(context, R.layout.tipopreguntaabiertaitem_layout, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.tipopreguntaabiertaitem_layout, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            // viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvNombreEntrevistado = (TextView) convertView.findViewById(R.id.tvNombreEncuestado);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        ListViewTipoPregunta1Item item = getItem(position);
        //viewHolder.ivIcon.setImageDrawable(item.icon);
        viewHolder.tvNombreEntrevistado.setText(item.nombreEntrevistado);

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */
    private static class ViewHolder {
        //ImageView ivIcon;
        TextView tvNombreEntrevistado;
    }

}
