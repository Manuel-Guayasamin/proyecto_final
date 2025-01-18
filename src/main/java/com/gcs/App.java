package com.gcs;

import com.gcs.services.TareaService;
import com.gcs.services.AsignaturaService;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    TareaService tareaService = new TareaService();
    AsignaturaService asignaturaService = new AsignaturaService();
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Gestionar Tareas");
        System.out.println("2. Gestionar Asignaturas");
        System.out.println("3. Salir");

        int opcionPrincipal = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcionPrincipal) {
          case 1:
            gestionarTareas(scanner, tareaService);
            break;
          case 2:
            gestionarAsignaturas(scanner, asignaturaService);
            break;
          case 3:
            System.out.println("Saliendo del sistema...");
            return;
          default:
            System.out.println("Opción no válida.");
        }
      }
    }
  }

  private static void gestionarTareas(Scanner scanner, TareaService tareaService) {
    while (true) {
      System.out.println("\n=== Gestión de Tareas ===");
      System.out.println("1. Agregar tarea");
      System.out.println("2. Ver tareas");
      System.out.println("3. Editar tarea");
      System.out.println("4. Eliminar tarea");
      System.out.println("5. Regresar al menú principal");

      int opcionTarea = scanner.nextInt();
      scanner.nextLine(); // Consumir el salto de línea

      switch (opcionTarea) {
        case 1 -> tareaService.agregarTarea();
        case 2 -> tareaService.verTareas();
        case 3 -> tareaService.editarTarea();
        case 4 -> tareaService.eliminarTarea();
        case 5 -> {
          return; // Regresar al menú principal
        }
        default -> System.out.println("Opción no válida.");
      }
    }
  }

  private static void gestionarAsignaturas(Scanner scanner, AsignaturaService asignaturaService) {
    while (true) {
      System.out.println("\n=== Gestión de Asignaturas ===");
      System.out.println("1. Agregar asignatura");
      System.out.println("2. Ver asignaturas");
      System.out.println("3. Editar asignatura");
      System.out.println("4. Eliminar asignatura");
      System.out.println("5. Regresar al menú principal");

      int opcionAsignatura = scanner.nextInt();
      scanner.nextLine(); // Consumir el salto de línea

      switch (opcionAsignatura) {
        case 1 -> asignaturaService.agregarAsignatura();
        case 2 -> asignaturaService.verAsignaturas();
        case 3 -> asignaturaService.editarAsignatura();
        case 4 -> asignaturaService.eliminarAsignatura();
        case 5 -> {
          return; // Regresar al menú principal
        }
        default -> System.out.println("Opción no válida.");
      }
    }
  }
}
