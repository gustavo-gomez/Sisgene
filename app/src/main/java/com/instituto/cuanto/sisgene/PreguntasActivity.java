package com.instituto.cuanto.sisgene;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.adapter.TipoPreguntaAbiertaAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMixtaAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaUnicaAdapter;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    Button btnSiguiente;
    ListView lvRespuestas_tipoGeneral;
    //TipoPreguntaAbiertaAdapter tipoPreguntaAbiertaAdapter;

    static ArrayList<String> nombresEncuestados;
    //ArrayList<TipoPreguntaAbiertaItem> miListaTipoPreguntaAbierta;
    Context context = PreguntasActivity.this;

    int numPregunta = -1;
    ScrollView scrollView;
    TextView tvEnunciadoPregunta;
    TextView tvOpcionesPregunta;
    String tipoPreguntaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        nombresEncuestados = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        //lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoGeneral = (ListView) findViewById(R.id.lvRespuestas_tipoGeneral);
        tvEnunciadoPregunta = (TextView) findViewById(R.id.tvEnunciadoPregunta);
        tvOpcionesPregunta = (TextView) findViewById(R.id.tvOpcionesPregunta);
        TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter = new TipoPreguntaAbiertaAdapter();
        //miListaTipoPreguntaAbierta = new ArrayList<>();
        scrollView = new ScrollView(PreguntasActivity.this);


        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(NombresPersonasEncuestadasActivity.KEY_ARG_NOMBRES_ENCUESTADOS)) {
            System.out.println("se obtienen datos");
            nombresEncuestados = getIntent().getStringArrayListExtra(NombresPersonasEncuestadasActivity.KEY_ARG_NOMBRES_ENCUESTADOS);
        }

        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);

        //leer todos los datos de la primera pregunta
        String tipoPregunta = "AB";
        tipoPreguntaActual = tipoPregunta;
        leerTipoPreguntaxPregunta(tipoPregunta);

    }

    private void leerTipoPreguntaxPregunta(String tipoPregunta) {
        //eliminar cuando se haya hecho la consulta a la base de datos

        boolean encuestarTodos = true;

        //Tipo de pregunta Unica
        if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaUnica(encuestarTodos);

            //Tipo de pregunta Multiple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();


            //Tipo de pregunta abierta
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            //TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaAbierta(encuestarTodos);

            //Tipo de pregunta Matriz Simple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Matriz Multiple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Mixta
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            // tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaMixta(encuestarTodos);
        }
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //System.out.println("antes: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
            //miListaTipoPreguntaAbierta.remove(0);

            leerDatos();
            leerRespuestasTipoAbierta();
            System.out.println("respuestas leidas -   se borra la lista");

            //TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();
            //TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.limpiarLista();

            //lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, TipoPreguntaAbiertaAdapter.myListPreguntaAbierta));
            //System.out.println("despues: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());

            leerTipoPreguntaxPregunta("MI");
        }
    };

    private void leerDatos() {
        //Leer los dato segun el tipo de pregunta actual


        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {

        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {

        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            leerRespuestasTipoAbierta();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {

        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {

        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMixta))) {

        }
    }

    private void leerRespuestasTipoAbierta() {
        System.out.println("len datos size: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
        System.out.println("len datos count: " + TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getCount());

        for (int i = 0; i < TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getItem(i);
            System.out.println("nombre: " + tipoPreguntaAbiertaItem.getTitle());
            System.out.println("respuesta: " + tipoPreguntaAbiertaItem.getDescription());
        }
    }

    private void leerRespuestasTipoUnica() {
        System.out.println("len datos size: " + TipoPreguntaUnicaAdapter.myListPreguntaUnica.size());
        System.out.println("len datos count: " + TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.getCount());

        for (int i = 0; i < TipoPreguntaUnicaAdapter.myListPreguntaUnica.size(); i++) {
            TipoPreguntaUnicaItem tipoPreguntaUnicaItem = TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.getItem(i);
            System.out.println("nombre: " + tipoPreguntaUnicaItem.getTitle());
            System.out.println("respuesta: " + tipoPreguntaUnicaItem.getRespuesta());
        }
    }

    private void poblarLista_TipoPreguntaAbierta(boolean encuestarTodos) {

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = new TipoPreguntaAbiertaItem();
            tipoPreguntaAbiertaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaAbiertaItem.setDescription(nombresEncuestados.get(i));

            TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.add(tipoPreguntaAbiertaItem);
            if (i == 0 && encuestarTodos == false)
                break;
        }
        System.out.println("miListaTipoPreguntaAbierta: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
        //colocar las aternativas
        //tvOpcionesPregunta.setText(datosOpc);

        //colocar el enunciado de la preguna
        //tvEnunciadoPregunta.setText();

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, TipoPreguntaAbiertaAdapter.myListPreguntaAbierta));
        System.out.println("num pregunta: " + numPregunta);
        //tipoPreguntaAbiertaAdapter.limpiarLista();
    }

    private void poblarLista_TipoPreguntaUnica(boolean encuestarTodos) {
        HashMap<Integer,String> alternativas = new HashMap<>();
        for (int i=0;i<5;i++) {
            alternativas.put(i+1, "Opcion "+(i+1));
        }

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaUnicaItem tipoPreguntaUnicaItem = new TipoPreguntaUnicaItem();
            tipoPreguntaUnicaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaUnicaItem.setAlternativas(alternativas);
            TipoPreguntaUnicaAdapter.myListPreguntaUnica.add(tipoPreguntaUnicaItem);
            if (i == 0 && encuestarTodos == false)
                break;
        }
        System.out.println("miListaTipoPreguntaUnica: " + TipoPreguntaUnicaAdapter.myListPreguntaUnica.size());

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaUnicaAdapter(context, TipoPreguntaUnicaAdapter.myListPreguntaUnica));
        System.out.println("num pregunta: " + numPregunta);
    }

    private void poblarLista_TipoPreguntaMixta(boolean encuestarTodos) {
        HashMap<Integer,String> alternativas = new HashMap<>();
        for (int i=0;i<5;i++) {
            alternativas.put(i + 1, "Opcion " + (i + 1));
        }

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMixtaItem tipoPreguntaMixtaItem = new TipoPreguntaMixtaItem();
            tipoPreguntaMixtaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMixtaItem.setAlternativas(alternativas);
            TipoPreguntaMixtaAdapter.myListPreguntaMixta.add(tipoPreguntaMixtaItem);
            if (i == 0 && encuestarTodos == false)
                break;
        }
        System.out.println("miListaTipoPreguntaMixta: " + TipoPreguntaMixtaAdapter.myListPreguntaMixta.size());
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMixtaAdapter(context, TipoPreguntaMixtaAdapter.myListPreguntaMixta));
        System.out.println("num pregunta: " + numPregunta);
    }
}
