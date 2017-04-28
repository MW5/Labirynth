/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth.Model;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author mw5
 */
public class Runaway extends Rectangle {
    private Canvas canvas;
    private GraphicsContext gc;
    private Labirynth labirynth;
    public boolean goingLeft;
    public boolean goingUp;
    public boolean goingRight;
    public boolean goingDown;
    
    public Runaway() {
        goingLeft = true;
        goingUp = false;
        goingRight = false;
        goingDown = false;
    }

    
    public void setGraphicsContext (GraphicsContext gc) {
        this.gc = gc;
    }
    public void setLabirynth (Labirynth labirynth) {
        this.labirynth = labirynth;
    }
    public void setCanvas (Canvas canvas) {
        this.canvas = canvas;
    }
    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        labirynth.draw(gc);
        gc.setFill(Color.RED);
        gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }


}
