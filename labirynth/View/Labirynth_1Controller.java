
package labirynth.View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import labirynth.Model.Labirynth_model;
import labirynth.Model.Runaway;
import labirynth.Model.Walkway;
import labirynth.Model.WallBlock;

public class Labirynth_1Controller implements Initializable {
    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    private Labirynth_model labirynth;
    private ArrayList<WallBlock> walls;
    private ArrayList<Walkway> walkways;
    public int[][] maze;
    private Runaway runaway;
    public AnimationTimer loop;
    
    private boolean startBlockChosen = false;
    private boolean victory = false;
    
    private int y;
    private int x;
    private char dirNow = 'N';
    int potato=0;
    
    public void init() {
        labirynth = new Labirynth_model();
        gc = canvas.getGraphicsContext2D();
        handleMouse();
        labirynth.prepareLabirynth();
        walls = labirynth.getWalls();
        walkways = labirynth.getWalkways();
        this.maze = labirynth.getMaze();
        labirynth.draw(gc);
    }
    
    private void handleMouse() {
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                if (!startBlockChosen) {
                    for (Walkway walkway : walkways) {
                        if (walkway.contains(me.getX(), me.getY())&& walkway.getX()>=40 && walkway.getY()>=40) {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            labirynth.draw(gc);
                            gc.setStroke(Color.RED);
                            gc.strokeRect(walkway.getX(), walkway.getY(), walkway.getWidth(), walkway.getHeight());
                        }
                        if (walkway.contains(me.getX(), me.getY()) && walkway.getX()<40 || walkway.getY()<40) {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            labirynth.draw(gc);
                        }
                    }
                    for (WallBlock wall : walls) {
                        if (wall.contains(me.getX(), me.getY())) {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            labirynth.draw(gc);
                        }
                    }
                }
            }
        });

        
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                if (!startBlockChosen) {
                    for (Walkway walkway : walkways) {
                        if (walkway.contains(me.getX(), me.getY()) && walkway.getX()>=40 && walkway.getY()>=40) {
                            startBlockChosen = true;
                            runaway = new Runaway();
                            runaway.setCanvas(canvas);
                            runaway.setGraphicsContext(gc);
                            runaway.setLabirynth(labirynth);
                            runaway.setWidth(40);
                            runaway.setHeight(40);
                            runaway.setX((int)walkway.getX()/40);
                            runaway.setY((int)walkway.getY()/40);
                            runaway.draw();
                            startAnimation();
                        }
                    }
                }
                if (victory) {
                    startBlockChosen = false;
                    victory = false;
                }
            }
        });
    }
    
    void walk(){
        x = (int)runaway.getX();
        y = (int)runaway.getY();
        if (dirNow == 'N'){
            if(maze[y-1][x]==0){
                    dirNow = 'W';
                    y--;
            }else if(maze[y][x-1]==0){
                    dirNow = 'N';
                    x--;
            }else if(maze[y+1][x]==0){
                    dirNow = 'E';
                    y++;
            }else if(maze[y][x+1]==0){
                    dirNow = 'S';
                    x++;
            }
        }
        //////////////////////////////
        else if (dirNow == 'E'){
            if(maze[y][x-1]==0){
                    dirNow = 'N';
                    x--;
            }else if(maze[y+1][x]==0){
                    dirNow = 'E';
                    y++;
            }else if(maze[y][x+1]==0){
                    dirNow = 'S';
                    x++;
            }else if(maze[y-1][x]==0){
                    dirNow = 'W';
                    y--;
            }
        }
        ///////////////////////////////////
        else if (dirNow == 'S'){
            if(maze[y+1][x]==0){
                    dirNow = 'E';
                    y++;
            }else if(maze[y][x+1]==0){
                    dirNow = 'S';
                    x++;
            }else if(maze[y-1][x]==0){
                    dirNow = 'W';
                    y--;
            }else if(maze[y][x-1]==0){
                    dirNow = 'N';
                    x--;
            }
        }
        ///////////////////////////
        else if (dirNow == 'W'){
            if(maze[y][x+1]==0){
                    dirNow = 'S';
                    x++;
            }else if(maze[y-1][x]==0){
                    dirNow = 'W';
                    y--;
            }else if(maze[y][x-1]==0){
                    dirNow = 'N';
                    x--;
            }else if(maze[y+1][x]==0){
                    dirNow = 'E';
                    y++;
            }
        }
        runaway.setX(x);
        runaway.setY(y);
    }
    int i = 0;
    private void startAnimation() {
        loop = new AnimationTimer() {
            private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 256_000_000) {
                    walk();	
                    potato++;
                    lastUpdate = now ;
                    runaway.draw();

                }
                if((y==1 && x==0 )) {
                    runaway.drawVictory();
                    victory = true;
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
