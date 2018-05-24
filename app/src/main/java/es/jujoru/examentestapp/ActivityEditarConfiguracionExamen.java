package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Clases.Examen;
import es.jujoru.examentestapp.Fragments.FragmentInformacionExamen;

public class ActivityEditarConfiguracionExamen extends AppCompatActivity {
    FloatingActionButton fab;
    TextView aece_titulo;
    Switch aece_switch;
    EditText aece_et_duracion, aece_et_numero_preguntas, aece_et_valor_blanco,
            aece_et_valor_acierto,aece_et_valor_fallo, aece_et_nota_corte;
    Examen examen;

    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_configuracion_examen);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            examen = b.getParcelable(FragmentInformacionExamen.EXTRA_PARAM1);
            cargarComponentes();
        }
       }


    private void cargarComponentes(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        aece_titulo = (TextView)findViewById(R.id.aece_titulo);
        aece_titulo.setText(examen.getNombre());
        aece_switch = (Switch) findViewById(R.id.aece_switch);
        if(examen.isLimite_tiempo()==1){
            aece_switch.setChecked(true);
        }else{
            aece_switch.setChecked(false);
        }

        aece_et_duracion = (EditText) findViewById(R.id.aece_et_duracion);
        aece_et_duracion.setText(""+examen.getDuracion());
        aece_et_numero_preguntas = (EditText) findViewById(R.id.aece_et_numero_preguntas);
        aece_et_numero_preguntas.setText(""+examen.getNumero_preguntas());
        aece_et_valor_blanco = (EditText) findViewById(R.id.aece_et_valor_blanco);
        aece_et_valor_blanco.setText(""+examen.getValor_blanco());
        aece_et_valor_acierto = (EditText) findViewById(R.id.aece_et_valor_acierto);
        aece_et_valor_acierto.setText(""+examen.getValor_acierto());
        aece_et_valor_fallo = (EditText) findViewById(R.id.aece_et_valor_fallo);
        aece_et_valor_fallo.setText(""+examen.getValor_fallo());
        aece_et_nota_corte = (EditText) findViewById(R.id.aece_et_nota_corte);
        aece_et_nota_corte.setText(""+examen.getNota_corte());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(aece_switch.isChecked()){
                    examen.setLimite_tiempo(1);
                }else{
                    examen.setLimite_tiempo(0);
                }
                int duracion = Integer.parseInt(aece_et_duracion.getText().toString());
                examen.setDuracion(duracion);

                int num_preguntas = Integer.parseInt(aece_et_numero_preguntas.getText().toString());
                examen.setNumero_preguntas(num_preguntas);
                Double valor_blanco = Double.parseDouble(aece_et_valor_blanco.getText().toString());
                examen.setValor_blanco(valor_blanco);
                Double valor_fallo = Double.parseDouble(aece_et_valor_fallo.getText().toString());
                examen.setValor_fallo(valor_fallo);
                Double valor_acierto = Double.parseDouble(aece_et_valor_acierto.getText().toString());
                examen.setValor_acierto(valor_acierto);


                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference().child("craetor/examenes");

                String id_examen = examen.getId();
                databaseReference.child(id_examen).setValue(examen, new DatabaseReference.CompletionListener(){
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if(error == null) {

                            Snackbar.make(getCurrentFocus(),"Configuracion del examen modificada", Snackbar.LENGTH_LONG).show();
                        }else {
                            Snackbar.make(getCurrentFocus(),"Configuracion del examen no modificada", Snackbar.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }

}
