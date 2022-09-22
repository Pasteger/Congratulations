package it.college.congratulations.controller;

import it.college.congratulations.service.AuthorizationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AuthorizationController extends Controller{
    AuthorizationService service = AuthorizationService.getAuthorizationService();
    @FXML private Label errorLabel;
    @FXML private Button exitButton;
    @FXML private ImageView fhImage;
    @FXML private TextField loginTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button registrationButton;

    @FXML void initialize() {
        exitButton.setOnAction(actionEvent -> openOtherWindow("", exitButton));
        registrationButton.setOnAction(actionEvent -> openOtherWindow(
                "/it/college/congratulations/layout/registration.fxml", registrationButton));
        passwordTextField.setOnAction(actionEvent -> {
            boolean response = service.authorization(
                    loginTextField.getText(), passwordTextField.getText(), errorLabel, fhImage);
            if (response) {
                openOtherWindow("/it/college/congratulations/layout/workspace.fxml", passwordTextField);
            }
        });
    }
}
