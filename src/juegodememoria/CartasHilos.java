/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodememoria;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class CartasHilos implements Runnable {

    private Carta cartaActual, cartaAnterior;
    ArrayList<Carta> cartas;
    int[] intentos;
    ArrayList<Carta> cartasAnteriores;
    SceneBuilder sb=new SceneBuilder();
    

    public CartasHilos(Carta cartaActual, ArrayList<Carta> cartas, ArrayList<Carta> cartasAnteriores, int[] intentos,SceneBuilder sb) {
        this.cartaActual = cartaActual;
        this.cartas = cartas;
        this.intentos = intentos;
        this.cartasAnteriores = cartasAnteriores;
        this.sb=sb;
    }
//    
//    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
//        @Override
//        public void handle(MouseEvent me) {
//            me.consume();
//        }
//
//    };

    @Override
    public void run() {
        Image im =new Image("ImagenesCartas/"+cartaActual.getRutaCarta());
        ///////////////////////////////////////////////////////////////////////////////////////////////
        cartaActual.getImagenCarta().setImage(im); //cambiando de DC a el superheroe cuando se da click
        try {
            Thread.sleep(500);// Durmiendo el hilo para que sea posible observar que el par de cartas se mantienen
        } catch (InterruptedException ex) {System.out.println("Problemas técnicos. Estamos resolviendo");
            Logger.getLogger(CartasHilos.class.getName()).log(Level.SEVERE, null, ex);
        }  
        cartaActual.setEstaCubierta(false);//cambiando el estado de la carta a esta descubierta
        ////////////////////////////////////////////////////////////////////////////////////////////////
        if (!Memoria.isGameEnd()) {
            if (cartasAnteriores.isEmpty()) {
                cartasAnteriores.add(cartaActual);

            } else {// Aqui se debe contar los intentos

             
                if (cartasAnteriores.get(0).getRutaCarta().equals(cartaActual.getRutaCarta())
                        && !(cartasAnteriores.get(0).getNombre().equals(cartaActual.getNombre()))) {

                    System.out.println("Son iguales las 2 cartas");

                    cartaActual.getImagenCarta().addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
                        System.out.println("Consumiendo Evento");
                        evt.consume();
                    });
                    cartasAnteriores.get(0).getImagenCarta().addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
                        System.out.println("Consumiendo Evento");
                        evt.consume();
                    });

                } else {

                    Image im2=new Image("ImagenesCartas/"+Memoria.RUTA_CARTA_CUBIERTA);

                    cartaActual.getImagenCarta().setImage(im2);
                    cartasAnteriores.get(0).getImagenCarta().setImage(im2);
                   
                    cartaActual.setEstaCubierta(true);
                    cartasAnteriores.get(0).setEstaCubierta(true);
                }
                cartasAnteriores.clear();
                intentos[0] += 1;
            }
        } else {

            System.out.println("Se acabo el juego");
            System.out.println("Realizo: " + intentos[0] + " intentos");
            Platform.runLater(()->Timer.DetenerTimer());
            Platform.runLater(()->sb.FinalScene(intentos[0],new Stage()));
            
        }
    }

    

}
