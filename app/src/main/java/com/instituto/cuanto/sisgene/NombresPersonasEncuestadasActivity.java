package com.instituto.cuanto.sisgene;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.EncuestaDAO;
import com.instituto.cuanto.sisgene.dao.PersonaDAO;
import com.instituto.cuanto.sisgene.dao.UsuarioDAO;
import com.instituto.cuanto.sisgene.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 01/11/2015.
 */
public class NombresPersonasEncuestadasActivity extends AppCompatActivity {

    LinearLayout lyNombre1, lyNombre2, lyNombre3, lyNombre4, lyNombre5, lyNombre6, lyNombre7, lyNombre8, lyNombre9, lyNombre10,
            lyNombre11, lyNombre12, lyNombre13, lyNombre14, lyNombre15, lyNombre16, lyNombre17, lyNombre18, lyNombre19, lyNombre20;
    EditText etNombre1, etNombre2, etNombre3, etNombre4, etNombre5, etNombre6, etNombre7, etNombre8, etNombre9, etNombre10,
            etNombre11, etNombre12, etNombre13, etNombre14, etNombre15, etNombre16, etNombre17, etNombre18, etNombre19, etNombre20;
    EditText edApellidoPat1, edApellidoPat2, edApellidoPat3, edApellidoPat4, edApellidoPat5, edApellidoPat6, edApellidoPat7, edApellidoPat8,
            edApellidoPat9, edApellidoPat10, edApellidoPat11, edApellidoPat12, edApellidoPat13, edApellidoPat14, edApellidoPat15, edApellidoPat16,
            edApellidoPat17, edApellidoPat18, edApellidoPat19, edApellidoPat20;
    EditText edApellidoMat1, edApellidoMat2, edApellidoMat3, edApellidoMat4, edApellidoMat5, edApellidoMat6, edApellidoMat7, edApellidoMat8,
            edApellidoMat9, edApellidoMat10, edApellidoMat11, edApellidoMat12, edApellidoMat13, edApellidoMat14, edApellidoMat15, edApellidoMat16,
            edApellidoMat17, edApellidoMat18, edApellidoMat19, edApellidoMat20;
    TextView tvCodigoEncuesta, tvNombreSupervisor, tvNombreUsuario, tvGrupo, tvFecha, tvFechaVigenciaInicio, tvFechaVigenciaFinal;

    TextView tvNumeroPersonas;
    Button btMenosNumPersonas, btMasNumPersonas, btAceptarNumeroPersonas;
    int numerodePersonasEncuestadas;
    ArrayList<LinearLayout> nombresLayouts;
    ArrayList<EditText> nombresEdits;
    ArrayList<EditText> apellidosPatEdits;
    ArrayList<EditText> apellidosMatEdits;
    Button btAceptar_nombresEncuestados;

    ArrayList<String> nombresEncuestados;
    ArrayList<Integer> codigosIdentEncuestados;

    public static String KEY_ARG_NOMBRES_ENCUESTADOS = "KEY_ARG_NOMBRES_ENCUESTADOS";
    public static String KEY_ARG_ID_ENCUESTADOS = "KEY_ARG_ID_ENCUESTADOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_personas_encuestadas);

        numerodePersonasEncuestadas = 0;
        nombresLayouts = new ArrayList<>();
        nombresEdits = new ArrayList<>();
        apellidosPatEdits = new ArrayList<>();
        apellidosMatEdits = new ArrayList<>();
        nombresEncuestados = new ArrayList<>();
        codigosIdentEncuestados = new ArrayList<>();

        lyNombre1 = (LinearLayout) findViewById(R.id.lyNombre1);
        lyNombre2 = (LinearLayout) findViewById(R.id.lyNombre2);
        lyNombre3 = (LinearLayout) findViewById(R.id.lyNombre3);
        lyNombre4 = (LinearLayout) findViewById(R.id.lyNombre4);
        lyNombre5 = (LinearLayout) findViewById(R.id.lyNombre5);
        lyNombre6 = (LinearLayout) findViewById(R.id.lyNombre6);
        lyNombre7 = (LinearLayout) findViewById(R.id.lyNombre7);
        lyNombre8 = (LinearLayout) findViewById(R.id.lyNombre8);
        lyNombre9 = (LinearLayout) findViewById(R.id.lyNombre9);
        lyNombre10 = (LinearLayout) findViewById(R.id.lyNombre10);
        lyNombre11 = (LinearLayout) findViewById(R.id.lyNombre11);
        lyNombre12 = (LinearLayout) findViewById(R.id.lyNombre12);
        lyNombre13 = (LinearLayout) findViewById(R.id.lyNombre13);
        lyNombre14 = (LinearLayout) findViewById(R.id.lyNombre14);
        lyNombre15 = (LinearLayout) findViewById(R.id.lyNombre15);
        lyNombre16 = (LinearLayout) findViewById(R.id.lyNombre16);
        lyNombre17 = (LinearLayout) findViewById(R.id.lyNombre17);
        lyNombre18 = (LinearLayout) findViewById(R.id.lyNombre18);
        lyNombre19 = (LinearLayout) findViewById(R.id.lyNombre19);
        lyNombre20 = (LinearLayout) findViewById(R.id.lyNombre20);

        etNombre1 = (EditText) findViewById(R.id.edNombre1);
        etNombre2 = (EditText) findViewById(R.id.edNombre2);
        etNombre3 = (EditText) findViewById(R.id.edNombre3);
        etNombre4 = (EditText) findViewById(R.id.edNombre4);
        etNombre5 = (EditText) findViewById(R.id.edNombre5);
        etNombre6 = (EditText) findViewById(R.id.edNombre6);
        etNombre7 = (EditText) findViewById(R.id.edNombre7);
        etNombre8 = (EditText) findViewById(R.id.edNombre8);
        etNombre9 = (EditText) findViewById(R.id.edNombre9);
        etNombre10 = (EditText) findViewById(R.id.edNombre10);
        etNombre11 = (EditText) findViewById(R.id.edNombre11);
        etNombre12 = (EditText) findViewById(R.id.edNombre12);
        etNombre13 = (EditText) findViewById(R.id.edNombre13);
        etNombre14 = (EditText) findViewById(R.id.edNombre14);
        etNombre15 = (EditText) findViewById(R.id.edNombre15);
        etNombre16 = (EditText) findViewById(R.id.edNombre16);
        etNombre17 = (EditText) findViewById(R.id.edNombre17);
        etNombre18 = (EditText) findViewById(R.id.edNombre18);
        etNombre19 = (EditText) findViewById(R.id.edNombre19);
        etNombre20 = (EditText) findViewById(R.id.edNombre20);

        edApellidoPat1 = (EditText) findViewById(R.id.edApellidoPat1);
        edApellidoPat2 = (EditText) findViewById(R.id.edApellidoPat2);
        edApellidoPat3 = (EditText) findViewById(R.id.edApellidoPat3);
        edApellidoPat4 = (EditText) findViewById(R.id.edApellidoPat4);
        edApellidoPat5 = (EditText) findViewById(R.id.edApellidoPat5);
        edApellidoPat6 = (EditText) findViewById(R.id.edApellidoPat6);
        edApellidoPat7 = (EditText) findViewById(R.id.edApellidoPat7);
        edApellidoPat8 = (EditText) findViewById(R.id.edApellidoPat8);
        edApellidoPat9 = (EditText) findViewById(R.id.edApellidoPat9);
        edApellidoPat10 = (EditText) findViewById(R.id.edApellidoPat10);
        edApellidoPat11 = (EditText) findViewById(R.id.edApellidoPat11);
        edApellidoPat12 = (EditText) findViewById(R.id.edApellidoMat12);
        edApellidoPat13 = (EditText) findViewById(R.id.edApellidoPat13);
        edApellidoPat14 = (EditText) findViewById(R.id.edApellidoPat14);
        edApellidoPat15 = (EditText) findViewById(R.id.edApellidoPat15);
        edApellidoPat16 = (EditText) findViewById(R.id.edApellidoPat16);
        edApellidoPat17 = (EditText) findViewById(R.id.edApellidoPat17);
        edApellidoPat18 = (EditText) findViewById(R.id.edApellidoPat18);
        edApellidoPat19 = (EditText) findViewById(R.id.edApellidoPat19);
        edApellidoPat20 = (EditText) findViewById(R.id.edApellidoPat20);

        edApellidoMat1 = (EditText) findViewById(R.id.edApellidoMat1);
        edApellidoMat2 = (EditText) findViewById(R.id.edApellidoMat2);
        edApellidoMat3 = (EditText) findViewById(R.id.edApellidoMat3);
        edApellidoMat4 = (EditText) findViewById(R.id.edApellidoMat4);
        edApellidoMat5 = (EditText) findViewById(R.id.edApellidoMat5);
        edApellidoMat6 = (EditText) findViewById(R.id.edApellidoMat6);
        edApellidoMat7 = (EditText) findViewById(R.id.edApellidoMat7);
        edApellidoMat8 = (EditText) findViewById(R.id.edApellidoMat8);
        edApellidoMat9 = (EditText) findViewById(R.id.edApellidoMat9);
        edApellidoMat10 = (EditText) findViewById(R.id.edApellidoMat10);
        edApellidoMat11 = (EditText) findViewById(R.id.edApellidoMat11);
        edApellidoMat12 = (EditText) findViewById(R.id.edApellidoMat12);
        edApellidoMat13 = (EditText) findViewById(R.id.edApellidoMat13);
        edApellidoMat14 = (EditText) findViewById(R.id.edApellidoMat14);
        edApellidoMat15 = (EditText) findViewById(R.id.edApellidoMat15);
        edApellidoMat16 = (EditText) findViewById(R.id.edApellidoMat16);
        edApellidoMat17 = (EditText) findViewById(R.id.edApellidoMat17);
        edApellidoMat18 = (EditText) findViewById(R.id.edApellidoMat18);
        edApellidoMat19 = (EditText) findViewById(R.id.edApellidoMat19);
        edApellidoMat20 = (EditText) findViewById(R.id.edApellidoMat20);

        tvFechaVigenciaFinal = (TextView) findViewById(R.id.tvFechaVigenciaFinal);
        tvNombreSupervisor = (TextView) findViewById(R.id.tvNombreSupervisor);
        tvCodigoEncuesta = (TextView) findViewById(R.id.tvCodigoEncuesta);
        tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuario);
        tvGrupo = (TextView) findViewById(R.id.tvGrupo);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvFechaVigenciaInicio = (TextView) findViewById(R.id.tvFechaVigenciaInicio);
        tvFechaVigenciaFinal = (TextView) findViewById(R.id.tvFechaVigenciaFinal);

        btAceptar_nombresEncuestados = (Button) findViewById(R.id.btAceptar_nombresEncuestados);

        tvNumeroPersonas = (TextView) findViewById(R.id.tvNumeroPersonas);
        btMenosNumPersonas = (Button) findViewById(R.id.btMenosNumPersonas);
        btMasNumPersonas = (Button) findViewById(R.id.btMasNumPersonas);
        btAceptarNumeroPersonas = (Button) findViewById(R.id.btAceptarNumeroPersonas);

        btMenosNumPersonas.setOnClickListener(btMenosNumPersonassetOnClickListener);
        btMasNumPersonas.setOnClickListener(btMasNumPersonassetOnClickListener);
        btAceptarNumeroPersonas.setOnClickListener(btAceptarNumeroPersonassetOnClickListener);
        btAceptar_nombresEncuestados.setOnClickListener(btAceptar_nombresEncuestadossetOnClickListener);
        btAceptar_nombresEncuestados.setClickable(false);

        llenarDatosCabecera();
        crearNombresLayoutsVariables();

        new AlertDialog.Builder(NombresPersonasEncuestadasActivity.this).setTitle("Mensaje").setMessage("En esta seccion ingrese la " +
                "cantidad de personas que se va a encuestar (SIN CONSIDERAR al jefe de familia)")
                .setNeutralButton("Aceptar", alertaAceptarOnClickListener).setCancelable(false).show();
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(DatosCabeceraActivity.KEY_ARG_NOMBRE_JEFE)) {
            nombresEncuestados = getIntent().getStringArrayListExtra(DatosCabeceraActivity.KEY_ARG_NOMBRE_JEFE);
            codigosIdentEncuestados = getIntent().getIntegerArrayListExtra(DatosCabeceraActivity.KEY_ARG_ID_JEFE);
        }
    }

    private void llenarDatosCabecera() {

        EncuestaDAO encuestaDAO = new EncuestaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        //codigo de encuesta
        String codEncuesta = encuestaDAO.obtenerCodigoEncuesta(NombresPersonasEncuestadasActivity.this);
        if (codEncuesta != null)
            tvCodigoEncuesta.setText(codEncuesta);

        // nombre de usuario
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String nombreUsu = pref.getString("nombres", null);
        String userUsu = pref.getString("user", null);
        tvNombreUsuario.setText(nombreUsu);

        //grupo
        String grupo = usuarioDAO.obtenerGrupoPorUsuario(NombresPersonasEncuestadasActivity.this, userUsu);
        tvGrupo.setText(grupo);

        //fechas
        tvFecha.setText(Util.obtenerFecha());
        CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();
        List<String> fechas = cabeceraRespuestaDAO.obtenerRangoFechasEncuesta(NombresPersonasEncuestadasActivity.this, userUsu);

        //if (fechas.get(0) == null)
            tvFechaVigenciaInicio.setText("");
        //else
          //  tvFechaVigenciaInicio.setText(fechas.get(0).toString().trim());

        //if (fechas.get(1) == null)
            tvFechaVigenciaFinal.setText("");
        //else
          //  tvFechaVigenciaFinal.setText(fechas.get(1).toString().trim());


        //Nombre del supervisor
        String idSupervisor = usuarioDAO.obtenerIDSupervisorXEncuestador(NombresPersonasEncuestadasActivity.this, userUsu);
        System.out.println("ID_SUPERVISOR : " + idSupervisor);
        String nombreSupervisor = usuarioDAO.obtenerNombreSupervisor(NombresPersonasEncuestadasActivity.this, idSupervisor);
        tvNombreSupervisor.setText(nombreSupervisor);

    }

    DialogInterface.OnClickListener alertaAceptarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };

    private void crearNombresLayoutsVariables() {

        nombresLayouts.add(lyNombre1);
        nombresLayouts.add(lyNombre2);
        nombresLayouts.add(lyNombre3);
        nombresLayouts.add(lyNombre4);
        nombresLayouts.add(lyNombre5);
        nombresLayouts.add(lyNombre6);
        nombresLayouts.add(lyNombre7);
        nombresLayouts.add(lyNombre8);
        nombresLayouts.add(lyNombre9);
        nombresLayouts.add(lyNombre10);
        nombresLayouts.add(lyNombre11);
        nombresLayouts.add(lyNombre12);
        nombresLayouts.add(lyNombre13);
        nombresLayouts.add(lyNombre14);
        nombresLayouts.add(lyNombre15);
        nombresLayouts.add(lyNombre16);
        nombresLayouts.add(lyNombre17);
        nombresLayouts.add(lyNombre18);
        nombresLayouts.add(lyNombre19);
        nombresLayouts.add(lyNombre20);

        nombresEdits.add(etNombre1);
        nombresEdits.add(etNombre2);
        nombresEdits.add(etNombre3);
        nombresEdits.add(etNombre4);
        nombresEdits.add(etNombre5);
        nombresEdits.add(etNombre6);
        nombresEdits.add(etNombre7);
        nombresEdits.add(etNombre8);
        nombresEdits.add(etNombre9);
        nombresEdits.add(etNombre10);
        nombresEdits.add(etNombre11);
        nombresEdits.add(etNombre12);
        nombresEdits.add(etNombre13);
        nombresEdits.add(etNombre14);
        nombresEdits.add(etNombre15);
        nombresEdits.add(etNombre16);
        nombresEdits.add(etNombre17);
        nombresEdits.add(etNombre18);
        nombresEdits.add(etNombre19);
        nombresEdits.add(etNombre20);


        apellidosPatEdits.add(edApellidoPat1);
        apellidosPatEdits.add(edApellidoPat2);
        apellidosPatEdits.add(edApellidoPat3);
        apellidosPatEdits.add(edApellidoPat4);
        apellidosPatEdits.add(edApellidoPat5);
        apellidosPatEdits.add(edApellidoPat6);
        apellidosPatEdits.add(edApellidoPat7);
        apellidosPatEdits.add(edApellidoPat8);
        apellidosPatEdits.add(edApellidoPat9);
        apellidosPatEdits.add(edApellidoPat10);
        apellidosPatEdits.add(edApellidoPat11);
        apellidosPatEdits.add(edApellidoPat12);
        apellidosPatEdits.add(edApellidoPat13);
        apellidosPatEdits.add(edApellidoPat14);
        apellidosPatEdits.add(edApellidoPat15);
        apellidosPatEdits.add(edApellidoPat16);
        apellidosPatEdits.add(edApellidoPat17);
        apellidosPatEdits.add(edApellidoPat18);
        apellidosPatEdits.add(edApellidoPat19);
        apellidosPatEdits.add(edApellidoPat20);

        apellidosMatEdits.add(edApellidoMat1);
        apellidosMatEdits.add(edApellidoMat2);
        apellidosMatEdits.add(edApellidoMat3);
        apellidosMatEdits.add(edApellidoMat4);
        apellidosMatEdits.add(edApellidoMat5);
        apellidosMatEdits.add(edApellidoMat6);
        apellidosMatEdits.add(edApellidoMat7);
        apellidosMatEdits.add(edApellidoMat8);
        apellidosMatEdits.add(edApellidoMat9);
        apellidosMatEdits.add(edApellidoMat10);
        apellidosMatEdits.add(edApellidoMat11);
        apellidosMatEdits.add(edApellidoMat12);
        apellidosMatEdits.add(edApellidoMat13);
        apellidosMatEdits.add(edApellidoMat14);
        apellidosMatEdits.add(edApellidoMat15);
        apellidosMatEdits.add(edApellidoMat16);
        apellidosMatEdits.add(edApellidoMat17);
        apellidosMatEdits.add(edApellidoMat18);
        apellidosMatEdits.add(edApellidoMat19);
        apellidosMatEdits.add(edApellidoMat20);
    }


    View.OnClickListener btMenosNumPersonassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int valor = Integer.parseInt(tvNumeroPersonas.getText().toString());
            if (valor > 0) {
                tvNumeroPersonas.setText(String.valueOf(valor - 1));
            }
            btAceptar_nombresEncuestados.setClickable(false);
        }
    };

    View.OnClickListener btMasNumPersonassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int valor = Integer.parseInt(tvNumeroPersonas.getText().toString());
            if (valor < 20) {
                tvNumeroPersonas.setText(String.valueOf(valor + 1));
            }
            btAceptar_nombresEncuestados.setClickable(false);
        }
    };

    View.OnClickListener btAceptarNumeroPersonassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < 20; i++)
                nombresLayouts.get(i).setVisibility(View.GONE);

            numerodePersonasEncuestadas = Integer.parseInt(tvNumeroPersonas.getText().toString());

            //Si se ingresan 0 personas, se manda solo el nombre del jefe de familia
            if (numerodePersonasEncuestadas == 0) {
                Intent intent = new Intent(NombresPersonasEncuestadasActivity.this, PreguntasActivity.class);
                intent.putStringArrayListExtra("NOMBRES_ENCUESTADOS", nombresEncuestados);
                intent.putExtra(KEY_ARG_NOMBRES_ENCUESTADOS, nombresEncuestados);
                startActivity(intent);
                finish();
            }

            for (int i = 0; i < numerodePersonasEncuestadas; i++)
                nombresLayouts.get(i).setVisibility(View.VISIBLE);
            btAceptar_nombresEncuestados.setClickable(true);

            Toast.makeText(NombresPersonasEncuestadasActivity.this, "Ingrese el nombre de las " +
                    +numerodePersonasEncuestadas + " personas ", Toast.LENGTH_LONG).show();
        }

    };

    View.OnClickListener btAceptar_nombresEncuestadossetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int isCorrect = 0;//OK
            //se recorren los campos para verificar que no esten vacios
            for (int i = 0; i < numerodePersonasEncuestadas; i++) {
                if (nombresEdits.get(i).getText().toString().trim().length() == 0) {
                    nombresEdits.get(i).setError("Ingrese nombre");
                    isCorrect++;
                }
                if (apellidosPatEdits.get(i).getText().toString().trim().length() == 0) {
                    apellidosPatEdits.get(i).setError("Ingrese Apellido Paterno");
                    isCorrect++;
                }
                if (apellidosMatEdits.get(i).getText().toString().trim().length() == 0) {
                    apellidosMatEdits.get(i).setError("Ingrese Apellido Materno");
                    isCorrect++;
                }
            }
            PersonaDAO personaDAO = new PersonaDAO();
            if (isCorrect == 0) {//No hay ningun capo vacio
                for (int i = 0; i < numerodePersonasEncuestadas; i++) {
                    //guardar los nombres en un array
                    nombresEncuestados.add(nombresEdits.get(i).getText().toString().trim() + " " + apellidosPatEdits.get(i).getText().toString().trim() +
                            " " + apellidosMatEdits.get(i).getText().toString().trim());

                    //guardar todos los nombres en la base de datos
                    boolean insertarAllegado = personaDAO.insertarAllegado(NombresPersonasEncuestadasActivity.this,
                            nombresEdits.get(i).getText().toString().trim(),
                            apellidosPatEdits.get(i).getText().toString().trim(),
                            apellidosMatEdits.get(i).getText().toString().trim());

                    if (insertarAllegado == false) {
                        Toast.makeText(NombresPersonasEncuestadasActivity.this, "Ingrese el nombre de las " +
                                +numerodePersonasEncuestadas + " personas ", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    codigosIdentEncuestados.add(personaDAO.obtenerUltIdAlle(NombresPersonasEncuestadasActivity.this));
                }

                /*
                //obteniendo el codigo de indentificacion de todos los usuarios
                for (int i = 0; i < numerodePersonasEncuestadas; i++) {
                    codigosIdentEncuestados.add(personaDAO.obtenerIdPersonabyNombres(NombresPersonasEncuestadasActivity.this,
                            nombresEdits.get(i).getText().toString().trim(),
                            apellidosPatEdits.get(i).getText().toString().trim(),
                            apellidosMatEdits.get(i).getText().toString().trim()));
                }
                */
                //eliminar for, solo para mostrar los nombres de los allegados
                for (int i = 0; i < nombresEncuestados.size(); i++) {
                    System.out.println("nombre " + i + " : " + nombresEncuestados.get(i).toString());
                    //System.out.println("id " + i + " : " + codigosIdentEncuestados.get(i).toString());
                }
                Intent intent = new Intent(NombresPersonasEncuestadasActivity.this, PreguntasActivity.class);
                intent.putStringArrayListExtra(KEY_ARG_NOMBRES_ENCUESTADOS, nombresEncuestados);
                intent.putIntegerArrayListExtra(KEY_ARG_ID_ENCUESTADOS, codigosIdentEncuestados);
                startActivity(intent);
                finish();

            }
        }
    };


}
