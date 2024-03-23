package com.sb.tareas.repositorio;

import com.sb.tareas.modelo.tarea;
import org.springframework.data.jpa.repository.JpaRepository;
//creacion de respositorio que es una interfaz y la comunicamos con la clase tarea
public interface TareaRepositorio extends JpaRepository<tarea, Integer > {
}
