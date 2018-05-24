package es.jujoru.examentestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class ActivityNuevoExamenConfiguracion extends AppCompatActivity {
    FloatingActionButton fab;
    
    Switch aece_switch;
    EditText anec_et_duracion, anec_et_numero_preguntas, anec_et_valor_blanco,
            anec_et_valor_acierto,anec_et_valor_fallo, anec_et_nota_corte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen_configuracion);
        cargarComponentes();


    }


    private void cargarComponentes(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
       
        aece_switch = (Switch) findViewById(R.id.aece_switch);
        anec_et_duracion = (EditText) findViewById(R.id.anec_et_duracion);
        anec_et_numero_preguntas = (EditText) findViewById(R.id.anec_et_numero_preguntas);
        anec_et_valor_blanco = (EditText) findViewById(R.id.anec_et_valor_blanco);
        anec_et_valor_acierto = (EditText) findViewById(R.id.anec_et_valor_acierto);
        anec_et_valor_fallo = (EditText) findViewById(R.id.anec_et_valor_fallo);
        anec_et_nota_corte = (EditText) findViewById(R.id.anec_et_nota_corte);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
