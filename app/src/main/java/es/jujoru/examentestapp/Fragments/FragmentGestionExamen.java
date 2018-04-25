package es.jujoru.examentestapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Clases.Examen;
import es.jujoru.examentestapp.ActivityGestionExamen;
import es.jujoru.examentestapp.Adapters.AdapterExamen;
import es.jujoru.examentestapp.R;


public class FragmentGestionExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Examen> examenes = new ArrayList<>();
    RecyclerView rvExamen;

    public FragmentGestionExamen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGestionExamen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGestionExamen newInstance(String param1, String param2) {
        FragmentGestionExamen fragment = new FragmentGestionExamen();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gestion_examen, container, false);
        rvExamen = (RecyclerView)view.findViewById(R.id.rvExamen);
        examenes.add(new Examen("Examen 1","2018-01-01","Asignatura 1"));
        examenes.add(new Examen("Examen 2","2018-02-01","Asignatura 2"));
        examenes.add(new Examen("Examen 3","2018-03-01","Asignatura 3"));

        rvExamen.setHasFixedSize(true);
        rvExamen.setLayoutManager(new LinearLayoutManager(getActivity()));

        AdapterExamen adapter = new AdapterExamen(examenes);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Examen ex = obtenerExamen(rvExamen.getChildAdapterPosition(v));
                Intent i =new Intent(getContext(), ActivityGestionExamen.class);
                startActivity(i);
            }
        });
        rvExamen.setAdapter(adapter);


        return view;

    }

    private Examen obtenerExamen(int position){
        return examenes.get(position);
    }








}
