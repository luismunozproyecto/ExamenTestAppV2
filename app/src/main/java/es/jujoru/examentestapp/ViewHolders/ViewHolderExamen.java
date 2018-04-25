package es.jujoru.examentestapp.ViewHolders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Clases.Examen;
import es.jujoru.examentestapp.R;

/**
 * Created by Nino Ruano on 17/04/2018.
 */

public class ViewHolderExamen extends RecyclerView.ViewHolder {
    private View mView;
    TextView tvNombre, tvFecha, tvAsignatura;
    ImageView ivCabecera;
    private Context mContext;
    private GradientDrawable mGradientDrawable;
    Examen examen;

    public ViewHolderExamen(View itemView,  Context c) {
        super(itemView);
        this.mView = itemView;
        tvNombre = (TextView)itemView.findViewById(R.id.itemNombreExamen);
        tvFecha = (TextView)itemView.findViewById(R.id.itemFechaExamen);
        tvAsignatura = (TextView)itemView.findViewById(R.id.itemAsignaturaExamen);
        ivCabecera = (ImageView)itemView.findViewById(R.id.itemImagenCabecera);
this.mContext=c;


    }

    public void bindExamen(Examen e){
        examen=e;
        tvNombre.setText(e.getNombre());
        tvFecha.setText(e.getFecha());
        tvAsignatura.setText(e.getAsignatura());
        //Glide.with(mContext).load(R.drawable.item_fondo).placeholder(mGradientDrawable).into(ivCabecera);

    }




}
