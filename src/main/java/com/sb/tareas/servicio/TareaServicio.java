package com.sb.tareas.servicio;

import com.sb.tareas.modelo.tarea;
import com.sb.tareas.repositorio.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//vamos a comunicar la clase con la interfaz
//esta clase sea un componente y sea de la fabrica de spring

@Service
public class TareaServicio  implements ITareaServicio{
// inyectar automaticamente con autowired
    @Autowired
    private TareaRepositorio tareaRepositorio;

    @Override
    public List<tarea> listarTareas() {
        //findAll sirve para regresar el objeto
        return tareaRepositorio.findAll();
    }

    @Override
    public tarea buscarTareaPorId(Integer idTarea) {
        //el metodo busca el id y si no lo encuentra lo regresa nulo
        tarea tarea = tareaRepositorio.findById(idTarea).orElse(null);
        return tarea;
    }

    @Override
    public void guardarTarea(tarea tarea) {
        //metodo guardar ya que lo implementamos del jparespositorio
        tareaRepositorio.save(tarea);
    }

    @Override
    public void eliminarTarea(tarea tarea) {
        //metodo eliminar ya que de la intyerfaz lo estamos implementando  jparespositorio
        tareaRepositorio.delete(tarea);
    }
}
