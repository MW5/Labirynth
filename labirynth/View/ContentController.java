
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
    
    private double colDist = 0;
    private double interDist = 0;
    private final double minColDist = 10; //down
    private final double tryInterval = 80;
    
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
                //test
                for (WallBlock wB : walls) {
                    if (wB.contains(me.getX(), me.getY())) {
//                        System.out.println(wB.getX());
//                        System.out.println(wB.getY());
                    }
                }
            }
        });
    }
    
    private void goLeft(double speed) {
        runaway.goingUp = false;
        runaway.goingRight = false;
        runaway.goingDown = false;
        runaway.goingLeft = true;
        double currX = runaway.getX();
        runaway.setX(currX-=speed);
        colDist+=1;
        interDist+=1;
        System.out.println("Col dist:" + colDist);
        System.out.println("Inter dist:" + interDist);
    }
    private void goUp(double speed) {
        double currY = runaway.getY();
        runaway.setY(currY-=speed);
        runaway.goingUp = true;
        runaway.goingRight = false;
        runaway.goingDown = false;
        runaway.goingLeft = false;
        colDist+=1;
        interDist+=1;
        System.out.println("Col dist:" + colDist);
        System.out.println("Inter dist:" + interDist);

    }
    private void goRight(double speed) {
        double currX = runaway.getX();
        runaway.setX(currX+=speed);
        runaway.goingUp = false;
        runaway.goingRight = true;
        runaway.goingDown = false;
        runaway.goingLeft = false;
        colDist+=1;
        interDist+=1;
        System.out.println("Col dist:" + colDist);
        System.out.println("Inter dist:" + interDist);
    }
    private void goDown(double speed) {
        double currY = runaway.getY();
        runaway.setY(currY+=speed);
        runaway.goingUp = false;
        runaway.goingRight = false;
        runaway.goingDown = true;
        runaway.goingLeft = false;
        colDist+=1;
        interDist+=1;
        System.out.println("Col dist:" + colDist);
        System.out.println("Inter dist:" + interDist);
    }
    
    private void startAnimation() {
        loop = new AnimationTimer() {
            
            double endX = 0; //place of animation stop
            double speed = 0.5;

            @Override
            public void handle(long now) {
                
                    for (WallBlock wall : walls) {
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingUp) {
//                            System.out.println(colDist);
                            double currY = runaway.getY();
                            runaway.setY(currY+5);
                            System.out.println("Coll UP");
                            if (colDist==minColDist) {
                                colDist = 0;
                                interDist = 0;
                                goRight(speed);
                            } else {
                                colDist = 0;
                                interDist = 0;
                                goLeft(speed);
                            }
                        }
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingRight) {
//                            System.out.println(colDist);
                            double currX = runaway.getX();
                            runaway.setX(currX-5);
                            System.out.println("Coll RIGHT");
                            if (colDist==minColDist) {
                                colDist = 0;
                                interDist = 0;
                                goDown(speed);
                            } else {
                                colDist = 0;
                                interDist = 0;
                                goUp(speed);
                            }
                        }
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingDown) {
//                            System.out.println(colDist);
                            double currY = runaway.getY();
                            runaway.setY(currY-5);
                            System.out.println("Coll DOWN");
                            if (colDist==minColDist) {
                                colDist = 0;
                                interDist = 0;
                                goLeft(speed);
                            } else {
                                colDist = 0;
                                interDist = 0;
                                goRight(speed);
                            }
                        }
                        if (runaway.intersects(wall.getBoundsInLocal()) && runaway.goingLeft) {
                            double currX = runaway.getX();
                            runaway.setX(currX+5);
                            System.out.println("Coll LEFT");
                            if (colDist==minColDist) {
                                colDist = 0 ;
                                interDist = 0;
                                goUp(speed);
//                                System.out.println("UP");
                            } else {
                                colDist = 0;
                                interDist = 0;
                                goDown(speed);
//                                System.out.println("DOWN"+dist);
                            }
                        }
                    }
                    
                    if (runaway.goingLeft) {
//                        if(interDist == tryInterval) {
//                            System.out.println("try down");
//                            interDist = 0;
//                            goDown(speed);
//                        } else {
                            goLeft(speed);
//                        }
                    }
                    if (runaway.goingUp) {
//                        System.out.println(dist);
//                        if(interDist == tryInterval) {
//                            interDist = 0;
//                            goLeft(speed);
//                        } else {
                            goUp(speed);
//                        }
                    }
                    if (runaway.goingRight) {
//                        System.out.println(dist);
//                        if(interDist == tryInterval) {
//                            interDist = 0;
//                            goUp(speed);
//                        } else {
                            goRight(speed);
//                        }
                    }
                    if (runaway.goingDown) {
//                        System.out.println(dist);
//                        if(interDist == tryInterval) {
//                            interDist = 0;
//                            goRight(speed);
//                        } else {
                            goDown(speed);
//                        }
                    }
                
                runaway.draw();

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
