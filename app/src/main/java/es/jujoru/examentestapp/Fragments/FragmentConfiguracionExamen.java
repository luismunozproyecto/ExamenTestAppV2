package es.jujoru.examentestapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import Clases.Examen;
import es.jujoru.examentestapp.ActivityEditarConfiguracionExamen;
import es.jujoru.examentestapp.R;


public class FragmentConfiguracionExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String EXTRA_PARAM1 = "EXAMEN";
    private static final String ARG_PARAM1 = "EXAMEN";
    private Examen examen;


    public FragmentConfiguracionExamen() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentConfiguracionExamen newInstance(Examen examen) {
        FragmentConfiguracionExamen fragment = new FragmentConfiguracionExamen();
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
        View view = inflater.inflate(R.layout.fragment_configuracion_examen, container, false);

        TextView tvCeTitulo = (TextView)view.findViewById(R.id.ce_titulo);
        tvCeTitulo.setText(examen.getNombre());
        TextView tvDuracion = (TextView)view.findViewById(R.id.ce_tv_duracion);
        tvDuracion.setText(tvDuracion.getText()+" "+examen.getDuracion());
        TextView tvTiempoLimite = (TextView)view.findViewById(R.id.ce_tv_tiempo_limite);
        if(examen.isLimite_tiempo()==1){
            tvTiempoLimite.setText("Limite: Si");
        }else{
            tvTiempoLimite.setText("Limite: No");
        }

        TextView tvNumeroPreguntas = (TextView)view.findViewById(R.id.ce_numero_preguntas);
        tvNumeroPreguntas.setText(tvNumeroPreguntas.getText()+" "+examen.getNumero_preguntas());
        TextView tvValorBlanco = (TextView)view.findViewById(R.id.ce_tv_valor_blanco);
        tvValorBlanco.setText(tvValorBlanco.getText()+" "+examen.getValor_blanco());
        TextView tvValorAcierto = (TextView)view.findViewById(R.id.ce_tv_valor_acierto);
        tvValorAcierto.setText(tvValorAcierto.getText()+" "+examen.getValor_acierto());
        TextView tvValorFallo = (TextView)view.findViewById(R.id.ce_tv_valor_fallo);
        tvValorFallo.setText(tvValorFallo.getText()+" "+examen.getValor_fallo());
        TextView tvNotaCorte = (TextView)view.findViewById(R.id.ce_tv_nota_corte);
        tvNotaCorte.setText(tvNotaCorte.getText()+" "+examen.getNota_corte());
        Button btEditar =(Button)view.findViewById(R.id.fce_btn_editaro);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ActivityEditarConfiguracionExamen.class);
                i.putExtra(EXTRA_PARAM1, examen);
                startActivity(i);
            }
        });
       /* FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabInfoConfig);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getActivity(), ActivityEditarConfiguracionExamen.class);
                startActivity(i);
            }
        });*/
        return view;

    }

}