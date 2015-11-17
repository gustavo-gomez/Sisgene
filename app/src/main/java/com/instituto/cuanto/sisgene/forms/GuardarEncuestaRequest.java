
package com.instituto.cuanto.sisgene.forms;

import java.util.List;
import com.instituto.cuanto.sisgene.entidad.Allegado;
import com.instituto.cuanto.sisgene.entidad.CabeceraEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DetalleEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DireccionViviendaEncuestada;
import com.instituto.cuanto.sisgene.entidad.PersonaEncuestada;

/**
 *
 * @author JMonzalve
 */
public class GuardarEncuestaRequest {
    
    private PersonaEncuestada persona_encuestada ;
    private DireccionViviendaEncuestada direccion_viviendaencuestada;
    private CabeceraEncuestaRpta cab_enc_rpta;
    private List<DetalleEncuestaRpta> lista_det_enc_rpta;
    private List<Allegado> lista_allegados;

    public GuardarEncuestaRequest() {
        //Constructor de la clase GuardarEncuestaRequest
    }

    public PersonaEncuestada getPersona_encuestada() {
        return persona_encuestada;
    }

    public void setPersona_encuestada(PersonaEncuestada persona_encuestada) {
        this.persona_encuestada = persona_encuestada;
    }

    public DireccionViviendaEncuestada getDireccion_viviendaencuestada() {
        return direccion_viviendaencuestada;
    }

    public void setDireccion_viviendaencuestada(DireccionViviendaEncuestada direccion_viviendaencuestada) {
        this.direccion_viviendaencuestada = direccion_viviendaencuestada;
    }

    public CabeceraEncuestaRpta getCab_enc_rpta() {
        return cab_enc_rpta;
    }

    public void setCab_enc_rpta(CabeceraEncuestaRpta cab_enc_rpta) {
        this.cab_enc_rpta = cab_enc_rpta;
    }

    public List<DetalleEncuestaRpta> getLista_det_enc_rpta() {
        return lista_det_enc_rpta;
    }

    public void setLista_det_enc_rpta(List<DetalleEncuestaRpta> lista_det_enc_rpta) {
        this.lista_det_enc_rpta = lista_det_enc_rpta;
    }

    public List<Allegado> getLista_allegados() {
        return lista_allegados;
    }

    public void setLista_allegados(List<Allegado> lista_allegados) {
        this.lista_allegados = lista_allegados;
    }
    
    
    
}
