/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth.Model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author mw5
 */
public class Labirynth {
    private ArrayList<WallBlock> walls;
    private ArrayList<Walkway> walkways;
    private final int[][] maze;
    private final int mazeRows;
    private final int mazeCols;

    public Labirynth() {
            mazeRows = 11;
            mazeCols = 16;
            walls = new ArrayList<WallBlock>();
            walkways = new ArrayList<Walkway>();
            maze = new int[][]
            { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
              {0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,1},
              {1,0,1,0,0,0,1,0,1,1,1,0,1,1,0,1},
              {1,0,0,0,1,1,1,0,0,0,0,0,1,0,0,1},
              {1,0,1,0,0,0,0,0,1,1,1,0,1,1,1,1},
              {1,0,1,0,1,1,1,0,1,0,0,0,1,0,0,1},
              {1,1,1,0,1,0,0,0,1,1,1,0,0,0,1,1},
              {1,0,1,0,1,1,1,0,1,0,1,0,1,0,0,1},
              {1,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1},
              {1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1},
              {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
            };
//            { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//              {1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
//              {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//              {1,1,1,1,1,1,0,0,0,1,1,1,0,1,1,1},
//              {1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1},
//              {1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
//              {1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
//              {1,0,0,0,1,1,0,0,0,1,1,1,0,0,1,1},
//              {1,0,1,0,1,1,1,1,1,1,1,1,1,0,1,1},
//              {1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
//              {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
//            };
    }
    
    public int[][] getMaze() {
        return maze;
    }

    public void prepareLabirynth() {
        for (int i=0; i<mazeRows; i++) {
            for (int j=0; j<mazeCols; j++) {
                if (maze[i][j] == 1) {
                    WallBlock wB = new WallBlock();
                    wB.setWidth(40);
                    wB.setHeight(40);
                    wB.setX(j*(int)wB.getWidth());
                    wB.setY(i*(int)wB.getHeight());
                    walls.add(wB);
                } else {
                    Walkway w = new Walkway();
                    w.setWidth(40);
                    w.setHeight(40);
                    w.setX(j*(int)w.getWidth());
                    w.setY(i*(int)w.getHeight());
                    walkways.add(w);
                }
            }
        }
    }
    
    public ArrayList<WallBlock> getWalls() {
        return walls;
    }
    public ArrayList<Walkway> getWalkways() {
        return walkways;
    }
    
    public void draw (GraphicsContext gc) {
        for (WallBlock wall : walls) {
            gc.setFill(Color.BLACK);
            gc.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        }
    }
}
