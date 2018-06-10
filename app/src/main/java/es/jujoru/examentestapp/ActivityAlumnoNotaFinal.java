package es.jujoru.examentestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityAlumnoNotaFinal extends AppCompatActivity {

    Button btnFinalizar;
    TextView tvNota, tvMensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_nota_final);
        declararElementos();
    }

    private void declararElementos(){
        btnFinalizar = (Button) findViewById(R.id.aavn_btnfinalizar);
        tvNota = (TextView) findViewById(R.id.avnf_nota);
        tvMensaje = (TextView) findViewById(R.id.avnf_mensaje);

        Bundle b=getIntent().getExtras();

        if(b!=null){
            String mensaje = b.getString("MENSAJE");
            double nota = b.getDouble("NOTA");

            tvNota.setText("NOTA: "+nota);
            tvMensaje.setText("ESTAS "+mensaje);

        }

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), ActivityMenuPrincipal.class);
                startActivity(i);
                finish();
            }
        });
    }
}
