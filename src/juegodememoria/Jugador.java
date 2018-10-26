/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodememoria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Luis A.Sarango-Parrales
 */
public class Jugador {

    private String name, tiempoJugado;
    private int NroIntentos;
    private String fecha;

    public Jugador(){}
    
    public Jugador(String name,String tiempoJugado,String NroIntentos,String fecha) {
        this.name = name;
        this.NroIntentos = Integer.parseInt(NroIntentos);
        this.tiempoJugado =tiempoJugado;
        this.fecha = fecha;
    }

    public String FechaDeHoy() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate); // 19/11/2018 
    }

    public String getName() {
        return name;
    }

    public String getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(String tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public int getNroIntentos() {
        return NroIntentos;
    }

    public void setNroIntentos(int NroIntentos) {
        this.NroIntentos = NroIntentos;
    }

    public String getFecha() {
        return fecha;
    }

    public void AgregarJugadorHistorial() {
        File f = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            f = new File("HistorialJugadores.txt");
            fos = new FileOutputStream(f, true);
            pw = new PrintWriter(fos);
            pw.println(this.getName() +","+this.getTiempoJugado() +","+ this.getNroIntentos() +","+ this.getFecha());
        } catch (Exception e1) {e1.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

    }

    public static ArrayList<String> leerHistorial() {
        ArrayList<String> datosHistorial = new ArrayList();
        File archivo = null; FileReader fr = null; BufferedReader br = null;
        try {
            archivo = new File("HistorialJugadores.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                datosHistorial.add(linea);
            }

        } catch (Exception e) {
            GridPane rt = new GridPane();
            rt.add(new Label("Lo sentimos. Ha habido un problema :("), 0, 0);
            Scene sProb = new Scene(rt, 300, 300);
            Stage stProblema = new Stage();
            stProblema.setScene(sProb);
            stProblema.show();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return datosHistorial;
    }

    public static void generarTableView(ArrayList<String> al) {
        TableView<Jugador> tabla = new TableView<Jugador>();
        tabla.setPrefWidth(310);
        Label lb = new Label("Lista de ganadores");
        lb.setFont(new Font("Tahoma", 30));
        TableColumn nombreColum = new TableColumn("Nombre");
        TableColumn tiempoColum = new TableColumn("Tiempo");
        TableColumn intentosColum = new TableColumn("Intentos");
        TableColumn fechaColum = new TableColumn("Fecha");
        tabla.getColumns().addAll(nombreColum, tiempoColum, intentosColum, fechaColum);
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.add(lb, 0, 0);
        root.add(tabla, 0, 1);

        final ObservableList<Jugador> data = FXCollections.observableArrayList();
        for (String st : al) {
            String[] stList = st.split(",");
            System.out.println(Arrays.asList(stList));
            Jugador p = new Jugador(stList[0],stList[1],stList[2],stList[3]);
            data.add(p);
        }

        nombreColum.setCellValueFactory(new PropertyValueFactory<Object,Object>("name"));
        
        tiempoColum.setCellValueFactory(new PropertyValueFactory<Object,Object>("tiempoJugado"));
        
        intentosColum.setCellValueFactory(new PropertyValueFactory<Object,Object>("NroIntentos"));
        
        fechaColum.setCellValueFactory(new PropertyValueFactory<Object,Object>("fecha"));
        
        tabla.setItems(data);

        Scene sc = new Scene(root, 500, 500);
        Stage stGanadores = new Stage();
        stGanadores.setTitle("Juego de memoria -  Ganadores");
        stGanadores.setScene(sc);
        stGanadores.show();
    }

}
