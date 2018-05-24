package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Clases.Examen;
import es.jujoru.examentestapp.Fragments.FragmentGestionExamen;
import es.jujoru.examentestapp.Fragments.FragmentInformacionExamen;

public class ActivityEditarInformacionExamen extends AppCompatActivity {
    TextView eie_titulo;
    EditText aeie_et_nombre, aeie_et_asignatura, aeie_et_tema,
            aeie_et_fecha, aeie_et_hora, aeie_et_fecha_activacion,aeie_et_hora_activacion, aeie_et_argumentario;

    Examen examen;

    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_informacion_examen);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            examen = b.getParcelable(FragmentInformacionExamen.EXTRA_PARAM1);
            cargarComponentes();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarInformacionExamen();
            }
        });

    }

    private void cargarComponentes() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eie_titulo = (TextView) findViewById(R.id.eie_titulo);
        eie_titulo.setText("" + examen.getNombre());
        aeie_et_nombre = (EditText) findViewById(R.id.aeie_et_nombre);
        aeie_et_nombre.setText("" + examen.getNombre());
        aeie_et_asignatura = (EditText) findViewById(R.id.aeie_et_asignatura);
        aeie_et_asignatura.setText("" + examen.getAsignatura());
        aeie_et_tema = (EditText) findViewById(R.id.aeie_et_tema);
        aeie_et_tema.setText("" + examen.getTema());
        aeie_et_fecha = (EditText) findViewById(R.id.aeie_et_fecha);
        aeie_et_fecha.setText("" + examen.getFecha());
        aeie_et_hora = (EditText) findViewById(R.id.aeie_et_hora);
        aeie_et_hora.setText("" + examen.getHora());
        aeie_et_fecha_activacion = (EditText) findViewById(R.id.aeie_et_fecha_activacion);
        aeie_et_fecha_activacion.setText("" + examen.getFecha_activacion());
        aeie_et_hora_activacion = (EditText) findViewById(R.id.aeie_et_hora_activacion);
        aeie_et_hora_activacion.setText("" + examen.getHora_activacion());
        aeie_et_argumentario = (EditText) findViewById(R.id.aeie_et_argumentario);
        aeie_et_argumentario.setText("" + examen.getArgumentario());


    }

    private void modificarInformacionExamen(){
        String nombre = aeie_et_nombre.getText().toString();
        examen.setNombre(nombre);
        String asignatura = aeie_et_asignatura.getText().toString();
        examen.setAsignatura(asignatura);
        String tema = aeie_et_tema.getText().toString();
        examen.setTema(tema);
        String fecha =  aeie_et_fecha.getText().toString();
        examen.setFecha(fecha);
        String hora = aeie_et_hora.getText().toString();
        examen.setHora(hora);
        String fecha_a = aeie_et_fecha_activacion.getText().toString();
        examen.setFecha_activacion(fecha_a);
        String hora_a = aeie_et_hora_activacion.getText().toString();
        examen.setHora_activacion(hora_a);
        String argumentario = aeie_et_argumentario.getText().toString();
        examen.setArgumentario(argumentario);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("creator/examenes");

        String id_examen = examen.getId();
        databaseReference.child(id_examen).setValue(examen, new DatabaseReference.CompletionListener(){
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if(error == null) {

                     Snackbar.make(getCurrentFocus(),"Información del examen modificada", Snackbar.LENGTH_LONG).show();
                }else {
                    Snackbar.make(getCurrentFocus(),"Información del examen no modificada", Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }

}
