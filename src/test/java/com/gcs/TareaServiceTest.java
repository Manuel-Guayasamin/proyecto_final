package com.gcs;

import com.gcs.models.Tarea;
import com.gcs.services.TareaService;
import com.gcs.utils.Prioridad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TareaServiceTest {

    private TareaService tareaService;

    @BeforeEach
    void setUp() {
        tareaService = new TareaService();
    }

    @Test
    void agregarTareaDirecta_shouldAddTaskSuccessfully() {
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción 1", Prioridad.ALTA, "2025-01-30");

        List<Tarea> tareas = tareaService.getTareas();
        assertEquals(1, tareas.size());
        assertEquals("Tarea 1", tareas.get(0).getTitulo());
        assertEquals(Prioridad.ALTA, tareas.get(0).getPrioridad());
    }

    @Test
    void editarTareaDirecta_shouldEditExistingTask() {
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción 1", Prioridad.MEDIA, "2025-01-30");
        Tarea tarea = tareaService.getTareas().get(0);

        tareaService.editarTareaDirecta(tarea.getId(), "Nuevo Titulo", "Nueva Descripción", Prioridad.ALTA, "2025-02-01");

        Tarea tareaEditada = tareaService.getTareas().get(0);
        assertEquals("Nuevo Titulo", tareaEditada.getTitulo());
        assertEquals("Nueva Descripción", tareaEditada.getDescripcion());
        assertEquals(Prioridad.ALTA, tareaEditada.getPrioridad());
        assertEquals("2025-02-01", tareaEditada.getFechaVencimiento());
    }

    @Test
    void eliminarTareaDirecta_shouldRemoveExistingTask() {
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción 1", Prioridad.BAJA, "2025-01-30");
        Tarea tarea = tareaService.getTareas().get(0);

        tareaService.eliminarTareaDirecta(tarea.getId());

        assertTrue(tareaService.getTareas().isEmpty());
    }

    @Test
    void eliminarTareaDirecta_shouldNotFailForNonExistentTask() {
        tareaService.eliminarTareaDirecta(999);

        assertTrue(tareaService.getTareas().isEmpty());
    }

    @Test
    void getTareas_shouldReturnAllTasks() {
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción 1", Prioridad.ALTA, "2025-01-30");
        tareaService.agregarTareaDirecta("Tarea 2", "Descripción 2", Prioridad.MEDIA, "2025-02-01");

        List<Tarea> tareas = tareaService.getTareas();
        assertEquals(2, tareas.size());
    }
}
