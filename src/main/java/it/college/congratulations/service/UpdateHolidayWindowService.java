package it.college.congratulations.service;

import it.college.congratulations.FaithfulHelper;
import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.Congratulation;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class UpdateHolidayWindowService {
    private static final UpdateHolidayWindowService UPDATE_HOLIDAY_WINDOW_SERVICE = new UpdateHolidayWindowService();
    private UpdateHolidayWindowService(){}
    public static UpdateHolidayWindowService getInstance(){return UPDATE_HOLIDAY_WINDOW_SERVICE;}
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final FaithfulHelper FH = new FaithfulHelper();
    private String date;

    public void setCongratulation(String image, String message, ImageView fhImage, Label errorLabel){
        if (databaseHandler.checkingUseOfDate(date)){
            fhImage.setImage(FH.SURPRISED);
            errorLabel.setText("На эту дату уже назначен праздник");
            return;
        }
        Congratulation congratulation = new Congratulation();
        congratulation.setDate(date);
        congratulation.setImage(image);
        congratulation.setMessage(message);

        fhImage.setImage(FH.NORMAL);
        errorLabel.setText("");

        boolean result = databaseHandler.saveCongratulation(congratulation);
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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
