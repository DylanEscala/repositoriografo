/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_pelicula;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Melina
 */
public class VentanaBacon {
    
    private BorderPane root;
    private Label titulo;
    private Label primerNombre;
    private Label segundoNombre;
    private TextField actor1;
    private TextField actor2;
    private Button find;
    private HBox panel;
    private HBox panel2;
    private VBox contenedor;
    public VentanaBacon(){
        root= new BorderPane();
        titulo= new Label("The Oracle");
        primerNombre=new Label("Nombre del Primer Actor/Actriz");
        segundoNombre=new Label("Nombre del Segundo Actor/Actriz");
        actor1=new TextField("");
        actor2=new TextField("");
        find= new Button("Encuentra la Relacion");
        panel= new HBox();
        panel2=new HBox();
        contenedor= new VBox();
        iniciar();
    }

    public void iniciar(){
       
        titulo.setAlignment(Pos.CENTER);
        root.setTop(titulo);
        panel.getChildren().addAll(primerNombre,actor1);
        panel2.getChildren().addAll(segundoNombre,actor2);
        contenedor.getChildren().addAll(panel,panel2,find);
        panel.setSpacing(34);
        panel2.setSpacing(20);
        root.setLeft(contenedor);
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    
    
    
}
