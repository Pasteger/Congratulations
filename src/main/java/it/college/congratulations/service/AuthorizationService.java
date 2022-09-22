package it.college.congratulations.service;

import it.college.congratulations.database.DatabaseHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.sql.SQLException;

public class AuthorizationService {
    private static final AuthorizationService authorizationService = new AuthorizationService();
    private AuthorizationService(){
        File surprisedImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\surprised.gif");
        File upsetImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\upset.gif");
        File happyImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\happy.gif");
        fhSurprised = new Image(surprisedImg.toURI().toString());
        fhUpset = new Image(upsetImg.toURI().toString());
        fhHappy = new Image(happyImg.toURI().toString());
    }
    public static AuthorizationService getAuthorizationService(){return  authorizationService;}

    private final DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private final WorkspaceService workspaceService = WorkspaceService.getWorkspaceService();
    private final Image fhSurprised;
    private final Image fhUpset;
    private final Image fhHappy;
    public boolean authorization(String login, String password, Label errorLabel, ImageView fh){
        try {
            String result = databaseHandler.authorization(login, password);
            if (result.equals("login not found")){
                errorLabel.setText("Пользователь с таким логином не найден");
                fh.setImage(fhSurprised);
                return false;
            }
            if (result.equals("incorrect password")){
                errorLabel.setText("О нет! Неверный пароль!");
                fh.setImage(fhUpset);
                return false;
            }
            errorLabel.setText("Успешно!");
            fh.setImage(fhHappy);
            workspaceService.setUser(databaseHandler.findUserById(Long.parseLong(result)));
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
