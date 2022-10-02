package it.college.congratulations.controller;

import it.college.congratulations.service.UpdateHolidayWindowService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UpdateHolidayWindowController {
    @FXML private TextField dateTextField;
    @FXML private Label errorLabel;
    @FXML private ImageView fhImage;
    @FXML private TextField imageTextField;
    @FXML private TextField messageTextField;
    UpdateHolidayWindowService service = UpdateHolidayWindowService.getInstance();

    @FXML void initialize(){
        dateTextField.setText(service.getDate());
        imageTextField.setText(service.getImage());
        messageTextField.setText(service.getMessage());
        fhImage.setOnMouseClicked(mouseEvent -> service.setCongratulation(
                imageTextField.getText(), messageTextField.getText(), fhImage, errorLabel));
    }
}
