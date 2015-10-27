package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.fragment.PreguntasFragment;
import com.instituto.cuanto.sisgene.util.Util;

/**
 * Created by Gustavo on 10/10/2015.
 */
public class DatosEncuestadosActivity extends AppCompatActivity {

    TextView tvCodigoEncuesta, tvNombreSupervisor, tvNombreUsuario, tvGrupo, tvFecha, tvFechaVigenciaInicio, tvFechaVigenciaFinal;
    TextInputLayout tilNombres, tilApellidoPaterno, tilApellidoMaterno, tilDni, tilDepartamento, tilProvincia, tilDistrito, tilCentroPoblado;
    TextInputLayout tilConglomeradoN, tilZonaAER, tilManzanaN, tilViviendaN, tilHogarN, tilDireccion, tilTelefono, tilCelular, tilEmail;
    LinearLayout lyNombres, lyApellidoPaterno, lyApellidoMaterno, lyDni, lyDepartamento, lyProvincia, lyDistrito, lyCentroPoblado;
    LinearLayout lyConglomeradoN, lyZonaAER, lyManzanaN, lyViviendaN, lyHogarN, lyDireccion, lyTelefono, lyCelular, lyEmail;
    Spinner spArea, spCondicion;
    LinearLayout lyspArea, lyspCondicion;
    Button btAceptar_datosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_encuestados);

        tvCodigoEncuesta = (TextView) findViewById(R.id.tvCodigoEncuesta);
        tvNombreSupervisor = (TextView) findViewById(R.id.tvNombreSupervisor);
        tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuario);
        tvGrupo = (TextView) findViewById(R.id.tvGrupo);
        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvFechaVigenciaInicio = (TextView) findViewById(R.id.tvFechaVigenciaInicio);
        tvFechaVigenciaFinal = (TextView) findViewById(R.id.tvFechaVigenciaFinal);

        tilNombres = (TextInputLayout) findViewById(R.id.tilNombres);
        tilApellidoPaterno = (TextInputLayout) findViewById(R.id.tilApellidoPaterno);
        tilApellidoMaterno = (TextInputLayout) findViewById(R.id.tilApellidoMaterno);
        tilDni = (TextInputLayout) findViewById(R.id.tilDni);
        tilDepartamento = (TextInputLayout) findViewById(R.id.tilDepartamento);
        tilProvincia = (TextInputLayout) findViewById(R.id.tilProvincia);
        tilDistrito = (TextInputLayout) findViewById(R.id.tilDistrito);
        tilCentroPoblado = (TextInputLayout) findViewById(R.id.tilCentroPoblado);
        tilConglomeradoN = (TextInputLayout) findViewById(R.id.tilConglomeradoN);
        tilZonaAER = (TextInputLayout) findViewById(R.id.tilZonaAER);
        tilManzanaN = (TextInputLayout) findViewById(R.id.tilManzanaN);
        tilViviendaN = (TextInputLayout) findViewById(R.id.tilViviendaN);
        tilHogarN = (TextInputLayout) findViewById(R.id.tilHogarN);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilCelular = (TextInputLayout) findViewById(R.id.tilCelular);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);

        lyNombres = (LinearLayout) findViewById(R.id.lyNombres);
        lyApellidoPaterno = (LinearLayout) findViewById(R.id.lyApellidoPaterno);
        lyApellidoMaterno = (LinearLayout) findViewById(R.id.lyApellidoMaterno);
        lyDni = (LinearLayout) findViewById(R.id.lyDni);
        lyDepartamento = (LinearLayout) findViewById(R.id.lyDepartamento);
        lyProvincia = (LinearLayout) findViewById(R.id.lyProvincia);
        lyDistrito = (LinearLayout) findViewById(R.id.lyDistrito);
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
            /* {

                //guardar campos para guardar en la base de datos
                tilNombres.getEditText().toString().trim()
                tilNombres.getEditText().toString().trim()
                tilApellidoPaterno.getEditText().toString().trim()
                tilApellidoMaterno.getEditText().toString().trim()
                tilDni.getEditText().toString().trim()
                tilDepartamento.getEditText().toString().trim()
                tilProvincia.getEditText().toString().trim()
                tilDistrito.getEditText().toString().trim()
                tilCentroPoblado.getEditText().toString().trim()
                tilConglomeradoN.getEditText().toString().trim()
                tilZonaAER.getEditText().toString().trim()
                tilManzanaN.getEditText().toString().trim()
                tilViviendaN.getEditText().toString().trim()
                tilHogarN.getEditText().toString().trim()
                tilDireccion.getEditText().toString().trim()
                tilTelefono.getEditText().toString().trim()
                tilCelular.getEditText().toString().trim()
                tilEmail.getEditText().toString().trim()


            }*/

             //Iniciar Encuesta

        }
    };

    void validarCamposDatosUsuarios() {
        boolean isComplete = true;

        tilNombres.setErrorEnabled(false);
        tilApellidoPaterno.setErrorEnabled(false);
        tilApellidoMaterno.setErrorEnabled(false);
        tilDni.setErrorEnabled(false);
        tilDireccion.setErrorEnabled(false);

        if (tilNombres.getEditText().getText().toString().trim().length() <= 0) {
            tilNombres.setErrorEnabled(true);
            tilNombres.setError("Ingrese Nombre");
            isComplete = false;
        }
        if (tilApellidoPaterno.getEditText().getText().toString().trim().length() <= 0) {
            tilApellidoPaterno.setErrorEnabled(true);
            tilApellidoPaterno.setError("Ingrese Apellido Paterno");
            isComplete = false;
        }
        if (tilApellidoMaterno.getEditText().getText().toString().trim().length() <= 0) {
            tilApellidoMaterno.setErrorEnabled(true);
            tilApellidoMaterno.setError("Ingrese Apellido Materno");
            isComplete = false;
        }
        if (tilDni.getEditText().getText().toString().trim().length() != 8) {
            tilDni.setError("Ingrese un DNI válido");
            tilDni.setErrorEnabled(true);
            isComplete = false;
        }
        if (tilDireccion.getEditText().getText().toString().trim().length() <= 0) {
            tilDireccion.setError("Ingrese Dirección");
            tilDireccion.setErrorEnabled(true);
            isComplete = false;
        }
        if(isComplete)
        {
            Intent intent = new Intent(DatosEncuestadosActivity.this, PreguntasActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
