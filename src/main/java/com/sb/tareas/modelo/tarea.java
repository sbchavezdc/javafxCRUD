package com.sb.tareas.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//clase de entidad que se comunica con la bd
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class tarea {
    //creacion de atributos
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer idTarea;
    private  String nombreTarea;
    private  String responsable;
    private  String estatus;
}
