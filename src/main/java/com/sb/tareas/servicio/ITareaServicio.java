package com.sb.tareas.servicio;

import com.sb.tareas.modelo.tarea;

import java.util.List;

//esta es nuestra interfaz la que hace el polimorfismo a la clase tareaservicio
public interface ITareaServicio {
    public List<tarea> listarTareas();
    public tarea buscarTareaPorId(Integer idTarea);

    public  void  guardarTarea(tarea tarea);

    public  void  eliminarTarea(tarea tarea);
}
