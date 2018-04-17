package Clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nino Ruano on 17/04/2018.
 */

public class Examen implements Parcelable{

    int id;
    String nom_usuario;
    Usuario usuario;
    String asignatura;
    String tema;
    String fecha;
    String hora;
    String fecha_activacion;
    String hora_activacion;
    String argumentario;
    ArrayList<Pregunta> preguntas;
    //Atributos de configuracion
    int duracion;
    boolean limite_tiempo;
    int numero_preguntas;
    double valor_blanco;
    double valor_acierto;
    double valor_fallo;
    double nota_corte;

    public Examen(int id, String nom_usuario, Usuario usuario, String asignatura, String tema, String fecha, String hora, String fecha_activacion, String hora_activacion, String argumentario, ArrayList<Pregunta> preguntas, int duracion, boolean limite_tiempo, int numero_preguntas, double valor_blanco, double valor_acierto, double valor_fallo, double nota_corte) {
        this.id = id;
        this.nom_usuario = nom_usuario;
        this.usuario = usuario;
        this.asignatura = asignatura;
        this.tema = tema;
        this.fecha = fecha;
        this.hora = hora;
        this.fecha_activacion = fecha_activacion;
        this.hora_activacion = hora_activacion;
        this.argumentario = argumentario;
        this.preguntas = preguntas;
        this.duracion = duracion;
        this.limite_tiempo = limite_tiempo;
        this.numero_preguntas = numero_preguntas;
        this.valor_blanco = valor_blanco;
        this.valor_acierto = valor_acierto;
        this.valor_fallo = valor_fallo;
        this.nota_corte = nota_corte;

    }

    public Examen() {
    }

    protected Examen(Parcel in) {
        id = in.readInt();
        nom_usuario = in.readString();
        usuario = in.readParcelable(Usuario.class.getClassLoader());
        asignatura = in.readString();
        tema = in.readString();
        fecha = in.readString();
        hora = in.readString();
        fecha_activacion = in.readString();
        hora_activacion = in.readString();
        argumentario = in.readString();
        preguntas = in.createTypedArrayList(Pregunta.CREATOR);
        duracion = in.readInt();
        limite_tiempo = in.readByte() != 0;
        numero_preguntas = in.readInt();
        valor_blanco = in.readDouble();
        valor_acierto = in.readDouble();
        valor_fallo = in.readDouble();
        nota_corte = in.readDouble();
    }

    public static final Creator<Examen> CREATOR = new Creator<Examen>() {
        @Override
        public Examen createFromParcel(Parcel in) {
            return new Examen(in);
        }

        @Override
        public Examen[] newArray(int size) {
            return new Examen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom_usuario);
        dest.writeParcelable(usuario, flags);
        dest.writeString(asignatura);
        dest.writeString(tema);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeString(fecha_activacion);
        dest.writeString(hora_activacion);
        dest.writeString(argumentario);
        dest.writeTypedList(preguntas);
        dest.writeInt(duracion);
        dest.writeByte((byte) (limite_tiempo ? 1 : 0));
        dest.writeInt(numero_preguntas);
        dest.writeDouble(valor_blanco);
        dest.writeDouble(valor_acierto);
        dest.writeDouble(valor_fallo);
        dest.writeDouble(nota_corte);
    }
}
