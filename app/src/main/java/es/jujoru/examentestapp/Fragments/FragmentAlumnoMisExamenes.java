package es.jujoru.examentestapp.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import es.jujoru.examentestapp.ActivityAlumnoExamen;
import es.jujoru.examentestapp.ActivityAlumnoPregunta;
import es.jujoru.examentestapp.ActivityGestionExamen;
import es.jujoru.examentestapp.Adapters.AdapterExamen;
import es.jujoru.examentestapp.R;

public class FragmentAlumnoMisExamenes  extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String EXTRA_EXAMEN = "EXAMEN";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;

    ArrayList<Examen> examenes = new ArrayList<>();
    RecyclerView rvExamen;
    public FragmentAlumnoMisExamenes() {
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
    public static FragmentAlumnoMisExamenes newInstance(String param1, String param2) {
        FragmentAlumnoMisExamenes fragment = new FragmentAlumnoMisExamenes();
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

        View view = inflater.inflate(R.layout.fragment_alumno_mis_examenes, container, false);
        rvExamen = (RecyclerView)view.findViewById(R.id.fame_rvExamen);


        rvExamen.setHasFixedSize(true);
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

        Examen e = dataSnapshot.getValue(Examen.class);

        if(e.getActivo()==1){
            examenes.add(dataSnapshot.getValue(Examen.class));

            AdapterExamen adapter = new AdapterExamen(examenes);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Examen ex = obtenerExamen(rvExamen.getChildAdapterPosition(v));
                    Intent i =new Intent(getContext(), ActivityAlumnoPregunta.class);
                    i.putExtra(FragmentGestionExamen.EXTRA_EXAMEN, ex);
                    startActivity(i);
                }
            });
            rvExamen.setAdapter(adapter);
        }


    }


    private Examen obtenerExamen(int position){
        return examenes.get(position);
    }


}

