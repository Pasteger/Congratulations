package it.college.congratulations;

import it.college.congratulations.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/authorization.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Controller.setStage(fxmlLoader);
    }

    public static void main(String[] args) {
        launch();
    }
}
