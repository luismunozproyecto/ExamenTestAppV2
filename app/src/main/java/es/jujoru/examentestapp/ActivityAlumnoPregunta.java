package es.jujoru.examentestapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Clases.Examen;
import Clases.Examen_Nota;
import Clases.Nota;
import Clases.Pregunta;
import es.jujoru.examentestapp.Fragments.FragmentAlumnoMisExamenes;

public class ActivityAlumnoPregunta extends AppCompatActivity {
    TextView aae_tv_titulo, aae_tv_pregunta, aae_tv_res1, aae_tv_res2, aae_tv_res3;
    Button aae_btn_res1, aae_btn_res2, aae_btn_res3,aae_btn_siguiente,aae_btn_anterior,aap_btn_guardarexamen;
    Examen e;
    int index =0;
    int max = 0;
    String usuario;
    double nota;
    String mensajenota;
    List<Pregunta> preguntas = null;
    HashMap<Integer,String> res = null;
    private static String EXTRA_EXAMEN = "EXAMEN";

    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_pregunta);
        cargarComponentes();
    }

    private void cargarComponentes() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();

        if (b != null) {

            e = b.getParcelable(FragmentAlumnoMisExamenes.EXTRA_EXAMEN);
            preguntas = e.getPreguntas();
            max = preguntas.size();
            res = new HashMap<>();
            usuario = obtenerUSuario();
            aae_tv_titulo = (TextView) findViewById(R.id.aae_tv_titulo);
            aae_tv_titulo.setText("Pregunta "+(index+1)+"/"+max);
            aae_tv_pregunta = (TextView) findViewById(R.id.aae_tv_pregunta);
            aae_tv_pregunta.setText(preguntas.get(index).getPregunta());
            aae_tv_res1 = (TextView) findViewById(R.id.aae_tv_res1);
            aae_tv_res1.setText(preguntas.get(index).getRespuestas().get(0));
            aae_tv_res2 = (TextView) findViewById(R.id.aae_tv_res2);
            aae_tv_res2.setText(preguntas.get(index).getRespuestas().get(1));
            aae_tv_res3 = (TextView) findViewById(R.id.aae_tv_res3);
            aae_tv_res3.setText(preguntas.get(index).getRespuestas().get(2));

            aae_btn_res1 = (Button) findViewById(R.id.aae_btn_res1);
            aae_btn_res2 = (Button) findViewById(R.id.aae_btn_res2);
            aae_btn_res3 = (Button) findViewById(R.id.aae_btn_res3);

            aae_btn_anterior = (Button) findViewById(R.id.aae_btn_anterior);
            aae_btn_siguiente = (Button) findViewById(R.id.aae_btn_siguiente);

            aap_btn_guardarexamen = (Button) findViewById(R.id.aap_btn_guardarexamen);


            aae_btn_res1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    res.put(index, preguntas.get(index).getRespuestas().get(0));
                    marcarBoton(aae_btn_res1);
                    aae_btn_res2.setEnabled(true);
                    aae_btn_res3.setEnabled(true);
                }
            });
            aae_btn_res2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    res.put(index, preguntas.get(index).getRespuestas().get(1));
                    marcarBoton(aae_btn_res2);
                    aae_btn_res1.setEnabled(true);
                    aae_btn_res3.setEnabled(true);
                }
            });
            aae_btn_res3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    res.put(index, preguntas.get(index).getRespuestas().get(2));
                    marcarBoton(aae_btn_res3);
                    aae_btn_res2.setEnabled(true);
                    aae_btn_res1.setEnabled(true);
                }
            });



            aae_btn_anterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(index>0){
                        index--;
                        cargarPregunta();
                    }
                }
            });
            aae_btn_siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(index<max-1){
                        index++;
                        cargarPregunta();
                    }
                }
            });

            aap_btn_guardarexamen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAlumnoPregunta.this);
                    builder.setMessage("¿Estás seguro de finalizar el examen? Se calculará y guardará tu nota de forma instantanea")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                      nota = calcularNota();

                                    guardarNota();

                                    Intent i = new Intent(getApplicationContext(), ActivityAlumnoNotaFinal.class);
                                    i.putExtra("NOTA",nota);
                                    i.putExtra("MENSAJE",mensajenota);
                                    startActivity(i);
                                    finish();
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


    private void cargarPregunta(){
        aae_tv_titulo.setText("Pregunta "+(index+1)+"/"+max);
        aae_tv_pregunta.setText(""+preguntas.get(index).getPregunta());
        aae_tv_res1.setText(""+preguntas.get(index).getRespuestas().get(0));
        aae_tv_res2.setText(""+preguntas.get(index).getRespuestas().get(1));
        aae_tv_res3.setText(""+preguntas.get(index).getRespuestas().get(2));
        descargarBotones();

    }

    private void marcarBoton(Button bt){
        bt.setEnabled(false);

    }
    private void descargarBotones(){
        aae_btn_res1.setEnabled(true);
        aae_btn_res2.setEnabled(true);
        aae_btn_res3.setEnabled(true);

        if(res.containsKey(index)){
            String opcionMarcada = res.get(index);

            if(opcionMarcada.equals(preguntas.get(index).getRespuestas().get(0))){
                aae_btn_res1.setEnabled(false);
            }else if(opcionMarcada.equals(preguntas.get(index).getRespuestas().get(1))){
                aae_btn_res2.setEnabled(false);
            }else if(opcionMarcada.equals(preguntas.get(index).getRespuestas().get(2))){
                aae_btn_res3.setEnabled(false);
            }
        }
    }

    private double calcularNota(){

        double valor_no_contesta=e.getValor_blanco();
        double valor_fallo=e.getValor_fallo();
        double valor_acierto=e.getValor_acierto();
        double nota_corte=e.getNota_corte();

        double nota=0;
        Iterator it = res.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int key = (int) pair.getKey();
            String opcion = (String) pair.getValue();

            String opcion_correcta = preguntas.get(key).getRespuesta_correcta();
            if(opcion.equals(opcion_correcta)){
                nota+=valor_acierto;
            }else{
                nota-=valor_fallo;
            }
            it.remove();
        }

        if(valor_no_contesta!=0){
            double nocontestadas =valor_no_contesta * (max-res.size());
            nota-=nocontestadas;
        }

        if(nota>=nota_corte){
            mensajenota="APROBADO";
        }else{
            mensajenota="SUSPENSO";
        }

        return nota;

    }
    private List<Nota> obtenerNotas(){
        List<Nota> lnotas = new ArrayList<>();


        return lnotas;
    }
    private void guardarNota(){
        Nota n = new Nota(usuario, nota);
        List<Nota> lnotas = new ArrayList<>();
        lnotas.add(n);
        Examen_Nota en =new Examen_Nota(e.getId(), lnotas);

        dbRef = FirebaseDatabase.getInstance().getReference()
                .child("creator/notas_examenes");
        dbRef.child(e.getId()).setValue(en, new DatabaseReference.CompletionListener(){
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if(error == null) {
                    Toast.makeText(getApplicationContext(),
                            "Nota guardada",
                            Toast.LENGTH_LONG).show();


                }else {
                    Toast.makeText(getApplicationContext(),
                            "No se ha podido crear el usuario",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    private String obtenerUSuario(){
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);

        String user = prefs.getString("nombre", "admin111");

        return user;
    }
}
