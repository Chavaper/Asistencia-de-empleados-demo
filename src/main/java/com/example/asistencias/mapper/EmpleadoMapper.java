
package com.example.asistencias.mapper;

import com.example.asistencias.modelo.Empleado;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpleadoMapper {

    List<Empleado> listar();

    Empleado buscarPorId(Integer id);

    int crear(Empleado empleado);

    int actualizar(Empleado empleado);

    int eliminar(Integer id);
}
