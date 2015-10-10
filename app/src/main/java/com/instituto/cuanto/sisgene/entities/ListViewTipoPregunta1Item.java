package com.instituto.cuanto.sisgene.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by Gustavo on 10/10/2015.
 */
    public class ListViewTipoPregunta1Item {


    //public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String nombreEntrevistado;        // the text for the ListView item title


    // public ListViewTipoPregunta1Item(Drawable icon, String nombreEntrevistado) {
    public ListViewTipoPregunta1Item(String nombreEntrevistado) {
        // this.icon = icon;
        this.nombreEntrevistado = nombreEntrevistado;
    }
}
