package it.college.congratulations.controller;

import it.college.congratulations.service.WorkspaceService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class WorkspaceController extends Controller{
    WorkspaceService service = WorkspaceService.getInstance();
    @FXML private ImageView backgroundImageView;
    @FXML private Button exitButton;
    @FXML private Button administratorButton;
    @FXML private ImageView fhImage;
    @FXML private Label titleLabel;


    @FXML void initialize(){
        service.setCongratulation(backgroundImageView, titleLabel);
        exitButton.setOnAction(actionEvent -> {
            service.resetUser();
            openOtherWindow("/it/college/congratulations/layout/authorization.fxml", exitButton);
        });
        fhImage.setOnMouseClicked(mouseEvent -> service.moveFH(fhImage));
        administratorButton.setVisible(service.getUser().getRole());
        administratorButton.setOnAction(actionEvent -> openOtherWindow(
                "/it/college/congratulations/layout/administrator_office.fxml", administratorButton));
    }
}
