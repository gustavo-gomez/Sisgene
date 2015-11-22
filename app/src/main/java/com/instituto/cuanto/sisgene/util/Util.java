package com.instituto.cuanto.sisgene.util;

import java.util.Calendar;
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

        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        return hora + ":" + minutos + ":" + segundos;
    }

}
