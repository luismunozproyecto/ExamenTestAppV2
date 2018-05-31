package es.jujoru.examentestapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Clases.Usuario;
import Controladores.ControladorLoginRegistro;

public class ActivityRegistro extends AppCompatActivity {
    private static final String EXTRA_USUARIO="USUARIO";
    EditText etUsuario, etPassword, etPassword2, etEmail, etDNI;
    DatabaseReference dbRef;
    Usuario u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarComponentes();

    }

    private void cargarComponentes(){
        etUsuario = (EditText)findViewById(R.id.ra_et_usuario);
        etPassword = (EditText)findViewById(R.id.ra_et_password);
        etPassword2 = (EditText)findViewById(R.id.ra_et_password2);
        etEmail = (EditText)findViewById(R.id.ra_et_email);
        etDNI = (EditText)findViewById(R.id.ra_et_dni);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickRegistro(View view){
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        String password2 = etPassword2.getText().toString();
        String email = etEmail.getText().toString();
        String dni = etDNI.getText().toString();
        List<String> valores = new ArrayList<>();
        Collections.addAll(valores, usuario, password,password2,email,dni);

        if(ControladorLoginRegistro.esVacio(valores)){
            Snackbar.make(view, R.string.gen_campos_vacios, Snackbar.LENGTH_LONG).show();
        }else{
            if(!ControladorLoginRegistro.passwordIguales(password,password2)){
                Snackbar.make(view, R.string.gen_passwords_diferentes, Snackbar.LENGTH_LONG).show();
            }else{
                if(!ControladorLoginRegistro.passwordFormato(password)){
                    Snackbar.make(view, R.string.gen_passwords_formato, Snackbar.LENGTH_LONG).show();
                }else{
                    if(!ControladorLoginRegistro.emailValido(email)){
                        Snackbar.make(view, R.string.gen_email_no_valido, Snackbar.LENGTH_LONG).show();
                    }else{
                        u = new Usuario(usuario,email,password,dni,"default.png",0);
                        dbRef = FirebaseDatabase.getInstance().getReference()
                                .child("creator/usuarios");
                        //  String clave = dbRef.push().getKey();
                        dbRef.child(u.getUsuario()).setValue(u, new DatabaseReference.CompletionListener(){
                            public void onComplete(DatabaseError error, DatabaseReference ref) {
                                if(error == null) {
                                    Toast.makeText(getApplicationContext(),
                                            "Usuario creado",
                                            Toast.LENGTH_LONG).show();

                                    SharedPreferences prefs =
                                            getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("nombre", u.getUsuario());
                                    editor.commit();

                                    Intent i=new Intent(getApplicationContext(), ActivityMenuPrincipal.class);
                                    i.putExtra(EXTRA_USUARIO, u);
                                    startActivity(i);
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),
                                            "No se ha podido crear el usuario",
                                            Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                    }
                }

            }
        }
    }
}
