/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegodememoria;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * @author Luis A. Sarango-Parrales
 *
 */
public class Timer extends Button {

    public static Timeline timeline;
    private final StringProperty timeSeconds = new SimpleStringProperty();
    private Duration time = Duration.ZERO;
    private boolean active;
    private static Label TiempoTranscurrido = new Label();

    public Timer() {
        this.textProperty().bind(timeSeconds);
        this.setOnAction(eve -> {
            start();
        });
        reset();
    }

    public void start() {

        if (active) {
            timeline.stop();
            active = false;
            timeSeconds.set(makeText(time));
//            String sub=makeText(time).substring(0, makeText(time).length()-1);
//            TiempoTranscurrido.setText(sub);

            return;
        }

        active = true;
        if (timeline == null) {
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(100),
                            e2 -> {
                                if (!active) {
                                    return;
                                }
                                final Duration duration = ((KeyFrame) e2.getSource()).getTime();
                                time = time.add(duration);
                                timeSeconds.set(makeText(time));
                                String sub = makeText(time).substring(0, makeText(time).length() - 1);
                                TiempoTranscurrido.setText("Tiempo Transcurrido: "+sub);
                                
                            }
                    )
            );
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public String makeText(Duration duration) {
        return String.format("%02d:%02d:%02d",
                (long) (duration.toMinutes() % 60.0),
                (long) (duration.toSeconds() % 60.0),
                (long) (duration.toMillis() % 60.0))
                + (active ? "▶" : "■");
    }

    public void reset() {

        time = Duration.ZERO;
        timeSeconds.set(makeText(time));
    }

    public boolean isActive() {
        return active;
    }

    public Label getTiempoTranscurrido() {
        return TiempoTranscurrido;
    }

    public static void DetenerTimer(){
        
            timeline.stop();
            
    }
  

}
