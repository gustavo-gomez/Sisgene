package com.instituto.cuanto.sisgene.util;

import com.instituto.cuanto.sisgene.entidad.TipoDocumento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * Created by Jesus on 07/12/2015.
 */
public class TiempoDiferencia {

    static long milisegundos_dia = 24 * 60 * 60 * 1000;

    public TiempoDiferencia(){}

    public String retornarDiferenciaTiempo(String fecha1, String fecha2) {

        String timepo = "";

        try {

            Date fechaInicial = TiempoDiferencia.StringToDate(fecha1, "/", 0);
            Date fechaFinal = TiempoDiferencia.StringToDate(fecha2, "/", 0);

            Calendar calFechaInicial = Calendar.getInstance();
            Calendar calFechaFinal = Calendar.getInstance();

            calFechaInicial.setTime(fechaInicial);
            calFechaFinal.setTime(fechaFinal);

            long horas = diferenciaHorasDias(calFechaInicial, calFechaFinal) + diferenciaHoras(calFechaInicial, calFechaFinal);
            long minutos = diferenciaMinutos(calFechaInicial, calFechaFinal);

            if(minutos < 0){
                System.out.println("Horas: " + (horas - 1) + " Minutos: " + (minutos + 60));
                horas = horas - 1 ;
                minutos = minutos + 60;
            }else{
                System.out.println("Horas: " + (horas) + " Minutos : " + minutos);
            }

            String sHora="",sMinuto="";
            if(horas < 10){
                sHora = "0"+horas;
            }else{
                sHora = horas+"";
            }

            if(minutos < 10 ){
                sMinuto = "0"+minutos;
            }else{
                sMinuto = ""+minutos;
            }

            return sHora+":"+sMinuto;

        }catch(Exception e){
            System.out.println("ERROR AL CALCULAR TIEMPO");
            return null;
        }

    }


    public static long diferenciaHorasDias(Calendar fechaInicial ,Calendar fechaFinal){
        //Milisegundos al día
        long diferenciaHoras=0;
        //Restamos a la fecha final la fecha inicial y lo dividimos entre el numero de milisegundos al dia
        diferenciaHoras=(fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/milisegundos_dia;

        if(diferenciaHoras>0){
            diferenciaHoras*=24;
        }

        return diferenciaHoras;
    }

    public static long diferenciaMinutos(Calendar fechaInicial ,Calendar fechaFinal){
        long diferenciaHoras=0;
        diferenciaHoras=(fechaFinal.get(Calendar.MINUTE)-fechaInicial.get(Calendar.MINUTE));
        return diferenciaHoras;
    }

    public static long cantidadTotalMinutos(Calendar fechaInicial ,Calendar fechaFinal){
        long totalMinutos=0;
        totalMinutos=((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000/60);
        return totalMinutos;
    }

    public static long cantidadTotalHoras(Calendar fechaInicial ,Calendar fechaFinal){
        long totalMinutos=0;
        totalMinutos=((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000/60/60);
        return totalMinutos;
    }

    public static long cantidadTotalSegundos(Calendar fechaInicial ,Calendar fechaFinal){
        long totalMinutos=0;
        totalMinutos=((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000);
        return totalMinutos;
    }

    public static long diferenciaHoras(Calendar fechaInicial ,Calendar fechaFinal){
        long diferenciaHoras=0;
        diferenciaHoras=(fechaFinal.get(Calendar.HOUR_OF_DAY)-fechaInicial.get(Calendar.HOUR_OF_DAY));
        return diferenciaHoras;
    }

    public static String DateToString(Date fecha,String caracter,int op){
        String formatoHora=" HH:mm:ss";
        //Formato de hora //caracter hace referencia al separador / -

        String formato="yyyy"+caracter+"MM"+caracter+"dd"+formatoHora;

        if(op==1) //
            formato="yyyy"+caracter+"dd"+caracter+"MM"+formatoHora;
        else if(op==2)
            formato="MM"+caracter+"yyyy"+caracter+"dd"+formatoHora;
        else if(op==3)
            formato="MM"+caracter+"dd"+caracter+"yyyy"+formatoHora;
        else if(op==4)
            formato="dd"+caracter+"yyyy"+caracter+"MM"+formatoHora;
        else if(op==5)
            formato="dd"+caracter+"MM"+caracter+"yyyy"+formatoHora;

        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
        String fechaFormato=null;

        sdf.setLenient(false); fechaFormato=sdf.format(fecha);

        return fechaFormato;
    }

    public static Date StringToDate(String fecha,String caracter,int op){

        String formatoHora=" HH:mm:ss";
        String formato="yyyy"+caracter+"MM"+caracter+"dd"+formatoHora;

        if(op==1) //
         formato="yyyy"+caracter+"dd"+caracter+"MM"+formatoHora;
        else if(op==2)
            formato="MM"+caracter+"yyyy"+caracter+"dd"+formatoHora;
        else if(op==3)
            formato="MM"+caracter+"dd"+caracter+"yyyy"+formatoHora;
        else if(op==4)
            formato="dd"+caracter+"yyyy"+caracter+"MM"+formatoHora;
        else if(op==5) formato="dd"+caracter+"MM"+caracter+"yyyy"+formatoHora;

        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());

        Date fechaFormato=null;

        try {
            sdf.setLenient(false);
            fechaFormato=sdf.parse(fecha);
        } catch (ParseException ex) {
        }

        return fechaFormato;
    }

}
