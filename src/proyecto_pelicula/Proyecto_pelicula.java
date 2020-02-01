 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_pelicula;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Familia
 */
public class Proyecto_pelicula extends Application {
    public static Scene escena;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       VentanaPrincipal nuevo= new VentanaPrincipal();
       escena= new Scene(nuevo.getRoot(),487,495);
       primaryStage.setTitle("The Oracle");
       primaryStage.setScene(escena);
       primaryStage.show();
       
       
    }
    
}
