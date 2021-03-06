/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_pelicula;

import Graph.GraphLA;
import Graph.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Melina
 */
public class VentanaBacon {
    GraphLA<String> grafo = new GraphLA<>(false);
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
    public VentanaBacon() throws IOException{
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

    public void iniciar() throws IOException{
        generargrafo();

        titulo.setAlignment(Pos.CENTER);
        panel.getChildren().addAll(primerNombre,actor1);
        panel2.getChildren().addAll(segundoNombre,actor2);
        
        panel.setSpacing(34);
        panel2.setSpacing(20);
        contenedor.setSpacing(10);
        root.getChildren().add(new ImageView(new Image("recursos/walk-fame.jpg")));
        
        root.setTop(titulo);
        root.setLeft(contenedor);
        VBox hb=new VBox();
        VBox hb2=new VBox();
        find.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(actor1.getText().equals("")||actor2.getText().equals("")){
                   Alert alerta= new Alert(AlertType.ERROR);
                   alerta.setHeaderText("Ingrese Nombres");
                   alerta.showAndWait();
                }
                else{
                    hb.getChildren().clear();
                    hb2.getChildren().clear();
                    long tiempoInicio = System.currentTimeMillis();
                    List<Vertex<String>> list=grafo.caminoMinimo(actor1.getText(),actor2.getText());
                    long tiempoFin = System.currentTimeMillis();
                    
                    Label pasos= new Label("Pasos: "+ Integer.toString(grafo.menorDistancia(actor1.getText(),actor2.getText())));
                    Label tiempo= new Label(String.valueOf(tiempoFin-tiempoInicio)+ " Milisegundos");
                    for(Vertex<String> vert:list){
                        Circle circ=new Circle(30);
                        circ.setFill(Color.BURLYWOOD);
                        Label la=new Label(vert.getData());
                        StackPane sp=new StackPane();
                        
                        
                        sp.getChildren().addAll(circ,la);
                        if(vert.getAntecesor()!=null){
                            Rectangle rect=new Rectangle(150, 30);
                            rect.setFill(Color.GOLDENROD);
                            Label peli=new Label(vert.getPeliantec());
                            StackPane sp0=new StackPane();
                            sp0.getChildren().addAll(rect,peli);
                            hb.getChildren().add(sp0);
                        }
                        hb.getChildren().add(sp);
                        
                    }
                    
                    hb2.getChildren().addAll(hb,pasos,tiempo);
                }
            }
        });
        contenedor.getChildren().addAll(panel,panel2,find,hb2);
        
    }
    
    public BorderPane getRoot() {
        return root;
    }
    public void generargrafo() throws FileNotFoundException, IOException{
        /*
        FileReader fr=null;
        BufferedReader bf=null;
        try{
            File file=new File("src/Posibles_DataSet/IMDB-Movie-Data.csv");
            fr = new FileReader(file);
            bf=new BufferedReader(fr);
            String line=bf.readLine();
            while(bf.ready()){
                    String[] lin=line.split(";");
                    String[] act=lin[3].split(",");
                    for(int i=0;i<act.length;i++){
                        while(act[i].charAt(0)==' ' || act[i].charAt(act[i].length()-1)==' '){
                            if(act[i].charAt(0)==' ')
                                act[i]=act[i].substring(1);
                            if(act[i].charAt(act[i].length()-1)==' ')
                                act[i]=act[i].substring(0,act[i].length()-2);
                        }
                        grafo.addVertex(act[i]);
                    }
                    for(int i=0;i<act.length;i++)
                        for(int i0=i+1;i0<act.length;i0++)
                            grafo.addEdge(act[i], act[i0], 1, lin[1]);
                line=bf.readLine();
                }
        }
        catch (IOException e) {
             
        } finally {
            bf.close();
        }

        */
        File file=new File("src/Posibles_DataSet/IMDB-Movie-Data.csv");
        try (BufferedReader bf=new BufferedReader(new FileReader(file));){
            
            
            String line=bf.readLine();
            while(bf.ready()){
                String[] lin=line.split(";");
                String[] act=lin[3].split(",");
                for(int i=0;i<act.length;i++){
                    while(act[i].charAt(0)==' ' || act[i].charAt(act[i].length()-1)==' '){
                        if(act[i].charAt(0)==' ')
                            act[i]=act[i].substring(1);
                        if(act[i].charAt(act[i].length()-1)==' ')
                            act[i]=act[i].substring(0,act[i].length()-2);
                    }
                    grafo.addVertex(act[i]);
                }
                for(int i=0;i<act.length;i++)
                    for(int i0=i+1;i0<act.length;i0++)
                        grafo.addEdge(act[i], act[i0], 1, lin[1]);
            line=bf.readLine();
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaBacon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaBacon.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
    
    
        
    
    
    
    }
    
}
