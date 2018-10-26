package juegodememoria;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class SceneBuilder{
    public Button start = new Button("Start"); public Button reset = new Button("Reset");
    public Timer cronometro = new Timer();
    String botonStyle="-fx-background-color: crimson;\n" +
                      "  -fx-text-fill: white;\n" +
                      "-fx-font-family: impact;\n"+
                      "-fx-font-size: 20px;\n"+
                      "  -fx-background-radius: 16px;";
    public static Stage pStage= new Stage();//Para que pueda modificarse en desde cualquier clase
    
    public SceneBuilder(){
        
    }
    
    public SceneBuilder(Stage st){
        this.pStage = st;
    }
    
    public void initialScene(){
        ///////////////////////////////////////////////////////////////////////
        Button botonJugar = new Button("Jugar");
        botonJugar.setMaxSize(125, 225); 
        botonJugar.setStyle(botonStyle); 
        ////////////////////////////////////////////////////////////////////////
        Button botonHistorial= new Button("Historial");
        botonHistorial.setMaxSize(125, 225); 
        botonHistorial.setStyle(botonStyle); 
        ////////////////////////////////////////////////////////////////////////
        Button botonSalir= new Button("Salir") ;
        botonSalir.setMaxSize(125,225);
        botonSalir.setStyle(botonStyle);
        Label l=new Label("¿Seguro que desea salir?");
        Button botonCancelar= new Button("CANCELAR"), botonAceptar=new Button("ACEPTAR");
        HBox pane1= new HBox(); VBox pane2= new VBox();
        pane1.getChildren().addAll(botonAceptar,botonCancelar);
        pane1.setAlignment(Pos.CENTER);
        pane1.setSpacing(30);
        pane2.getChildren().addAll(l,pane1);
        pane2.setAlignment(Pos.CENTER);  
        pane2.setSpacing(50);
        Scene AskExit=new Scene(pane2,280,170);
        ////////////////////////////////////////////////////////////////////////
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER_RIGHT);// Alineamiento en e centro de los botones dentro de un VBox
        root.getChildren().addAll(botonJugar,botonHistorial,botonSalir);
        root.setSpacing(40); // Colocando el mismo espacio entre los botones del VBox
     
        Scene scene = new Scene(root, 900,506);
     
        pStage.setTitle("Juego de Memoria");
        pStage.setScene(scene);
        pStage.setResizable(false);
        pStage.show();
        ////////////////////////////////////////////////////////////////////////
        botonSalir.setOnAction(e->pStage.setScene(AskExit));   
        botonCancelar.setOnAction(e->pStage.setScene(scene));
        botonHistorial.setOnAction((ActionEvent event)->{Jugador.generarTableView(Jugador.leerHistorial()); 
    });
        botonAceptar.setOnAction(e->pStage.close());
        botonJugar.setOnAction((ActionEvent event) -> {GameScene();start.setText(cronometro.isActive() ? "Start" : "Stop");
            cronometro.start();}); 
        ////////////////////////////////////////////////////////////////////////
        root.setStyle("-fx-background-image: url('ImagenesCartas/JusticeLeague4.jpg');"
                + "-fx-background-repeat: stretch;   \n" +
"    -fx-background-size: 900 506;\n" +
"    -fx-background-position: center center;\n" +
"    -fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0); ");
    }
    
    public void GameScene(){
        VBox root=new VBox();
        
        
        Memoria m=new Memoria(3,4);
        GridPane pane = m.setGame();

        
        cronometro.setStyle("-fx-font-size: 4em;");
        
        start.setOnAction(eve -> {
            start.setText(cronometro.isActive() ? "Stop" : "Start");
            cronometro.start();
        });
        
        
        //reset.setOnAction(eve -> {cronometro.reset();});
        ImageView iv=Memoria.generarIv("Logo.jpg", 90, 90);
        HBox hb =new HBox(cronometro,iv); //
        hb.setAlignment(Pos.CENTER); 
        root.getChildren().addAll(hb,pane);
       
        
        Scene Game=new Scene(root);
        
        pStage.setScene(Game);
        pStage.show();
        pStage.setResizable(false);
        
    }

    public void FinalScene(int intentos,Stage pStage ){ 
       
                        StackPane root= new StackPane();
                        Label l=new Label("Felicitaciones Ganaste!!!\nNumero de intentos: " + intentos);
                        ////////////////////////////////////////////////////////////////////////////////
                        Button salir=new Button("Salir");
                        salir.setOnAction(e->{pStage.close();this.pStage.close();});
                        ////////////////////////////////////////////////////////////////////////////////
                        VBox vb=new VBox();
                        vb.getChildren().addAll(l,cronometro.getTiempoTranscurrido(),salir);
                        vb.setAlignment(Pos.CENTER); 
                        root.getChildren().add(vb);
                        root.setAlignment(Pos.CENTER);
                        root.setPadding(new Insets(15));
                        pStage.setScene(new Scene(root));
                        pStage.setTitle("Juego de Memoria");
                        pStage.show(); 
    }
    
    
    
    
}
    
    
 


