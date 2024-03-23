package com.sb.tareas.presentacion;

import com.sb.tareas.TareasApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class SistemaTareasFx extends Application {
    private ConfigurableApplicationContext applicationContext;

      // public static void main(String[] args) {
     //   launch(args);
    //}

    public  void  init(){
        this.applicationContext = new SpringApplicationBuilder(TareasApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //este metodo nos ayuda a cargar y obtener los datos
        FXMLLoader loder = new FXMLLoader(TareasApplication.class.getResource("/templates/index.fxml"));
        loder.setControllerFactory(applicationContext::getBean);

        Scene escena = new Scene(loder.load());
        stage.setScene(escena);

        stage.show();

    }

    @Override
    public  void  stop(){
        applicationContext.close();
        Platform.exit();
    }
}
