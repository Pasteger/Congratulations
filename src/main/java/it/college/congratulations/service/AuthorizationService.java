package it.college.congratulations.service;

import it.college.congratulations.FaithfulHelper;
import it.college.congratulations.database.DatabaseHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.sql.SQLException;

public class AuthorizationService {
    private static final AuthorizationService AUTHORIZATION_SERVICE = new AuthorizationService();
    private AuthorizationService(){}
    public static AuthorizationService getInstance(){return  AUTHORIZATION_SERVICE;}
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final WorkspaceService workspaceService = WorkspaceService.getInstance();
    private final FaithfulHelper FH = new FaithfulHelper();
    public boolean authorization(String login, String password, Label errorLabel, ImageView fh){
        try {
            String result = databaseHandler.authorization(login, password);
            if (result.equals("login not found")){
                errorLabel.setText("Пользователь с таким логином не найден");
                fh.setImage(FH.SURPRISED);
                return false;
            }
            if (result.equals("incorrect password")){
                errorLabel.setText("О нет! Неверный пароль!");
                fh.setImage(FH.UPSET);
                return false;
            }
            errorLabel.setText("Успешно!");
            fh.setImage(FH.HAPPY);
            workspaceService.setUser(databaseHandler.findUserById(Long.parseLong(result)));
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
