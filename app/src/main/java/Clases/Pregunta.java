package Clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Nino Ruano on 17/04/2018.
 */

public class Pregunta implements Parcelable{

    int id_examen;
    String pregunta;
    ArrayList<String> respuestas;
    String respuesta_correcta;

    public Pregunta() {
    }

    public Pregunta(int id_examen, String pregunta, ArrayList<String> respuestas, String respuesta_correcta) {
        this.id_examen = id_examen;
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.respuesta_correcta = respuesta_correcta;
    }

    protected Pregunta(Parcel in) {
        id_examen = in.readInt();
        pregunta = in.readString();
        respuestas = in.createStringArrayList();
        respuesta_correcta = in.readString();
    }

    public static final Creator<Pregunta> CREATOR = new Creator<Pregunta>() {
        @Override
        public Pregunta createFromParcel(Parcel in) {
            return new Pregunta(in);
        }

        @Override
        public Pregunta[] newArray(int size) {
            return new Pregunta[size];
        }
    };

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }

    public String getRespuesta_correcta() {
        return respuesta_correcta;
    }

    public void setRespuesta_correcta(String respuesta_correcta) {
        this.respuesta_correcta = respuesta_correcta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_examen);
        dest.writeString(pregunta);
        dest.writeStringList(respuestas);
        dest.writeString(respuesta_correcta);
    }
}
