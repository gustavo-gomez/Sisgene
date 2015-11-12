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
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMatrizMultipleAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMatrizSimpleAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMixtaAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaUnicaAdapter;
import com.instituto.cuanto.sisgene.bean.EncuestaPregunta;
import com.instituto.cuanto.sisgene.dao.EncuestaDAO;
import com.instituto.cuanto.sisgene.entities.MixtaAlternativa;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    static ArrayList<String> nombresEncuestados;
    static ArrayList<Integer> codigosIdentEncuestados;
    Button btnSiguiente;
    ListView lvRespuestas_tipoGeneral;
    Context context = PreguntasActivity.this;

    ScrollView scrollView;
    TextView tvEnunciadoPregunta;
    TextView tvSeccion;
    TextView tvSubSeccion;
    TextView tvOpcionesPregunta;


    //variables que seran cambiadas de pregunta en pregunta
    int numPregunta = -1;
    String tipoPreguntaActual;
    boolean encuestarTodos;
    boolean ordenImportancia;
    String nombreSecccion;
    String nommbreSubSeccion;
    String descSeccion;
    String descSubSeccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        nombresEncuestados = new ArrayList<>();
        codigosIdentEncuestados = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        //lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoGeneral = (ListView) findViewById(R.id.lvRespuestas_tipoGeneral);
        tvEnunciadoPregunta = (TextView) findViewById(R.id.tvEnunciadoPregunta);
        tvOpcionesPregunta = (TextView) findViewById(R.id.tvOpcionesPregunta);
        tvSeccion = (TextView) findViewById(R.id.tvSeccion);
        tvSubSeccion = (TextView) findViewById(R.id.tvSubSeccion);
        TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter = new TipoPreguntaAbiertaAdapter();
        TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter = new TipoPreguntaUnicaAdapter();
        //miListaTipoPreguntaAbierta = new ArrayList<>();
        scrollView = new ScrollView(PreguntasActivity.this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(NombresPersonasEncuestadasActivity.KEY_ARG_NOMBRES_ENCUESTADOS)) {
            System.out.println("se obtienen datos - nombres y codigos de indentificacion");
            nombresEncuestados = getIntent().getStringArrayListExtra(NombresPersonasEncuestadasActivity.KEY_ARG_NOMBRES_ENCUESTADOS);
            codigosIdentEncuestados = getIntent().getIntegerArrayListExtra(NombresPersonasEncuestadasActivity.KEY_ARG_ID_ENCUESTADOS);
        }

        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);

        leerPrimeraPregunta();        //leer todos los datos de la primera pregunta
        tipoPreguntaActual = "MU";
        leerTipoPreguntaxPregunta();
    }

    private void leerPrimeraPregunta() {

        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;

        //ir a BD para sacar la primera preguna de dicha ecuesta
        encuestaPregunta = encuestaDAO.obtenerPreguntaEncuesta(PreguntasActivity.this);

        // Setear datos para la primera pregunta
        encuestarTodos = Boolean.valueOf(encuestaPregunta.getPre_unica_persona());
        nombreSecccion = encuestaPregunta.getSec_numero_seccion();
        nombreSecccion = encuestaPregunta.getPre_importarordenrptamu();
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            leerDatos();
            System.out.println("respuestas leidas -   se borra la lista");

            leerTipoPreguntaxPregunta();
        }
    };

    private void leerTipoPreguntaxPregunta() {
        //eliminar cuando se haya hecho la consulta a la base de datos

        boolean encuestarTodos = true;
        tipoPreguntaActual = tipoPreguntaActual;
        //Tipo de pregunta Unica
        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaUnica();

            //Tipo de pregunta Multiple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaMultipleOMixta(false);

            //Tipo de pregunta abierta
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            //TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaAbierta();

            //Tipo de pregunta Matriz Simple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaMatrizSimple();

            //Tipo de pregunta Matriz Multiple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaMatrizMultiple();

            //Tipo de pregunta Mixta
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            // tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaMultipleOMixta(true);
        }
    }

    private void leerDatos() {
        //Leer los dato segun el tipo de pregunta actual


        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            leerRespuestasTipoUnica();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            leerRespuestasTipoMultiple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            leerRespuestasTipoAbierta();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {

        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {

        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            leerRespuestasTipoMixta();
        }
    }

    private void leerRespuestasTipoMixta() {

    }

    private void leerRespuestasTipoMultiple() {
        TipoPreguntaMixtaItem tp = new TipoPreguntaMixtaItem();
        tp = TipoPreguntaMixtaAdapter.tipoPreguntaMixtaAdapter.getItem(0);

        ArrayList<MixtaAlternativa> mixta = new ArrayList<>();
        mixta = tp.getAlternativasAsArrayList();

        for (int i = 0; i < mixta.size(); i++) {
            System.out.println("mixta titulo" + mixta.get(i).getTitle());
            System.out.println("mixta value" + mixta.get(i).getValue());
        }
    }

    private void leerRespuestasTipoAbierta() {
        String respuestaAbierta = "";
        System.out.println("dimencion myListPreguntaAbierta:" + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());

        for (int i = 0; i < TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getItem(i);

            //agregar el codigo de identificacion
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            //respuestaAbierta = "[" + codIdent + "]";
            respuestaAbierta = respuestaAbierta + "[" + "]";
            //en caso el usuario no haya escrito nada
            if (nombresEncuestados.get(i).trim().equals(tipoPreguntaAbiertaItem.getDescription().trim())) {
                respuestaAbierta = respuestaAbierta + "null";
            } else
                respuestaAbierta = respuestaAbierta + tipoPreguntaAbiertaItem.getDescription();

            if (i != TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size() - 1)
                respuestaAbierta = "" + respuestaAbierta + "&";
        }

        System.out.println("respuesta: {" + respuestaAbierta + "}");

        //guardar en base de datos la respuesta


    }

    private void leerRespuestasTipoUnica() {
        String respuestaUnica = "";

        for (int i = 0; i < TipoPreguntaUnicaAdapter.myListPreguntaUnica.size(); i++) {
            TipoPreguntaUnicaItem tipoPreguntaUnicaItem = TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.getItem(i);

            //agregar el codigo de identificacion
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            //respuestaAbierta = "[" + codIdent + "]";
            respuestaUnica = respuestaUnica + "[" + "]";

            if (tipoPreguntaUnicaItem.getPos() == 0)
                respuestaUnica = respuestaUnica + "null";
            else
                respuestaUnica = respuestaUnica + tipoPreguntaUnicaItem.getRespuesta();

            if (i != TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size() - 1)
                respuestaUnica = "" + respuestaUnica + "&";
        }
        System.out.println("Respuesta tipo pregunta unica: {" + respuestaUnica + "}");
    }


    private void poblarLista_TipoPreguntaAbierta() {

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

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, TipoPreguntaAbiertaAdapter.myListPreguntaAbierta));
        System.out.println("num pregunta: " + numPregunta);
        //tipoPreguntaAbiertaAdapter.limpiarLista();
    }

    private void poblarLista_TipoPreguntaUnica() {

        HashMap<Integer, String> alternativas = new HashMap<>();

        alternativas.put(0, "Seleccione alternativa");
        for (int i = 1; i <= 5; i++) {
            alternativas.put(i, "Opcion " + i);
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
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaUnicaAdapter(context, TipoPreguntaUnicaAdapter.myListPreguntaUnica));
    }

    private void poblarLista_TipoPreguntaMultipleOMixta(boolean mixta) {
        //cambiar por valores de la bd
        int numMaxChequeados = 2;
        boolean importancia = false;

        HashMap<Integer, String> alternativas = new HashMap<>();
        for (int i = 0; i < 5; i++) {
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
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMixtaAdapter(context, TipoPreguntaMixtaAdapter.myListPreguntaMixta,
                mixta, numMaxChequeados, importancia));

    }

    private void poblarLista_TipoPreguntaMatrizSimple() {
        ArrayList<String> horizontales = new ArrayList<>();
        horizontales.add("Pesimo");
        horizontales.add("Malo");
        horizontales.add("Regular");
        horizontales.add("Bueno");
        horizontales.add("Muy Bueno");
        horizontales.add("Excelente");

        ArrayList<String> verticales = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            verticales.add("Opcion " + (i + 1));
        }
        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMatrizItem tipoPreguntaMatrizItem = new TipoPreguntaMatrizItem();
            tipoPreguntaMatrizItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMatrizItem.setHorizontal(horizontales);
            tipoPreguntaMatrizItem.setVertical(verticales);
            TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz.add(tipoPreguntaMatrizItem);
            if (i == 0 && encuestarTodos == false)
                break;
        }
        System.out.println("miListaTipoPreguntaMatrizSimple: " + TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz.size());
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMatrizSimpleAdapter(context, TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz));
        System.out.println("num pregunta: " + numPregunta);
    }

    private void poblarLista_TipoPreguntaMatrizMultiple() {
        ArrayList<String> horizontales = new ArrayList<>();
        horizontales.add("Pesimo");
        horizontales.add("Malo");
        horizontales.add("Regular");
        horizontales.add("Bueno");
        horizontales.add("Muy Bueno");
        horizontales.add("Excelente");

        ArrayList<String> verticales = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            verticales.add("Opcion " + (i + 1));
        }
        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMatrizItem tipoPreguntaMatrizItem = new TipoPreguntaMatrizItem();
            tipoPreguntaMatrizItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMatrizItem.setHorizontal(horizontales);
            tipoPreguntaMatrizItem.setVertical(verticales);
            TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz.add(tipoPreguntaMatrizItem);
            if (i == 0 && encuestarTodos == false)
                break;
        }
        System.out.println("miListaTipoPreguntaMatrizMultiple: " + TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz.size());
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMatrizMultipleAdapter(context, TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz));
        System.out.println("num pregunta: " + numPregunta);
    }

    private void guardarRespuesta(String rpta) {

    }
}
