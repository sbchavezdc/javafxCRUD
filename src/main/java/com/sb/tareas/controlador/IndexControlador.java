package com.sb.tareas.controlador;


import com.sb.tareas.modelo.tarea;
import com.sb.tareas.servicio.TareaServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {
    private  static  final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<tarea> tareaTabla;

    @FXML
    private  TableColumn<tarea, Integer> idTareaColumna;

    @FXML
    private  TableColumn<tarea, Integer> TareaColumna;
    @FXML
    private  TableColumn<tarea, Integer> ResponsableColumna;
    @FXML
    private  TableColumn<tarea, Integer> EstatusColumna;


    private  final ObservableList<tarea> tareaLista = FXCollections.observableArrayList();

    @FXML
    private TextField nombreTareasTexto;
    @FXML
    private TextField  responsableTextoTarea;
    @FXML
    private TextField  estatusTextoTarea;
    private Integer idTareaInterno;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configuararColumnas();
        listaTareas();

    }

   private void configuararColumnas(){
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        TareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        ResponsableColumna.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        EstatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));


   }

   private  void  listaTareas(){
        logger.info("Ejecutando la lista de tareas");
        tareaLista.clear();
        tareaLista.addAll(tareaServicio.listarTareas());
        tareaTabla.setItems(tareaLista);

   }

   public void  agregarTarea(){
    if (nombreTareasTexto.getText().isEmpty()){
        mostarMnesaje("Error de validacio","Debe de proporcionar una tarea");
        nombreTareasTexto.requestFocus();
        return;
    }else {
        var tareas = new tarea();
        recolectarDatosFormulario(tareas);
        tareas.setIdTarea(null);
        tareaServicio.guardarTarea(tareas);
        mostarMnesaje("Informacion", "Tarea agregada");
        limpiarFormulario();
        listaTareas();

    }
   }

    public void  seleccionTarea(){
    var tarea = tareaTabla.getSelectionModel().getSelectedItem();
    if (tarea != null){
        idTareaInterno= tarea.getIdTarea();
        nombreTareasTexto.setText(tarea.getNombreTarea());
        responsableTextoTarea.setText(tarea.getResponsable());
        estatusTextoTarea.setText(tarea.getEstatus());
        }

    }

    public void  modificarTarea(){
        if (idTareaInterno == null){
            mostarMnesaje("Informacion","debes modificar una tarea");
            return;
        }if (nombreTareasTexto.getText().isEmpty()){
            mostarMnesaje("Error","Debes de proporcionar una tarea");
            nombreTareasTexto.requestFocus();
            return;
        }
        var tarea = new tarea();
        recolectarDatosFormulario(tarea);
        tareaServicio.guardarTarea(tarea);
        mostarMnesaje("Informativo","tarea modificada");
        limpiarFormulario();
        listaTareas();

    }
    public void  eliminarTarea(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea != null){
            logger.info("registro eliminado"+ tarea.toString());
            tareaServicio.eliminarTarea(tarea);
            mostarMnesaje("informacion","tarea eliminada"+tarea.getIdTarea());
            limpiarFormulario();
            listaTareas();
        }
        else {
            mostarMnesaje("error","no se selecciono ninguna tarea");
        }

    }


    public   void  limpiarFormulario(){
        idTareaInterno=null;
        nombreTareasTexto.clear();
        responsableTextoTarea.clear();
        estatusTextoTarea.clear();
        estatusTextoTarea.clear();
    }

    private void  recolectarDatosFormulario(tarea tareas){
        if (idTareaInterno != null)
            tareas.setIdTarea(idTareaInterno);
        tareas.setNombreTarea(nombreTareasTexto.getText());
        tareas.setResponsable(responsableTextoTarea.getText());
        tareas.setEstatus(estatusTextoTarea.getText());

    }
    public  void  mostarMnesaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


}
