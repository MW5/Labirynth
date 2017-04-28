
package labirynth.View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import labirynth.Model.Labirynth;
import labirynth.Model.Walkway;
import labirynth.Model.WallBlock;

public class ContentController implements Initializable {
    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    private Labirynth labirynth;
    private ArrayList<WallBlock> walls;
    private ArrayList<Walkway> walkways;
    
    public void init() {
        labirynth = new Labirynth();
        gc = canvas.getGraphicsContext2D();
        //wB = new WallBlock(); //temp
        handleMouse();
        labirynth.prepareLabirynth();
        walls = labirynth.getWalls();
        walkways = labirynth.getWalkways();
        labirynth.drawLabirynth(gc);
    }
    
    private void handleMouse() {
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                //System.out.println(me.getTarget());
                for (Walkway walkway : walkways) {
                    if (walkway.contains(me.getX(), me.getY())) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        labirynth.drawLabirynth(gc);
                        gc.setStroke(Color.RED);
                        gc.strokeRect(walkway.getX(), walkway.getY(), walkway.getWidth(), walkway.getHeight());
                    }
                }
                for (WallBlock wall : walls) {
                    if (wall.contains(me.getX(), me.getY())) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        labirynth.drawLabirynth(gc);
                    }
                }
                
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                //System.out.println(me.getTarget());
                for (Walkway walkway : walkways) {
                    if (walkway.contains(me.getX(), me.getY())) {
                        System.out.println("walkway");
                    }
                }
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
