package com.example.asistencias.controller;

import com.example.asistencias.Controller.EmpleadoController;
import com.example.asistencias.mapper.EmpleadoMapper;
import com.example.asistencias.modelo.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoMapper mapper;

    @Test
    void buscarPorId_CuandoExiste_DebeRetornar200() throws Exception {
        // Arrange: simular un empleado existente
        Empleado empleado = new Empleado();
        empleado.setId(1);
        empleado.setNombres("Luis");
        empleado.setApellidos("Ramos Huamán");
        empleado.setTipo("P");
        empleado.setIdHorario(1);

        when(mapper.buscarPorId(1)).thenReturn(empleado);

        // Act + Assert: realizar la petición y comprobar la respuesta
        mockMvc.perform(get("/api/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombres").value("Luis"))
                .andExpect(jsonPath("$.apellidos").value("Ramos Huamán"))
                .andExpect(jsonPath("$.tipo").value("P"))
                .andExpect(jsonPath("$.idHorario").value(1));
    }

    @Test
    void buscarPorId_CuandoNoExiste_DebeRetornar404() throws Exception {
        // Arrange: simular que no existe el empleado
        when(mapper.buscarPorId(999)).thenReturn(null);

        // Act + Assert: comprobar que la respuesta sea 404
        mockMvc.perform(get("/api/empleados/999"))
                .andExpect(status().isNotFound());
    }
}