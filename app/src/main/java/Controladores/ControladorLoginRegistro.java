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
    public static boolean passwordFormato(String p){

        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String letrasMin = "abcdefghijklmnñopqrstuvwxyz";
        String numeros = "1234567890";
        boolean num=false;
        boolean let=false;
        for (int i=0; i<p.length(); i++){
            char c1=p.charAt(i);
            for (int j=0; j<letras.length(); j++){
                char c2=letras.charAt(j);
                char c3=letrasMin.charAt(j);
                if(c1==c2 || c1==c3){
                    let=true;
                }
            }
            for (int k=0; k<numeros.length(); k++){
                if(numeros.charAt(k)==p.charAt(i)){
                    num=true;
                }
            }
        }
        if(num && let){
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
