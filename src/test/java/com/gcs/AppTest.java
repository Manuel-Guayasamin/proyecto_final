package com.gcs;

import com.gcs.models.Tarea;
import com.gcs.services.TareaService;
import com.gcs.utils.Prioridad;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testAgregarTarea() {
        TareaService tareaService = new TareaService();
        tareaService.agregarTareaDirecta("Título 1", "Descripción 1", Prioridad.ALTA, "2025-01-31");

        List<Tarea> tareas = tareaService.getTareas();
        assertEquals(1, tareas.size());
        assertEquals("Título 1", tareas.get(0).getTitulo());
    }

    @Test
    public void testEditarTarea() {
        TareaService tareaService = new TareaService();
        tareaService.agregarTareaDirecta("Título 1", "Descripción 1", Prioridad.ALTA, "2025-01-31");
        tareaService.editarTareaDirecta(1, "Nuevo título", "Nueva descripción", Prioridad.BAJA, "2025-02-15");

        Tarea tarea = tareaService.getTareas().get(0);
        assertEquals("Nuevo título", tarea.getTitulo());
        assertEquals(Prioridad.BAJA, tarea.getPrioridad());
    }

    @Test
    public void testEliminarTarea() {
        TareaService tareaService = new TareaService();
        tareaService.agregarTareaDirecta("Título 1", "Descripción 1", Prioridad.ALTA, "2025-01-31");
        tareaService.eliminarTareaDirecta(1);

        assertTrue(tareaService.getTareas().isEmpty());
    }
}
