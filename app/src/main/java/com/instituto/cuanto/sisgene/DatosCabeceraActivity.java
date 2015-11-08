package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.util.Util;

import java.util.ArrayList;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class DatosCabeceraActivity extends AppCompatActivity {

    TextView tvCodigoEncuesta, tvNombreSupervisor, tvNombreUsuario, tvGrupo, tvFecha, tvFechaVigenciaInicio, tvFechaVigenciaFinal;
    EditText etNombres, etApellidoPaterno, etApellidoMaterno, etDni, etCentroPoblado;
    EditText etConglomeradoN, etZonaAER, etManzanaN, etViviendaN, etHogarN, etDireccion, etTelefono, etCelular, etEmail;
    LinearLayout lyNombres, lyApellidoPaterno, lyApellidoMaterno, lyDni, lyDepartamento, lyProvincia, lyDistrito, lyCentroPoblado;
    LinearLayout lyConglomeradoN, lyZonaAER, lyManzanaN, lyViviendaN, lyHogarN, lyDireccion, lyTelefono, lyCelular, lyEmail;
    Spinner spArea, spCondicion;
    LinearLayout lyspArea, lyspCondicion;
    Button btAceptar_datosUsuario;
    ArrayList<String> nombresEncuestados;
    public static String KEY_ARG_NOMBRE_JEFE = "KEY_ARG_NOMBRE_JEFE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_encuestado);
        nombresEncuestados = new ArrayList<>();
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

        btAceptar_datosUsuario = (Button) findViewById(R.id.btAceptar_datosUsuario);
        spArea = (Spinner) findViewById(R.id.spArea);
        spCondicion = (Spinner) findViewById(R.id.spCondicion);
        lyspArea = (LinearLayout) findViewById(R.id.lyspArea);
        lyspCondicion = (LinearLayout) findViewById(R.id.lyspCondicion);

        tvFecha.setText(Util.obtenerFecha());

        //modificar
        tvFechaVigenciaInicio.setText(Util.obtenerFecha());
        tvFechaVigenciaFinal.setText(Util.obtenerFecha());
        tvCodigoEncuesta.setText("ABC001");
        tvNombreSupervisor.setText("Gustavo Gómez");
        tvGrupo.setText("Grupo 02");
        tvNombreUsuario.setText("Jesus Cahuana");


        btAceptar_datosUsuario.setOnClickListener(btAceptar_datosUsuariosetOnClickListener);
    }

    View.OnClickListener btAceptar_datosUsuariosetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //validar que se haya ingresado todos los campos
            validarCamposDatosUsuarios();
        }
    };

    void validarCamposDatosUsuarios() {
        boolean isComplete = true;

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

            Intent intent = new Intent(DatosCabeceraActivity.this, NombresPersonasEncuestadasActivity.class);
            intent.putExtra(KEY_ARG_NOMBRE_JEFE, nombresEncuestados);
            startActivity(intent);
            finish();
        }
    }
}
