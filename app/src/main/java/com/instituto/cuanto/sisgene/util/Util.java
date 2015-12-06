package com.instituto.cuanto.sisgene.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;

/**
 * Created by Gustavo on 19/10/2015.
 */
public class Util {


    public static String obtenerFecha() {
        Calendar c = new GregorianCalendar();
        int dia, mes, ano;
        String sDia = "", sMes = "";

        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH) + 1;
        ano = c.get(Calendar.YEAR);

        if (dia < 10) {
            sDia = "0" + dia;
        } else {
            sDia = "" + dia;
        }

        if (mes < 10) {
            sMes = "0" + mes;
        } else {
            sMes = "" + mes;
        }

        return sDia + "/" + sMes + "/" + ano;
    }

    public static String obtenerHora() {
        int hora, minutos, segundos;
        Calendar calendario = Calendar.getInstance();

        Formatter fHora = new Formatter();
        Formatter fminutos = new Formatter();
        Formatter fsegundos = new Formatter();

        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);


        fHora.format("%02d", hora);
        fminutos.format("%02d", minutos);
        fsegundos.format("%02d", segundos);

        return "" + fHora + ":" + fminutos + ":" + fsegundos;
    }
    public static String obtenerFechayHora(){

        Date date = new Date();

        // Obtener hora y fecha y salida por pantalla con formato:
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return hourdateFormat.format(date);
    }
}
