package es.jujoru.examentestapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import Clases.Examen;
import es.jujoru.examentestapp.Fragments.FragmentGestionExamen;
import es.jujoru.examentestapp.Fragments.FragmentInformacionExamen;

public class ActivityEditarInformacionExamen extends AppCompatActivity {
    TextView eie_titulo;
    EditText aeie_et_nombre, aeie_et_asignatura, aeie_et_tema,
            aeie_et_fecha, aeie_et_hora, aeie_et_fecha_activacion,aeie_et_hora_activacion, aeie_et_argumentario;

    Examen examen;
    final static String EXTRA_EXAMEN = "EXAMEN";
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    //FECHA
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpFromSameTask(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cargarComponentes() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        eie_titulo = (TextView) findViewById(R.id.eie_titulo);
        eie_titulo.setText("" + examen.getNombre());
        aeie_et_nombre = (EditText) findViewById(R.id.aeie_et_nombre);
        aeie_et_nombre.setText("" + examen.getNombre());
        aeie_et_asignatura = (EditText) findViewById(R.id.aeie_et_asignatura);
        aeie_et_asignatura.setText("" + examen.getAsignatura());
        aeie_et_tema = (EditText) findViewById(R.id.aeie_et_tema);
        aeie_et_tema.setText("" + examen.getTema());
        aeie_et_fecha = (EditText) findViewById(R.id.aeie_et_fecha);
        aeie_et_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
obtenerFecha(aeie_et_fecha);
            }
        });
        aeie_et_fecha.setText("" + examen.getFecha());
        aeie_et_hora = (EditText) findViewById(R.id.aeie_et_hora);
        aeie_et_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
obtenerHora(aeie_et_hora);
            }
        });
        aeie_et_hora.setText("" + examen.getHora());
        aeie_et_fecha_activacion = (EditText) findViewById(R.id.aeie_et_fecha_activacion);
        aeie_et_fecha_activacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha(aeie_et_fecha_activacion);
            }
        });
        aeie_et_fecha_activacion.setText("" + examen.getFecha_activacion());
        aeie_et_hora_activacion = (EditText) findViewById(R.id.aeie_et_hora_activacion);
        aeie_et_hora_activacion.setText("" + examen.getHora_activacion());
        aeie_et_hora_activacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora(aeie_et_hora_activacion);
            }
        });
        aeie_et_argumentario = (EditText) findViewById(R.id.aeie_et_argumentario);
        aeie_et_argumentario.setText("" + examen.getArgumentario());


    }
    private void obtenerHora(final EditText et){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario

                //Muestro la hora con el formato deseado
                et.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }
    private void obtenerFecha(final EditText et){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                et.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

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
