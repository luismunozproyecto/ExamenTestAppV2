package es.jujoru.examentestapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Clases.Examen;
import es.jujoru.examentestapp.ActivityGestionExamen;
import es.jujoru.examentestapp.ActivityMenuPrincipal;
import es.jujoru.examentestapp.Adapters.AdapterExamen;
import es.jujoru.examentestapp.R;

/**
 * Created by Nino Ruano on 13/04/2018.
 */

public class FragmentActivarExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int tipo=0;
    Examen eSeleccionado=null;
    ArrayList<Examen> examenes = new ArrayList<>();
    RecyclerView rvExamen;


    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;
    public FragmentActivarExamen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentActivarExamen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentActivarExamen newInstance(String param1, String param2) {
        FragmentActivarExamen fragment = new FragmentActivarExamen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_activar_examen, container, false);
        rvExamen = (RecyclerView)view.findViewById(R.id.fae_rvExamen);  rvExamen.setHasFixedSize(true);
        rvExamen.setLayoutManager(new LinearLayoutManager(getActivity()));

  cargarExamenesFireBase();


        return view;
    }
 /*==================================

            METODOS DE FIREBASE

    ======================================
     */

    private void cargarExamenesFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("creator/examenes");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshotExamenes: dataSnapshot.getChildren()) {

                    Examen e = dataSnapshotExamenes.getValue(Examen.class);
                    examenes.add(e);

                }

                examenes.clear();
                for (DataSnapshot dataSnapshotExamenes: dataSnapshot.getChildren()) {
                    cargarRecyclerViewExamen(dataSnapshotExamenes);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ActivityParte2","DATABASE ERROR");
            }
        };
        databaseReference.addValueEventListener(valueEventListener);


    }

    private void cargarRecyclerViewExamen (DataSnapshot dataSnapshot){

        examenes.add(dataSnapshot.getValue(Examen.class));


        AdapterExamen adapter = new AdapterExamen(examenes);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eSeleccionado = obtenerExamen(rvExamen.getChildAdapterPosition(v));
                final String mensaje;

                if(eSeleccionado.getActivo()==0){
                    mensaje =" activar ";
                    tipo = 1;
                }else{
                    mensaje =" desactivar ";
                    tipo=0;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Deseas "+mensaje+" el examen?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                eSeleccionado.setActivo(tipo);
                                databaseReference.child(eSeleccionado.getId()).setValue(eSeleccionado, new DatabaseReference.CompletionListener(){
                                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                                        if(error == null) {

                                            Snackbar.make(getView(),"Examen "+mensaje, Snackbar.LENGTH_LONG).show();
                                        }else {
                                            Snackbar.make(getView(),"No se ha podido "+mensaje+" el examen", Snackbar.LENGTH_LONG).show();

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
            }
        });
        rvExamen.setAdapter(adapter);

    }


    private Examen obtenerExamen(int position){
        return examenes.get(position);
    }





}
