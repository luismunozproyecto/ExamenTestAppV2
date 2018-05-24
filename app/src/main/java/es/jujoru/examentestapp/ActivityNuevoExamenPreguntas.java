package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ActivityNuevoExamenPreguntas extends AppCompatActivity {
    Button anep_btn_guardar;
    FloatingActionButton fabBack, fabNext;
    EditText nep_et_pregunta, nep_et_res1,nep_et_res2,nep_et_res3;
    RadioGroup nep_rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen_preguntas);
        cargarComplementos();


    }

    private void cargarComplementos(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        anep_btn_guardar = (Button) findViewById(R.id.anep_btn_guardar);
        nep_et_pregunta = (EditText) findViewById(R.id.nep_et_pregunta);
        nep_et_res1 = (EditText) findViewById(R.id.nep_et_res1);
        nep_et_res2 = (EditText) findViewById(R.id.nep_et_res2);
        nep_et_res3 = (EditText) findViewById(R.id.nep_et_res3);
        nep_rg = (RadioGroup) findViewById(R.id.nep_rg);


        anep_btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Atras", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Siguiente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
