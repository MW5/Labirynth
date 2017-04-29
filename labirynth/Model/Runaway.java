/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth.Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author mw5
 */
public class Runaway extends Rectangle {
    private Canvas canvas;
    private GraphicsContext gc;
    private Labirynth_model labirynth;
    private String victory;
    private String instructions;
    
    public void setGraphicsContext (GraphicsContext gc) {
        this.gc = gc;
    }
    public void setLabirynth (Labirynth_model labirynth) {
        this.labirynth = labirynth;
    }
    public void setCanvas (Canvas canvas) {
        this.canvas = canvas;
    }
    public void draw() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        labirynth.draw(gc);
        gc.setFill(Color.RED);
        gc.fillRect(this.getX()*40, this.getY()*40, this.getWidth(), this.getHeight());
    }
    public void drawVictory() {
        victory = "Zwycienstfo!";
        instructions = "Kliknij gdziekolwiek aby rozpocząć ponownie";
        Color myColour = new Color(0, 0, 0, 0.5);
        gc.setFill(myColour);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        Font f = new Font(70);
        gc.setFont(f);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.RED);
        gc.fillText(victory, canvas.getWidth()/2, canvas.getHeight()/2);
        f = new Font(25);
        gc.setFont(f);
        gc.setFill(Color.RED);
        gc.fillText(instructions, canvas.getWidth()/2, canvas.getHeight()/2+70);
    }

}
