/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_pelicula;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Familia
 */
public class VentanaPrincipal {
    
    private BorderPane root;
    private Button btn;
    private ImageView back;
    
    public VentanaPrincipal(){
        root= new BorderPane();
        btn= new Button("Inicia");
        back=new ImageView(new Image("recursos/backGround1.jpg"));
        iniciar();
    }
    
    
    public void iniciar(){
        String estilos= "-fx-background-color:  #FFFFFF;";
        btn.setStyle(estilos);
        btn.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(back);
        root.setCenter(btn);
        
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                VentanaBacon vn=new VentanaBacon();
                Proyecto_pelicula.escena.setRoot(vn.getRoot());
                
            }
        
            });
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    
}
