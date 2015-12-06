package com.instituto.cuanto.sisgene;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.adapter.TipoPreguntaAbiertaAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMatrizMultipleAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMatrizSimpleAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMixtaAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaMultipleAdapter;
import com.instituto.cuanto.sisgene.adapter.TipoPreguntaUnicaAdapter;
import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.bean.EncuestaPregunta;
import com.instituto.cuanto.sisgene.bean.PreguntaAlternativa;
import com.instituto.cuanto.sisgene.bean.PreguntaItem;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.DetalleEncRptaDAO;
import com.instituto.cuanto.sisgene.dao.EncuestaDAO;
import com.instituto.cuanto.sisgene.dao.TipoPreguntaDAO;
import com.instituto.cuanto.sisgene.entities.Pregunta;
import com.instituto.cuanto.sisgene.entities.RespuestaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizMultipleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizSimpleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMultipleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;
import com.instituto.cuanto.sisgene.util.EnvioServiceUtil;
import com.instituto.cuanto.sisgene.util.Util;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    static ArrayList<String> nombresEncuestados;
    static ArrayList<Integer> codigosIdentEncuestados;
    List<PreguntaAlternativa> listPreguntaAlterntiva;
    List<PreguntaItem> listPreguntaItems;

    Button btnSiguiente;
    Button btnGuardarEncuesta;
    Button btnBuscarPregunta;
    ListView lvRespuestas_tipoGeneral;
    Context context = PreguntasActivity.this;

    ScrollView scrollView;
    TextView tvEnunciadoPregunta;
    TextView tvSeccion;
    TextView tvSubSeccion;
    EditText etnumPregunta;

    //variables que seran cambiadas de pregunta en pregunta
    //Pregunta
    String enunciadoPregunta;
    String numeroPreguntaBD;
    int numPregunta = -1;
    String idPregunta;
    int mumMaxChequeados;
    String tipoPreguntaActual;
    int encuestarTodos;
    int ordenImportancia;
    //Seccion
    String nombreSecccion;
    String numeroSecccion;
    String nombreSubSeccion;
    String numeroSubSeccion;

    //datos para validar encuesta
    int ultimoIdPregunta; //ultimo id de pregunta de la tabla Pregunta. Para validar si ya se ha repondido todas las preguntas
    boolean ultimaPregunta = false;
    EditText editTextObservacionRechazar;
    EditText editTextObservacionFinalizar;
    EditText editTextObservacion;

    //Retomar encuesta
    int idRespuesta;
    String valorRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        nombresEncuestados = new ArrayList<>();
        codigosIdentEncuestados = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnGuardarEncuesta = (Button) findViewById(R.id.btnGuardarEncuesta);
        //btnRechazarEncuesta = (Button) findViewById(R.id.btnRechazarEncuesta);
        //btnFinalizarEncuesta = (Button) findViewById(R.id.btnFinalizarEncuesta);
        btnBuscarPregunta = (Button) findViewById(R.id.btnBuscarPregunta);
        etnumPregunta = (EditText) findViewById(R.id.etnumPregunta);

        editTextObservacion = new EditText(context);
        editTextObservacionRechazar = new EditText(context);
        editTextObservacionFinalizar = new EditText(context);

        //lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoGeneral = (ListView) findViewById(R.id.lvRespuestas_tipoGeneral);
        tvEnunciadoPregunta = (TextView) findViewById(R.id.tvEnunciadoPregunta);
        tvSeccion = (TextView) findViewById(R.id.tvSeccion);
        tvSubSeccion = (TextView) findViewById(R.id.tvSubSeccion);
        TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter = new TipoPreguntaAbiertaAdapter();
        TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter = new TipoPreguntaUnicaAdapter();
        //miListaTipoPreguntaAbierta = new ArrayList<>();
        scrollView = new ScrollView(PreguntasActivity.this);
        listPreguntaItems = new ArrayList<>();

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(NombresPersonasEncuestadasActivity.KEY_ARG_NOMBRES_ENCUESTADOS)) {
            System.out.println("se obtienen datos - nombres y codigos de indentificacion");
            nombresEncuestados = getIntent().getStringArrayListExtra(NombresPersonasEncuestadasActivity.KEY_ARG_NOMBRES_ENCUESTADOS);
            codigosIdentEncuestados = getIntent().getIntegerArrayListExtra(NombresPersonasEncuestadasActivity.KEY_ARG_ID_ENCUESTADOS);
        }

        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
        btnGuardarEncuesta.setOnClickListener(btnGuardarEncuestasetOnClickListener);
        //btnRechazarEncuesta.setOnClickListener(btnRechazarEncuestasetOnClickListener);
        //btnFinalizarEncuesta.setOnClickListener(btnFinalizarEncuestasetOnClickListener);
        btnBuscarPregunta.setOnClickListener(btnBuscarPreguntasetOnClickListener);

        leerPrimeraPregunta();        //leer todos los datos de la primera pregunta
        leerTipoPreguntaxPregunta();
        obtenerNumeroPreguntas();
    }

    private void obtenerNumeroPreguntas() {
        //Validar que aun no se hayan culminado el numero de encuestas a realizar por el usuario
        TipoPreguntaDAO tipoPreguntaDAO = new TipoPreguntaDAO();
        ultimoIdPregunta = tipoPreguntaDAO.obtenerUltiIdPregunta(PreguntasActivity.this);
    }

    private void leerPrimeraPregunta() {

        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;
        //ir a BD para sacar la primera preguna de dicha ecuesta
        encuestaPregunta = encuestaDAO.obtenerPreguntaEncuesta(PreguntasActivity.this);
        // Setear datos para la primera pregunta
        System.out.println("encuestaPregunta.getSec_nombre" + encuestaPregunta.getSec_nombre());
        nombreSecccion = encuestaPregunta.getSec_nombre();
        numeroSecccion = encuestaPregunta.getSec_numero_seccion();
        numeroSecccion = encuestaPregunta.getSec_numero_seccion();
        numeroSubSeccion = encuestaPregunta.getSus_numero_subseccion();
        nombreSubSeccion = encuestaPregunta.getSus_nombre();
        idPregunta = encuestaPregunta.getPre_id();
        //pregutna
        tipoPreguntaActual = encuestaPregunta.getPre_tipo_rpta();
        enunciadoPregunta = encuestaPregunta.getPre_enunciado();
        numeroPreguntaBD = encuestaPregunta.getPre_numero();
        encuestarTodos = Integer.parseInt(encuestaPregunta.getPre_unica_persona()); // 0:todos   -  1: una persona
        try {
            ordenImportancia = Integer.parseInt(encuestaPregunta.getPre_importarordenrptamu()); //Que va a retornar
        } catch (Exception ex) {
            ex.printStackTrace();
            ordenImportancia = 0;
        }

        //obtener alternativas
        listPreguntaAlterntiva = encuestaDAO.obtenerAlternativas(PreguntasActivity.this, idPregunta);

        if (tipoPreguntaActual.equals("MI") || tipoPreguntaActual.equals("MM"))
            listPreguntaItems = encuestaDAO.obtenerItems(PreguntasActivity.this, idPregunta);

    }

    private void mostrarDatosSeccion() {
        tvSeccion.setText(numeroSecccion + ": " + nombreSecccion);
        tvSubSeccion.setText(numeroSubSeccion + " " + nombreSubSeccion);
        tvEnunciadoPregunta.setText(numeroPreguntaBD + ": " + enunciadoPregunta);
        String opciones = "";

        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            opciones = opciones + listPreguntaAlterntiva.get(i).getOpc_nombre().trim() + "\t";
            System.out.println("opcion " + i + ": " + listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }
        opciones = opciones + "\n";

        if (tipoPreguntaActual.equals("MS") || tipoPreguntaActual.equals("MM"))
            for (int i = 0; i < listPreguntaItems.size(); i++) {
                opciones = opciones + listPreguntaItems.get(i).getIte_nombre().toString().trim() + "\t";
                System.out.println("item " + i + ": " + listPreguntaItems.get(i).getIte_nombre().trim());
            }

        //mostrar datos -- eliminar
        System.out.println("secccion: " + numeroSecccion + ": " + nombreSecccion);
        System.out.println("subsecccion: " + numeroSubSeccion + ": " + nombreSubSeccion);
        System.out.println("Enunciado: " + enunciadoPregunta);
        System.out.println("Opciones: " + opciones);
        System.out.println("Tipo de pregunta: " + tipoPreguntaActual);
        System.out.println("idPregunta: " + idPregunta);
        System.out.println("encuestar todos: " + encuestarTodos);
    }

    private void leerSiguientePregunta() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;
        //ir a BD para sacar la primera preguna de dicha ecuesta
        encuestaPregunta = encuestaDAO.obtenerPreguntaEncuesta(PreguntasActivity.this, idPregunta);
        // Setear datos para la primera pregunta
        if (encuestaPregunta != null) {
            nombreSecccion = encuestaPregunta.getSec_nombre();
            numeroSecccion = encuestaPregunta.getSec_numero_seccion();
            numeroSecccion = encuestaPregunta.getSec_numero_seccion();
            numeroSubSeccion = encuestaPregunta.getSus_numero_subseccion();
            nombreSubSeccion = encuestaPregunta.getSus_nombre();
            idPregunta = encuestaPregunta.getPre_id();
            //pregutna
            tipoPreguntaActual = encuestaPregunta.getPre_tipo_rpta();
            enunciadoPregunta = encuestaPregunta.getPre_enunciado();
            numeroPreguntaBD = encuestaPregunta.getPre_numero();
            encuestarTodos = Integer.parseInt(encuestaPregunta.getPre_unica_persona()); // 0:todos   -  1: una persona
            try {
                ordenImportancia = Integer.parseInt(encuestaPregunta.getPre_importarordenrptamu()); //Que va a retornar
            } catch (Exception ex) {
                ex.printStackTrace();
                ordenImportancia = 0;
            }
            //obtener alternativas
            if (!tipoPreguntaActual.equals("AB"))
                listPreguntaAlterntiva = encuestaDAO.obtenerAlternativas(PreguntasActivity.this, idPregunta);

            if (tipoPreguntaActual.equals("MS") || tipoPreguntaActual.equals("MM"))
                listPreguntaItems = encuestaDAO.obtenerItems(PreguntasActivity.this, idPregunta);
        } else {
            idPregunta = Integer.parseInt(idPregunta) + 1 + "";
            leerSiguientePregunta();
        }

    }

    private void leerPreguntaPorNumPregunta() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;

        if (etnumPregunta.getText().toString().trim().length() == 0) {
            Toast.makeText(PreguntasActivity.this, "Ingrese el número de pregunta", Toast.LENGTH_LONG).show();
            return;
        } else
            encuestaPregunta = encuestaDAO.obtenerPreguntaPorNumPregunta(PreguntasActivity.this,
                    etnumPregunta.getText().toString().trim());

        if (encuestaPregunta != null) {
            nombreSecccion = encuestaPregunta.getSec_nombre();
            numeroSecccion = encuestaPregunta.getSec_numero_seccion();
            numeroSecccion = encuestaPregunta.getSec_numero_seccion();
            numeroSubSeccion = encuestaPregunta.getSus_numero_subseccion();
            nombreSubSeccion = encuestaPregunta.getSus_nombre();
            idPregunta = encuestaPregunta.getPre_id();
            //pregutna
            tipoPreguntaActual = encuestaPregunta.getPre_tipo_rpta();
            enunciadoPregunta = encuestaPregunta.getPre_enunciado();
            numeroPreguntaBD = encuestaPregunta.getPre_numero();
            encuestarTodos = Integer.parseInt(encuestaPregunta.getPre_unica_persona()); // 0:todos   -  1: una persona
            try {
                ordenImportancia = Integer.parseInt(encuestaPregunta.getPre_importarordenrptamu()); //Que va a retornar
            } catch (Exception ex) {
                ex.printStackTrace();
                ordenImportancia = 0;
            }
            //obtener alternativas
            if (!tipoPreguntaActual.equals("AB"))
                listPreguntaAlterntiva = encuestaDAO.obtenerAlternativas(PreguntasActivity.this, idPregunta);

            if (tipoPreguntaActual.equals("MS") || tipoPreguntaActual.equals("MM"))
                listPreguntaItems = encuestaDAO.obtenerItems(PreguntasActivity.this, idPregunta);
        } else {
            limpiarLista();
            new AlertDialog.Builder(PreguntasActivity.this).setTitle("Alerta").setMessage("El número de pregunta no existe")
                    .setNeutralButton("Aceptar", alertaCancelarOnClickListener)
                    .setCancelable(false).show();
        }
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //leer los datos de la pregunta actual y guardar en la base de datos
            leeryGuardarDatos();
            if (!ultimaPregunta) {
                //leer la siguiente pregunta
                leerSiguientePregunta();

                if (Integer.parseInt(idPregunta) != ultimoIdPregunta) {
                    //Aun no se llega a la ultima pregunta
                    //mostrar interfaz segun la pregunta leida
                    leerTipoPreguntaxPregunta();
                } else
                    ultimaPregunta = true;
            } else {
                btnSiguiente.setEnabled(false);
                Toast.makeText(PreguntasActivity.this, "Se han respondido todas las preguntas", Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener btnGuardarEncuestasetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //
            editTextObservacion = new EditText(context);
            editTextObservacion.setHint("Observaciones");
            editTextObservacion.setTextColor(getResources().getColor(R.color.color_texto));

            if (ultimaPregunta) {
                new AlertDialog.Builder(PreguntasActivity.this)
                        .setTitle("Alerta")
                        .setView(editTextObservacion)
                        .setMessage("¿Desea guardar la encuesta?")
                        .setIcon(R.drawable.ic_save_black_24dp)
                        .setPositiveButton("Terminar encuesta", alertaAceptarEncuestaFinalizadaOnClickListener)
                        .setNegativeButton("Continuar encuesta", alertaCancelarOnClickListener)
                        .setCancelable(false).show();
            } else
                new AlertDialog.Builder(PreguntasActivity.this)
                        .setTitle("Alerta").setView(editTextObservacion)
                        .setIcon(R.drawable.cancel)
                        .setMessage("¿Desea finalizar la encuesta sin terminar la ejecucion de las preguntas?")
                        .setPositiveButton("Terminar encuesta", alertaAceptarOnClickListener)
                        .setNegativeButton("Continuar encuesta", alertaCancelarOnClickListener)
                        .setCancelable(false).show();
        }
    };

    //Buscar pregunta por numero de pregunta
    View.OnClickListener btnBuscarPreguntasetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!ultimaPregunta) {
                //leer la siguiente pregunta
                leerPreguntaPorNumPregunta();
                etnumPregunta.setText("");
                if (Integer.parseInt(idPregunta) != ultimoIdPregunta) {
                    //Aun no se llega a la ultima pregunta, mostrar interfaz segun la pregunta leida
                    leeryGuardarDatos();
                    leerTipoPreguntaxPregunta();
                } else
                    ultimaPregunta = true;
            } else {
                btnSiguiente.setEnabled(false);
                Toast.makeText(PreguntasActivity.this, "Ésta es la última pregunta de la encuesta", Toast.LENGTH_LONG).show();
            }
        }
    };

    DialogInterface.OnClickListener alertaAceptarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            //obtener el id de la ultima cabecera
            CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);
            //Guardar la encuesta con estado incompleto

            cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this, "I",
                    Util.obtenerFecha(), "00", "", "", editTextObservacion.getText().toString().trim(),
                    cabeceraRespuesta.getIdCabeceraEnc());

            new AlertDialog.Builder(PreguntasActivity.this).setTitle("Mensaje")
                    .setMessage("Se ha guardado la encuesta satisfactoriamente")
                    .setNeutralButton("Aceptar", alertaAceptarGuardarEncuestaOnClickListener)
                    .setCancelable(false).show();
        }
    };

    DialogInterface.OnClickListener alertaAceptarRechazarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            //obtener el id de la ultima cabecera
            CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);
            //Guardar la encuesta con estado incompleto

            cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this, "R",
                    Util.obtenerFecha(), "00", "", "", editTextObservacionRechazar.getText().toString().trim(),
                    cabeceraRespuesta.getIdCabeceraEnc());

            new AlertDialog.Builder(PreguntasActivity.this).setTitle("Mensaje")
                    .setMessage("Se ha guardado la encuesta satisfactoriamente")
                    .setNeutralButton("Aceptar", alertaAceptarGuardarEncuestaOnClickListener)
                    .setCancelable(false).show();
        }
    };
    DialogInterface.OnClickListener alertaAceptarEncuestaFinalizadaOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {


            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            //obtener el id de la ultima cabecera
            CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);

            cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this, "C",
                    Util.obtenerFechayHora(), "00", "", "", editTextObservacionFinalizar.getText().toString().trim(),
                    cabeceraRespuesta.getIdCabeceraEnc());

            EnvioServiceUtil envioServiceUtil = new EnvioServiceUtil();
            boolean estTemp = false;
            //Si hay conexion a internet invocar al WS para envviar la data
            if (conectadoInternet()) {
                estTemp = envioServiceUtil.enviarEncuestaEjecutada(PreguntasActivity.this, cabeceraRespuesta.getIdCabeceraEnc() + "");
            }

            //el envio de data al WS fue satisfactorio
            if (estTemp) {
                cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this,
                        "C",
                        Util.obtenerFechayHora(),
                        "00",//Calcular tiempo de encuesta
                        "", //enviado
                        Util.obtenerFechayHora(), //fecha envio
                        editTextObservacionFinalizar.getText().toString().trim(),
                        cabeceraRespuesta.getIdCabeceraEnc())
            }

            new AlertDialog.Builder(PreguntasActivity.this).setTitle("Mensaje")
                    .setMessage("Se ha guardado la encuesta completada satisfactoriamente")
                    .setNeutralButton("Aceptar", alertaAceptarGuardarEncuestaOnClickListener)
                    .setCancelable(false).show();
        }
    };
    DialogInterface.OnClickListener alertaAceptarGuardarEncuestaOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(PreguntasActivity.this, PrincipalEncuestaActivity.class);
            startActivity(intent);
            dialogInterface.dismiss();
            finish();
        }
    };
    DialogInterface.OnClickListener alertaCancelarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }

    };

    /*
        Muestra los datos como enunciado y seccion
        Lee el tipo de pregunta y muestra las preguntas para responder (Los campos ya deben estar seteados)
    */
    private void leerTipoPreguntaxPregunta() {

        //mostrar datos de seccion, subseccion, enunciado y opciones
        mostrarDatosSeccion();
        //Mostrar lista segun el tipo de pregunta
        //Tipo de pregunta Unica
        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaUnica();

            //Tipo de pregunta Multiple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            //tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaMultiple();

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
            poblarLista_TipoPreguntaMixta();
        }
    }

    private void leeryGuardarDatos() {
        //Leer los dato segun el tipo de pregunta actual
        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            leerRespuestasTipoUnica();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            leerRespuestasTipoMultiple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            leerRespuestasTipoAbierta();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            leerRespuestasTipoMatrizSimple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            leerRespuestasTipoMatrizMultiple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            leerRespuestasTipoMixta();
        }
    }

    private void leerRespuestasTipoAbierta() {
        String respuestaAbierta = "";

        for (int i = 0; i < TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getItem(i);
            //agregar el codigo de identificacion
            Formatter codIdent = new Formatter();
            codIdent.format("%02d", codigosIdentEncuestados.get(i));
            respuestaAbierta = respuestaAbierta + "[" + codIdent + "]";

            //en caso el usuario no haya escrito nada
            if (nombresEncuestados.get(i).trim().equals(tipoPreguntaAbiertaItem.getDescription().trim())) {
                respuestaAbierta = respuestaAbierta + "null";
            } else {
                respuestaAbierta = respuestaAbierta + tipoPreguntaAbiertaItem.getDescription();
            }
            if (i != TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size() - 1) {
                respuestaAbierta = "" + respuestaAbierta + "&";
            }
        }

        //guardar en base de datos la respuesta
        TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();

        guardarRespuesta(respuestaAbierta);
    }

    private void leerRespuestasTipoUnica() {
        String respuestaUnica = "";

        for (int i = 0; i < TipoPreguntaUnicaAdapter.myListPreguntaUnica.size(); i++) {
            TipoPreguntaUnicaItem tipoPreguntaUnicaItem = TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.getItem(i);

            //agregar el codigo de identificacion
            Formatter codIdent = new Formatter();
            codIdent.format("%02d", codigosIdentEncuestados.get(i));
            respuestaUnica = respuestaUnica + "[" + codIdent + "]";

            if (tipoPreguntaUnicaItem.getPos() == 0)
                respuestaUnica = respuestaUnica + "null";
            else
                respuestaUnica = respuestaUnica + tipoPreguntaUnicaItem.getRespuesta();

            if (i != TipoPreguntaUnicaAdapter.myListPreguntaUnica.size() - 1)
                respuestaUnica = "" + respuestaUnica + "&";
        }
        TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.limpiarLista();

        guardarRespuesta(respuestaUnica);
    }

    private void leerRespuestasTipoMultiple() {
        String respuestaMultiple = "";
        ArrayList<TipoPreguntaMultipleItem> tipoPreguntaMultipleItems = TipoPreguntaMultipleAdapter.myListPreguntaMultiple;
        int cont = 0;
        //agregar el codigo de identificacion

        for (int i = 0; i < tipoPreguntaMultipleItems.size(); i++) {
            Formatter codIdent = new Formatter();
            codIdent.format("%02d", codigosIdentEncuestados.get(i));
            respuestaMultiple = respuestaMultiple + "[" + codIdent + "]";

            for (int j = 0; j < tipoPreguntaMultipleItems.get(i).getRespuestas().size(); j++) {
                respuestaMultiple = respuestaMultiple + tipoPreguntaMultipleItems.get(i).getRespuestas().get(j).trim();

                if (j != tipoPreguntaMultipleItems.get(i).getRespuestas().size() - 1)
                    respuestaMultiple = "" + respuestaMultiple + "$";
                cont++;
            }
            if (i != tipoPreguntaMultipleItems.size() - 1) {
                if (cont == 0)
                    respuestaMultiple = respuestaMultiple + "null";
                respuestaMultiple = "" + respuestaMultiple + "&";
            }
            cont = 0;
        }
        //guardar en base de datos
        TipoPreguntaMultipleAdapter.tipoPreguntaMultipleAdapter.limpiarLista();

        guardarRespuesta(respuestaMultiple);
    }

    private void leerRespuestasTipoMixta() {
        String respuestaMixta = "";
        ArrayList<TipoPreguntaMixtaItem> tipoPreguntaMixtaItems = TipoPreguntaMixtaAdapter.myListPreguntaMixta;
        int cont = 0;
        //agregar el codigo de identificacion

        for (int i = 0; i < tipoPreguntaMixtaItems.size(); i++) {
            Formatter codIdent = new Formatter();
            codIdent.format("%02d", codigosIdentEncuestados.get(i));
            respuestaMixta = respuestaMixta + "[" + codIdent + "]";

            int sizeAlternativas = tipoPreguntaMixtaItems.get(i).getAlternativas().size();
            ArrayList<String> respuestas = tipoPreguntaMixtaItems.get(i).getRespuestas();

            for (int j = 0; j < respuestas.size(); j++) {
                String rpta;
                if (respuestas.get(j).equals(tipoPreguntaMixtaItems.get(i).getAlternativas().get(sizeAlternativas - 1))) {
                    rpta = "(" + tipoPreguntaMixtaItems.get(i).getRespuestas().get(j).trim() + ")";
                    rpta = rpta + tipoPreguntaMixtaItems.get(i).getPreguntaMixta();
                } else {
                    rpta = tipoPreguntaMixtaItems.get(i).getRespuestas().get(j).trim();
                }
                respuestaMixta = respuestaMixta + rpta;
                if (j != tipoPreguntaMixtaItems.get(i).getRespuestas().size() - 1)
                    respuestaMixta = "" + respuestaMixta + "$";
                cont++;
            }
            if (i != tipoPreguntaMixtaItems.size() - 1) {
                if (cont == 0)
                    respuestaMixta = respuestaMixta + "null";
                respuestaMixta = "" + respuestaMixta + "&";
            }
            cont = 0;
        }
        //guardar en base de datos
        TipoPreguntaMixtaAdapter.tipoPreguntaMixtaAdapter.limpiarLista();

        guardarRespuesta(respuestaMixta);
    }

    private void leerRespuestasTipoMatrizSimple() {
        String respuestaMatrizS = "";
        System.out.println("dimension myListPreguntaMatrizSimple:" + TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz.size());
        ArrayList<TipoPreguntaMatrizSimpleItem> tipoPreguntaMatrizSimpleItems = TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz;

        System.out.println("size: " + tipoPreguntaMatrizSimpleItems.size());

        for (int i = 0; i < tipoPreguntaMatrizSimpleItems.size(); i++) {
            Formatter codIdent = new Formatter();

            codIdent.format("%02d", codigosIdentEncuestados.get(i));
            respuestaMatrizS = respuestaMatrizS + "[" + codIdent + "]";

            for (int k = 0; k < tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().size(); k++) {
                System.out.println("vertical: " + tipoPreguntaMatrizSimpleItems.get(i).getVertical().get(k));
                System.out.println("respuesta " + k + " :" + tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim());

                respuestaMatrizS = respuestaMatrizS + tipoPreguntaMatrizSimpleItems.get(i).getVertical().get(k).trim();
                respuestaMatrizS = respuestaMatrizS + "%";

                if (tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim().equals(""))
                    respuestaMatrizS = respuestaMatrizS + "null";
                else {
                    respuestaMatrizS = respuestaMatrizS + tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim();
                }
                if (k != tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().size() - 1)
                    respuestaMatrizS = "" + respuestaMatrizS + "$";
            }
            if (i != tipoPreguntaMatrizSimpleItems.size() - 1)
                respuestaMatrizS = "" + respuestaMatrizS + "&";
        }

        //System.out.println("respuesta: {" + respuestaMatrizS + "}");
        //guardar en base de datos la respuesta
        TipoPreguntaMatrizSimpleAdapter.tipoPreguntaMatrizAdapter.limpiarLista();
        //guardarRespuesta(respuestaMatrizS);
    }

    private void leerRespuestasTipoMatrizMultiple() {
        String respuestaMatrizM = "";
        System.out.println("dimension myListPreguntaMatrizMultiple:" + TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz.size());
        ArrayList<TipoPreguntaMatrizMultipleItem> tipoPreguntaMatrizMultipleItems = TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz;
        int cont = 0;

        for (int i = 0; i < tipoPreguntaMatrizMultipleItems.size(); i++) {
            Formatter codIdent = new Formatter();
            codIdent.format("%02d", codigosIdentEncuestados.get(i));
            respuestaMatrizM = respuestaMatrizM + "[" + codIdent + "]";

            ArrayList<RespuestaItem> respuestas = tipoPreguntaMatrizMultipleItems.get(i).getRespuestas();
            System.out.println("usuario " + (i + 1) + "size:" + respuestas.size());


            for (int l = 0; l < respuestas.size(); l++) {
                for (int j = 0; j < tipoPreguntaMatrizMultipleItems.get(i).getHorizontal().size(); j++) {
                    for (int k = 0; k < tipoPreguntaMatrizMultipleItems.get(i).getVertical().size(); k++) {
                        //nombre de opcion
                        respuestaMatrizM = respuestaMatrizM + tipoPreguntaMatrizMultipleItems.get(i).getVertical().get(k);

                        if (respuestas.get(l).getCol() == j && respuestas.get(l).getRow() == k) {

                            System.out.println("posicion: " + j + " , " + k);
                            System.out.println("multiple usuario " + (i + 1) + ": " + respuestas.get(l).getTexto());

                            respuestaMatrizM = respuestaMatrizM + "%";
                            respuestaMatrizM = respuestaMatrizM + respuestas.get(l).getTexto();
                            cont++;
                        }
                        if (cont == 0)
                            respuestaMatrizM = respuestaMatrizM + "null";
                        if (k != tipoPreguntaMatrizMultipleItems.get(i).getVertical().size() - 1)
                            respuestaMatrizM = respuestaMatrizM + "$";
                        cont = 0;
                    }

                }
            }
            if (i != tipoPreguntaMatrizMultipleItems.size() - 1) {
                if (cont == 0) respuestaMatrizM = respuestaMatrizM + "null";
            }

            cont = 0;
        }

        //guardar en base de datos la respuesta
        TipoPreguntaMatrizMultipleAdapter.tipoPreguntaMatrizAdapter.limpiarLista();

        guardarRespuesta(respuestaMatrizM);
    }


    private void poblarLista_TipoPreguntaAbierta() {

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = new TipoPreguntaAbiertaItem();
            tipoPreguntaAbiertaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaAbiertaItem.setDescription(nombresEncuestados.get(i));

            TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.add(tipoPreguntaAbiertaItem);
            if (i == 0 && encuestarTodos == 1)
                break;
        }
        System.out.println("miListaTipoPreguntaAbierta: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, TipoPreguntaAbiertaAdapter.myListPreguntaAbierta));
        System.out.println("num pregunta: " + numPregunta);

    }

    private void poblarLista_TipoPreguntaUnica() {

        HashMap<Integer, String> alternativas = new HashMap<>();

        alternativas.put(0, "Seleccione alternativa");
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            alternativas.put(i + 1, listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaUnicaItem tipoPreguntaUnicaItem = new TipoPreguntaUnicaItem();
            tipoPreguntaUnicaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaUnicaItem.setAlternativas(alternativas);
            TipoPreguntaUnicaAdapter.myListPreguntaUnica.add(tipoPreguntaUnicaItem);
            if (i == 0 && encuestarTodos == 1)
                break;
        }
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaUnicaAdapter(context, TipoPreguntaUnicaAdapter.myListPreguntaUnica));
    }

    private void poblarLista_TipoPreguntaMultiple() {

        ArrayList<String> alternativas = new ArrayList<>();
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            alternativas.add(listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMultipleItem tipoPreguntaMultipleItem = new TipoPreguntaMultipleItem();
            tipoPreguntaMultipleItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMultipleItem.setAlternativas(alternativas);
            TipoPreguntaMultipleAdapter.myListPreguntaMultiple.add(tipoPreguntaMultipleItem);
            if (i == 0 && encuestarTodos == 1)
                break;
        }
        System.out.println("miListaTipoPreguntaMixta: " + TipoPreguntaMultipleAdapter.myListPreguntaMultiple.size());
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMultipleAdapter(context, TipoPreguntaMultipleAdapter.myListPreguntaMultiple));

    }

    private void poblarLista_TipoPreguntaMixta() {
        mumMaxChequeados = 3;

        ArrayList<String> alternativas = new ArrayList<>();
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            alternativas.add(listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }

        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMixtaItem tipoPreguntaMixtaItem = new TipoPreguntaMixtaItem();
            tipoPreguntaMixtaItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMixtaItem.setAlternativas(alternativas);
            TipoPreguntaMixtaAdapter.myListPreguntaMixta.add(tipoPreguntaMixtaItem);
            if (i == 0 && encuestarTodos == 1)
                break;
        }
        System.out.println("miListaTipoPreguntaMixta: " + TipoPreguntaMixtaAdapter.myListPreguntaMixta.size());

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMixtaAdapter(context, TipoPreguntaMixtaAdapter.myListPreguntaMixta,
                mumMaxChequeados, ordenImportancia));

    }

    private void poblarLista_TipoPreguntaMatrizSimple() {
        ArrayList<String> horizontales = new ArrayList<>();
        for (int i = 0; i < listPreguntaItems.size(); i++) {
            horizontales.add(listPreguntaItems.get(i).getIte_nombre().trim());
        }
        horizontales.add("");

        ArrayList<String> verticales = new ArrayList<>();
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            verticales.add(listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }
        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMatrizSimpleItem tipoPreguntaMatrizSimpleItem = new TipoPreguntaMatrizSimpleItem();
            tipoPreguntaMatrizSimpleItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMatrizSimpleItem.setHorizontal(horizontales);
            tipoPreguntaMatrizSimpleItem.setVertical(verticales);
            TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz.add(tipoPreguntaMatrizSimpleItem);
            if (i == 0 && encuestarTodos == 1)
                break;
        }
        System.out.println("miListaTipoPreguntaMatrizSimple: " + TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz.size());
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMatrizSimpleAdapter(context, TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz));
        System.out.println("num pregunta: " + numPregunta);
    }

    private void poblarLista_TipoPreguntaMatrizMultiple() {
        ArrayList<String> horizontales = new ArrayList<>();
        for (int i = 0; i < listPreguntaItems.size(); i++) {
            horizontales.add(listPreguntaItems.get(i).getIte_nombre().trim());
        }
        horizontales.add("");

        ArrayList<String> verticales = new ArrayList<>();
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            verticales.add(listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }
        //cargar datos a la lista
        for (int i = 0; i < nombresEncuestados.size(); i++) {
            TipoPreguntaMatrizMultipleItem tipoPreguntaMatrizMultipleItem = new TipoPreguntaMatrizMultipleItem();
            tipoPreguntaMatrizMultipleItem.setTitle(nombresEncuestados.get(i));
            tipoPreguntaMatrizMultipleItem.setHorizontal(horizontales);
            tipoPreguntaMatrizMultipleItem.setVertical(verticales);
            TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz.add(tipoPreguntaMatrizMultipleItem);
            if (i == 0 && encuestarTodos == 1)
                break;
        }
        System.out.println("miListaTipoPreguntaMatrizMultiple: " + TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz.size());
        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaMatrizMultipleAdapter(context, TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz));
        System.out.println("num pregunta: " + numPregunta);
    }

    private void guardarRespuesta(String rpta) {

        CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();
        System.out.println("Guardar respuesta: " + rpta);

        //Obtener el ultimo Id de la tabla cab_enc_rpta, para ser usado al guardar la pregunta
        CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);
        System.out.println("idCabecera obtenida: " + cabeceraRespuesta.getIdCabeceraEnc());

        //Guardar la respuesta de cada pregunta en BD
        cabeceraRespuestaDAO.insertarDetEnc(PreguntasActivity.this, rpta, String.valueOf(cabeceraRespuesta.getIdCabeceraEnc()), idPregunta);

    }

    private void limpiarLista() {
        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.limpiarLista();

            //Tipo de pregunta Multiple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            TipoPreguntaMultipleAdapter.tipoPreguntaMultipleAdapter.limpiarLista();

            //Tipo de pregunta abierta
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();

            //Tipo de pregunta Matriz Simple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            TipoPreguntaMatrizSimpleAdapter.tipoPreguntaMatrizAdapter.limpiarLista();

            //Tipo de pregunta Matriz Multiple
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            TipoPreguntaMatrizMultipleAdapter.tipoPreguntaMatrizAdapter.limpiarLista();

            //Tipo de pregunta Mixta
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            TipoPreguntaMixtaAdapter.tipoPreguntaMixtaAdapter.limpiarLista();
        }
    }

    public void retomarEncuesta() {
        //se buscara la primera pregunta que no haya sido respondida por todos.
        DetalleEncRptaDAO detalleEncRptaDAO = new DetalleEncRptaDAO();
        EncuestaDAO encuestaDAO = new EncuestaDAO();

        String idPreguntaDeRpta = null;
        ArrayList<String> respuesta = null;


        int numeroPreguntas = encuestaDAO.obtenerNumeroPreguntas(PreguntasActivity.this);

        if (numeroPreguntas != -1) {
            for (int i = 0; i < numeroPreguntas; i++) {

                if (i == 0) {
                    respuesta = detalleEncRptaDAO.obtenerRpta(PreguntasActivity.this);
                } else {
                    //respuesta = detalleEncRptaDAO.obtenerRptaxId(PreguntasActivity.this,);
                }

                idRespuesta = Integer.parseInt(respuesta.get(0));
                valorRespuesta = respuesta.get(1).toString().toLowerCase();

                if (valorRespuesta.contains("null")) //pregunta que no ha sido respondida totalmente
                {
                    idPreguntaDeRpta = respuesta.get(2).toString(); // se asigna el id de la Pregunta que no ha sido respondida
                }
            }

            //setear los datos en las variables globales


            //invocar al metodo para mostrar las preguntas a responder
            leerTipoPreguntaxPregunta();
        }
        //error al obtener el numero de preguntas
        else {
            Toast.makeText(PreguntasActivity.this, "Error en el sistema. Consulte con su Adm.", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(PreguntasActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.finalizarEncuesta:
                editTextObservacionFinalizar = new EditText(context);
                editTextObservacionFinalizar.setHint("Observaciones");
                editTextObservacionFinalizar.setTextColor(getResources().getColor(R.color.color_texto));
                new AlertDialog.Builder(PreguntasActivity.this)
                        .setTitle("Mensaje")
                        .setView(editTextObservacionFinalizar)
                        .setMessage("¿Desea finalizar la encuesta con estado Completo?")
                        .setIcon(R.drawable.ic_save_black_24dp)
                        .setPositiveButton("Finalizar encuesta", alertaAceptarEncuestaFinalizadaOnClickListener)
                        .setNegativeButton("Continuar encuesta", alertaCancelarOnClickListener)
                        .setCancelable(false).show();
                return true;

            case R.id.rechazarEncuesta:

                editTextObservacionRechazar = new EditText(context);
                editTextObservacionRechazar.setHint("Observaciones");
                editTextObservacionRechazar.setTextColor(getResources().getColor(R.color.color_texto));

                new AlertDialog.Builder(PreguntasActivity.this)
                        .setTitle("Alerta")
                        .setView(editTextObservacionRechazar)
                        .setMessage("¿Desea rechazar la encuesta?")
                        .setPositiveButton("Rechazar encuesta", alertaAceptarRechazarOnClickListener)
                        .setNegativeButton("Continuar encuesta", alertaCancelarOnClickListener)
                        .setCancelable(false).show();
                return true;
        }
        return true;
    }

    protected boolean conectadoInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }

        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }

        return false;
    }
}


