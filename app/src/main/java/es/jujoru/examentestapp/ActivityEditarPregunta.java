package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityEditarPregunta extends AppCompatActivity {
    FloatingActionButton fab;
    TextView aep_ic_titulo;
    EditText aep_et_pregunta,aep_et_res1,aep_et_res2,aep_et_res3;
    RadioGroup aep_rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pregunta);

    }


    private void cargarComponentes(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        aep_ic_titulo= (TextView)findViewById(R.id.aep_ic_titulo);
        aep_et_pregunta = (EditText)findViewById(R.id.aep_et_pregunta);
        aep_et_res1 = (EditText)findViewById(R.id.aep_et_res1);
        aep_et_res2 = (EditText)findViewById(R.id.aep_et_res2);
        aep_et_res3 = (EditText)findViewById(R.id.aep_et_res3);
        aep_rg = (RadioGroup) findViewById(R.id.aep_rg);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


}
