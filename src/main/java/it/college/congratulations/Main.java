package it.college.congratulations;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/calendar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 654, 622);*/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
