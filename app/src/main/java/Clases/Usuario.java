package Clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nino Ruano on 11/04/2018.
 */

public class Usuario implements Parcelable {

    private String usuario;
    private String email;
    private String password;
    private String dni;
    private String avatar;
    private int es_profesor;


    public Usuario(String usuario, String email, String password, String dni, String avatar, int es_profesor) {
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.avatar = avatar;
        this.es_profesor=es_profesor;
    }

    public Usuario() {

    }

    public Usuario(Parcel p) {
       this.usuario = p.readString();
       this.email = p.readString();
       this.password = p.readString();
       this.dni = p.readString();
       this.avatar = p.readString();
       this.es_profesor = p.readInt();
    }


    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public int getProfesor() {
        return es_profesor;
    }

    public void setProfesor(int es_profesor) {
        this.es_profesor = es_profesor;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.usuario);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.dni);
        dest.writeString(this.avatar);
        dest.writeInt(this.es_profesor);

    }
}
