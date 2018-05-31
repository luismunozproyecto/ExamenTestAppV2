package es.jujoru.examentestapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Clases.Usuario;
import Controladores.ControladorLoginRegistro;

public class ActivityLogin extends AppCompatActivity {

    static final String EXTRA_USUARIO = "USUARIO";
    EditText etUsuario, etPassword;
    Usuario u=null;

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarComponentes();
    }

    private void cargarComponentes(){

        etUsuario = (EditText)findViewById(R.id.la_et_usuario);
        etPassword = (EditText)findViewById(R.id.la_et_password);

    }

    public void onClickIrRegistro(View view){
        Intent i=new Intent(getApplicationContext(), ActivityRegistro.class);
        startActivity(i);
    }

    public void onClickLogin(View view){

        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        List<String> valores = new ArrayList<>();
        Collections.addAll(valores, usuario, password);

        if(ControladorLoginRegistro.esVacio(valores)){
            Snackbar.make(view, R.string.gen_campos_vacios, Snackbar.LENGTH_LONG).show();
        }else{
             // u = new Usuario("luis", "luis@gmail.com", "luis", "123123", "avatar",0);
            //  u = new Usuario("profesor", "profesor@gmail.com", "profesor", "123123", "avatar",1);
            dbRef = FirebaseDatabase.getInstance().getReference()
                    .child("creator/usuarios/"+usuario);

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    u = dataSnapshot.getValue(Usuario.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("ActivityLogin","DATABASE ERROR");
                }
            };
            dbRef.addValueEventListener(valueEventListener);

            if(u!=null){
                SharedPreferences prefs =
                        getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", u.getUsuario());
                editor.commit();

                Intent i=new Intent(getApplicationContext(), ActivityMenuPrincipal.class);
                i.putExtra(EXTRA_USUARIO, u);
                startActivity(i);
                finish();
            }else{
                Snackbar.make(view, "Usuario/Password erroneos", Snackbar.LENGTH_LONG).show();
            }

        }
    }
}
