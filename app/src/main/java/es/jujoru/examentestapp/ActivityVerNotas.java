package es.jujoru.examentestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Clases.Examen;
import Clases.Examen_Nota;
import Clases.Nota;
import es.jujoru.examentestapp.Adapters.AdapterExamen;
import es.jujoru.examentestapp.Fragments.FragmentVerNotas;

public class ActivityVerNotas extends AppCompatActivity {

    ListView listView;
    Examen e;
    String idExamen;
    List<Nota> notas = new ArrayList<>();
    Examen_Nota examen_nota = null;
    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        cargarComponentes();
    }


    private void cargarComponentes(){

        Bundle b= getIntent().getExtras();

        if(b!=null){
            e = b.getParcelable(FragmentVerNotas.EXTRA_EXAMEN);
            idExamen=e.getId();
        }
        listView = (ListView)findViewById(R.id.avn_lv);
        cargarNotasFireBase();

    }


    /*==================================

            METODOS DE FIREBASE

    ======================================
     */

    private void cargarNotasFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("creator/notas_examenes/"+idExamen);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Examen_Nota examen_nota = dataSnapshot.getValue(Examen_Nota.class);
                notas = examen_nota.getNotas();
                cargarListViewNotas();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ActivityParte1","DATABASE ERROR");
            }
        };
        databaseReference.addValueEventListener(valueEventListener);


    }

    private void cargarListViewNotas (){


        String [] array =setArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
    }

    private String[] setArray(){
        String [] array = new String[notas.size()];
        int i=0;
        for (Nota n: notas){
            String linea = n.getUsuario()+": "+n.getNota();
            array[i]=linea;
            i++;
        }
        return array;
    }



}
