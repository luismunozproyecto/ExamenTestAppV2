package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Clases.Examen;
import Clases.Pregunta;
import es.jujoru.examentestapp.Fragments.FragmentTestExamen;

public class ActivityVerPregunta extends AppCompatActivity {

    TextView avp_pregunta, avp_res1, avp_res2, avp_res3;
    Examen examen;
    String idExamen;
    Pregunta pregunta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pregunta);
        cargarElementos();

        Bundle b = getIntent().getExtras();
        if (b != null) {

            pregunta = b.getParcelable(FragmentTestExamen.EXTRA_PARAM2);

            mostrarPregunta();

        }
    }


    private void cargarElementos() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        avp_pregunta = (TextView) findViewById(R.id.avp_pregunta);
        avp_res1 = (TextView) findViewById(R.id.avp_res1);
        avp_res2 = (TextView) findViewById(R.id.avp_res2);
        avp_res3 = (TextView) findViewById(R.id.avp_res3);
    }


    private void mostrarPregunta(){
        avp_pregunta.setText(pregunta.getPregunta());
        avp_res1.setText(pregunta.getRespuestas().get(0));
        avp_res2.setText(pregunta.getRespuestas().get(1));
        avp_res3.setText(pregunta.getRespuestas().get(2));

    }


}
