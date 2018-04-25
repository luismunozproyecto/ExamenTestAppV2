package es.jujoru.examentestapp.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Clases.Pregunta;
import es.jujoru.examentestapp.R;


public class FragmentTestExamen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lvPreguntas;
    public FragmentTestExamen() {
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
    public static FragmentTestExamen newInstance(String param1, String param2) {
        FragmentTestExamen fragment = new FragmentTestExamen();
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


        String[] datos =
                new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, datos);

        lvPreguntas.setAdapter(adaptador);
        lvPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarOpciones();
            }
        });
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

    private void mostrarMenuPregunta(String opcion) {


        switch (opcion){
            case "Eliminar":
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("¿Estás seguro de eliminar esta pregunta?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

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
                Toast.makeText(getContext(), "Ver",Toast.LENGTH_LONG).show();
            break;
            case "Editar":
                Toast.makeText(getContext(), "Editar",Toast.LENGTH_LONG).show();
            break;

        }

    }
    private ArrayList<Pregunta> testPreguntas(){

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta(1,"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500",null,""));
        preguntas.add(new Pregunta(2,"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500",null,""));
        preguntas.add(new Pregunta(3,"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500",null,""));
        preguntas.add(new Pregunta(4,"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500",null,""));
        preguntas.add(new Pregunta(5,"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500",null,""));
        preguntas.add(new Pregunta(6,"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500",null,""));

        return preguntas;
    }
}