package com.instituto.cuanto.sisgene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.dao.CabeceraRespuestaDAO;
import com.instituto.cuanto.sisgene.dao.EncuestaDAO;
import com.instituto.cuanto.sisgene.dao.PersonaDAO;
import com.instituto.cuanto.sisgene.dao.UsuarioDAO;

import java.util.List;

/**
 * Created by Jesus on 01/11/2015.
 */
public class ModificarEncuestaActivity extends AppCompatActivity {

    private String numRegistro;
    private String estadoEnvi;
    TextView tv_codencuesta,tv_nomsupervisor,tv_nomusuario,tv_grupo,tv_fecha,tv_hora,tv_numencuesta;
    EditText et_nombres,et_appaterno,et_apmaterno,et_dni,et_centropoblado,et_conglomerado,et_zona,et_manzana,
            et_vivienda,et_hogar,et_telefono,et_celular,et_correo;
    Button btnEditar, btnCancelar;

    int idPersonaSeleccionada;
    int idCabeceraSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarencuesta);

        //TextView
        tv_codencuesta = (TextView) findViewById(R.id.tvCodigoEncuesta);
        tv_nomsupervisor = (TextView) findViewById(R.id.tvNombreSupervisor);
        tv_nomusuario = (TextView) findViewById(R.id.tvNombreUsuario);
        tv_grupo = (TextView) findViewById(R.id.tvGrupo);
        tv_fecha = (TextView) findViewById(R.id.tvFecha);
        tv_hora = (TextView) findViewById(R.id.tvHora);
        tv_numencuesta = (TextView) findViewById(R.id.tvNumEncuesta);

        //EditText
        et_nombres = (EditText) findViewById(R.id.etNombres);
        et_appaterno = (EditText) findViewById(R.id.etApellidoPaterno);
        et_apmaterno = (EditText) findViewById(R.id.etApellidoMaterno);
        et_dni = (EditText) findViewById(R.id.etDni);
        et_centropoblado = (EditText) findViewById(R.id.etCentroPoblado);
        et_conglomerado = (EditText) findViewById(R.id.etConglomeradoN);
        et_zona = (EditText) findViewById(R.id.etZonaAER);
        et_manzana =(EditText) findViewById(R.id.etManzanaN);
        et_vivienda  = (EditText) findViewById(R.id.etViviendaN);
        et_hogar = (EditText) findViewById(R.id.etHogarN);
        et_telefono = (EditText) findViewById(R.id.etTelefono);
        et_celular = (EditText) findViewById(R.id.etCelular);
        et_correo = (EditText) findViewById(R.id.etEmail);

        //BUTTON
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnEditar.setOnClickListener(btnEditarsetOnClickListener);
        btnCancelar.setOnClickListener(btnCancelarsetOnClickListener);

        //Bundle bundle = getIntent().getExtras();
        numRegistro = getIntent().getStringExtra("posicion");
        estadoEnvi = getIntent().getStringExtra("estadoEnvi");

        cargarDatosInicial();

    }

    View.OnClickListener btnEditarsetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
            PersonaDAO personaDAO = new PersonaDAO();

            try {
                String nombre = et_nombres.getText().toString();
                String appaterno = et_appaterno.getText().toString();
                String apmaterno = et_apmaterno.getText().toString();
                String dni = et_dni.getText().toString();
                String telefono = et_telefono.getText().toString();
                String celular = et_celular.getText().toString();
                String correo = et_correo.getText().toString();

                String centropoblado = et_centropoblado.getText().toString();
                String conglomerado = et_conglomerado.getText().toString();
                String zona = et_zona.getText().toString();
                String manzana = et_manzana.getText().toString();
                String vivienda = et_vivienda.getText().toString();
                String hogar = et_hogar.getText().toString();

                boolean response = personaDAO.actualizarPersona(ModificarEncuestaActivity.this, nombre, appaterno, apmaterno, dni, telefono,
                        celular, correo, idPersonaSeleccionada);

                boolean response2 = cabeceraRespDAO.actualizarCabEnc(ModificarEncuestaActivity.this, conglomerado, zona, manzana, vivienda,
                        hogar, centropoblado,idCabeceraSeleccionada);

                if (response == true && response2 == true) {
                    Toast.makeText(ModificarEncuestaActivity.this, "Editado con éxito", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ModificarEncuestaActivity.this, ConsultarSupervisorActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ModificarEncuestaActivity.this, "Error al Editar", Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){
                System.out.println("ERROR : "+e.getMessage());
                Toast.makeText(ModificarEncuestaActivity.this, "Error al Editar : "+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    };

    View.OnClickListener btnCancelarsetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public void cargarDatosInicial(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String userUsu = pref.getString("user", null);
        String nombreUsu = pref.getString("nombres", null);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EncuestaDAO encuestaDAO = new EncuestaDAO();
        String grupo = usuarioDAO.obtenerGrupoPorUsuario(ModificarEncuestaActivity.this,userUsu);
        String codEncuesta = encuestaDAO.obtenerCodigoEncuesta(ModificarEncuestaActivity.this);

        CabeceraRespuesta cabResp = obtenerCabeceraRespuesta();
        tv_codencuesta.setText(codEncuesta);
        tv_nomsupervisor.setText(nombreUsu);
        tv_nomusuario.setText("");
        tv_grupo.setText(grupo);
        tv_fecha.setText(cabResp.getFechaDesarrollo());
        tv_hora.setText(cabResp.getHoraInicio() +" - "+ cabResp.getHoraFin());
        tv_numencuesta.setText(cabResp.getNumEncuesta());

        et_nombres.setText(cabResp.getNombreEncuestado());
        et_appaterno.setText(cabResp.getApPaternoEncuestado());
        et_apmaterno.setText(cabResp.getApMaternoEncuestado());
        et_dni.setText(cabResp.getNum_documento());
        et_centropoblado.setText(cabResp.getCentropoblado());
        et_conglomerado.setText(cabResp.getConglomerado());
        et_zona.setText(cabResp.getZona());
        et_manzana.setText(cabResp.getManzana());
        et_vivienda.setText(cabResp.getVivienda());
        et_hogar.setText(cabResp.getHogar());
        et_telefono.setText(cabResp.getTelefono());
        et_celular.setText(cabResp.getCelular());
        et_correo.setText(cabResp.getCorreo());

        idPersonaSeleccionada = cabResp.getIdPesona();
        idCabeceraSeleccionada = cabResp.getIdCabeceraEnc();

    }

    public CabeceraRespuesta obtenerCabeceraRespuesta(){

        CabeceraRespuestaDAO cabeceraRespDAO = new CabeceraRespuestaDAO();
        List<CabeceraRespuesta> listaCabeceraResp = cabeceraRespDAO.obtenerCabeceraRespuesta(ModificarEncuestaActivity.this,estadoEnvi);

        CabeceraRespuesta cabeceraResp = listaCabeceraResp.get(Integer.parseInt(numRegistro)-1);

        return cabeceraResp;
    }
}
