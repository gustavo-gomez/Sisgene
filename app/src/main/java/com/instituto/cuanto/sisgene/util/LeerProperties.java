package com.instituto.cuanto.sisgene.util;

import android.os.Environment;

import java.io.File;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Created by Jesus on 24/11/2015.
 */
public class LeerProperties {

    public LeerProperties(){}

    public String leerIPWS(){
        try {

            File ruta_sd = Environment.getExternalStorageDirectory();
            String rutaArch = ruta_sd.getAbsolutePath()+"/SISGENE/sisgene.properties";
            //rutaArch = "/Memoria Interna";
            System.out.println("ruta sd : --->>> "+ruta_sd.getAbsolutePath());
            System.out.println("ruta sd : --->>> "+rutaArch);

            /**Creamos un Objeto de tipo Properties*/
            Properties propiedades = new Properties();

            /**Cargamos el archivo desde la ruta especificada*/
            propiedades.load(new FileInputStream(rutaArch));

            /**Obtenemos los parametros definidos en el archivo*/
            String ip = propiedades.getProperty("IPWS");

            System.out.println("NOMBREEEEE : "+ip);

            return  ip;

        } catch (FileNotFoundException e) {
            System.out.println("Error, El archivo no exite "+e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Error, No se puede leer el archivo  " +e.getMessage());
            return null;
        }
    }

    public String leerPUERTOWS(){
        try {
            File ruta_sd = Environment.getExternalStorageDirectory();

            /**Creamos un Objeto de tipo Properties*/
            Properties propiedades = new Properties();
            String rutaArch = ruta_sd.getAbsolutePath();
            //rutaArch = "/Memoria Interna";

            /**Cargamos el archivo desde la ruta especificada*/
            propiedades.load(new FileInputStream(rutaArch+"/SISGENE/sisgene.properties"));

            /**Obtenemos los parametros definidos en el archivo*/
            String puerto = propiedades.getProperty("PUERTOWS");

            System.out.println("PUERRTOOOOO : "+puerto);

            return  puerto;

        } catch (FileNotFoundException e) {
            System.out.println("Error, El archivo no exite");
            return null;
        } catch (IOException e) {
            System.out.println("Error, No se puede leer el archivo");
            return null;
        }
    }
}
