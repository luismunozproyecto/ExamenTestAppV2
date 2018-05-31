package es.jujoru.examentestapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import Clases.Examen;
import Clases.Pregunta;

public class ActivityNuevoExamenInformacion extends AppCompatActivity {

    final static String EXTRA_EXAMEN = "EXAMEN";
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";


    FloatingActionButton fab;
    TextView nei_titulo;
    EditText anei_et_nombre, anei_et_asignatura,anei_et_tema,
            anei_et_fecha, anei_et_hora,anei_et_fecha_activacion,anei_et_hora_activacion, anei_et_argumentario;

    Examen examen;
    String nombreUsuario="luis";
    //FECHA
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_examen_informacion);
        cargarComponentes();

        
        
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
    private void cargarComponentes(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nei_titulo = (TextView)findViewById(R.id.nei_titulo);
        anei_et_nombre = (EditText)findViewById(R.id.anei_et_nombre);
        anei_et_asignatura = (EditText)findViewById(R.id.anei_et_asignatura);
        anei_et_tema = (EditText)findViewById(R.id.anei_et_tema);
        anei_et_fecha = (EditText)findViewById(R.id.anei_et_fecha);
        anei_et_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha(anei_et_fecha);
            }
        });
        anei_et_hora = (EditText)findViewById(R.id.anei_et_hora);
        anei_et_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora(anei_et_hora);
            }
        });
        anei_et_fecha_activacion = (EditText)findViewById(R.id.anei_et_fecha_activacion);
        anei_et_fecha_activacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha(anei_et_fecha_activacion);
            }
        });
        anei_et_hora_activacion = (EditText)findViewById(R.id.anei_et_hora_activacion);
        anei_et_hora_activacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerHora(anei_et_hora_activacion);
            }
        });


        anei_et_argumentario = (EditText)findViewById(R.id.anei_et_argumentario);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = anei_et_nombre.getText().toString();
                String asignatura = anei_et_asignatura.getText().toString();
                String tema = anei_et_tema.getText().toString();
                String fecha = anei_et_fecha.getText().toString();
                String hora = anei_et_hora.getText().toString();
                String fecha_activacion = anei_et_fecha_activacion.getText().toString();
                String hora_activacion =  anei_et_hora_activacion.getText().toString();
                String argumentario = anei_et_argumentario.getText().toString();

                if(nombre.equals("") || fecha.equals("") || hora.equals("") || fecha_activacion.equals("") || hora_activacion.equals("")){
                    Snackbar.make(getCurrentFocus(),"Debes de rellenar un nombre y las fechas/horas", Snackbar.LENGTH_LONG).show();

                }else{

                    examen = new Examen(nombre,nombreUsuario,asignatura, tema, fecha,hora, fecha_activacion,hora_activacion,argumentario);

                    Intent i=new Intent(getApplicationContext(), ActivityNuevoExamenConfiguracion.class);
                    i.putExtra(EXTRA_EXAMEN, examen);
                    startActivity(i);
                }
            }
        });

    }
}
