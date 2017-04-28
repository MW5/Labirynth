
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
                        runaway.setWidth(30); //change
                        runaway.setHeight(30); //change
                        runaway.setX(walkway.getX()+5);
                        runaway.setY(walkway.getY()+5);
                        runaway.draw();
                        startAnimation();
                    }
                }
            }
        });
    }
    
    //doesn`t work so far
    private void tryTurn() {
        //for Y movement
        boolean tried = false;
        if (runaway.getY()%30 == 0) {
            runaway.goingLeft = false;
            runaway.goingUp = true;
            runaway.goingRight = false;
            runaway.goingDown = false;
        }
        tried = true;
    }
    
    private void goLeft(double speed) {
        double currX = runaway.getX();
        runaway.setX(currX-=speed);
    }
    private void goUp(double speed) {
        double currY = runaway.getY();
        runaway.setY(currY-=speed);
    }
    private void goRight(double speed) {
        double currX = runaway.getX();
        runaway.setX(currX+=speed);
    }
    private void goDown(double speed) {
        double currY = runaway.getY();
        runaway.setY(currY+=speed);
    }
    
    private void startAnimation() {
        loop = new AnimationTimer() {
            
            double endX = 0; //place of animation stop
            double speed =1;

            @Override
            public void handle(long now) {
                
                    for (WallBlock wall : walls) {
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingUp) {
                            System.out.println("collision");
                            double currY = runaway.getY();
                            runaway.setY(currY+5);
                            runaway.goingUp = false;
                            runaway.goingRight = false;
                            runaway.goingDown = false;
                            runaway.goingLeft = true;
                        }
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingRight) {
                            System.out.println("collision");
                            double currX = runaway.getX();
                            runaway.setX(currX-5);
                            runaway.goingUp = true;
                            runaway.goingRight = false;
                            runaway.goingDown = false;
                            runaway.goingLeft = false;
                        }
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingDown) {
                            System.out.println("collision");
                            double currY = runaway.getY();
                            runaway.setY(currY-5);
                            runaway.goingUp = false;
                            runaway.goingRight = true;
                            runaway.goingDown = false;
                            runaway.goingLeft = false;
                        }
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingLeft) {
                            System.out.println("collision");
                            double currX = runaway.getX();
                            runaway.setX(currX+5);
                            runaway.goingUp = false;
                            runaway.goingRight = false;
                            runaway.goingDown = true;
                            runaway.goingLeft = false;
                        }
                    }
                    if (runaway.goingLeft) {
                        goLeft(speed);
                    }
                    if (runaway.goingUp) {
                        goUp(speed);
                    }
                    if (runaway.goingRight) {
                        goRight(speed);
                    }
                    if (runaway.goingDown) {
                        goDown(speed);
                    }
                
                runaway.draw();
                System.out.println(runaway.getX());

                if( runaway.getX() <= endX) {
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
