package com.gcs;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.gcs.models.Tarea;
import com.gcs.services.TareaService;
import com.gcs.utils.Prioridad;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

public class TareaServiceTest {

    
    private Scanner scanner = new Scanner(System.in);

    private TareaService tareaService = new TareaService();


    @Test
    public void testAgregarTarea() {
        // Mocking the user input for adding a task
        when(scanner.nextLine()).thenReturn("Tarea 1", "Descripción de tarea 1", "2025-12-31");
        when(scanner.nextInt()).thenReturn(1);

        tareaService.agregarTarea();

        List<Tarea> tareas = tareaService.getTareas();
        assertEquals(1, tareas.size(), "Debe haber una tarea agregada.");
        assertEquals("Tarea 1", tareas.get(0).getTitulo());
        assertEquals("Descripción de tarea 1", tareas.get(0).getDescripcion());
        assertEquals(Prioridad.ALTA, tareas.get(0).getPrioridad());
        assertEquals("2025-12-31", tareas.get(0).getFechaVencimiento());
    }

    @Test
    public void testVerTareas() {
        // Adding a task directly
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción tarea 1", Prioridad.ALTA, "2025-12-31");

        // Verify if tareas are displayed correctly
        tareaService.verTareas();
        List<Tarea> tareas = tareaService.getTareas();
        assertFalse(tareas.isEmpty(), "Debe haber tareas para mostrar.");
    }

    @Test
    public void testEditarTarea() {
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción tarea 1", Prioridad.ALTA, "2025-12-31");

        tareaService.editarTareaDirecta(1, "Tarea Editada", "Descripción tarea editada", Prioridad.BAJA, "2025-11-30");
        
        Tarea tarea = tareaService.getTareas().get(0);
        assertEquals("Tarea Editada", tarea.getTitulo());
        assertEquals("Descripción tarea editada", tarea.getDescripcion());
        assertEquals(Prioridad.BAJA, tarea.getPrioridad());
        assertEquals("2025-11-30", tarea.getFechaVencimiento());
    }

    @Test
    public void testEliminarTarea() {
        tareaService.agregarTareaDirecta("Tarea 1", "Descripción tarea 1", Prioridad.ALTA, "2025-12-31");

        tareaService.eliminarTareaDirecta(1);

        List<Tarea> tareas = tareaService.getTareas();
        assertTrue(tareas.isEmpty(), "La lista de tareas debe estar vacía después de eliminar la tarea.");
    }

    @Test
    public void testEliminarTareaNoExistente() {
        tareaService.eliminarTareaDirecta(999); // ID no existente
        List<Tarea> tareas = tareaService.getTareas();
        assertTrue(tareas.isEmpty(), "No debe eliminar tareas si no existe.");
    }
}
