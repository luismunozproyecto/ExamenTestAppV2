package Clases;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Date;


public class Examen implements Parcelable{

    int id;
    String nombre;
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


    public Examen(String nombre, String fecha, String asignatura) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.fecha = fecha;
    }

    public Examen(int id, String nombre, String nom_usuario, Usuario usuario, String asignatura, String tema, String fecha, String hora, String fecha_activacion, String hora_activacion, String argumentario, ArrayList<Pregunta> preguntas, int duracion, boolean limite_tiempo, int numero_preguntas, double valor_blanco, double valor_acierto, double valor_fallo, double nota_corte) {
        this.nombre = nombre;
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
        nombre= in.readString();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha_activacion() {
        return fecha_activacion;
    }

    public void setFecha_activacion(String fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }

    public String getHora_activacion() {
        return hora_activacion;
    }

    public void setHora_activacion(String hora_activacion) {
        this.hora_activacion = hora_activacion;
    }

    public String getArgumentario() {
        return argumentario;
    }

    public void setArgumentario(String argumentario) {
        this.argumentario = argumentario;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isLimite_tiempo() {
        return limite_tiempo;
    }

    public void setLimite_tiempo(boolean limite_tiempo) {
        this.limite_tiempo = limite_tiempo;
    }

    public int getNumero_preguntas() {
        return numero_preguntas;
    }

    public void setNumero_preguntas(int numero_preguntas) {
        this.numero_preguntas = numero_preguntas;
    }

    public double getValor_blanco() {
        return valor_blanco;
    }

    public void setValor_blanco(double valor_blanco) {
        this.valor_blanco = valor_blanco;
    }

    public double getValor_acierto() {
        return valor_acierto;
    }

    public void setValor_acierto(double valor_acierto) {
        this.valor_acierto = valor_acierto;
    }

    public double getValor_fallo() {
        return valor_fallo;
    }

    public void setValor_fallo(double valor_fallo) {
        this.valor_fallo = valor_fallo;
    }

    public double getNota_corte() {
        return nota_corte;
    }

    public void setNota_corte(double nota_corte) {
        this.nota_corte = nota_corte;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
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
