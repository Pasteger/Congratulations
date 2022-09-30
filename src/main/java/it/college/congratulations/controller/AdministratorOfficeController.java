package it.college.congratulations.controller;

import it.college.congratulations.database.entity.User;
import it.college.congratulations.service.AdministratorOfficeService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class AdministratorOfficeController extends Controller{
    AdministratorOfficeService service = AdministratorOfficeService.getAdministratorOfficeService();
    @FXML private ImageView backgroundImageView;
    @FXML private TableColumn<User, String> birthdayDateTC;
    @FXML private ImageView calendarButton;
    @FXML private Button exitButton;
    @FXML private TableColumn<User, Long> idTC;
    @FXML private TableColumn<User, String> lastnameTC;
    @FXML private TableColumn<User, String> loginTC;
    @FXML private TableColumn<User, String> nameTC;
    @FXML private TableColumn<User, String> passwordTC;
    @FXML private TableColumn<User, String> registrationDateTC;
    @FXML private TableColumn<User, Boolean> roleTC;
    @FXML private TableColumn<User, String> secondnameTC;
    @FXML private TableView<User> usersTable;

    @FXML void initialize(){
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameTC.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        secondnameTC.setCellValueFactory(new PropertyValueFactory<>("secondname"));
        birthdayDateTC.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));
        registrationDateTC.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        roleTC.setCellValueFactory(new PropertyValueFactory<>("role"));
        loginTC.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordTC.setCellValueFactory(new PropertyValueFactory<>("password"));

        updateUsersTable();

        calendarButton.setOnMouseClicked(mouseEvent -> openNewWindow(
                "/it/college/congratulations/layout/calendar.fxml"));
        exitButton.setOnAction(actionEvent -> openOtherWindow(
                "/it/college/congratulations/layout/workspace.fxml", exitButton));
    }

    private void updateUsersTable(){
        usersTable.setItems(service.getUsers());
    }
}
