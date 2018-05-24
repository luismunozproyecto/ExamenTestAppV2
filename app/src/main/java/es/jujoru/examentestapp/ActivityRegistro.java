package es.jujoru.examentestapp;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Clases.Usuario;
import Controladores.ControladorLoginRegistro;

public class ActivityRegistro extends AppCompatActivity {

    EditText etUsuario, etPassword, etPassword2, etEmail, etDNI;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cargarComponentes();
        mAuth = FirebaseAuth.getInstance();
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
                if(ControladorLoginRegistro.emailValido(email)){
                    Snackbar.make(view, R.string.gen_email_no_valido, Snackbar.LENGTH_LONG).show();
                }else{
                    Usuario u = new Usuario(usuario,email,password,dni,"default.png",0);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getApplicationContext(), "Authentication bien.",
                                                Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("BB", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
            }
        }
    }
}
