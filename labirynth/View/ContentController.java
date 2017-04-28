
package labirynth.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import labirynth.Model.Labirynth;
import labirynth.Model.WallBlock;

public class ContentController implements Initializable {
    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    private Labirynth labirynth;
    private WallBlock wB; //make a list of these
    
    public void init() {
        labirynth = new Labirynth();
        gc = canvas.getGraphicsContext2D();
        //wB = new WallBlock(); //temp
        handleMouse();
        labirynth.prepareLabirynth();
        labirynth.drawLabirynth(gc);
    }
    
    private void handleMouse() {
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
