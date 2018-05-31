package es.jujoru.examentestapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Clases.Examen;
import Clases.Pregunta;
import es.jujoru.examentestapp.ActivityEditarPregunta;
import es.jujoru.examentestapp.ActivityVerPregunta;
import es.jujoru.examentestapp.R;


public class FragmentTestExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    public static final String EXTRA_PARAM1 = "EXAMEN";
    public static final String EXTRA_PARAM2 = "PREGUNTA";
    public static final String EXTRA_POSITION = "POSITION";
    private static final String ARG_PARAM1 = "EXAMEN";
    private Examen examen;
    String preguntaSeleccionada;
    Pregunta p;
    int pos;
    DatabaseReference dbRef;
    ValueEventListener valueEventListener;
    ListView lvPreguntas;
    List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
    public FragmentTestExamen() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentTestExamen newInstance(Examen examen) {
        FragmentTestExamen fragment = new FragmentTestExamen();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, examen);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            examen = getArguments().getParcelable(ARG_PARAM1);
            listaPreguntas = examen.getPreguntas();

        }
    }
    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

     super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_pregunta, menu);


    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuPregunta1:
                Toast.makeText(getActivity(), "1",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuPregunta2:
                Toast.makeText(getActivity(), "2",Toast.LENGTH_LONG).show();
                return true;
            default:

                return super.onContextItemSelected(item);
        }
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_examen, container, false);
        lvPreguntas = (ListView)view.findViewById(R.id.te_lv_preguntas);
        obtenerPreguntas();


        //String[] datos = obtenerPreguntas();

        return view;



    }

    private void mostrarOpciones(){

        final String[] items = {"Eliminar", "Ver", "Editar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.te_mensaje_alert)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        mostrarMenuPregunta(items[item]);

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void eliminarPregunta(int pos){
        listaPreguntas.remove(pos);
    }
    private void obtenerPreguntas (){
        String[] preguntas = new String[listaPreguntas.size()];

        for (int i=0; i<preguntas.length; i++){

                preguntas[i] = listaPreguntas.get(i).getPregunta();


        }

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, preguntas);

        lvPreguntas.setAdapter(adaptador);
        lvPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  preguntaSeleccionada = parent.getItemAtPosition(position).toString();
                  p = listaPreguntas.get(position);
                  pos = position;
                  mostrarOpciones();
            }
        });
       // return preguntas;
    }
    private void mostrarMenuPregunta(String opcion) {


        switch (opcion){
            case "Eliminar":
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Estás seguro de eliminar esta pregunta?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                 p = listaPreguntas.get(pos);
                                dbRef = FirebaseDatabase.getInstance().getReference()
                                        .child("creator/examenes/"+p.getId_examen()+"/preguntas");


                                dbRef.child(pos+"").removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError error, DatabaseReference databaseReference) {
                                        if(error == null) {
                                            Snackbar.make(getView(),"Se eliminó la pregunta correctamente", Snackbar.LENGTH_LONG).show();
                                            eliminarPregunta(pos);
                                            obtenerPreguntas();

                                        }else {
                                            Snackbar.make(getView(),"La pregunta no se ha podido eliminar", Snackbar.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            break;
            case "Ver":

                Intent iv = new Intent(getActivity(), ActivityVerPregunta.class );
                iv.putExtra(EXTRA_PARAM2,p);
                startActivity(iv);
            break;
            case "Editar":
                Intent ie = new Intent(getActivity(), ActivityEditarPregunta.class );
                ie.putExtra(EXTRA_PARAM2,p);
                ie.putExtra(EXTRA_PARAM1,examen);
                ie.putExtra(EXTRA_POSITION,pos);


                startActivity(ie);
            break;

        }

    }





}