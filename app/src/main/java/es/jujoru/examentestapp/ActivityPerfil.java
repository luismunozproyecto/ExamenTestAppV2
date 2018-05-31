package es.jujoru.examentestapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Clases.Usuario;
import Controladores.ControladorLoginRegistro;

public class ActivityPerfil extends AppCompatActivity {

    EditText etUsuario, etPassword, etPassword2, etEmail, etDNI;
    ImageView imageUsuario;
    String mCurrentPhotoPath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private final int PICK_IMAGE_REQUEST = 71;
    static final String EXTRA_USUARIO = "USUARIO";
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;
    String urlImagen;
    Usuario u;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cargarComponentes();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                u.setAvatar(u.getUsuario()+".png");
                                dbRef.child(u.getUsuario()).setValue(u, new DatabaseReference.CompletionListener(){
                                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                                        if(error == null) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Usuario modificado",
                                                    Toast.LENGTH_LONG).show();
                                            uploadImage();
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
                                                    "No se ha podido modificar el usuario",
                                                    Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                            }
                        }

                    }
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    
    private void cargarComponentes() {

        //Firebase
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        etUsuario = (EditText) findViewById(R.id.pf_et_usuario);
        etUsuario.setEnabled(false);
        etPassword = (EditText) findViewById(R.id.pf_et_password);
        etPassword2 = (EditText) findViewById(R.id.pf_et_password2);
        etEmail = (EditText) findViewById(R.id.pf_et_email);
        etDNI = (EditText) findViewById(R.id.pf_et_dni);
        etDNI.setEnabled(false);
        imageUsuario = (ImageView)findViewById(R.id.pf_img_usuario);

        Bundle b =getIntent().getExtras();
        if(b!=null){
            u = b.getParcelable(ActivityMenuPrincipal.EXTRA_USUARIO);
            etUsuario.setText(u.getUsuario());
            etDNI.setText(u.getDni());
            etPassword.setText(u.getPassword());
            etPassword2.setText(u.getPassword());
            etEmail.setText(u.getEmail());
            imageUsuario();


        }
    }
    private void imageUsuario(){
        storageReference.child("avatares/"+u.getAvatar()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri.toString()).into(imageUsuario);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    public void onClickFotoPerfil(View view){
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
      /*   Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }*/
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
  // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void uploadImage()
    {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            urlImagen = u.getAvatar().toString();

            StorageReference ref = storageReference.child("avatares/"+urlImagen);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            progressDialog.dismiss();
                            //Toast.makeText(ActivityEventsNewEvent.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Error al subir la imagen "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     /*   if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageUsuario.setImageBitmap(imageBitmap);
        }*/

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null )
        {
            filePath = data.getData();

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageUsuario.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }



    }

}
