/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth.Model;

import javafx.scene.shape.Rectangle;


public class WallBlock extends Rectangle {
    private int posX;
    private int posY;
    
    public WallBlock() {
        this.posX = posX;
        this.posY = posY;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

}
