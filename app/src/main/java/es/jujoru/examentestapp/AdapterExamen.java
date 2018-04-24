package es.jujoru.examentestapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Clases.Examen;

/**
 * Created by Nino Ruano on 17/04/2018.
 */

public class AdapterExamen extends RecyclerView.Adapter<ViewHolderExamen>
        implements View.OnClickListener {


    private ArrayList<Examen> datos;
    private View.OnClickListener listener;


    public AdapterExamen(ArrayList<Examen> datos) {
        this.datos = datos;
    }

    @Override
    public ViewHolderExamen onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_examen, viewGroup, false);

        itemView.setOnClickListener(this);
        ViewHolderExamen tvh = new ViewHolderExamen(itemView,viewGroup.getContext());

        return tvh;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderExamen viewHolder, int pos) {
        Examen item = datos.get(pos);
        viewHolder.bindExamen(item);
    }


    @Override
    public int getItemCount() {
        return datos.size();
    }

}
