package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Clases.Examen;
import Clases.Pregunta;
import es.jujoru.examentestapp.Fragments.FragmentTestExamen;

public class ActivityEditarPregunta extends AppCompatActivity {
    FloatingActionButton fab;
    TextView aep_ic_titulo;
    EditText aep_et_pregunta,aep_et_res1,aep_et_res2,aep_et_res3;
    RadioGroup aep_rg;
    RadioButton rb_res1, rb_res2, rb_res3;
    Pregunta pregunta;
    Examen examen;
    int pos;
    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pregunta);
        cargarComponentes();


    }

    private void cargarComponentes(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Bundle b = getIntent().getExtras();

        if(b!=null){
            pregunta = b.getParcelable(FragmentTestExamen.EXTRA_PARAM2);
            examen = b.getParcelable(FragmentTestExamen.EXTRA_PARAM1);
            pos = b.getInt(FragmentTestExamen.EXTRA_POSITION);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        aep_ic_titulo= (TextView)findViewById(R.id.aep_ic_titulo);
        aep_ic_titulo.setText(examen.getNombre());
        aep_et_pregunta = (EditText)findViewById(R.id.aep_et_pregunta);
        aep_et_pregunta.setText(pregunta.getPregunta());
        aep_et_res1 = (EditText)findViewById(R.id.aep_et_res1);
        aep_et_res1.setText(pregunta.getRespuestas().get(0));
        aep_et_res2 = (EditText)findViewById(R.id.aep_et_res2);
        aep_et_res2.setText(pregunta.getRespuestas().get(1));
        aep_et_res3 = (EditText)findViewById(R.id.aep_et_res3);
        aep_et_res3.setText(pregunta.getRespuestas().get(2));
        aep_rg = (RadioGroup) findViewById(R.id.aep_rg);
        rb_res1 = (RadioButton) findViewById(R.id.aep_rb1);
        rb_res2 = (RadioButton) findViewById(R.id.aep_rb2);
        rb_res3 = (RadioButton) findViewById(R.id.aep_rb3);

        if(pregunta.getRespuesta_correcta().equals(pregunta.getRespuestas().get(0))){
            rb_res1.setChecked(true);
        }else if(pregunta.getRespuesta_correcta().equals(pregunta.getRespuestas().get(1))){
            rb_res2.setChecked(true);
        }else if(pregunta.getRespuesta_correcta().equals(pregunta.getRespuestas().get(2))){
            rb_res3.setChecked(true);
        }




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pregunta = aep_et_pregunta.getText().toString();
                String  et_res1 = aep_et_res1.getText().toString();
                String  et_res2 = aep_et_res2.getText().toString();
                String  et_res3 = aep_et_res3.getText().toString();
                List<String> res = new ArrayList<>();
                Collections.addAll(res, et_res1,et_res2, et_res3);
                int posSel =aep_rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)findViewById(posSel);
                String posCorrecta="";
                if(radioButton.getText().toString().equals(R.string.aep_rb_res1)){
                    posCorrecta=et_res1;
                }else if(radioButton.getText().toString().equals(R.string.aep_rb_res2)){
                    posCorrecta=et_res2;
                }else if(radioButton.getText().toString().equals(R.string.aep_rb_res3)){
                    posCorrecta=et_res3;
                }

                Pregunta updatePregunta = new Pregunta(examen.getId(),pregunta,res,posCorrecta);



                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("creator/examenes");

                String id_examen = examen.getId();
                databaseReference.child(id_examen+"/preguntas/"+pos).setValue(updatePregunta, new DatabaseReference.CompletionListener(){
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if(error == null) {

                            Snackbar.make(getCurrentFocus(),"Pregunta del examen modificada", Snackbar.LENGTH_LONG).show();
                        }else {
                            Snackbar.make(getCurrentFocus(),"Pregunta del examen no modificada", Snackbar.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });

    }


}
