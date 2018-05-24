package es.jujoru.examentestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import Clases.Usuario;
import es.jujoru.examentestapp.Fragments.FragmentActivarExamen;
import es.jujoru.examentestapp.Fragments.FragmentAlumnoActivarExamen;
import es.jujoru.examentestapp.Fragments.FragmentAlumnoMisExamenes;
import es.jujoru.examentestapp.Fragments.FragmentGestionExamen;
import es.jujoru.examentestapp.Fragments.FragmentVerNotas;

public class ActivityMenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Usuario u =null;
    int esAlumno=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b= getIntent().getExtras();
        if(b!=null){
           u = b.getParcelable(ActivityLogin.EXTRA_USUARIO);
           esAlumno = u.getEs_profesor();
        }

        if(esAlumno == 1){
            setContentView(R.layout.activity_menu_principal_alumno);
        }else{
            setContentView(R.layout.activity_menu_principal);
        }
        //setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityNuevoExamenInformacion.class);
                startActivity(i);
            }
        });

        if(esAlumno == 1){
            fab.setVisibility(View.INVISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro de salir y cerrar la aplicación?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ActivityMenuPrincipal.super.onBackPressed();
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
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        String titulo = getString(R.string.app_name);
        if (id == R.id.nav_gestion_examen) {
            fragment = new FragmentGestionExamen();
            titulo = getString(R.string.ge_titulo);
        } else if (id == R.id.nav_activar_examen) {
            fragment = new FragmentActivarExamen();
            titulo = getString(R.string.ae_titulo);
        } else if (id == R.id.nav_ver_notas) {
            fragment = new FragmentVerNotas();
            titulo = getString(R.string.vn_titulo);
        } else if (id == R.id.nav_perfil) {
            Intent i = new Intent(getApplicationContext(), ActivityPerfil.class);
            startActivity(i);
        } else if (id == R.id.nav_cerrar_sesion) {
            Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
            startActivity(i);
            finish();
        }else if(id ==R.id.nav_activar_examen_alumno){
            fragment = new FragmentAlumnoActivarExamen();
            titulo = getString(R.string.faae_titulo);
        }else if(id ==R.id.nav_mis_examenes){
            fragment = new FragmentAlumnoMisExamenes();
            titulo = getString(R.string.faae_titulo);
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titulo);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
