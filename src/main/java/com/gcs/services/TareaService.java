package com.gcs.services;

import com.gcs.models.Tarea;
import com.gcs.utils.Prioridad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TareaService {
  private List<Tarea> tareas = new ArrayList<>();
  private int contadorId = 1;
  private Scanner scanner = new Scanner(System.in);

  public void agregarTarea() {
    System.out.println("Ingrese el titulo de la tarea:");
    String titulo = scanner.nextLine();

    System.out.println("Ingrese la descripción de la tarea:");
    String descripcion = scanner.nextLine();

    System.out.println("Seleccione la prioridad (1: Alta, 2: Media, 3: Baja):");
    int prioridadSeleccion = scanner.nextInt();
    scanner.nextLine();
    Prioridad prioridad = Prioridad.values()[prioridadSeleccion - 1];

    System.out.println("Ingrese la fecha de vencimiento (formato: AAAA-MM-DD):");
    String fechaVencimiento = scanner.nextLine();

    Tarea tarea = new Tarea(contadorId++, titulo, descripcion, prioridad, fechaVencimiento);
    tareas.add(tarea);

    System.out.println("Tarea agregada exitosamente.");
  }

  public void verTareas() {
    if (tareas.isEmpty()) {
      System.out.println("No hay tareas registradas.");
    } else {
      tareas.forEach(System.out::println);
    }
  }

  public void editarTarea() {
    System.out.println("Ingrese el ID de la tarea a editar:");
    int id = scanner.nextInt();
    scanner.nextLine();

    Tarea tarea = buscarTarea(id);
    if (tarea == null) {
      System.out.println("Tarea no encontrada.");
      return;
    }

    System.out.println("Nueva descripción:");
    tarea.setDescripcion(scanner.nextLine());

    System.out.println("Nueva prioridad (1: Alta, 2: Media, 3: Baja):");
    int prioridadSeleccion = scanner.nextInt();
    scanner.nextLine();
    tarea.setPrioridad(Prioridad.values()[prioridadSeleccion - 1]);

    System.out.println("Nueva fecha de vencimiento:");
    tarea.setFechaVencimiento(scanner.nextLine());

    System.out.println("Tarea actualizada.");
  }

  public void eliminarTarea() {
    System.out.println("Ingrese el ID de la tarea a eliminar:");
    int id = scanner.nextInt();
    scanner.nextLine();

    Tarea tarea = buscarTarea(id);
    if (tarea == null) {
      System.out.println("Tarea no encontrada.");
      return;
    }

    tareas.remove(tarea);
    System.out.println("Tarea eliminada.");
  }
//buscar por titulo
  private Tarea buscarTarea(int id) {
    return tareas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
  }
//Pruebas
  public void agregarTareaDirecta(String titulo, String descripcion, Prioridad prioridad, String fechaVencimiento) {
    Tarea tarea = new Tarea(contadorId++, titulo, descripcion, prioridad, fechaVencimiento);
    tareas.add(tarea);
  }
    
  public void editarTareaDirecta(int id, String nuevoTitulo, String nuevaDescripcion, Prioridad nuevaPrioridad, String nuevaFechaVencimiento) {
    Tarea tarea = buscarTarea(id);
    if (tarea != null) {
      tarea.setTitulo(nuevoTitulo);
      tarea.setDescripcion(nuevaDescripcion);
      tarea.setPrioridad(nuevaPrioridad);
      tarea.setFechaVencimiento(nuevaFechaVencimiento);
    }
  }
    
  public void eliminarTareaDirecta(int id) {
    Tarea tarea = buscarTarea(id);
    if (tarea != null) {
      tareas.remove(tarea);
    }
  }
    
  public List<Tarea> getTareas() {
    return new ArrayList<>(tareas);
  }
}
