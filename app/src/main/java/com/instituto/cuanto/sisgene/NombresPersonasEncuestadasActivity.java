package com.instituto.cuanto.sisgene;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gustavo on 01/11/2015.
 */
public class NombresPersonasEncuestadasActivity extends AppCompatActivity {

    LinearLayout lyNombre1, lyNombre2, lyNombre3, lyNombre4, lyNombre5, lyNombre6, lyNombre7, lyNombre8, lyNombre9, lyNombre10,
            lyNombre11, lyNombre12, lyNombre13, lyNombre14, lyNombre15, lyNombre16, lyNombre17, lyNombre18, lyNombre19, lyNombre20;
    EditText etNombre1, etNombre2, etNombre3, etNombre4, etNombre5, etNombre6, etNombre7, etNombre8, etNombre9, etNombre10,
            etNombre11, etNombre12, etNombre13, etNombre14, etNombre15, etNombre16, etNombre17, etNombre18, etNombre19, etNombre20;
    TextView tvNumeroPersonas;
    Button btMenosNumPersonas, btMasNumPersonas, btAceptarNumeroPersonas;
    int numerodePersonasEncuestadas;
    ArrayList<LinearLayout> nombresLayouts;
    ArrayList<EditText> nombresEdits;
    Button btAceptar_nombresEncuestados;
    ArrayList<String> nombresEncuestados;
    public static String KEY_ARG_NOMBRES_ENCUESTADOS = "KEY_ARG_NOMBRES_ENCUESTADOS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres_personas_encuestadas);
        numerodePersonasEncuestadas = 0;
        nombresLayouts = new ArrayList<>();
        nombresEdits = new ArrayList<>();
        nombresEncuestados = new ArrayList<>();

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
        crearNombresLayoutsVariables();
        new AlertDialog.Builder(NombresPersonasEncuestadasActivity.this).setTitle("Mensaje").setMessage("En esta seccion ingrese la " +
                "cantidad de personas que se va a encuestar (SIN CONSIDERAR al jefe de familia)")
                .setNeutralButton("Aceptar", alertaAceptarOnClickListener).setCancelable(false).show();
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(DatosCabeceraActivity.KEY_ARG_NOMBRE_JEFE)) {
            nombresEncuestados = getIntent().getStringArrayListExtra(DatosCabeceraActivity.KEY_ARG_NOMBRE_JEFE);
        }
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
            }
            if (isCorrect == 0) {//No hay ningun capo vacio
                for (int i = 0; i < numerodePersonasEncuestadas; i++)
                    nombresEncuestados.add(nombresEdits.get(i).getText().toString().trim());

                for (int i = 0; i < numerodePersonasEncuestadas + 1; i++) {
                    System.out.println("nombre: " + i + " : " + nombresEncuestados.get(i));
                }
                Intent intent = new Intent(NombresPersonasEncuestadasActivity.this, PreguntasActivity.class);
                intent.putStringArrayListExtra("NOMBRES_ENCUESTADOS", nombresEncuestados);
                startActivity(intent);
                finish();
            }
        }
    };


}
