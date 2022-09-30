package it.college.congratulations.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    public static void openOtherWindow(String window, Button button){
        button.getScene().getWindow().hide();
        openNewWindow(window);
    }
    public static void openOtherWindow(String window, ImageView imageView){
        imageView.getScene().getWindow().hide();
        openNewWindow(window);
    }
    public static void openOtherWindow(String window, Label label){
        label.getScene().getWindow().hide();
        openNewWindow(window);
    }
    public static void openOtherWindow(String window, TextField textField){
        textField.getScene().getWindow().hide();
        openNewWindow(window);
    }

    public static void openNewWindow(String window){
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(window));
        try {
            loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        setStage(loader);
    }

    public static void setStage(FXMLLoader loader){
        Stage stage = new Stage();
        stage.setTitle("Congratulations");
        stage.getIcons().add(new Image("file:src\\main\\resources\\it\\college\\congratulations\\image\\logo.png"));
        stage.setResizable(false);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
