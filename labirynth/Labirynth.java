/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirynth;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import labirynth.View.ContentController;

/**
 *
 * @author mw5
 */
public class Labirynth extends Application {
    public BorderPane root;
    public AnchorPane menu;
    public Canvas content;
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        initRoot();
        initMenu();
        initContent();
        
        Scene scene = new Scene(root);
        stage.setTitle("Labirynt");
        stage.setScene(scene);
        stage.show();
    }
    

    
    public void initRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("View/rootPane.fxml"));
            root = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void initMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("View/menu.fxml"));
            menu = (AnchorPane) loader.load();
            root.setTop(menu);
            
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        
    }
    
    public void initContent() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("View/content.fxml"));
            content = (Canvas) loader.load();
            root.setBottom(content);
            ContentController controller = loader.getController();
            controller.init();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
