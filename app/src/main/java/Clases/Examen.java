package Clases;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Examen implements Parcelable{

    String id;
    String nombre;
    String nom_usuario;
    String asignatura;
    String tema;
    String fecha;
    String hora;
    String fecha_activacion;
    String hora_activacion;
    String argumentario;
    int activo;
    int duracion;
    int limite_tiempo;
    int numero_preguntas;
    double valor_blanco;
    double valor_acierto;
    double valor_fallo;
    double nota_corte;
    List<Pregunta> preguntas;

    public Examen(String nombre, String nom_usuario, String asignatura,String tema,String fecha,String hora, String fecha_activacion, String hora_activacion, String argumentario){
        this.nombre = nombre;
        this.nom_usuario=nom_usuario;
        this.asignatura =asignatura;
        this.tema= tema;
        this.fecha=fecha;
        this.hora=hora;
        this.fecha_activacion=fecha_activacion;
        this.hora_activacion=hora_activacion;
        this.argumentario=argumentario;
    }
    public Examen(String nombre, String fecha, String asignatura, int activo, List<Pregunta> preguntas) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.activo = activo;
        this.preguntas = preguntas;
    }

    public Examen(String id, String nombre, String nom_usuario,  String asignatura, String tema, String fecha, String hora, String fecha_activacion, String hora_activacion, String argumentario, int activo, int duracion, int limite_tiempo, int numero_preguntas, double valor_blanco, double valor_acierto, double valor_fallo, double nota_corte, List<Pregunta> preguntas) {
        this.nombre = nombre;
        this.id = id;
        this.nom_usuario = nom_usuario;
        this.asignatura = asignatura;
        this.tema = tema;
        this.fecha = fecha;
        this.hora = hora;
        this.fecha_activacion = fecha_activacion;
        this.hora_activacion = hora_activacion;
        this.argumentario = argumentario;
        this.activo=activo;
        this.duracion = duracion;
        this.limite_tiempo = limite_tiempo;
        this.numero_preguntas = numero_preguntas;
        this.valor_blanco = valor_blanco;
        this.valor_acierto = valor_acierto;
        this.valor_fallo = valor_fallo;
        this.nota_corte = nota_corte;
        this.preguntas = preguntas;

    }
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    public Examen() {
    }

    protected Examen(Parcel in) {
        id = in.readString();
        nombre= in.readString();
        nom_usuario = in.readString();
        asignatura = in.readString();
        tema = in.readString();
        fecha = in.readString();
        hora = in.readString();
        fecha_activacion = in.readString();
        hora_activacion = in.readString();
        argumentario = in.readString();
        activo = in.readInt();
        duracion = in.readInt();
        limite_tiempo = in.readInt();
        numero_preguntas = in.readInt();
        valor_blanco = in.readDouble();
        valor_acierto = in.readDouble();
        valor_fallo = in.readDouble();
        nota_corte = in.readDouble();
        preguntas = in.createTypedArrayList(Pregunta.CREATOR);

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int isLimite_tiempo() {
        return limite_tiempo;
    }

    public void setLimite_tiempo(int limite_tiempo) {
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
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(nom_usuario);
        dest.writeString(asignatura);
        dest.writeString(tema);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeString(fecha_activacion);
        dest.writeString(hora_activacion);
        dest.writeString(argumentario);
        dest.writeInt(activo);
        dest.writeInt(duracion);
        dest.writeInt(limite_tiempo);
        dest.writeInt(numero_preguntas);
        dest.writeDouble(valor_blanco);
        dest.writeDouble(valor_acierto);
        dest.writeDouble(valor_fallo);
        dest.writeDouble(nota_corte);
        dest.writeTypedList(preguntas);
    }


}
