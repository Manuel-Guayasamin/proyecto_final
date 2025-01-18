package com.gcs.models;

public class Asignatura {
    private int id;
    private String nombre;
    private String semestre;
    private int creditos;
    private String horarios;
    private String profesor;

    public Asignatura(int id, String nombre, String semestre, int creditos, String horarios, String profesor) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
        this.creditos = creditos;
        this.horarios = horarios;
        this.profesor = profesor;
    }

    // Getters y Setters
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

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", semestre='" + semestre + '\'' +
                ", creditos=" + creditos +
                ", horarios='" + horarios + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}
