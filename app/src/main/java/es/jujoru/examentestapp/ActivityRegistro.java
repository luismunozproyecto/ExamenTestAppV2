package es.jujoru.examentestapp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Clases.Usuario;
import Controladores.ControladorLoginRegistro;

public class ActivityRegistro extends AppCompatActivity {

    EditText etUsuario, etPassword, etPassword2, etEmail, etDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarComponentes();
    }

    private void cargarComponentes(){
        etUsuario = (EditText)findViewById(R.id.ra_et_usuario);
        etPassword = (EditText)findViewById(R.id.ra_et_password);
        etPassword2 = (EditText)findViewById(R.id.ra_et_password2);
        etEmail = (EditText)findViewById(R.id.ra_et_email);
        etDNI = (EditText)findViewById(R.id.ra_et_dni);
    }

    public void onClickRegistro(View view){
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        String password2 = etPassword2.getText().toString();
        String email = etEmail.getText().toString();
        String dni = etDNI.getText().toString();
        List<String> valores = new ArrayList<>();
        Collections.addAll(valores, usuario, password,password2,email,dni);

        if(ControladorLoginRegistro.esVacio(valores)){
            Snackbar.make(view, R.string.gen_campos_vacios, Snackbar.LENGTH_LONG).show();
        }else{
            if(!ControladorLoginRegistro.passwordIguales(password,password2)){
                Snackbar.make(view, R.string.gen_passwords_diferentes, Snackbar.LENGTH_LONG).show();
            }else{
                if(ControladorLoginRegistro.emailValido(email)){
                    Snackbar.make(view, R.string.gen_email_no_valido, Snackbar.LENGTH_LONG).show();
                }else{
                    Usuario u = new Usuario(usuario,email,password,dni,"default.png");

                }
            }
        }
    }
}
