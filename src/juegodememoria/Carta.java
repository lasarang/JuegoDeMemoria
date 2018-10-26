package juegodememoria;


import javafx.scene.image.ImageView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Luis A. Sarango-Parrales
 */
public class Carta {
    private String nombre, rutaCarta; ImageView imagenCarta=Memoria.generarIv(Memoria.RUTA_CARTA_CUBIERTA,100,150); 
    boolean estaCubierta=true;

    public Carta(String nombre, String rutaCarta) {
        this.nombre = nombre;
        this.rutaCarta = rutaCarta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaCarta() {
        return rutaCarta;
    }

    public void setRutaCarta(String rutaCarta) {
        this.rutaCarta = rutaCarta;
    }

    public ImageView getImagenCarta() {
        return imagenCarta;
    }

    public void setImagenCarta(ImageView imagenCarta) {
        this.imagenCarta = imagenCarta;
    }

    public boolean isEstaCubierta() {
        return estaCubierta;
    }

    public void setEstaCubierta(boolean estaCubierta) {
        this.estaCubierta = estaCubierta;
    }
    

}
