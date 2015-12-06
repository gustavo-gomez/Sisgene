package com.instituto.cuanto.sisgene;

import android.content.Context;
import android.widget.ArrayAdapter;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.CatalogoDAO;
import com.instituto.cuanto.sisgene.dao.DireccionDAO;
import com.instituto.cuanto.sisgene.dao.EncuestaDAO;
import com.instituto.cuanto.sisgene.dao.PersonaDAO;
import com.instituto.cuanto.sisgene.dao.UsuarioDAO;
import com.instituto.cuanto.sisgene.util.Util;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class DatosCabeceraActivity extends AppCompatActivity {

    TextView tvCodigoEncuesta, tvNombreSupervisor, tvNombreUsuario, tvGrupo, tvFecha, tvFechaVigenciaInicio, tvFechaVigenciaFinal;
    EditText etNombres, etApellidoPaterno, etApellidoMaterno, etDni, etCentroPoblado;
    EditText etConglomeradoN, etZonaAER, etManzanaN, etViviendaN, etHogarN, etDireccion, etTelefono, etCelular, etEmail;
    LinearLayout lyNombres, lyApellidoPaterno, lyApellidoMaterno, lyDni, lyCentroPoblado;
    LinearLayout lyConglomeradoN, lyZonaAER, lyManzanaN, lyViviendaN, lyHogarN, lyDireccion, lyTelefono, lyCelular, lyEmail;
    Spinner spArea, spCondicion;
    LinearLayout lyspArea, lyspCondicion;
    Button btAceptareIniciarEncuesta;
    Button btSalir_datosCabecera;
    ArrayList<String> nombresEncuestados;
    ArrayList<Integer> codigosIdentEncuestados;
    public static String KEY_ARG_NOMBRE_JEFE = "KEY_ARG_NOMBRE_JEFE";
    public static String KEY_ARG_ID_JEFE = "KEY_ARG_ID_JEFE";
    String userUsu;

    protected ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_encuestado);

        nombresEncuestados = new ArrayList<>();
        codigosIdentEncuestados = new ArrayList<>();
        tvCodigoEncuesta = (TextView) findViewById(R.id.tvCodigoEncuesta);
        tvNombreSupervisor = (TextView) findViewById(R.id.tvNombreSupervisor);
        tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuario);
        tvGrupo = (TextView) findViewById(R.id.tvGrupo);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvFechaVigenciaInicio = (TextView) findViewById(R.id.tvFechaVigenciaInicio);
        tvFechaVigenciaFinal = (TextView) findViewById(R.id.tvFechaVigenciaFinal);

        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidoPaterno = (EditText) findViewById(R.id.etApellidoPaterno);
        etApellidoMaterno = (EditText) findViewById(R.id.etApellidoMaterno);
        etDni = (EditText) findViewById(R.id.etDni);
        etCentroPoblado = (EditText) findViewById(R.id.etCentroPoblado);
        etConglomeradoN = (EditText) findViewById(R.id.etConglomeradoN);
        etZonaAER = (EditText) findViewById(R.id.etZonaAER);
        etManzanaN = (EditText) findViewById(R.id.etManzanaN);
        etViviendaN = (EditText) findViewById(R.id.etViviendaN);
        etHogarN = (EditText) findViewById(R.id.etHogarN);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etCelular = (EditText) findViewById(R.id.etCelular);
        etEmail = (EditText) findViewById(R.id.etEmail);

        lyNombres = (LinearLayout) findViewById(R.id.lyNombres);
        lyApellidoPaterno = (LinearLayout) findViewById(R.id.lyApellidoPaterno);
        lyApellidoMaterno = (LinearLayout) findViewById(R.id.lyApellidoMaterno);
        lyDni = (LinearLayout) findViewById(R.id.lyDni);

        lyCentroPoblado = (LinearLayout) findViewById(R.id.lyCentroPoblado);
        lyConglomeradoN = (LinearLayout) findViewById(R.id.lyConglomeradoN);
        lyZonaAER = (LinearLayout) findViewById(R.id.lyZonaAER);
        lyManzanaN = (LinearLayout) findViewById(R.id.lyManzanaN);
        lyViviendaN = (LinearLayout) findViewById(R.id.lyViviendaN);
        lyHogarN = (LinearLayout) findViewById(R.id.lyHogarN);
        lyDireccion = (LinearLayout) findViewById(R.id.lyDireccion);
        lyTelefono = (LinearLayout) findViewById(R.id.lyTelefono);
        lyCelular = (LinearLayout) findViewById(R.id.lyCelular);
        lyEmail = (LinearLayout) findViewById(R.id.lyEmail);

        btAceptareIniciarEncuesta = (Button) findViewById(R.id.btAceptareIniciarEncuesta);
        btSalir_datosCabecera = (Button) findViewById(R.id.btSalir_datosCabecera);
        spArea = (Spinner) findViewById(R.id.spArea);
        spCondicion = (Spinner) findViewById(R.id.spCondicion);
        lyspArea = (LinearLayout) findViewById(R.id.lyspArea);
        lyspCondicion = (LinearLayout) findViewById(R.id.lyspCondicion);

        //Cargando Spinner Area y condicion
        CatalogoDAO catalogoDAO = new CatalogoDAO();
        List<String> listaAreas = catalogoDAO.obtenerTipoZona(DatosCabeceraActivity.this);
        List<String> listaCondiciones = catalogoDAO.obtenerGradoFam(DatosCabeceraActivity.this);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaAreas);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArea.setAdapter(adaptador);

        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCondiciones);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCondicion.setAdapter(adaptador2);


        tvFecha.setText(Util.obtenerFecha());

        llenarDatosCabecera();
        btAceptareIniciarEncuesta.setOnClickListener(btAceptareIniciarEncuestasetOnClickListener);
        btSalir_datosCabecera.setOnClickListener(btSalir_datosCabecerasetOnClickListener);
    }

    private void llenarDatosCabecera() {
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        //codigo de encuesta
        String codEncuesta = encuestaDAO.obtenerCodigoEncuesta(DatosCabeceraActivity.this);
        if (codEncuesta != null)
            tvCodigoEncuesta.setText(codEncuesta);

        // nombre de usuario
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String nombreUsu = pref.getString("nombres", null);
        userUsu = pref.getString("user", null);
        tvNombreUsuario.setText(nombreUsu);

        //grupo
        String grupo = usuarioDAO.obtenerGrupoPorUsuario(DatosCabeceraActivity.this, userUsu);
        tvGrupo.setText(grupo);

        //fechas
        tvFechaVigenciaInicio.setText(Util.obtenerFecha());
        tvFechaVigenciaFinal.setText(Util.obtenerFecha());

        //modificar
        //tvNombreSupervisor.setText("Gustavo Gómez");
    }

    View.OnClickListener btAceptareIniciarEncuestasetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //validar que se haya ingresado todos los campos
            validarCamposDatosUsuarios();
        }
    };

    View.OnClickListener btSalir_datosCabecerasetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //validar que se haya ingresado todos los campos
            new AlertDialog.Builder(DatosCabeceraActivity.this).setTitle("Mensaje")
                    .setMessage("¿Esta seguro que desea cancelar la encuesta?")
                    .setPositiveButton("Continuar con la encuesta", alertaAceptarOnClickListener)
                    .setNegativeButton("Salir", alertaSalirOnClickListener)
                    .setCancelable(false).show();
        }
    };
    DialogInterface.OnClickListener alertaAceptarOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };
    DialogInterface.OnClickListener alertaSalirOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(DatosCabeceraActivity.this, PrincipalEncuestaActivity.class);
            startActivity(intent);
            finish();
        }
    };

    void validarCamposDatosUsuarios() {
        boolean isComplete = true;
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (etNombres.getText().toString().trim().length() <= 0) {
            etNombres.setError("Ingrese Nombre");
            isComplete = false;
        }
        if (etApellidoPaterno.getText().toString().trim().length() <= 0) {
            etApellidoPaterno.setError("Ingrese Apellido Paterno");
            isComplete = false;
        }
        if (etApellidoMaterno.getText().toString().trim().length() <= 0) {
            etApellidoMaterno.setError("Ingrese Apellido Materno");
            isComplete = false;
        }
        if (etDni.getText().toString().trim().length() != 8) {
            etDni.setError("Ingrese un DNI válido");
            isComplete = false;
        }
        if (etDireccion.getText().toString().trim().length() <= 0) {
            etDireccion.setError("Ingrese Dirección");
            isComplete = false;
        }
        if (isComplete) {
            nombresEncuestados.add(etNombres.getText().toString().trim() + " " + etApellidoPaterno.getText().toString().trim() + " " +
                    etApellidoMaterno.getText().toString().trim());

            //insertar direccion en BD
            DireccionDAO direccionDAO = new DireccionDAO();
            direccionDAO.insertarDireccion(DatosCabeceraActivity.this, etDireccion.getText().toString().trim());

            ////
            PersonaDAO personaDAO = new PersonaDAO();
            //obtener el id del jefe de famiia

            //Insercion de los daros del jefe de familia
            boolean insertoPersona = personaDAO.insertarPersona(DatosCabeceraActivity.this, etNombres.getText().toString().trim(),
                    etApellidoPaterno.getText().toString().trim(), etApellidoMaterno.getText().toString().trim(),
                    etDni.getText().toString().trim(), etTelefono.getText().toString().trim(),
                    etCelular.getText().toString().trim(), etEmail.getText().toString().trim());

            CabeceraRespuestaDAO cabeceraRespuestaDAO = new CabeceraRespuestaDAO();

            if (insertoPersona == true) {

                boolean insertoAlleg = personaDAO.insertarAllegado(DatosCabeceraActivity.this, etNombres.getText().toString().trim(),
                        etApellidoPaterno.getText().toString().trim(), etApellidoMaterno.getText().toString().trim());

                codigosIdentEncuestados.add(personaDAO.obtenerUltIdAlle(DatosCabeceraActivity.this));
                int personaId = personaDAO.obtenerUltIdPersona(DatosCabeceraActivity.this);

                if (personaId == 0) {
                    Toast.makeText(DatosCabeceraActivity.this, "Error al obtener el id de Usuario", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    int numEncuestas = cabeceraRespuestaDAO.obtenerCantidadEncuestas(DatosCabeceraActivity.this);
                    String numeroEnc = "";
                    if (numEncuestas != -2) { //Consulta a BD sin errores
                        System.out.println("ID DE USUARIO " + userUsu + ": " + usuarioDAO.obtenerIdUsuario(DatosCabeceraActivity.this, userUsu));
                        if (numEncuestas == 0) {
                            System.out.println("NUMERO DE ENCUESTAS CERO");
                            //si no hay encuestas, el numero sera el campo DESDE

                            numeroEnc = String.valueOf(cabeceraRespuestaDAO.obtenerDesdeNumEnc(DatosCabeceraActivity.this, userUsu));
                        } else {
                            System.out.println("EXISTEN ENCUESTAS");
                            //si hay encuesta, se trae el ultimo y se suma 1
                            numeroEnc = String.valueOf(cabeceraRespuestaDAO.obtenerUltimoNumeroEncuestaCabecera(DatosCabeceraActivity.this) + 1);
                        }
                        System.out.println("numeroEnc: " + numeroEnc);
                        System.out.println("userUsu: " + userUsu);

                        boolean insertarCabecera = cabeceraRespuestaDAO.insertarCabEnc2(DatosCabeceraActivity.this,
                                numeroEnc,
                                "I",
                                Util.obtenerFechayHora(),
                                "observaciones",
                                etConglomeradoN.getText().toString().trim(),
                                etZonaAER.getText().toString().trim(),
                                etManzanaN.getText().toString().trim(),
                                etViviendaN.getText().toString().trim(),
                                etHogarN.getText().toString().trim(),
                                "0",
                                "0",
                                "0",
                                Util.obtenerFechayHora(),
                                "",
                                "",
                                "",
                                etCentroPoblado.getText().toString().trim(),
                                "",
                                "0",
                                "",
                                usuarioDAO.obtenerIdUsuario(DatosCabeceraActivity.this, userUsu),
                                String.valueOf(personaId),
                                String.valueOf(direccionDAO.obtenerUltIdDireccion(DatosCabeceraActivity.this)));

                        if (insertarCabecera == true) {
                            Toast.makeText(DatosCabeceraActivity.this, "Datos almacenados correctamente", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(DatosCabeceraActivity.this, NombresPersonasEncuestadasActivity.class);
                            intent.putExtra(KEY_ARG_NOMBRE_JEFE, nombresEncuestados);
                            intent.putExtra(KEY_ARG_ID_JEFE, codigosIdentEncuestados);
                            startActivity(intent);
                            finish();
                        } else
                            finish();

                    } else {
                        Toast.makeText(DatosCabeceraActivity.this, "Error en base de datos.Consulte con su Administrador", Toast.LENGTH_LONG).show();
                    }

                }
            } else {
                Toast.makeText(DatosCabeceraActivity.this, "Error al insertar en base de datos", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}

