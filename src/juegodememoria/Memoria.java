package juegodememoria;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Memoria {

    public static int FILAS, COLUMNAS;// Estatico Para poder utilizarlas en isGameEnd()
    public static ArrayList<Carta> cartas;// Estatico Para poder utilizarlas en isGameEnd()
    public static final String RUTA_CARTA_CUBIERTA = "DcComics.jpg";

    public Memoria(int FILAS, int COLUMNAS) {
        this.FILAS = FILAS;
        this.COLUMNAS = COLUMNAS;
        this.cartas = new ArrayList();
        llenarCartas(); ResizeGame();// Invocando el nuevo metodo para poder modificar los pares de la lista de cartas
    }

    public void llenarCartas() {
        this.cartas.add(new Carta("Batman1", "Batman.jpg"));
        this.cartas.add(new Carta("Batman2", "Batman.jpg"));

        this.cartas.add(new Carta("Superman1", "Superman.jpg"));
        this.cartas.add(new Carta("Superman2", "Superman.jpg"));

        this.cartas.add(new Carta("WonderWoman1", "WonderWoman.jpg"));
        this.cartas.add(new Carta("WonderWoman2", "WonderWoman.jpg"));

        this.cartas.add(new Carta("GreenLantern1", "GreenLantern.jpg"));
        this.cartas.add(new Carta("GreenLantern2", "GreenLantern.jpg"));

        this.cartas.add(new Carta("Flash1", "Flash.jpg"));
        this.cartas.add(new Carta("Flash2", "Flash.jpg"));

        this.cartas.add(new Carta("Aquaman1", "Aquaman.jpg"));
        this.cartas.add(new Carta("Aquaman2", "Aquaman.jpg"));

        this.cartas.add(new Carta("Cyborg1", "Cyborg.jpg"));
        this.cartas.add(new Carta("Cyborg2", "Cyborg.jpg"));

        this.cartas.add(new Carta("Shazam1", "Shazam.jpg"));
        this.cartas.add(new Carta("Shazam2", "Shazam.jpg"));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int obtenerAleatorio(int limiteHasta) {
        Random rdn = new Random();
        return rdn.nextInt(limiteHasta);
    }

    public static ImageView generarIv(String rutaIm, int x, int y) {
        try {
            ImageView iv = new ImageView("ImagenesCartas/"+rutaIm);
            iv.setFitHeight(y);
            iv.setFitWidth(x);
            return iv;
        } catch (Exception e) {
            System.out.println("Problemas técnicos. Estamos resolviendo");
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public GridPane setGame() {
        GridPane pane = new GridPane();
        int c = 0;   SceneBuilder sb=new SceneBuilder();
        Timer cronometro = new Timer();

        Collections.shuffle(cartas);
        int[] intentos = {0};
        ArrayList<Carta> cartasAnteriores = new ArrayList<>();
        //////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < this.FILAS; i++) {
            for (int j = 0; j < this.COLUMNAS; j++) {

                Carta cartaActual = cartas.get(c);

                EventHandler<MouseEvent> handler = (MouseEvent event) -> {
                    Runnable r=new CartasHilos(cartaActual,cartas,cartasAnteriores ,intentos, sb);
                    Thread t= new Thread(r); t.start();

                };

                cartaActual.getImagenCarta().addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

                pane.add(cartaActual.getImagenCarta(), j, i);

                c++;
            }
        }

        return pane;
    }

    public static boolean isGameEnd() {//Se Modificó
        int c = 0;
        for (Carta carta : cartas) {
            if (!carta.isEstaCubierta()) {
                c++;
            }
        }
        if (c == (FILAS*COLUMNAS) ){
            return true;

        } else {
            return false;
        }
    }
public void ResizeGame(){///Cambia la cantidad de filas y columnas de las cartas
    List<Carta[]> cartasEscogidas= new ArrayList<>(); 
    for(int i=0; i<this.cartas.size();i++){
        if ((i%2)==0){
            Carta[] par={this.cartas.get(i),this.cartas.get(i+1)};
            cartasEscogidas.add(par);    
        }
    }
    Collections.shuffle(cartasEscogidas); this.cartas.clear();
    for (int j=0; j<((this.FILAS*this.COLUMNAS)/2);j++){
        Carta[] par=cartasEscogidas.get(j); this.cartas.add(par[0]);this.cartas.add(par[1]);
    }
     
}
    
//    public static void main(String[] a) {
//
//    }
//
//}

}
