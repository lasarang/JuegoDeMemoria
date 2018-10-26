/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodememoria;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luis A. Sarango-Parrales
 */

public class JuegoDeMemoria extends Application {
    Scene AskExit, Game;
    
    @Override
    public void start(Stage primaryStage) { 
        
        SceneBuilder sc = new SceneBuilder(primaryStage);
        sc.initialScene(); 
        
    }

    /**
     * @param a the command line arguments
     */
    public static void main(String[] a) {
        launch(a);
    }
    
   
    
}
