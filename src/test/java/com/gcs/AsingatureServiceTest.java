package com.gcs;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.gcs.models.Asignatura;
import com.gcs.services.AsignaturaService;

public class AsingatureServiceTest {
    private AsignaturaService asignaturaService = new AsignaturaService();

    // @BeforeEach
    // public void setUp() {
    //     asignaturaService = new AsignaturaService(); // Asegurar que se inicializa
    //     assertNotNull(asignaturaService, "Error: asignaturaService es null en setUp()");
    // }

    @Test
    public void testAgregarAsignaturaDirecta() {
        asignaturaService.agregarAsignaturaDirecta("Matemáticas", "1er semestre", 5, "Lunes y Miércoles", "Dr. Smith");
        List<Asignatura> asignaturas = asignaturaService.getAsignaturas();
        assertEquals(1, asignaturas.size());
        assertEquals("Matemáticas", asignaturas.get(0).getNombre());
    }

    @Test
    public void testEditarAsignaturaDirecta() {
        asignaturaService.agregarAsignaturaDirecta("Física", "2do semestre", 4, "Martes y Jueves", "Dra. Johnson");
        asignaturaService.editarAsignaturaDirecta(1, "Física Avanzada", "3er semestre", 6, "Viernes", "Dr. Brown");
        Asignatura asignatura = asignaturaService.getAsignaturas().get(0);
        assertEquals("Física Avanzada", asignatura.getNombre());
        assertEquals("3er semestre", asignatura.getSemestre());
        assertEquals(6, asignatura.getCreditos());
        assertEquals("Viernes", asignatura.getHorarios());
        assertEquals("Dr. Brown", asignatura.getProfesor());
    }

    @Test
    public void testEliminarAsignaturaDirecta() {
        asignaturaService.agregarAsignaturaDirecta("Química", "1er semestre", 4, "Lunes y Viernes", "Prof. Adams");
        assertEquals(1, asignaturaService.getAsignaturas().size());
        asignaturaService.eliminarAsignaturaDirecta(1);
        assertTrue(asignaturaService.getAsignaturas().isEmpty());
    }
}
