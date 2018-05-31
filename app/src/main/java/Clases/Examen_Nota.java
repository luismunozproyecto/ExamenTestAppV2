package Clases;

import java.util.List;

public class Examen_Nota {

    String id_examen;
    List<Nota> notas;


    public String getId_examen() {
        return id_examen;
    }

    public void setId_examen(String id_examen) {
        this.id_examen = id_examen;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public Examen_Nota(String id_examen, List<Nota> notas) {
        this.id_examen = id_examen;
        this.notas = notas;
    }

    public Examen_Nota() {
    }
}

