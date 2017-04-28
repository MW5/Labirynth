/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author mw5
 */
public class Labirynth {
    private ArrayList<WallBlock> walls;
    private final int[][] maze;
    private final int mazeRows;
    private final int mazeCols;

    public Labirynth() {
            mazeRows = 11;
            mazeCols = 16;
            walls = new ArrayList<WallBlock>();
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
    }

    public void prepareLabirynth() {
        for (int i=0; i<mazeRows; i++) {
            for (int j=0; j<mazeCols; j++) {
                if (maze[i][j] == 1) {
                    WallBlock wB = new WallBlock();
                    wB.setWidth(40);
                    wB.setHeight(40);
                    wB.setPosX(j*(int)wB.getWidth());
                    wB.setPosY(i*(int)wB.getHeight());
                    walls.add(wB);
                }
            }
        }
    }
    
    public void drawLabirynth (GraphicsContext gc) {
        for (WallBlock wall : walls) {
            gc.setFill(Color.BLACK);
            gc.fillRect(wall.getPosX(), wall.getPosY(), wall.getWidth(), wall.getHeight());
        }
    }
}
