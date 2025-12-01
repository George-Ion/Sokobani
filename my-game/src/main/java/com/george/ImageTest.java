package com.george;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ImageTest extends Application {

    @Override
    public void start(Stage stage) {

        var image = ImageLoader.load("player.png"); 
        
        ImageView view = new ImageView(image);

        StackPane root = new StackPane(view);
        Scene scene = new Scene(root, 300, 300); 
        stage.setTitle("Image Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}