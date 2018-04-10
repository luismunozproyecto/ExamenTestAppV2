package es.jujoru.examentestapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ActivityLogin extends AppCompatActivity {

    EditText etUsuario, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarComponentes();
    }

    private void cargarComponentes(){

        etUsuario = (EditText)findViewById(R.id.la_et_usuario);
        etPassword = (EditText)findViewById(R.id.la_et_password);

    }

    public void onClickRegistro(View view){
        Intent i=new Intent(getApplicationContext(), ActivityRegistro.class);
        startActivity(i);
    }

    public void onClickLogin(View view){

        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        List<String> valores = new ArrayList<>();
        Collections.addAll(valores, usuario, password);

        if(ControladorLoginRegistro.esVacio(valores)){
            Snackbar.make(view, R.string.gen_campos_vacios, Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(view, "genial", Snackbar.LENGTH_LONG).show();
        }
    }
}
