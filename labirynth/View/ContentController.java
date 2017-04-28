
package labirynth.View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import labirynth.Model.Labirynth;
import labirynth.Model.Runaway;
import labirynth.Model.Walkway;
import labirynth.Model.WallBlock;

public class ContentController implements Initializable {
    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    private Labirynth labirynth;
    private ArrayList<WallBlock> walls;
    private ArrayList<Walkway> walkways;
    private Runaway runaway;
    public AnimationTimer loop;
    
    public void init() {
        labirynth = new Labirynth();
        gc = canvas.getGraphicsContext2D();
        //wB = new WallBlock(); //temp
        handleMouse();
        labirynth.prepareLabirynth();
        walls = labirynth.getWalls();
        walkways = labirynth.getWalkways();
        labirynth.draw(gc);
    }
    
    
    //restrict to choose state
    private void handleMouse() {
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                //System.out.println(me.getTarget());
                for (Walkway walkway : walkways) {
                    if (walkway.contains(me.getX(), me.getY())) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        labirynth.draw(gc);
                        gc.setStroke(Color.RED);
                        gc.strokeRect(walkway.getX(), walkway.getY(), walkway.getWidth(), walkway.getHeight());
                    }
                }
                for (WallBlock wall : walls) {
                    if (wall.contains(me.getX(), me.getY())) {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        labirynth.draw(gc);
                    }
                }
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                for (Walkway walkway : walkways) {
                    if (walkway.contains(me.getX(), me.getY())) {
                        runaway = new Runaway();
                        runaway.setCanvas(canvas);
                        runaway.setGraphicsContext(gc);
                        runaway.setLabirynth(labirynth);
                        runaway.setWidth(40);
                        runaway.setHeight(40);
                        runaway.setX(walkway.getX());
                        runaway.setY(walkway.getY());
                        runaway.draw();
                        startAnimation();
                        
                    }
                }
            }
        });
    }
    
    private void startAnimation() {
        loop = new AnimationTimer() {

            double startX = runaway.getX();
            double endX = 1000;
            double y = runaway.getY();
            double x = startX;
            double speed =1;

            @Override
            public void handle(long now) {
                
                runaway.setX(x+=speed);
                runaway.draw();
                System.out.println(runaway.getX());
                
                
                

                if( x >= endX) {
                    loop.stop();
                }
            }
        };

        loop.start();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
