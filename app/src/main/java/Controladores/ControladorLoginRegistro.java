package Controladores;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ControladorLoginRegistro {

    private static final Pattern PATRON_EMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);



    public static boolean esVacio(List<String> valores){

        for (int i=0; i<valores.size(); i++){
            if(valores.get(i).trim().equals("")){
                return true;
            }
        }

        return false;
    }

    public static boolean passwordIguales(String p, String p2){
        if(p.equals(p2)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean emailValido(String email){
        Matcher matcher = PATRON_EMAIL .matcher(email);
        return matcher.find();
    }


}
