package com.instituto.cuanto.sisgene.entities;

import android.widget.EditText;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaAbiertaItem {
    String description;
    String title;
    String subtipo;
    String tiponumerico;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getTiponumerico() {
        return tiponumerico;
    }

    public void setTiponumerico(String tiponumerico) {
        this.tiponumerico = tiponumerico;
    }
}
