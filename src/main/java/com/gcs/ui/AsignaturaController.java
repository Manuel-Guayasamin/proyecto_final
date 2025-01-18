package com.gcs.ui;

import com.gcs.models.Asignatura;
import com.gcs.services.AsignaturaService;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    public AsignaturaController() {
        asignaturaService = new AsignaturaService();
    }

    // Mostrar el diálogo para agregar una asignatura
    public void showAddAsignaturaDialog() {
        String nombre;
        do {
            nombre = showInputDialog("Nombre de la asignatura:");
            if (nombre.isEmpty()) {
                showAlert("Error", "El nombre de la asignatura no puede estar vacío.");
            } else if (!nombre.matches("[a-zA-Z ]+")) {
                showAlert("Error", "El nombre de la asignatura solo puede contener letras.");
            }
        } while (nombre.isEmpty() || !nombre.matches("[a-zA-Z ]+"));

        String semestre;
        do {
            semestre = showInputDialog("Semestre de la asignatura:");
            if (semestre.isEmpty()) {
                showAlert("Error", "El semestre no puede estar vacío.");
            }
        } while (semestre.isEmpty());

        String creditosStr;
        int creditos = 0;
        do {
            creditosStr = showInputDialog("Créditos de la asignatura:");
            if (creditosStr.isEmpty()) {
                showAlert("Error", "Los créditos no pueden estar vacíos.");
            } else if (!isNumeric(creditosStr)) {
                showAlert("Error", "Los créditos deben ser un número.");
            } else {
                creditos = Integer.parseInt(creditosStr);
            }
        } while (creditosStr.isEmpty() || !isNumeric(creditosStr));

        String horarios;
        do {
            horarios = showInputDialog("Horarios de la asignatura:");
            if (horarios.isEmpty()) {
                showAlert("Error", "El horario no puede estar vacío.");
            }
        } while (horarios.isEmpty());

        String profesor;
        do {
            profesor = showInputDialog("Nombre del profesor:");
            if (profesor.isEmpty()) {
                showAlert("Error", "El nombre del profesor no puede estar vacío.");
            } else if (!profesor.matches("[a-zA-Z ]+")) {
                showAlert("Error", "El nombre del profesor solo puede contener letras.");
            }
        } while (profesor.isEmpty() || !profesor.matches("[a-zA-Z ]+"));

        asignaturaService.agregarAsignaturaDirecta(nombre, semestre, creditos, horarios, profesor);
        showAlert("Exito", "Asignatura agregada exitosamente.");
    }

    // Método auxiliar para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método auxiliar para mostrar diálogos de entrada de texto
    private String showInputDialog(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);
        Optional<String> result = dialog.showAndWait();
        return result.orElse("").trim();
    }

    // Método auxiliar para verificar si un String es numérico
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Mostrar el diálogo para editar una asignatura
    public void showEditAsignaturaDialog() {
        String input = showInputDialog("ID o nombre de la asignatura a editar:");

        Asignatura asignatura = null;
        if (isNumeric(input)) {
            int id = Integer.parseInt(input);
            asignatura = asignaturaService.getAsignaturas().stream()
                    .filter(a -> a.getId() == id)
                    .findFirst()
                    .orElse(null);
        } else {
            asignatura = asignaturaService.getAsignaturas().stream()
                    .filter(a -> a.getNombre().equalsIgnoreCase(input))
                    .findFirst()
                    .orElse(null);
        }

        if (asignatura != null) {
            String nuevoNombre = showInputDialog("Nuevo nombre:");
            if (nuevoNombre.isEmpty()) {
                showAlert("Error", "El nombre no puede estar vacío.");
                return;
            }
            if (!nuevoNombre.matches("[a-zA-Z ]+")) {
                showAlert("Error", "El nombre solo puede contener letras.");
                return;
            }

            String nuevoSemestre = showInputDialog("Nuevo semestre:");
            if (nuevoSemestre.isEmpty()) {
                showAlert("Error", "El semestre no puede estar vacío.");
                return;
            }

            String nuevosCreditosStr = showInputDialog("Nuevos créditos:");
            if (nuevosCreditosStr.isEmpty()) {
                showAlert("Error", "Los créditos no pueden estar vacíos.");
                return;
            }
            if (!isNumeric(nuevosCreditosStr)) {
                showAlert("Error", "Los créditos deben ser un número.");
                return;
            }
            int nuevosCreditos = Integer.parseInt(nuevosCreditosStr);

            String nuevosHorarios = showInputDialog("Nuevos horarios:");
            if (nuevosHorarios.isEmpty()) {
                showAlert("Error", "Los horarios no pueden estar vacíos.");
                return;
            }

            String nuevoProfesor = showInputDialog("Nuevo profesor:");
            if (nuevoProfesor.isEmpty()) {
                showAlert("Error", "El profesor no puede estar vacío.");
                return;
            }
            if (!nuevoProfesor.matches("[a-zA-Z ]+")) {
                showAlert("Error", "El nombre del profesor solo puede contener letras.");
                return;
            }

            asignaturaService.editarAsignaturaDirecta(asignatura.getId(), nuevoNombre, nuevoSemestre, nuevosCreditos, nuevosHorarios, nuevoProfesor);
            showAlert("Exito", "Asignatura editada exitosamente.");
        } else {
            showAlert("Error", "Asignatura no encontrada.");
        }
    }
 // Mostrar la lista de asignaturas
 public void showAsignaturasList() {
    StringBuilder builder = new StringBuilder();
    for (Asignatura asignatura : asignaturaService.getAsignaturas()) {
        builder.append(asignatura).append("\n");
    }
    showAlert("Lista de Asignaturas", builder.toString());
}
    // Método auxiliar para mostrar alertas

    // Método para eliminar una asignatura
public void showDeleteAsignaturaDialog() {
    String input = showInputDialog("ID o nombre de la asignatura a eliminar:");

    // Si el input es un número, lo tratamos como ID
    Asignatura asignatura = null;
    if (isNumeric(input)) {
        int id = Integer.parseInt(input);
        asignatura = asignaturaService.getAsignaturas().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    } else {
        // Si no es un número, lo tratamos como nombre
        asignatura = asignaturaService.getAsignaturas().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }

    if (asignatura != null) {
        asignaturaService.eliminarAsignaturaDirecta(asignatura.getId());
        showAlert("Error", "Asignatura eliminada exitosamente.");
    } else {
        showAlert("Error", "Asignatura no encontrada.");
    }
}

}
