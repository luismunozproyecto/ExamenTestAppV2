package es.jujoru.examentestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityNuevoExamenInformacion extends AppCompatActivity {
    FloatingActionButton fab;
    TextView nei_titulo;
    EditText anei_et_nombre, anei_et_asignatura,anei_et_tema,
            anei_et_fecha, anei_et_hora,anei_et_fecha_activacion,anei_et_argumentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen_informacion);
        cargarComponentes();

        
        
    }

    private void cargarComponentes(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nei_titulo = (TextView)findViewById(R.id.nei_titulo);
        anei_et_nombre = (EditText)findViewById(R.id.anei_et_nombre);
        anei_et_asignatura = (EditText)findViewById(R.id.anei_et_asignatura);
        anei_et_tema = (EditText)findViewById(R.id.anei_et_tema);
        anei_et_fecha = (EditText)findViewById(R.id.anei_et_fecha);
        anei_et_hora = (EditText)findViewById(R.id.anei_et_hora);
        anei_et_fecha_activacion = (EditText)findViewById(R.id.anei_et_fecha_activacion);
        anei_et_argumentario = (EditText)findViewById(R.id.anei_et_argumentario);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), ActivityNuevoExamenConfiguracion.class);
                startActivity(i);
            }
        });

    }
}
