
package labirynth.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class ContentController implements Initializable {
    @FXML
    public Canvas canvas;
    
    public void init() {
        handleMouse();
    }
    
    public void handleMouse() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle (MouseEvent me) {
                System.out.println("ok"); //WORKS SO FAR
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
