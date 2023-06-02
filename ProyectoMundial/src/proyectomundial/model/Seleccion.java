/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.model;

/**
 *
 * @author miguelropero
 */
public class Seleccion {

    String nombre;
    String continente;
    String director;
    String nacionalidad;

    public Seleccion() {
    }

    public Seleccion(String nombre, String continente, String director, String nacionalidad) {
        this.nombre = nombre;
        this.continente = continente;
        this.director = director;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getDt() {
        return director;
    }

    public void setDt(String dt) {
        this.director = dt;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
