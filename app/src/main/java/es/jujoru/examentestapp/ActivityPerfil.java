package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class ActivityPerfil extends AppCompatActivity {
    EditText etUsuario, etPassword, etPassword2, etEmail, etDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cargarComponentes();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    private void cargarComponentes(){

        etUsuario = (EditText)findViewById(R.id.pf_et_usuario);
        etPassword = (EditText)findViewById(R.id.pf_et_password);
        etPassword2 = (EditText)findViewById(R.id.pf_et_password2);
        etEmail = (EditText)findViewById(R.id.pf_et_email);
        etDNI = (EditText)findViewById(R.id.pf_et_dni);
    }

}
