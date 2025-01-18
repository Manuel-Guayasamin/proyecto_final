package com.gcs.ui;

import com.gcs.models.Tarea;
import com.gcs.services.TareaService;
import com.gcs.utils.Prioridad;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Controller {
  private final TareaService tareaService;

  public Controller() {
    tareaService = new TareaService();
  }

  public void showAddTaskDialog() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Agregar Tarea");
    dialog.setHeaderText("Ingrese los detalles de la tarea:");
        
    String titulo = showInputDialog("Título de la tarea:");
    String descripcion = showInputDialog("Descripción de la tarea:");
    String prioridadStr = showInputDialog("Prioridad (1: Alta, 2: Media, 3: Baja):");
    String fecha = showInputDialog("Fecha de vencimiento (AAAA-MM-DD):");

    Prioridad prioridad = Prioridad.values()[Integer.parseInt(prioridadStr) - 1];
    tareaService.agregarTareaDirecta(titulo, descripcion, prioridad, fecha);

    showAlert("Tarea agregada exitosamente.");
  }

  public void showTasksList() {
    StringBuilder builder = new StringBuilder();
    for (Tarea tarea : tareaService.getTareas()) {
      builder.append(tarea).append("\n");
    }
    showAlert("Lista de Tareas", builder.toString());
  }

  public void showEditTaskDialog() {
    String idStr = showInputDialog("ID de la tarea a editar:");
    int id = Integer.parseInt(idStr);

    Tarea tarea = tareaService.getTareas().stream()
    .filter(t -> t.getId() == id)
    .findFirst()
    .orElse(null);

    if (tarea != null) {
      String nuevoTitulo = showInputDialog("Nuevo título:");
      String nuevaDescripcion = showInputDialog("Nueva descripción:");
      String nuevaPrioridadStr = showInputDialog("Nueva prioridad (1: Alta, 2: Media, 3: Baja):");
      String nuevaFecha = showInputDialog("Nueva fecha de vencimiento:");

      Prioridad nuevaPrioridad = Prioridad.values()[Integer.parseInt(nuevaPrioridadStr) - 1];
      tareaService.editarTareaDirecta(id, nuevoTitulo, nuevaDescripcion, nuevaPrioridad, nuevaFecha);

      showAlert("Tarea actualizada exitosamente.");
    } else {
      showAlert("Error", "Tarea no encontrada.");
    }
  }

  public void showDeleteTaskDialog() {
    String idStr = showInputDialog("ID de la tarea a eliminar:");
    int id = Integer.parseInt(idStr);

    tareaService.eliminarTareaDirecta(id);
    showAlert("Tarea eliminada exitosamente.");
  }

  private String showInputDialog(String message) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setHeaderText(message);
    Optional<String> result = dialog.showAndWait();
    return result.orElse("");
  }

  private void showAlert(String message) {
    showAlert("Información", message);
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
