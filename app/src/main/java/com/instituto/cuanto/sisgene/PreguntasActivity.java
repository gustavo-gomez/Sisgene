package com.instituto.cuanto.sisgene;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.adapter.TipoPreguntaAbiertaAdapter;
import com.instituto.cuanto.sisgene.entities.Pregunta;
import com.instituto.cuanto.sisgene.entities.PreguntaOpcion;
import com.instituto.cuanto.sisgene.entities.TipoPreguntaAbiertaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 09/10/2015.
 */
public class PreguntasActivity extends AppCompatActivity {

    Button btnSiguiente;
    LinearLayout lyFragmentoListaPreguntas;
    //android.app.FragmentManager fragmentManager;
    ListView lvRespuestas_tipoGeneral;
    static ArrayList<String> arrayList;
    TipoPreguntaAbiertaAdapter tipoPreguntaAbiertaAdapter;
    static ArrayList<String> nombresEncuestados;
    ArrayList<TipoPreguntaAbiertaItem> miListaTipoPreguntaAbiertaAdapter;
    Context context = PreguntasActivity.this;
    static boolean dataLista = false;
    AlertDialog.Builder alert;
    int numPregunta=-1;
    final ArrayList<EditText> editTexts = new ArrayList<>();
    final ArrayList<TextView> textViews = new ArrayList<>();
    ScrollView scrollView;
    TextView tvEnunciadoPregunta;
    TextView tvOpcionesPregunta;
    List<String> listaPreguntas = new ArrayList<String>();
    ArrayList<String> nombres = new ArrayList<>();
    List<String> claves1;
    List<String> claves2;
    List<String> claves3;
    List<String> claves4;
    List<String> claves5;
    List<String> claves6;
    List<String> claves7;
    List<String> claves8;
    List<String> claves9;
    List<String> claves10;
    List<List<String>> listaClaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypreguntas);

        arrayList = new ArrayList<>();
        nombresEncuestados = new ArrayList<>();
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        lyFragmentoListaPreguntas = (LinearLayout) findViewById(R.id.lyFragmentoListaPreguntas);
        lvRespuestas_tipoGeneral = (ListView) findViewById(R.id.lvRespuestas_tipoGeneral);
        tvEnunciadoPregunta = (TextView) findViewById(R.id.tvEnunciadoPregunta);
        tvOpcionesPregunta = (TextView) findViewById(R.id.tvOpcionesPregunta);
        tipoPreguntaAbiertaAdapter = new TipoPreguntaAbiertaAdapter();
        miListaTipoPreguntaAbiertaAdapter = new ArrayList<>();
        scrollView = new ScrollView(PreguntasActivity.this);
        alert = new AlertDialog.Builder(this);
        claves1 = new ArrayList<>();
        claves2 = new ArrayList<>();
        claves3 = new ArrayList<>();
        claves4 = new ArrayList<>();
        claves5 = new ArrayList<>();
        claves6 = new ArrayList<>();
        claves7 = new ArrayList<>();
        claves8 = new ArrayList<>();
        claves9 = new ArrayList<>();
        claves10 = new ArrayList<>();

        if(numPregunta==-1)
        nombresEncuestados.add("Gustavo Gómez");
        nombresEncuestados.add("Jesus Cahuana");
        nombresEncuestados.add("Diego Alvarez");
        nombresEncuestados.add("Pedro Santamaria");
        nombresEncuestados.add("Jorge Rios");
        nombresEncuestados.add("Josue Taipe");
        nombresEncuestados.add("Cristian Perez");
        nombresEncuestados.add("Jose Zavala");
        nombresEncuestados.add("Pedro Macedo");
        nombresEncuestados.add("Miguel Ticeran");
        nombresEncuestados.add("Jordi Tambillo");
        nombresEncuestados.add("Javier Diaz");

        listaClaves = new ArrayList<List<String>>();
        //Se lee la primera pregunta que retorna un objeto de tipo PreguntaOpcion
        PreguntaOpcion preguntaOpcion = new PreguntaOpcion();
        //preguntar a las base de datos

        //Obteniendo el tipo de pregunta
        //String tipoPregunta = preguntaOpcion.getPre_idPadre().getPre_tipo_rpta();
        String tipoPregunta = "UN";

        //obteniendo numero maximo de personas que deben responder las preguntas
        //int numEncuestado = preguntaOpcion.getPre_idPadre().getPre_num_max_allegados();
        int numEncuestado = 10;

        if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaUnica))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();
            poblarLista_TipoPreguntaAbierta(numEncuestado);

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        } else if (tipoPregunta.equals(getResources().getString(R.string.tipoPreguntaMultiple))) {
            tipoPreguntaAbiertaAdapter.limpiarLista();

        }


        //se comenta el uso de fragmentos
        /*
        //La primera vez carga la primera pregunta
        fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListViewTipoPregunta1Fragment listViewTipoPregunta1Fragment = new ListViewTipoPregunta1Fragment();
        fragmentTransaction.add(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta1Fragment);
        fragmentTransaction.commit();
        */
        //btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
        btnSiguiente.setOnClickListener(btnSiguientesetOnClickListener);
    }

    View.OnClickListener btnSiguientesetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            poblarLista_TipoPreguntaAbierta(12);
            //Intent intent = new Intent(PreguntasActivity.this, PreguntasActivity.class);
            //startActivity(intent);
            /*
            fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListViewTipoPregunta2Fragment listViewTipoPregunta2Fragment = new ListViewTipoPregunta2Fragment();
            fragmentTransaction.replace(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta2Fragment);
            fragmentTransaction.commit();*/
            /*
            fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListViewTipoPregunta2Fragment listViewTipoPregunta2Fragment = new ListViewTipoPregunta2Fragment();
            fragmentTransaction.replace(lyFragmentoListaPreguntas.getId(), listViewTipoPregunta2Fragment);
            fragmentTransaction.commit();
            */
        }
    };

    public void obtenerNombresEncuestados(int num) {
        listaPreguntas.add("¿Cuál es la relación de parentesco con el Jefe del Hogar?");
        listaPreguntas.add("Sexo:");
        listaPreguntas.add("¿Qué edad tiene en años	cumplidos?");
        listaPreguntas.add("¿Sabe leer y escribir?");
        listaPreguntas.add("¿Este año esta matriculado en un Colegio, Escuela, Intituto Superior o Universidad?");
        listaPreguntas.add("¿Cuanto es el Máximo Nivel Educativo alcanzado y último año de estudios aprobado?");
        listaPreguntas.add("¿Usted usa internet?");
        listaPreguntas.add("¿Desde cuando utiliza el internet?");
        listaPreguntas.add("¿Por su experiencia en internet, diria usted que es...?");
        listaPreguntas.add("Usted usa internet por medio de ...");

        claves1.add("1 Jefe/Jefa");
        claves1.add("2 Esposa(o)");
        claves1.add("3 Hijo(a)");
        claves1.add("4 Nieto");
        claves1.add("5 Yerno/Nuera");
        claves1.add("6 Padres/Suegros");
        claves1.add("7 Otros parientes");
        claves1.add("8 Trabaj. hogar");
        claves1.add("9 Pensionista");
        claves1.add("10 Otros no parientes");


        claves2.add("1 Hombre");
        claves2.add("2 Mujer");


        claves3.add("");


        claves4.add("1 Si");
        claves4.add("2 NO");


        claves5.add("1 Si");
        claves5.add("2 NO");


        claves6.add("1 Sin estudios");
        claves6.add("2 Inicial");
        claves6.add("3 Primaria Incompleta");
        claves6.add("4 Primaria Completa");
        claves6.add("5 Secundaria Incompleta");
        claves6.add("6 Secundaria Completa");
        claves6.add("7 Superior No Universitaria Incompleta");
        claves6.add("8 Superior No Universitaria Completa");
        claves6.add("9 Superior Universitaria Incompleta");
        claves6.add("10 Superior Universitaria Completa");
        claves6.add("11 Postgrado");


        claves7.add("1 Si");
        claves7.add("2 NO");


        claves8.add("1 Menos de 1 año");
        claves8.add("2 De 1 a menos de 2 años");
        claves8.add("3 De 2 a 3 años");
        claves8.add("4 Mas de 3 años");


        claves9.add("1 Muy facil de usar");
        claves9.add("2 Facil de usar");
        claves9.add("3 Dificil de usar");
        claves9.add("4 Muy dificil de usar");


        claves10.add("1 Conexion fija dentro de la vivienda o en el hogar(PC/Laptop)");
        claves10.add("2 Conexion inalámbrica desde laptop (vía wifi de terceros, plazas, lugares comerciales, lugares públicos, etc)");
        claves10.add("3 Conexión inalámbrica gratuita desde celular/tablet (via Wi-Fi de terceros, plazas, lugares comerciales, lugares públicos, etc)");
        claves10.add("4 En el trabajo");
        claves10.add("5 En centro de estudios, centro educativo");
        claves10.add("6 En la casa (departamento) de un vecino, amigo u otro familiar ajeno a mi hogar");
        claves10.add("7 Conexión con módem USB desde PC/laptop pagado");
        claves10.add("8 Conexión con celular/Tablet Pagado");
        claves10.add("9 Cabina Pública");
        claves10.add("10 Otro");

        listaClaves.add(claves1);
        listaClaves.add(claves2);
        listaClaves.add(claves3);
        listaClaves.add(claves4);
        listaClaves.add(claves5);
        listaClaves.add(claves6);
        listaClaves.add(claves7);
        listaClaves.add(claves8);
        listaClaves.add(claves9);
        listaClaves.add(claves10);


        /*
        final LinearLayout lyinput = new LinearLayout(PreguntasActivity.this);
        lyinput.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < num; i++) {

            final TextView tvinput = new TextView(PreguntasActivity.this);
            tvinput.setText("Nombre " + (i + 1) + ":  ");
            tvinput.setTextSize(20);

            final EditText etinput = new EditText(PreguntasActivity.this);
            etinput.setWidth(1000);

            textViews.add(tvinput);
            editTexts.add(etinput);
        }

        for (int i = 0; i < num; i++) {
            lyinput.addView(textViews.get(i));
            lyinput.addView(editTexts.get(i));
        }
        //alert.setTitle("Ingrese el nombre de las " + num + " personas");
        scrollView.addView(lyinput);
        //alert.setView(scrollView);
        //new ProgressAsyncTask().execute();


        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                System.out.println("campos: " + editTexts.size());
                for (int i = 0; i < editTexts.size(); i++) {
                    nombresEncuestados.add(editTexts.get(i).getText().toString().trim());
                }
                System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
                for (int i = 0; i < nombresEncuestados.size(); i++) {
                    System.out.println("campo: " + nombresEncuestados.get(i));
                }
                System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
                dataLista = true;
                dialog.dismiss();
            }

        });

        //alert.setCancelable(false).setView(scrollView).show();
        System.out.println("lanza hilo");

        System.out.println("aasdfadsfsdflskdfjdjklfjkdslfjkfldskjldfskjldfskjdfskjlfds");
        */
    }


    private void poblarLista_TipoPreguntaAbierta(int numEncuestado) {
        if(numPregunta <9)
            numPregunta++;
        else
        {
            Intent intent = new Intent(PreguntasActivity.this, PrincipalEncuestaActivity.class);
            startActivity(intent);
        }
        if (numEncuestado != 1) {
            obtenerNombresEncuestados(numEncuestado);

            System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
        }
        System.out.println("cargar datos");
        System.out.println("datos: " + nombresEncuestados.size());

        if(numPregunta==0)
        {
            for (int i = 0; i < nombresEncuestados.size(); i++) {

                TipoPreguntaAbiertaItem tipoPreguntaAbiertaItem = new TipoPreguntaAbiertaItem();
                tipoPreguntaAbiertaItem.setTitle(nombresEncuestados.get(i));

                miListaTipoPreguntaAbiertaAdapter.add(tipoPreguntaAbiertaItem);
            }

        }

        String datosOpc=null;
        String datos=null;
        System.out.println("dimmm: " + listaClaves.get(numPregunta).size());

        switch (numPregunta)
        {
            case 0:
                datosOpc = "1 Jefe/Jefa"+
                "   2 Esposa(o)"+
                "   3 Hijo(a)"+
                "   4 Nieto"+
                "   5 Yerno/Nuera"+
                "   6 Padres/Suegros"+
                "   7 Otros parientes"+
                "   8 Trabaj. hogar"+
                "   9 Pensionista"+
                "   10 Otros no parientes";
                break;
            case 1:
                datosOpc = "1 Hombre"+
                "   2 Mujer";
                break;
            case 2:
                datosOpc = " ";
                break;
            case 3:
                datosOpc = "1 SI   2 NO ";
                break;
            case 4:
                datosOpc = "1 SI   2 NO ";
                break;
            case 5:
                datosOpc = "1. Sin estudios"+
                "   2. Inicial"+
                "   3. Primaria Incompleta"+
                "   4. Primaria Completa"+
                "   5. Secundaria Incompleta"+
                "   6. Secundaria Completa"+
                "   7. Superior No Universitaria Incompleta"+
                "   8. Superior No Universitaria Completa"+
                "   9. Superior Universitaria Incompleta"+
                "   10. Superior Universitaria Completa"+
                "   11. Postgrado";

                break;
            case 6:
                datosOpc = "1 SI   2 NO ";
                break;
            case 7:
                datosOpc = "1 Menos de 1 año"+
                "   2. De 1 a menos de 2 años"+
                "   3. De 2 a 3 años"+
                "   4. Mas de 3 años";

                break;
            case 8:
                datosOpc= "1. Muy facil de usar"+
                "   2. Facil de usar"+
                "   3. Dificil de usar"+
                "   4. Muy dificil de usar";

                break;
            case 9:
                datosOpc = "1. Conexion fija dentro de la vivienda o en el hogar(PC/Laptop)"+
                "   2. Conexion inalámbrica desde laptop (vía wifi de terceros, plazas, lugares comerciales, lugares públicos, etc)"+
                "   3. Conexión inalámbrica gratuita desde celular/tablet (via Wi-Fi de terceros, plazas, lugares comerciales, lugares públicos, etc)"+
                "   4. En el trabajo"+
                "   5. En centro de estudios, centro educativo"+
                "   6. En la casa (departamento) de un vecino, amigo u otro familiar ajeno a mi hogar"+
                "   7. Conexión con módem USB desde PC/laptop pagado"+
                "   8. Conexión con celular/Tablet Pagado"+
                "   9. Cabina Pública"+
                "   10. Otro";

                break;
            case 10:
                break;

        }

        tvOpcionesPregunta.setText(datosOpc);

        tvEnunciadoPregunta.setText(listaPreguntas.get(numPregunta));

        lvRespuestas_tipoGeneral.setAdapter(new TipoPreguntaAbiertaAdapter(context, miListaTipoPreguntaAbiertaAdapter));
        System.out.println("num pregunta: "+ numPregunta);
        tipoPreguntaAbiertaAdapter.limpiarLista();
    }


    class ProgressAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //alert = new AlertDialog.Builder(PreguntasActivity.this).setTitle("\"Ingrese el nombre de las \" + num + \" personas\"")
            //      .setPositiveButton("Aceptar", alertAcceptOnClickListener).setCancelable(false).setView(scrollView).show();

        }

        DialogInterface.OnClickListener alertAcceptOnClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                System.out.println("campos: " + editTexts.size());
                for (int k = 0; k < editTexts.size(); k++) {
                    nombresEncuestados.add(editTexts.get(i).getText().toString().trim());
                }
                System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
                for (int k = 0; k < nombresEncuestados.size(); k++) {
                    System.out.println("campo: " + nombresEncuestados.get(i));
                }
                System.out.println("len nombresEncuestados: " + nombresEncuestados.size());
                dataLista = true;
                dialogInterface.dismiss();
            }
        };

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1500);
                while (dataLista != true) {
                    System.out.println("dataLista: " + dataLista);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}
