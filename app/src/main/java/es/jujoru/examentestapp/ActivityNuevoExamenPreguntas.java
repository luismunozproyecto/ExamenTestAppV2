package es.jujoru.examentestapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Clases.Examen;
import Clases.Pregunta;

public class ActivityNuevoExamenPreguntas extends AppCompatActivity {
    Button anep_btn_guardar;
    FloatingActionButton fabBack, fabNext;
    TextView nep_ic_titulo;
    EditText nep_et_pregunta, nep_et_res1,nep_et_res2,nep_et_res3;
    RadioGroup nep_rg;
    RadioButton rb1, rb2, rb3;
    int indice = 1;
    int max = 0;
    Examen examen;
    List<Pregunta> lista_preguntas = new ArrayList<>();
    View view;

    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen_preguntas);
        cargarComplementos();


    }
    private String obtenerUsuarioActivo(){
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);

        String user = prefs.getString("nombre", "admin");
        return user;
    }
    private void guardarExamen(){
        dbRef = FirebaseDatabase.getInstance().getReference()
                .child("creator/examenes");
        String clave = dbRef.push().getKey();

        examen.setId(clave);
        examen.setNom_usuario(obtenerUsuarioActivo());
        for(Pregunta p: examen.getPreguntas()) {
            p.setId_examen(clave);
        }

        dbRef.child(examen.getId()).setValue(examen, new DatabaseReference.CompletionListener(){
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if(error == null) {
                    Toast.makeText(getApplicationContext(),
                            "Examen guardado con éxito",
                            Toast.LENGTH_LONG).show();

                    Intent i=new Intent(getApplicationContext(), ActivityGestionExamen.class);
                    startActivity(i);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),
                            "Examen NO Guardado.",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    private void limpiarFormulario(){
        nep_et_pregunta.setText("");
        nep_et_res1.setText("");
        nep_et_res2.setText("");
        nep_et_res3.setText("");
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);

    }
    private void cargarComplementos(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();

        if(b!=null){
             examen = b.getParcelable(ActivityNuevoExamenConfiguracion.EXTRA_EXAMEN);
             max = examen.getNumero_preguntas();
        }

        fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        fabBack.setVisibility(View.INVISIBLE);

        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        nep_ic_titulo = (TextView) findViewById(R.id.nep_ic_titulo);
        nep_ic_titulo.setText("Pregunta "+(indice)+"/"+max);
        anep_btn_guardar = (Button) findViewById(R.id.anep_btn_guardar);
        anep_btn_guardar.setEnabled(false);
        nep_et_pregunta = (EditText) findViewById(R.id.nep_et_pregunta);
        nep_et_res1 = (EditText) findViewById(R.id.nep_et_res1);
        nep_et_res2 = (EditText) findViewById(R.id.nep_et_res2);
        nep_et_res3 = (EditText) findViewById(R.id.nep_et_res3);
        nep_rg = (RadioGroup) findViewById(R.id.nep_rg);
        rb1 = (RadioButton) findViewById(R.id.nep_rb1);
        rb2 = (RadioButton) findViewById(R.id.nep_rb2);
        rb3 = (RadioButton) findViewById(R.id.nep_rb3);


        anep_btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examen.setPreguntas(lista_preguntas);
                examen.setActivo(0);

                guardarExamen();
            }
        });
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nep_ic_titulo.setText("Pregunta "+(indice+1)+"/"+max);
                indice--;
                Pregunta preguntaAnterior =  lista_preguntas.get(indice);
                nep_et_pregunta.setText(preguntaAnterior.getPregunta());
                nep_et_res1.setText(preguntaAnterior.getRespuestas().get(0));
                nep_et_res2.setText(preguntaAnterior.getRespuestas().get(1));
                nep_et_res3.setText(preguntaAnterior.getRespuestas().get(2));
                if(preguntaAnterior.getRespuesta_correcta().equals(preguntaAnterior.getRespuestas().get(0))){
                    rb1.setChecked(true);
                }else if(preguntaAnterior.getRespuesta_correcta().equals(preguntaAnterior.getRespuestas().get(1))){
                    rb2.setChecked(true);
                }else if(preguntaAnterior.getRespuesta_correcta().equals(preguntaAnterior.getRespuestas().get(2))){
                    rb3.setChecked(true);
                }


            }
        });
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String pregunta = nep_et_pregunta.getText().toString();
                String et_res1 = nep_et_res1.getText().toString();
                String et_res2 = nep_et_res2.getText().toString();
                String et_res3 = nep_et_res3.getText().toString();

                if (pregunta.equals("") || et_res1.equals("") || et_res2.equals("") || et_res3.equals("")){
                    Snackbar.make(getCurrentFocus(), "Debes de rellenar los campos", Snackbar.LENGTH_LONG).show();
                }else{



                    if(indice == max  ){
                        anep_btn_guardar.setEnabled(true);
                        nep_et_pregunta.setEnabled(false);
                        nep_et_res1.setEnabled(false);
                        nep_et_res2.setEnabled(false);
                        nep_et_res3.setEnabled(false);
                        nep_rg.setEnabled(false);
                        fabNext.setVisibility(View.INVISIBLE);

                    }
                    indice++;
                    nep_ic_titulo.setText("Pregunta "+(indice)+"/"+max);
                    List<String> respuestas = new ArrayList<>();
                    Collections.addAll(respuestas,et_res1,et_res2, et_res3);

                    int posSel =nep_rg.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton)findViewById(posSel);
                    String posCorrecta="";
                    if(radioButton.getText().toString().equals(R.string.nep_rb_res1)){
                        posCorrecta=et_res1;
                    }else if(radioButton.getText().toString().equals(R.string.nep_rb_res2)){
                        posCorrecta=et_res2;
                    }else if(radioButton.getText().toString().equals(R.string.nep_rb_res3)){
                        posCorrecta=et_res3;
                    }

                    Pregunta p = new Pregunta(examen.getId(),pregunta,respuestas,posCorrecta);
                    lista_preguntas.add(p);

                    limpiarFormulario();

                }

            }
        });

    }

}
