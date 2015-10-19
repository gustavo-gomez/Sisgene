package com.instituto.cuanto.sisgene;

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

        //modificar segun los datos visibles desde la base de datos, se está poniendo que todos los campos sean visibles
        lyNombres.setVisibility(View.VISIBLE);
        lyNombres.setVisibility(View.VISIBLE);
        lyApellidoPaterno.setVisibility(View.VISIBLE);
        lyApellidoMaterno.setVisibility(View.VISIBLE);
        lyDni.setVisibility(View.VISIBLE);
        lyDepartamento.setVisibility(View.VISIBLE);
        lyProvincia.setVisibility(View.VISIBLE);
        lyDistrito.setVisibility(View.VISIBLE);
        lyCentroPoblado.setVisibility(View.VISIBLE);
        lyConglomeradoN.setVisibility(View.VISIBLE);
        lyZonaAER.setVisibility(View.VISIBLE);
        lyManzanaN.setVisibility(View.VISIBLE);
        lyViviendaN.setVisibility(View.VISIBLE);
        lyHogarN.setVisibility(View.VISIBLE);
        lyDireccion.setVisibility(View.VISIBLE);
        lyTelefono.setVisibility(View.VISIBLE);
        lyCelular.setVisibility(View.VISIBLE);
        lyEmail.setVisibility(View.VISIBLE);



        btAceptar_datosUsuario.setOnClickListener(btAceptar_datosUsuariosetOnClickListener);
    }

    View.OnClickListener btAceptar_datosUsuariosetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //validar que se haya ingresado datos en los campos que estén visibles

        }
    };
}
