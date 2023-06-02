    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package proyectomundial.model;

    /**
     *
     * @author DANI Y GABY
     */
    public class Resultados {

    String grupo;
    String Local;
    String visitante;
    String continente_local;
    String continente_visitante;
    String goles_local;
    String goles_visitante;

    
    public Resultados(String grupo, String Local, String visitante, String continente_local, String continente_visitante, String goles_local, String goles_visitante) {
        this.grupo = grupo;
        this.Local = Local;
        this.visitante = visitante;
        this.continente_local = continente_local;
        this.continente_visitante = continente_visitante;
        this.goles_local = goles_local;
        this.goles_visitante = goles_visitante;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setLocal(String Local) {
        this.Local = Local;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public void setContinente_local(String continente_local) {
        this.continente_local = continente_local;
    }

    public void setContinente_visitante(String continente_visitante) {
        this.continente_visitante = continente_visitante;
    }

    public void setGoles_local(String goles_local) {
        this.goles_local = goles_local;
    }

    public void setGoles_visitante(String goles_visitante) {
        this.goles_visitante = goles_visitante;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getLocal() {
        return Local;
    }

    public String getVisitante() {
        return visitante;
    }

    public String getContinente_local() {
        return continente_local;
    }

    public String getContinente_visitante() {
        return continente_visitante;
    }

    public String getGoles_local() {
        return goles_local;
    }

    public String getGoles_visitante() {
        return goles_visitante;
    }
    

    }