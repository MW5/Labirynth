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
   
    private final int width;
    private final int height;
    private final int[][] maze;

    public Labirynth() {
            this.width = 10;
            this.height = 10;
            maze = new int[this.width][this.height];
            walls = new ArrayList<WallBlock>();
            generateMaze(0, 0);
    }

    public void prepareLabirynth() {
            for (int i = 0; i < height; i++) {
//                    // draw the north edge
//                    for (int j = 0; j < width; j++) {
//                            System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
//                    }
//                    System.out.println("+");
                    
                    //draw the west edge
                    for (int j = 0; j < width; j++) {
                            System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
                    }
                    System.out.println("|");
            }
//            // draw the bottom line
//            for (int j = 0; j < width; j++) {
//                    System.out.print("+---");
//            }
//            System.out.println("+");
        for (int i = 0; i < height; i++) {
            // draw the west edge //it WORKS BUT FIX THE MESS
            for (int j = 0; j < width; j++) {
                    if((maze[j][i] & 8) == 0) {
                        WallBlock wB = new WallBlock(j*40, i*20); // will have to be tweaked later
                        wB.setWidth(20);
                        wB.setHeight(20);
                        walls.add(wB);
                    };
            }
            WallBlock wB = new WallBlock(10*40, i*20);
            wB.setWidth(20);
            wB.setHeight(20);
            walls.add(wB);
        }
    }

    private void generateMaze(int cx, int cy) {
            DIR[] dirs = DIR.values();
            Collections.shuffle(Arrays.asList(dirs));
            for (DIR dir : dirs) {
                    int nx = cx + dir.dx;
                    int ny = cy + dir.dy;
                    if (between(nx, width) && between(ny, height)
                                    && (maze[nx][ny] == 0)) {
                            maze[cx][cy] |= dir.bit;
                            maze[nx][ny] |= dir.opposite.bit;
                            generateMaze(nx, ny);
                    }
            }
    }

    private static boolean between(int v, int upper) {
            return (v >= 0) && (v < upper);
    }

    private enum DIR {
            N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
            private final int bit;
            private final int dx;
            private final int dy;
            private DIR opposite;

            // use the static initializer to resolve forward references
            static {
                    N.opposite = S;
                    S.opposite = N;
                    E.opposite = W;
                    W.opposite = E;
            }

            private DIR(int bit, int dx, int dy) {
                    this.bit = bit;
                    this.dx = dx;
                    this.dy = dy;
            }
    };
    
    public void drawLabirynth (GraphicsContext gc) {
        for (WallBlock wall : walls) {
            gc.setFill(Color.BLACK);
            gc.fillRect(wall.getPosX(), wall.getPosY(), wall.getWidth(), wall.getHeight());
        }
    }
}
