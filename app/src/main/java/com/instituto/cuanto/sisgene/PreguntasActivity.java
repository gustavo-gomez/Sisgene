package com.instituto.cuanto.sisgene;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.instituto.cuanto.sisgene.dao.EnviarEncuestaDAO;
import com.instituto.cuanto.sisgene.dao.TipoPreguntaDAO;
import com.instituto.cuanto.sisgene.entidad.CabeceraEncuestaRpta;
import com.instituto.cuanto.sisgene.entities.Pregunta;
import com.instituto.cuanto.sisgene.entities.RespuestaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizMultipleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMatrizSimpleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMixtaItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaMultipleItem;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaUnicaItem;
import com.instituto.cuanto.sisgene.util.EnvioServiceUtil;
import com.instituto.cuanto.sisgene.util.TiempoDiferencia;
import com.instituto.cuanto.sisgene.util.Util;

import java.io.File;
import java.io.IOException;
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

    String rangoDesde;
    String rangoHasta;

    String comentario;
    //datos para validar encuesta
    int ultimoIdPregunta; //ultimo id de pregunta de la tabla Pregunta. Para validar si ya se ha repondido todas las preguntas
    boolean ultimaPregunta = false;
    EditText editTextObservacionRechazar;
    EditText editTextObservacionFinalizar;
    EditText editTextObservacion;

    //Retomar encuesta
    int idRespuesta;
    String valorRespuesta;
    String idPreguntaDeRpta;

    //TIPO ABIERTO VALID
    String pre_subtipo;
    String pre_tiponumerico;

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
            System.out.println("PreguntasActivity DIM codigosIdentEncuestados: " + codigosIdentEncuestados.size());
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
        System.out.println("ultimoIdPregunta: " + ultimoIdPregunta);
    }

    private void leerPrimeraPregunta() {


        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;
        //ir a BD para sacar la primera preguna de dicha ecuesta
        encuestaPregunta = encuestaDAO.obtenerPreguntaEncuesta(PreguntasActivity.this);
        // Setear datos para la primera pregunta

        //Obteniendo rangos
        rangoDesde = encuestaPregunta.getPre_desde();
        rangoHasta = encuestaPregunta.getPre_hasta();
        System.out.println("encuestaPregunta.getSec_nombre" + encuestaPregunta.getSec_nombre());

        nombreSecccion = encuestaPregunta.getSec_nombre();
        numeroSecccion = encuestaPregunta.getSec_numero_seccion();
        numeroSecccion = encuestaPregunta.getSec_numero_seccion();
        numeroSubSeccion = encuestaPregunta.getSus_numero_subseccion();
        System.out.println("SECCION : "+numeroSecccion+" - nombre: "+nombreSecccion);
        System.out.println("SECCION : "+numeroSubSeccion+" - nombre: "+nombreSubSeccion);
        nombreSubSeccion = encuestaPregunta.getSus_nombre();
        idPregunta = encuestaPregunta.getPre_id();

        //VALID SBTIPO Y NUMER
        pre_subtipo = encuestaPregunta.getPre_subtipo();
        pre_tiponumerico = encuestaPregunta.getPre_tiponumerico();

        comentario  = encuestaPregunta.getPre_comentario();
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
        tvSeccion.setText("Sección Nro. "+numeroSecccion + ": " + nombreSecccion);
        tvSubSeccion.setText(" - Subsección Nro. "+numeroSubSeccion + ": " + nombreSubSeccion);
        tvEnunciadoPregunta.setText("Pregunta Nro. "+numeroPreguntaBD + ": " + enunciadoPregunta+" ("+comentario+")");
        String opciones = "";
        System.out.println("\n\n*************MOSTRAR DATOS SECCION*****************");
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            opciones = opciones + listPreguntaAlterntiva.get(i).getOpc_nombre().trim() + "\t";
            System.out.println("opcion " + i + ": " + listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
        }
        opciones = opciones + "\n";

        if (tipoPreguntaActual.equals("MS") || tipoPreguntaActual.equals("MM"))
            System.out.println("Cantidad de Items: " + listPreguntaItems.size());
        for (int i = 0; i < listPreguntaItems.size(); i++) {
            opciones = opciones + listPreguntaItems.get(i).getIte_nombre().trim() + "\t";
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
        System.out.println("**************FIN DATOS SECCION****************\n\n");
    }

    private int leerSiguientePregunta() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;
        //ir a BD para sacar la primera preguna de dicha ecuesta
        encuestaPregunta = encuestaDAO.obtenerPreguntaEncuesta(PreguntasActivity.this, idPregunta);
        // Setear datos para la primera pregunta

        if (encuestaPregunta != null) {
            //VALID SBTIPO Y NUMER
            pre_subtipo = encuestaPregunta.getPre_subtipo();
            pre_tiponumerico = encuestaPregunta.getPre_tiponumerico();

            //Obteniendo rangos
            rangoDesde = encuestaPregunta.getPre_desde();
            rangoHasta = encuestaPregunta.getPre_hasta();

            nombreSecccion = encuestaPregunta.getSec_nombre();
            numeroSecccion = encuestaPregunta.getSec_numero_seccion();
            numeroSecccion = encuestaPregunta.getSec_numero_seccion();
            numeroSubSeccion = encuestaPregunta.getSus_numero_subseccion();
            nombreSubSeccion = encuestaPregunta.getSus_nombre();
            idPregunta = encuestaPregunta.getPre_id();
            System.out.println("2.- SECCION : "+numeroSecccion+" - nombre: "+nombreSecccion);
            System.out.println("2.- SUBSECCION : "+numeroSubSeccion+" - nombre: "+nombreSubSeccion);
            //pregutna
            tipoPreguntaActual = encuestaPregunta.getPre_tipo_rpta();
            enunciadoPregunta = encuestaPregunta.getPre_enunciado();
            numeroPreguntaBD = encuestaPregunta.getPre_numero();
            encuestarTodos = Integer.parseInt(encuestaPregunta.getPre_unica_persona()); // 0:todos   -  1: una persona
            comentario = encuestaPregunta.getPre_comentario();
            try {
                System.out.println("obtener ordenIMportancia");
                ordenImportancia = Integer.parseInt(encuestaPregunta.getPre_importarordenrptamu()); //Que va a retornar
            } catch (Exception ex) {
                System.out.println("CATCH");
                ex.printStackTrace();
                ordenImportancia = 0;
            }
            try {
                System.out.println("obtener mumMaxChequeados");
                mumMaxChequeados = Integer.parseInt(encuestaPregunta.getPre_nummaxrptamu()); //campo que indica el numero maximo de items a chequear para las preguntas mixtas
                System.out.println("FIN TRY");
            } catch (Exception ex) {
                System.out.println("CATCH");
                ex.printStackTrace();
                mumMaxChequeados = 1;
            }
            //obtener alternativas
            if (!tipoPreguntaActual.equals("AB"))
                listPreguntaAlterntiva = encuestaDAO.obtenerAlternativas(PreguntasActivity.this, idPregunta);
            System.out.println("Se obtuvo num de alternativas");

            if (tipoPreguntaActual.equals("MS") || tipoPreguntaActual.equals("MM"))
                listPreguntaItems = encuestaDAO.obtenerItems(PreguntasActivity.this, idPregunta);
            System.out.println("Se obtuvo num de items");

            return 0;
        }
        return 1;
    }

    private int leerPreguntaPorNumPregunta() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        EncuestaPregunta encuestaPregunta;

        if (etnumPregunta.getText().toString().trim().length() == 0) {
            Toast.makeText(PreguntasActivity.this, "Ingrese el número de pregunta", Toast.LENGTH_LONG).show();
            return -1;
        } else
            encuestaPregunta = encuestaDAO.obtenerPreguntaPorNumPregunta(PreguntasActivity.this,
                    etnumPregunta.getText().toString().trim());

        if (encuestaPregunta != null) {
            //antes de setear los datos de la pregunta encontrada, guardas las respuestas de la pregunta actual
            leeryGuardarDatos();

            //VALID SBTIPO Y NUMER
            pre_subtipo = encuestaPregunta.getPre_subtipo();
            pre_tiponumerico = encuestaPregunta.getPre_tiponumerico();

            //Obteniendo rangos
            rangoDesde = encuestaPregunta.getPre_desde();
            rangoHasta = encuestaPregunta.getPre_hasta();

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

            comentario = encuestaPregunta.getPre_comentario();
            try {
                ordenImportancia = Integer.parseInt(encuestaPregunta.getPre_importarordenrptamu()); //Que va a retornar
            } catch (Exception ex) {
                ex.printStackTrace();
                ordenImportancia = 0;
            }
            //obtener alternativas
            if (!tipoPreguntaActual.equals("AB")) {
                System.out.println("OBTENER ALTERNATIVAS");
                listPreguntaAlterntiva = encuestaDAO.obtenerAlternativas(PreguntasActivity.this, idPregunta);
            }

            if (tipoPreguntaActual.equals("MS") || tipoPreguntaActual.equals("MM")) {
                System.out.println("OBTENER ITEMS");
                listPreguntaItems = encuestaDAO.obtenerItems(PreguntasActivity.this, idPregunta);
                System.out.println("NUMERO DE ITEMS: " + listPreguntaItems.size());
            }
        } else {
            limpiarLista();
            new AlertDialog.Builder(PreguntasActivity.this).setTitle("Alerta").setMessage("El número de pregunta no existe")
                    .setNeutralButton("Aceptar", alertaCancelarOnClickListener)
                    .setCancelable(false).show();
            return -1;
        }
        return 0;
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            System.out.println(" ***********  ENTRO A BOTON SIGTE  ************");

            //leer los datos de la pregunta actual y guardar en la base de datos
            boolean valida = true;

            valida = leeryGuardarDatos();

            System.out.println("**************** VALIDA : "+valida);

            if(valida == true) {

                if (!ultimaPregunta) {
                    //leer la siguiente pregunta

                    System.out.println("Se ejecuto leerSiguientePregunta de la pregunta " + idPregunta);
                    System.out.println("ultimoIdPregunta siguiente: " + ultimoIdPregunta);

                    if (leerSiguientePregunta() == 0) {
                        //Aun no se llega a la ultima pregunta
                        //mostrar interfaz segun la pregunta leida
                        leerTipoPreguntaxPregunta();
                    } else {
                        // if(Integer.parseInt(idPregunta) != ultimoIdPregunta )
                        ultimaPregunta = true;
                        btnSiguiente.setEnabled(false);

                        System.out.println(" ***********  ENTRO A BOTON DIALOG 0  ************");

                        new AlertDialog.Builder(PreguntasActivity.this)
                                .setTitle("Confirmación")
                                .setView(editTextObservacion)
                                .setMessage("Se ha Finalizado la Encuesta")
                                .setIcon(R.drawable.ic_save_black_24dp)
                                .setPositiveButton("Terminar encuesta", alertaAceptarEncuestaFinalizadaOnClickListener)
                                .setCancelable(false).show();
                    }
                } else {
                    btnSiguiente.setEnabled(false);
                    Toast.makeText(PreguntasActivity.this, "Se han respondido todas las preguntas", Toast.LENGTH_LONG).show();

                    System.out.println(" ***********  ENTRO A BOTON DIALOG  ************");

                    new AlertDialog.Builder(PreguntasActivity.this)
                            .setTitle("Alerta")
                            .setView(editTextObservacion)
                            .setMessage("¿Desea guardar la encuesta?")
                            .setIcon(R.drawable.ic_save_black_24dp)
                            .setPositiveButton("Terminar encuesta", alertaAceptarEncuestaFinalizadaOnClickListener)
                            .setNegativeButton("Continuar encuesta", alertaCancelarOnClickListener)
                            .setCancelable(false).show();
                }
            }else{
                Toast.makeText(PreguntasActivity.this, "Cantidad ingresada tiene que estar en el rango de "+rangoDesde+" y "+rangoHasta, Toast.LENGTH_LONG).show();
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

            if (leerPreguntaPorNumPregunta() == 0) {
                if (!ultimaPregunta) {
                    etnumPregunta.setText("");
                    if (Integer.parseInt(idPregunta) != ultimoIdPregunta) {
                        //Aun no se llega a la ultima pregunta, mostrar interfaz segun la pregunta leida
                        leerTipoPreguntaxPregunta();
                    } else
                        ultimaPregunta = true;
                } else {
                    btnSiguiente.setEnabled(false);
                    Toast.makeText(PreguntasActivity.this, "Ésta es la última pregunta de la encuesta", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    DialogInterface.OnClickListener alertaAceptarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            String fechaInicio;
            String diferenciaHoras = "";
            String fechaFin = Util.obtenerFechayHora();

            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            //obtener el id de la ultima cabecera
            CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);

            //obtener la hora de inicio de l encuesta
            fechaInicio = cabeceraRespuestaDAO.obtenerFechayHoraInicioxEncuesta(PreguntasActivity.this,
                    "" + cabeceraRespuesta.getIdCabeceraEnc());
            //si no hay errr al obtener la fecha inicio
            if (fechaInicio != null)
                diferenciaHoras = TiempoDiferencia.retornarDiferenciaTiempo(fechaInicio, fechaFin);

            cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this,
                    "I",//estado
                    fechaFin,// horaFin
                    diferenciaHoras,// tiempo
                    "0",//Estado enviado
                    " ",// fecha_envio
                    editTextObservacion.getText().toString().trim(),
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
            String fechaInicio;
            String diferenciaHoras = "";
            String fechaFin = Util.obtenerFechayHora();

            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            //obtener el id de la ultima cabecera
            CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);

            //obtener la hora de inicio de l encuesta
            fechaInicio = cabeceraRespuestaDAO.obtenerFechayHoraInicioxEncuesta(PreguntasActivity.this,
                    "" + cabeceraRespuesta.getIdCabeceraEnc());
            //si no hay errr al obtener la fecha inicio
            if (fechaInicio != null)
                diferenciaHoras = TiempoDiferencia.retornarDiferenciaTiempo(fechaInicio, fechaFin);

            //Guardar la encuesta con estado incompleto
            cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this,
                    "R",                         //estado
                    fechaFin,   // horaFin
                    diferenciaHoras,// tiempo
                    "0",//Estado enviado
                    " ",// fecha_envio
                    editTextObservacionRechazar.getText().toString().trim(),
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

            String fechaInicio;
            String diferenciaHoras = "";
            String fechaFin = Util.obtenerFechayHora();
            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            //obtener el id de la ultima cabecera
            CabeceraRespuesta cabeceraRespuesta = cabeceraRespuestaDAO.obteneridUltimaCabecera(PreguntasActivity.this);

            //obtener la hora de inicio de l encuesta
            fechaInicio = cabeceraRespuestaDAO.obtenerFechayHoraInicioxEncuesta(PreguntasActivity.this,
                    "" + cabeceraRespuesta.getIdCabeceraEnc());
            //si no hay errr al obtener la fecha inicio
            if (fechaInicio != null)
                diferenciaHoras = TiempoDiferencia.retornarDiferenciaTiempo(fechaInicio, fechaFin);

            cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this,
                    "C",            //estado
                    fechaFin,       // horaFin
                    diferenciaHoras,// tiempo
                    "0",            //Estado enviado
                    "",             // fecha_envio
                    editTextObservacionFinalizar.getText().toString().trim(), // Obs
                    cabeceraRespuesta.getIdCabeceraEnc()); // cabRpta_id

            EnvioServiceUtil envioServiceUtil = new EnvioServiceUtil();
            boolean estTemp = false;
            //Si hay conexion a internet invocar al WS para envviar la data
            Log.i("AL SD", "ANTES DE EJECUTAR LA FUNCION");
            envioServiceUtil.guardarSDEncuestaEjecutada(PreguntasActivity.this, cabeceraRespuesta.getIdCabeceraEnc() + "");
            Log.i("AL SD", "DESPUES DE EJECUTAR LA FUNCION");

            System.out.println("ULTIMO ID ACTUUUUUUUUUUUUU : "+cabeceraRespuesta.getIdCabeceraEnc());

            if (conectadoInternet()) {
                EnviarEncuestaDAO enviarEncuestaDAO = new EnviarEncuestaDAO();
                CabeceraEncuestaRpta cabeceraEncuestaRpta = enviarEncuestaDAO.obtenerCabEncRtpaEncuestada(context, cabeceraRespuesta.getIdCabeceraEnc()+"");

                estTemp = envioServiceUtil.enviarEncuestaEjecutada(PreguntasActivity.this, cabeceraRespuesta.getIdCabeceraEnc() + "");

            }

            //el envio de data al WS fue satisfactorio
            if (estTemp) {
                cabeceraRespuestaDAO.actualizarCabEncFinalEjecucion(PreguntasActivity.this,
                        "C",
                        fechaFin, // horaFin
                        diferenciaHoras,//tiempo
                        "1", //enviado
                        Util.obtenerFechayHora(), //fecha envio
                        editTextObservacionFinalizar.getText().toString().trim(),
                        cabeceraRespuesta.getIdCabeceraEnc());

                new AlertDialog.Builder(PreguntasActivity.this).setTitle("Mensaje")
                        .setMessage("Se ha enviado la encuesta al servidor satisfactoriamente")
                        .setNeutralButton("Aceptar", alertaAceptarGuardarEncuestaOnClickListener)
                        .setCancelable(false).show();
            } else {
                new AlertDialog.Builder(PreguntasActivity.this).setTitle("Mensaje")
                        .setMessage("Se ha guardado la encuesta completada satisfactoriamente")
                        .setNeutralButton("Aceptar", alertaAceptarGuardarEncuestaOnClickListener)
                        .setCancelable(false).show();
            }

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

    private boolean leeryGuardarDatos() {
        //Leer los dato segun el tipo de pregunta actual
        boolean retorno = true;

        if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            leerRespuestasTipoUnica();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            leerRespuestasTipoMultiple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaAbierta))) {
            retorno = leerRespuestasTipoAbierta();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatSimple))) {
            leerRespuestasTipoMatrizSimple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMatMultiple))) {
            leerRespuestasTipoMatrizMultiple();
        } else if (tipoPreguntaActual.equals(getResources().getString(R.string.tipoPreguntaMixta))) {
            leerRespuestasTipoMixta();
        }

        return retorno;
    }

    private boolean leerRespuestasTipoAbierta() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String respuestaAbierta = "";

        System.out.println("numero de personas :" + codigosIdentEncuestados.size());
        System.out.println("TipoPreguntaAbiertaAdapter: " + TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size());

        boolean estado = true;

        for (int i = 0; i < TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size(); i++) {
            TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.getItem(i);
            //agregar el codigo de identificacion
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            codIdent.format("%02d", (i+1));
            respuestaAbierta = respuestaAbierta + "[" + codIdent + "]";

            System.out.println("******************  DESCRIPCION ******* : "+tipoPreguntaAbiertaItem.getDescription());
            System.out.println("******************  tipoPreguntaActual ******* : "+tipoPreguntaActual);
            System.out.println("******************  rangoDesde ******* : "+rangoDesde);
            System.out.println("******************  rangoHasta ******* : "+rangoHasta);

            //    respuestaAbierta = respuestaAbierta + "null";

            if (rangoDesde == null || rangoDesde.trim().equals("") || rangoHasta == null || rangoHasta.trim().equals("")) {
            } else {
                double descrip=0.0;
                double rngDesde=0.0;
                double rngHasta=0.0;
                try {
                    descrip = Double.parseDouble(tipoPreguntaAbiertaItem.getDescription());
                    rngDesde = Double.parseDouble(rangoDesde);
                    rngHasta = Double.parseDouble(rangoHasta);
                }catch(NumberFormatException n){
                    descrip=0.0;
                }

                if (descrip > rngHasta || descrip < rngDesde) {
                    estado = false;
                }

                if(descrip == 0.0){
                    estado = false;
                }
            }


            System.out.println("******************  estado ******* : "+estado);

            //en caso el usuario no haya escrito nada
            if (nombresEncuestados.get(i).trim().equals(tipoPreguntaAbiertaItem.getDescription().trim())) {
                respuestaAbierta = respuestaAbierta + "null";
            } else {
                //int idPreguntaTemp = Integer.parseInt(idPregunta);
                //String valorTemp = encuestaDAO.obtenerValorIdOpcion(PreguntasActivity.this,idPreguntaTemp,tipoPreguntaAbiertaItem.getDescription().trim());
                respuestaAbierta = respuestaAbierta + tipoPreguntaAbiertaItem.getDescription();
                //respuestaAbierta = respuestaAbierta + valorTemp;
            }
            if (i != TipoPreguntaAbiertaAdapter.myListPreguntaAbierta.size() - 1) {
                respuestaAbierta = "" + respuestaAbierta + "&";
            }
        }

        System.out.println("RESP TA: " + respuestaAbierta);

        if(estado == true) {
            //guardar en base de datos la respuesta
            System.out.println("RESP TA 2 : " + respuestaAbierta);
            TipoPreguntaAbiertaAdapter.tipoPreguntaAbiertaAdapter.limpiarLista();
            guardarRespuesta(respuestaAbierta);
            respuestaAbierta = respuestaAbierta + "null";
        }
        return estado;
    }

    private void leerRespuestasTipoUnica() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String respuestaUnica = "";

        for (int i = 0; i < TipoPreguntaUnicaAdapter.myListPreguntaUnica.size(); i++) {
            TipoPreguntaUnicaItem tipoPreguntaUnicaItem = TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.getItem(i);

            //agregar el codigo de identificacion
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            codIdent.format("%02d", (i+1));
            respuestaUnica = respuestaUnica + "[" + codIdent + "]";

            if (tipoPreguntaUnicaItem.getPos() == 0) {
                respuestaUnica = respuestaUnica + "null";
            }else {
                int idPreguntaTemp = Integer.parseInt(idPregunta);
                String valorTemp = encuestaDAO.obtenerValorIdOpcion(PreguntasActivity.this, idPreguntaTemp, tipoPreguntaUnicaItem.getRespuesta().trim());
                //respuestaUnica = respuestaUnica + tipoPreguntaUnicaItem.getRespuesta();
                respuestaUnica = respuestaUnica + valorTemp;
            }

            if (i != TipoPreguntaUnicaAdapter.myListPreguntaUnica.size() - 1)
                respuestaUnica = "" + respuestaUnica + "&";
        }
        TipoPreguntaUnicaAdapter.tipoPreguntaUnicaAdapter.limpiarLista();
        System.out.println("RESP TU: " + respuestaUnica);
        guardarRespuesta(respuestaUnica);
    }

    private void leerRespuestasTipoMultiple() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String respuestaMultiple = "";
        ArrayList<TipoPreguntaMultipleItem> tipoPreguntaMultipleItems = TipoPreguntaMultipleAdapter.myListPreguntaMultiple;
        int cont = 0;
        //agregar el codigo de identificacion

        for (int i = 0; i < tipoPreguntaMultipleItems.size(); i++) {
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            codIdent.format("%02d", (i+1));
            respuestaMultiple = respuestaMultiple + "[" + codIdent + "]";

            for (int j = 0; j < tipoPreguntaMultipleItems.get(i).getRespuestas().size(); j++) {

                int idPreguntaTemp = Integer.parseInt(idPregunta);
                String valorTemp = encuestaDAO.obtenerValorIdOpcion(PreguntasActivity.this, idPreguntaTemp, tipoPreguntaMultipleItems.get(i).getRespuestas().get(j).trim());

                //respuestaMultiple = respuestaMultiple + tipoPreguntaMultipleItems.get(i).getRespuestas().get(j).trim();
                respuestaMultiple = respuestaMultiple + valorTemp;

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
        System.out.println("RESP TM: " + respuestaMultiple);
        guardarRespuesta(respuestaMultiple);
    }

    private void leerRespuestasTipoMixta() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String respuestaMixta = "";
        ArrayList<TipoPreguntaMixtaItem> tipoPreguntaMixtaItems = TipoPreguntaMixtaAdapter.myListPreguntaMixta;
        int cont = 0;
        //agregar el codigo de identificacion

        for (int i = 0; i < tipoPreguntaMixtaItems.size(); i++) {
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            codIdent.format("%02d", (i+1));
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
                int idPreguntaTemp = Integer.parseInt(idPregunta);
                String valorTemp = encuestaDAO.obtenerValorIdOpcion(PreguntasActivity.this, idPreguntaTemp, rpta.trim());

                //respuestaMixta = respuestaMixta + rpta;
                respuestaMixta = respuestaMixta + valorTemp;

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
        System.out.println("RESP TMixta: " + respuestaMixta);
        guardarRespuesta(respuestaMixta);
    }

    private void leerRespuestasTipoMatrizSimple() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String respuestaMatrizS = "";
        System.out.println("dimension myListPreguntaMatrizSimple:" + TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz.size());
        ArrayList<TipoPreguntaMatrizSimpleItem> tipoPreguntaMatrizSimpleItems = TipoPreguntaMatrizSimpleAdapter.myListPreguntaMatriz;

        System.out.println("size: " + tipoPreguntaMatrizSimpleItems.size());

        for (int i = 0; i < tipoPreguntaMatrizSimpleItems.size(); i++) {
            Formatter codIdent = new Formatter();

            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            codIdent.format("%02d", (i+1));
            respuestaMatrizS = respuestaMatrizS + "[" + codIdent + "]";

            for (int k = 0; k < tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().size(); k++) {
                System.out.println("vertical: " + tipoPreguntaMatrizSimpleItems.get(i).getVertical().get(k));
                System.out.println("respuesta " + k + " :" + tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim());

                respuestaMatrizS = respuestaMatrizS + tipoPreguntaMatrizSimpleItems.get(i).getVertical().get(k).trim();

                respuestaMatrizS = respuestaMatrizS + "%";

                if (tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim().equals(""))
                    respuestaMatrizS = respuestaMatrizS + "null";
                else {
                    int idPreguntaTemp = Integer.parseInt(idPregunta);
                    String valorTemp = encuestaDAO.obtenerValorIdItem(PreguntasActivity.this, idPreguntaTemp, tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim());

                    //respuestaMatrizS = respuestaMatrizS + tipoPreguntaMatrizSimpleItems.get(i).getRespuestas().get(k).trim();
                    respuestaMatrizS = respuestaMatrizS + valorTemp.trim();
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
        System.out.println("RESP TMatriz simple: " + respuestaMatrizS);
        guardarRespuesta(respuestaMatrizS);
        System.out.println("---------FIN TOMA DE DATOS MATRIZ SIMPLE");
    }

    private void leerRespuestasTipoMatrizMultiple() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String respuestaMatrizM = "";
        System.out.println("dimension myListPreguntaMatrizMultiple:" + TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz.size());
        ArrayList<TipoPreguntaMatrizMultipleItem> tipoPreguntaMatrizMultipleItems = TipoPreguntaMatrizMultipleAdapter.myListPreguntaMatriz;
        int cont = 0;

        for (int i = 0; i < tipoPreguntaMatrizMultipleItems.size(); i++) {
            Formatter codIdent = new Formatter();
            //codIdent.format("%02d", codigosIdentEncuestados.get(i));
            codIdent.format("%02d", (i+1));
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

                            int idPreguntaTemp = Integer.parseInt(idPregunta);
                            String valorTemp = encuestaDAO.obtenerValorIdItem(PreguntasActivity.this, idPreguntaTemp, respuestas.get(l).getTexto());

                            //respuestaMatrizM = respuestaMatrizM + respuestas.get(l).getTexto();
                            respuestaMatrizM = respuestaMatrizM + valorTemp;

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
        System.out.println("RESP TMatriz mult: " + respuestaMatrizM);
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

        TipoPreguntaAbiertaAdapter.pre_subtipo = pre_subtipo;
        TipoPreguntaAbiertaAdapter.pre_tiponumerico = pre_tiponumerico;

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
        //mumMaxChequeados = 3;

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

        System.out.println("poblarLista_TipoPreguntaMatrizSimple- listPreguntaItems" + listPreguntaItems.size());

        for (int i = 0; i < listPreguntaItems.size(); i++) {
            horizontales.add(listPreguntaItems.get(i).getIte_nombre().trim());
            //temporal
            System.out.println("ITEM " + (i + 1) + ": " + listPreguntaItems.get(i).getIte_nombre().trim());
            //
        }

        ArrayList<String> verticales = new ArrayList<>();
        for (int i = 0; i < listPreguntaAlterntiva.size(); i++) {
            System.out.println("Vertical " + (i + 1) + ": " + listPreguntaAlterntiva.get(i).getOpc_nombre().trim());
            String cadenTemp = listPreguntaAlterntiva.get(i).getOpc_nombre().trim();
            String cadUtl = "";
            cadUtl = cadenTemp;
            /*if(cadenTemp.length()>50){
                cadUtl = cadenTemp.substring(0,50) + "\n" + cadenTemp.substring(50,cadenTemp.length());
            }else{
                cadUtl = cadenTemp;
            }*/
            verticales.add(cadUtl);
        }

        System.out.println("Len horizontales: " + horizontales.size());
        System.out.println("Len verticales: " + verticales.size());

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

        ArrayList<String> respuesta = null;


        int numeroPreguntas = encuestaDAO.obtenerNumeroPreguntas(PreguntasActivity.this);

        if (numeroPreguntas != -1) {
            //recorrer las respuestas hasta encontrar una que no haya sido respondita totalmente
            //for (int i = 0; i < numeroPreguntas; i++) {

            //if (i == 0)
            // {
            respuesta = detalleEncRptaDAO.obtenerRpta(PreguntasActivity.this);
            //} else {
            //   respuesta = detalleEncRptaDAO.obtenerRptaxId(PreguntasActivity.this,idPregunta);
            //}

            //idRespuesta = Integer.parseInt(respuesta.get(0));
            valorRespuesta = respuesta.get(1).toLowerCase();

            if (valorRespuesta.contains("null")) //pregunta que no ha sido respondida totalmente
                idPregunta = respuesta.get(2); // se asigna el id de la Pregunta que no ha sido respondida
            //}

            //setear los datos en las variables globalesu
            leerSiguientePregunta();

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

            case R.id.tomarFoto:
                try {
                    getCamara();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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


    /** FOTO ADICIONAL*/
    private String foto;
    private File file;
    private File carp;
    private Uri output;
    private Bitmap bmp;
    private ImageView imagen;

    private void getCamara() throws IOException {
        CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();
        EncuestaDAO encuestaDAO = new EncuestaDAO();

        String numEncuesta = cabeceraRespuestaDAO.obtenerUltNumeroEncuesta(PreguntasActivity.this);
        String codEncuesta = encuestaDAO.obtenerCodigoEncuesta(PreguntasActivity.this);
        System.out.println("NuM ENC : "+numEncuesta);

        String dirCarpeta = Environment.getExternalStorageDirectory() +"/SISGENE_FOTO/";

        foto = Environment.getExternalStorageDirectory() +"/SISGENE_FOTO/"+codEncuesta+"_"+numEncuesta+".jpg";
        System.out.println("RUTA FOTO: "+foto);

        carp = new File(dirCarpeta);
        carp.mkdir();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(carp.exists()){
            file = new File(foto);
            file.createNewFile();

            output = Uri.fromFile(file);
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
            startActivityForResult(intent, 1);
        }else{
            Toast.makeText(PreguntasActivity.this, "NO EXISTE ARCHIVO SISGENE_FOTO ",
            Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == MainActivity.RESULT_OK){

            Toast.makeText(PreguntasActivity.this, "FOTO GUARDADO EN : "+foto,
                    Toast.LENGTH_LONG).show();
        }

    }
}


