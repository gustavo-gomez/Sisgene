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
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;

import java.util.ArrayList;

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

        leerTipoPreguntaxPregunta();

    }

    private void leerTipoPreguntaxPregunta() {
        //eliminar cuando se haya hecho la consulta a la base de datos
        String tipoPregunta = "AB";
        int numEncuestado = 0;

        //Realizar la consulta a base de datos para obtener la siguiente pregunta


        //Tipo de pregunta Unica
        if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();


            //Tipo de pregunta Multiple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();


            //Tipo de pregunta abierta
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            //TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaAbierta(numEncuestado);

            //Tipo de pregunta Matriz Simple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Matriz Multiple
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Mixta
        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            // tipoPreguntaAbiertaAdapter.limpiarLista();
        }
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("antes: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
            //miListaTipoPreguntaAbierta.remove(0);
            leerRespuestas();
            System.out.println("respuestas leidas -   se borra la lista");
            TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();
            TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.notifyDataSetChanged();
            System.out.println("despues: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
            //poblarLista_TipoPreguntaAbierta(12);
        }
    };

    private void leerRespuestas() {
        System.out.println("len datos size: "+ TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
        System.out.println("len datos count: "+ TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getCount());

        for (int i = 0; i < TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getItem(i);
            System.out.println("nombre: " + tipoPreguntaAbiertaItem.getTitle());
            System.out.println("respuesta: " + tipoPreguntaAbiertaItem.getDescription());
        }
    }

    private void poblarLista_TipoPreguntaAbierta(int numEncuestado) {

        //if (numEncuestado != 1) {
        //     obtenerNombresEncuestados(numEncuestado);
        //   System.out.println("len nombresEncuestados: ");
        // }
        System.out.println("cargar datos");
        System.out.println("datos: " + nombresEncuestados.size());


        for (int i = 0; i < nombresEncuestados.size(); i++) {

            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = new TipoPreguntaAbiertaItem();
            tipoPreguntaAbiertaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaAbiertaItem.setDescription(nombresEncuestados.get(i));

            TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.add(tipoPreguntaAbiertaItem);
        }
        System.out.println("miListaTipoPreguntaAbierta: "+ TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());
        //colocar las aternativas
        //tvOpcionesPregunta.setText(datosOpc);

        //colocar el enunciado de la preguna
        //tvEnunciadoPregunta.setText();

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, TipoPreguntaAbiertaAdapter.myListPreguntaAbierta));
        System.out.println("num pregunta: " + numPregunta);
        //tipoPreguntaAbiertaAdapter.limpiarLista();
    }
}
