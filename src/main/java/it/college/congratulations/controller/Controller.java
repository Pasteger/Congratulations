package it.college.congratulations.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controller.class.getResource(window));
        try {
            loader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}