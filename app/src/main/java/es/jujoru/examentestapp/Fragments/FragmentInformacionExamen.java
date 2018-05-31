package es.jujoru.examentestapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import Clases.Examen;
import es.jujoru.examentestapp.ActivityEditarInformacionExamen;
import es.jujoru.examentestapp.R;

/**
 * Created by Nino Ruano on 18/04/2018.
 */

public class FragmentInformacionExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "EXAMEN";
    public static final String EXTRA_PARAM1 = "EXAMEN";
    private Examen examen;



    public FragmentInformacionExamen() {
        // Required empty public constructor
    }




    public static FragmentInformacionExamen newInstance(Examen examen) {
        FragmentInformacionExamen fragment = new FragmentInformacionExamen();
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



        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_informacion_examen, container, false);

        if(examen!=null) {

            TextView tvIcTitulo = (TextView) view.findViewById(R.id.ic_titulo);
            tvIcTitulo.setText(examen.getNombre());
            TextView tvIcAsignatura = (TextView) view.findViewById(R.id.ie_tv_asignatura);
            tvIcAsignatura.setText(tvIcAsignatura.getText() + " " + examen.getAsignatura());
            TextView tvIcTema = (TextView) view.findViewById(R.id.ie_tv_tema);
            tvIcTema.setText(tvIcTema.getText() + " " + examen.getTema());
            TextView tvIcFecha = (TextView) view.findViewById(R.id.ie_tv_fecha);
            tvIcFecha.setText(tvIcFecha.getText() + " " + examen.getFecha());
            TextView tvIcHora = (TextView) view.findViewById(R.id.ie_tv_hora);
            tvIcHora.setText(tvIcHora.getText() + " " + examen.getHora());
            TextView tvIcFechaActivacion = (TextView) view.findViewById(R.id.ie_tv_fecha_activacion);
            tvIcFechaActivacion.setText(tvIcFechaActivacion.getText() + " " + examen.getFecha_activacion());
            TextView tvIcHoraActivacion = (TextView) view.findViewById(R.id.ie_tv_hora_activacion);
            tvIcHoraActivacion.setText(tvIcHoraActivacion.getText() + " " + examen.getHora_activacion());
            TextView tvIcArguemntario = (TextView) view.findViewById(R.id.ie_tv_argumentario);
            tvIcArguemntario.setText(tvIcArguemntario.getText() + " " + examen.getArgumentario());
            Button btEditar = (Button) view.findViewById(R.id.fie_btn_editar);
            btEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ActivityEditarInformacionExamen.class);
                    i.putExtra(EXTRA_PARAM1, examen);
                    startActivity(i);
                }
            });

        }

     /*   FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i=new Intent(getActivity(), ActivityEditarInformacionExamen.class);
                    startActivity(i);

            }
        });
*/
        return view;

    }

}