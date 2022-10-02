package it.college.congratulations.service;

import it.college.congratulations.FaithfulHelper;
import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.Congratulation;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.sql.SQLException;

public class UpdateHolidayWindowService {
    private static final UpdateHolidayWindowService UPDATE_HOLIDAY_WINDOW_SERVICE = new UpdateHolidayWindowService();
    private UpdateHolidayWindowService(){}
    public static UpdateHolidayWindowService getInstance(){return UPDATE_HOLIDAY_WINDOW_SERVICE;}
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final FaithfulHelper FH = new FaithfulHelper();
    private Congratulation congratulation;
    boolean exist;

    public void setCongratulation(String image, String message, ImageView fhImage, Label errorLabel){
        congratulation.setImage(image);
        congratulation.setMessage(message);
        fhImage.setImage(FH.NORMAL);
        errorLabel.setText("");

        boolean result;
        if (image.equals("") || message.equals("")){
            result = databaseHandler.deleteCongratulation(congratulation);
        }
        else if (exist){
            result = databaseHandler.updateCongratulation(congratulation);
        }
        else {
            result = databaseHandler.saveCongratulation(congratulation);
        }

        if (result){
            fhImage.setImage(FH.HAPPY);
            errorLabel.setText("Выполнено!");
        }
        else {
            fhImage.setImage(FH.UPSET);
            errorLabel.setText("Что-то пошло не так");
        }
    }

    public String getDate() {
        return congratulation.getDate();
    }
    public String getImage() {
        return congratulation.getImage();
    }
    public String getMessage() {
        return congratulation.getMessage();
    }

    public void setDate(String date) {
        try {
            congratulation = databaseHandler.getCongratulation(date);
            exist = true;
        } catch (SQLException e) {
            congratulation = new Congratulation();
            congratulation.setDate(date);
            exist = false;
        }
    }
}
