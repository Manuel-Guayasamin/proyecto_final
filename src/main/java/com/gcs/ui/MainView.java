package com.gcs.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {
    private Controller controller;
    private AsignaturaController asignaturaController;

    @Override
    public void start(Stage primaryStage) {
        // Inicializar controladores
        controller = new Controller();
        asignaturaController = new AsignaturaController();

        // Crear el layout principal
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10; -fx-alignment: top-center;");

        // Título principal
        Label title = new Label("Menú de Gestión");
        title.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        // Botones principales
        Button btnGestionTareas = new Button("Gestionar Tareas");
        Button btnGestionAsignaturas = new Button("Gestionar Asignaturas");

        // Acciones para botones principales
        btnGestionTareas.setOnAction(e -> showTareasOptions());
        btnGestionAsignaturas.setOnAction(e -> showAsignaturasOptions());

        // Agregar los botones al layout
        root.getChildren().addAll(title, btnGestionTareas, btnGestionAsignaturas);

        // Crear la escena y mostrarla
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Gestión de Tareas y Asignaturas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Mostrar las opciones de gestión de tareas
    private void showTareasOptions() {
        VBox tareasOptions = new VBox(10);
        tareasOptions.setStyle("-fx-padding: 10; -fx-alignment: top-center;");

        Label tareasTitle = new Label("Gestión de Tareas");
        tareasTitle.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Button btnAdd = new Button("Agregar Tarea");
        Button btnView = new Button("Ver Tareas");
        Button btnEdit = new Button("Editar Tarea");
        Button btnDelete = new Button("Eliminar Tarea");

        btnAdd.setOnAction(e -> controller.showAddTaskDialog());
        btnView.setOnAction(e -> controller.showTasksList());
        btnEdit.setOnAction(e -> controller.showEditTaskDialog());
        btnDelete.setOnAction(e -> controller.showDeleteTaskDialog());

        tareasOptions.getChildren().addAll(tareasTitle, btnAdd, btnView, btnEdit, btnDelete);

        Scene scene = new Scene(tareasOptions, 400, 300);
        Stage tareasStage = new Stage();
        tareasStage.setTitle("Gestión de Tareas");
        tareasStage.setScene(scene);
        tareasStage.show();
    }

    // Mostrar las opciones de gestión de asignaturas
    private void showAsignaturasOptions() {
        VBox asignaturasOptions = new VBox(10);
        asignaturasOptions.setStyle("-fx-padding: 10; -fx-alignment: top-center;");

        Label asignaturasTitle = new Label("Gestión de Asignaturas");
        asignaturasTitle.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Button btnAddAsignatura = new Button("Agregar Asignatura");
        Button btnViewAsignatura = new Button("Ver Asignaturas");
        Button btnEditAsignatura = new Button("Editar Asignatura");
        Button btnDeleteAsignatura = new Button("Eliminar Asignatura");

        btnAddAsignatura.setOnAction(e -> asignaturaController.showAddAsignaturaDialog());
        btnViewAsignatura.setOnAction(e -> asignaturaController.showAsignaturasList());
        btnEditAsignatura.setOnAction(e -> asignaturaController.showEditAsignaturaDialog());
        btnDeleteAsignatura.setOnAction(e -> asignaturaController.showDeleteAsignaturaDialog());

        asignaturasOptions.getChildren().addAll(asignaturasTitle, btnAddAsignatura, btnViewAsignatura, btnEditAsignatura, btnDeleteAsignatura);

        Scene scene = new Scene(asignaturasOptions, 400, 300);
        Stage asignaturasStage = new Stage();
        asignaturasStage.setTitle("Gestión de Asignaturas");
        asignaturasStage.setScene(scene);
        asignaturasStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
