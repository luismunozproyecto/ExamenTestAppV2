package es.jujoru.examentestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentConfiguracionExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentConfiguracionExamen() {
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
    public static FragmentConfiguracionExamen newInstance(String param1, String param2) {
        FragmentConfiguracionExamen fragment = new FragmentConfiguracionExamen();
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
        View view = inflater.inflate(R.layout.fragment_configuracion_examen, container, false);

        TextView tvCeTitulo = (TextView)view.findViewById(R.id.ce_titulo);
        TextView tvDuracion = (TextView)view.findViewById(R.id.ce_tv_duracion);
        TextView tvTiempoLimite = (TextView)view.findViewById(R.id.ce_tv_tiempo_limite);
        TextView tvNumeroPreguntas = (TextView)view.findViewById(R.id.ce_numero_preguntas);
        TextView tvValorBlanco = (TextView)view.findViewById(R.id.ce_tv_valor_blanco);
        TextView tvValorAcierto = (TextView)view.findViewById(R.id.ce_tv_valor_acierto);
        TextView tvValorFallo = (TextView)view.findViewById(R.id.ce_tv_valor_fallo);
        TextView tvNotaCorte = (TextView)view.findViewById(R.id.ce_tv_nota_corte);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabInfoConfig);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getActivity(), ActivityEditarConfiguracionExamen.class);
                startActivity(i);
            }
        });
        return view;

    }

}