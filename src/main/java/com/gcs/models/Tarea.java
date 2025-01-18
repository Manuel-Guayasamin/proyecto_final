package com.gcs.models;

import com.gcs.utils.Prioridad;

public class Tarea {
  private int id;
  private String titulo;
  private String descripcion;
  private Prioridad prioridad;
  private String fechaVencimiento;

  public Tarea(int id, String titulo, String descripcion, Prioridad prioridad, String fechaVencimiento) {
    this.id = id;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.prioridad = prioridad;
    this.fechaVencimiento = fechaVencimiento;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Prioridad getPrioridad() {
    return prioridad;
  }

  public void setPrioridad(Prioridad prioridad) {
    this.prioridad = prioridad;
  }

  public String getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(String fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  @Override
  public String toString() {
    return "Tarea{" +
      "id=" + id +
      ", titulo='" + titulo + '\'' +
      ", descripción='" + descripcion + '\'' +
      ", prioridad=" + prioridad +
      ", fecha de vencimiento='" + fechaVencimiento + '\'' +
    '}';
  }
}
