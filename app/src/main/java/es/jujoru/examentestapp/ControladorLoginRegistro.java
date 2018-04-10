package es.jujoru.examentestapp;

import java.util.List;

/**
 * Created by Nino Ruano on 10/04/2018.
 */

public class ControladorLoginRegistro {


    public static boolean esVacio(List<String> valores){

        for (int i=0; i<valores.size(); i++){
            if(valores.get(i).trim().equals("")){
                return true;
            }
        }

        return false;
    }
}
