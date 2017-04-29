
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
    public int[][] maze;
    private Runaway runaway;
    public AnimationTimer loop;
    
    private boolean startBlockChosen = false;
    
    
    private int x; //startpoints
    private int y;
    static char dirNow = 'N';
    int potato=0;
    
    public void init() {
        labirynth = new Labirynth();
        gc = canvas.getGraphicsContext2D();
        //wB = new WallBlock(); //temp
        handleMouse();
        labirynth.prepareLabirynth();
        walls = labirynth.getWalls();
        walkways = labirynth.getWalkways();
        this.maze = labirynth.getMaze();
        labirynth.draw(gc);
    }
    
    
    //restrict to choose state
    private void handleMouse() {
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                if (!startBlockChosen) {
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
            }
        });

        
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                for (Walkway walkway : walkways) {
                    if (walkway.contains(me.getX(), me.getY())) {
                        y = (int)walkway.getX()/40;
                        x = (int)walkway.getY()/40;
                        startBlockChosen = true;
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
    
    void walk(){
        if (dirNow == 'N'){
                if(maze[x-1][y]==0){
                        dirNow = 'W';
                        x--;
                }else if(maze[x][y-1]==0){
                        dirNow = 'N';
                        y--;
                }else if(maze[x+1][y]==0){
                        dirNow = 'E';
                        x++;
                }else if(maze[x][y+1]==0){
                        dirNow = 'S';
                        y++;
                }
        }
        //////////////////////////////
        else if (dirNow == 'E'){
                if(maze[x][y-1]==0){
                        dirNow = 'N';
                        y--;
                }else if(maze[x+1][y]==0){
                        dirNow = 'E';
                        x++;
                }else if(maze[x][y+1]==0){
                        dirNow = 'S';
                        y++;
                }else if(maze[x-1][y]==0){
                        dirNow = 'W';
                        x--;
                }
        }
        ///////////////////////////////////
        else if (dirNow == 'S'){
                if(maze[x+1][y]==0){
                        dirNow = 'E';
                        x++;
                }else if(maze[x][y+1]==0){
                        dirNow = 'S';
                        y++;
                }else if(maze[x-1][y]==0){
                        dirNow = 'W';
                        x--;
                }else if(maze[x][y-1]==0){
                        dirNow = 'N';
                        y--;
                }
        }
        ///////////////////////////
        else if (dirNow == 'W'){
                if(maze[x][y+1]==0){
                        dirNow = 'S';
                        y++;
                }else if(maze[x-1][y]==0){
                        dirNow = 'W';
                        x--;
                }else if(maze[x][y-1]==0){
                        dirNow = 'N';
                        y--;
                }else if(maze[x+1][y]==0){
                        dirNow = 'E';
                        x++;
                }
            }
            gc.fillRect(y*40, x*40, 40, 40);
        }
    
    private void startAnimation() {
        loop = new AnimationTimer() {
            
            double endX = 0; //place of animation stop
            double speed = 0.5;

            @Override
            public void handle(long now) {
                
                
                while(!(x==1 && y==1 )){
                        walk();	
                        potato++;
                        //runaway.draw();
                }
                
                
//                runaway.draw();

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
