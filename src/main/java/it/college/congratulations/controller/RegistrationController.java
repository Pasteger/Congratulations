package it.college.congratulations.controller;

import it.college.congratulations.service.RegistrationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegistrationController extends Controller {
    RegistrationService service = RegistrationService.getAuthorizationService();
    @FXML private Button backButton;
    @FXML private Button registrationButton;
    @FXML private Label errorLabel;
    @FXML private ImageView fhImage;
    @FXML private TextField birthdayDateTextField;
    @FXML private TextField lastnameTextField;
    @FXML private TextField loginTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField secondnameTextField;

    @FXML void initialize(){
        backButton.setOnAction(actionEvent ->
                openOtherWindow("/it/college/congratulations/layout/authorization.fxml", backButton));
        registrationButton.setOnAction(actionEvent -> {
                boolean response = service.registration(nameTextField.getText(), lastnameTextField.getText(),
                        secondnameTextField.getText(), birthdayDateTextField.getText(), loginTextField.getText(),
                        passwordTextField.getText(), errorLabel, fhImage
                );
                if (response){
                    openOtherWindow("/it/college/congratulations/layout/authorization.fxml", registrationButton);
                }
        });
    }
}
