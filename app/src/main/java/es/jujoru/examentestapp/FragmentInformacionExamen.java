package es.jujoru.examentestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Clases.Examen;

/**
 * Created by Nino Ruano on 18/04/2018.
 */

public class FragmentInformacionExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentInformacionExamen() {
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
    public static FragmentInformacionExamen newInstance(String param1, String param2) {
        FragmentInformacionExamen fragment = new FragmentInformacionExamen();
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
        View view = inflater.inflate(R.layout.fragment_informacion_examen, container, false);

        TextView tvIcTitulo = (TextView)view.findViewById(R.id.ic_titulo);
        TextView tvIcAsignatura = (TextView)view.findViewById(R.id.ie_tv_asignatura);
        TextView tvIcTema = (TextView)view.findViewById(R.id.ie_tv_tema);
        TextView tvIcFecha = (TextView)view.findViewById(R.id.ie_tv_fecha);
        TextView tvIcHora = (TextView)view.findViewById(R.id.ie_tv_hora);
        TextView tvIcFechaActivacion = (TextView)view.findViewById(R.id.ie_tv_fecha_activacion);
        TextView tvIcHoraActivacion = (TextView)view.findViewById(R.id.ie_tv_hora_activacion);
        TextView tvIcArguemntario = (TextView)view.findViewById(R.id.ie_tv_argumentario);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i=new Intent(getActivity(), ActivityEditarInformacionExamen.class);
                    startActivity(i);

            }
        });

        return view;

    }

}