package com.instituto.cuanto.sisgene.util;

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

            /**Creamos un Objeto de tipo Properties*/
            Properties propiedades = new Properties();

            /**Cargamos el archivo desde la ruta especificada*/
            propiedades.load(new FileInputStream("/storage/emulated/0/SISGENE/sisgene.properties"));

            /**Obtenemos los parametros definidos en el archivo*/
            String ip = propiedades.getProperty("IPWS");

            System.out.println("NOMBREEEEE : "+ip);

            return  ip;

        } catch (FileNotFoundException e) {
            System.out.println("Error, El archivo no exite");
            return null;
        } catch (IOException e) {
            System.out.println("Error, No se puede leer el archivo");
            return null;
        }
    }

    public String leerPUERTOWS(){
        try {

            /**Creamos un Objeto de tipo Properties*/
            Properties propiedades = new Properties();

            /**Cargamos el archivo desde la ruta especificada*/
            propiedades.load(new FileInputStream("/storage/emulated/0/SISGENE/sisgene.properties"));

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
