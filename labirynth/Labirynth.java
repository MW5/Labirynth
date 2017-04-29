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
import labirynth.View.Labirynth_1Controller;
import labirynth.View.MenuController;

/**
 *
 * @author mw5
 */
public class Labirynth extends Application {
    public BorderPane root;
    public AnchorPane menu;
    public Canvas content;
    
    private int currentState;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        this.currentState = 0;
        initRoot();
        initMenu();
        initContent();
        
        Scene scene = new Scene(root);
        stage.setTitle("Labirynt");
        stage.setScene(scene);
        stage.show();
    }
    
    public int getState() {
        return currentState;
    }
    public void setState(int state) {
        this.currentState = state;
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
            MenuController controller = loader.getController();
            controller.setLabirynth(this);
            
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        
    }
    
    public void initContent() {
        if (currentState == 0) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("View/labirynth_1.fxml"));
                content = (Canvas) loader.load();
                root.setBottom(content);
                Labirynth_1Controller controller = loader.getController();
                controller.init();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
        if (currentState == 1) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("View/labirynth_2.fxml"));
                content = (Canvas) loader.load();
                root.setBottom(content);
                Labirynth_1Controller controller = loader.getController();
                //controller.init();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
