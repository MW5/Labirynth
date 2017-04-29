/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import labirynth.Labirynth;

/**
 * FXML Controller class
 *
 * @author mw5
 */
public class MenuController implements Initializable {

    public Labirynth labirynth;
    
    public void setLabirynth(Labirynth labirynth) {
        this.labirynth = labirynth;
    }
    
    @FXML
    public void reset(ActionEvent action) {
        labirynth.initContent();
    }
    @FXML
    public void handleStateChange_0(ActionEvent action) {
        labirynth.setState(0);
        labirynth.initContent();
    }
    @FXML
    public void handleStateChange_1(ActionEvent action) {
        labirynth.setState(1);
        labirynth.initContent();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
