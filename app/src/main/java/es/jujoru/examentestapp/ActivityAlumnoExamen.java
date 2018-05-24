package es.jujoru.examentestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ActivityAlumnoExamen extends AppCompatActivity {

    ListView lvPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_examen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvPreguntas = (ListView) findViewById(R.id.ale_lv);
        String[] prueba = {"Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum...","Loremp ipsum Loremp ipsum..."};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,prueba);
        lvPreguntas.setAdapter(adapter);
        lvPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i=new Intent(getApplicationContext(), ActivityAlumnoPregunta.class);
                    startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabFinalizar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("¿Estas seguro de finalizar el examen, una vez finalices se calculará tu nota automáticamente" +
                        "y no podrás volver atrás?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}
