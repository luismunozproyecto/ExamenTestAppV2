package Clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Nota implements Parcelable{

    String usuario;
    double nota;

    public Nota() {

    }

    public Nota(String usuario, double nota) {
        this.usuario = usuario;
        this.nota = nota;
    }

    protected Nota(Parcel in) {
        usuario = in.readString();
        nota = in.readDouble();
    }

    public static final Creator<Nota> CREATOR = new Creator<Nota>() {
        @Override
        public Nota createFromParcel(Parcel in) {
            return new Nota(in);
        }

        @Override
        public Nota[] newArray(int size) {
            return new Nota[size];
        }
    };

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeDouble(nota);
    }
}
