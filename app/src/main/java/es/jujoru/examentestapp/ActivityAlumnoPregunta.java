package es.jujoru.examentestapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityAlumnoPregunta extends AppCompatActivity {
    TextView aae_tv_pregunta, aae_tv_res1,aae_tv_res2,aae_tv_res3;
    Button aae_btn_res1, aae_btn_res2, aae_btn_res3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_pregunta);
        cargarComponentes();
    }

    private void cargarComponentes(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aae_tv_pregunta = (TextView)findViewById(R.id.aae_tv_pregunta);
        aae_tv_res1 = (TextView)findViewById(R.id.aae_tv_res1);
        aae_tv_res2 = (TextView)findViewById(R.id.aae_tv_res2);
        aae_tv_res3 = (TextView)findViewById(R.id.aae_tv_res3);
        aae_btn_res1 = (Button)findViewById(R.id.aae_btn_res1);
        aae_btn_res2 = (Button)findViewById(R.id.aae_btn_res2);
        aae_btn_res3 = (Button)findViewById(R.id.aae_btn_res3);


        aae_btn_res1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        aae_btn_res2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        aae_btn_res3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
