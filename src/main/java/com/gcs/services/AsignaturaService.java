package com.gcs.services;

import com.gcs.models.Asignatura;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AsignaturaService {
    private List<Asignatura> asignaturas = new ArrayList<>();
    private int contadorId = 1;
    private Scanner scanner = new Scanner(System.in);

    // Método para agregar una asignatura
    public void agregarAsignatura() {
        System.out.println("Ingrese el nombre de la asignatura:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el semestre de la asignatura:");
        String semestre = scanner.nextLine();

        System.out.println("Ingrese los créditos de la asignatura:");
        int creditos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese los horarios de la asignatura:");
        String horarios = scanner.nextLine();

        System.out.println("Ingrese el nombre del profesor:");
        String profesor = scanner.nextLine();

        Asignatura asignatura = new Asignatura(contadorId++, nombre, semestre, creditos, horarios, profesor);
        asignaturas.add(asignatura);

        System.out.println("Asignatura agregada exitosamente.");
    }

    // Método para ver todas las asignaturas
    public void verAsignaturas() {
        if (asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            asignaturas.forEach(System.out::println);
        }
    }

    // Método para editar una asignatura
    public void editarAsignatura() {
        System.out.println("Ingrese el ID de la asignatura a editar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Asignatura asignatura = buscarAsignatura(id);
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }

        System.out.println("Nuevo nombre:");
        asignatura.setNombre(scanner.nextLine());

        System.out.println("Nuevo semestre:");
        asignatura.setSemestre(scanner.nextLine());

        System.out.println("Nuevos créditos:");
        asignatura.setCreditos(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Nuevo horario:");
        asignatura.setHorarios(scanner.nextLine());

        System.out.println("Nuevo nombre del profesor:");
        asignatura.setProfesor(scanner.nextLine());

        System.out.println("Asignatura actualizada.");
    }

    // Método para eliminar una asignatura
    public void eliminarAsignatura() {
        System.out.println("Ingrese el ID de la asignatura a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Asignatura asignatura = buscarAsignatura(id);
        if (asignatura == null) {
            System.out.println("Asignatura no encontrada.");
            return;
        }

        asignaturas.remove(asignatura);
        System.out.println("Asignatura eliminada.");
    }

    // Método para buscar una asignatura por ID
    private Asignatura buscarAsignatura(int id) {
        return asignaturas.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    // Métodos de prueba para agregar, editar y eliminar asignaturas directamente
    public void agregarAsignaturaDirecta(String nombre, String semestre, int creditos, String horarios, String profesor) {
        Asignatura asignatura = new Asignatura(contadorId++, nombre, semestre, creditos, horarios, profesor);
        asignaturas.add(asignatura);
    }

    public void editarAsignaturaDirecta(int id, String nuevoNombre, String nuevoSemestre, int nuevosCreditos, String nuevosHorarios, String nuevoProfesor) {
        Asignatura asignatura = buscarAsignatura(id);
        if (asignatura != null) {
            asignatura.setNombre(nuevoNombre);
            asignatura.setSemestre(nuevoSemestre);
            asignatura.setCreditos(nuevosCreditos);
            asignatura.setHorarios(nuevosHorarios);
            asignatura.setProfesor(nuevoProfesor);
        }
    }

    public void eliminarAsignaturaDirecta(int id) {
        Asignatura asignatura = buscarAsignatura(id);
        if (asignatura != null) {
            asignaturas.remove(asignatura);
        }
    }

    // Método para obtener todas las asignaturas
    public List<Asignatura> getAsignaturas() {
        return new ArrayList<>(asignaturas);
    }
}
