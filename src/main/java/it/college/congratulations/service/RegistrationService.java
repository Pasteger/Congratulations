package it.college.congratulations.service;

import it.college.congratulations.database.DatabaseHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistrationService {
    private static final RegistrationService registrationService = new RegistrationService();
    private RegistrationService(){
        File surprisedImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\surprised.gif");
        File upsetImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\upset.gif");
        File happyImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\happy.gif");
        fhSurprised = new Image(surprisedImg.toURI().toString());
        fhUpset = new Image(upsetImg.toURI().toString());
        fhHappy = new Image(happyImg.toURI().toString());
    }
    public static RegistrationService getAuthorizationService(){return registrationService;}

    private final DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private final Image fhSurprised;
    private final Image fhUpset;
    private final Image fhHappy;

    public boolean registration(String name, String lastname, String secondname, String birthdayDate,
                             String login, String password, Label errorLabel, ImageView fhImage){
        if (name.equals("") || lastname.equals("") || !birthdayDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}") ||
            login.equals("") || password.equals("")){
            errorLabel.setText("Что-то неправильно введено");
            fhImage.setImage(fhSurprised);
            return false;
        }
        if (databaseHandler.findByLogin(login)){
            errorLabel.setText("Логин занят");
            fhImage.setImage(fhUpset);
            return false;
        }
        Calendar calendar = new GregorianCalendar();
        int month = calendar.get(Calendar.MONTH) + 1;
        String registrationDate =
                calendar.get(Calendar.DAY_OF_MONTH) +
                "." + (month > 9 ? month : "0" + month) + "." +
                calendar.get(Calendar.YEAR);

        boolean result = databaseHandler.registration(
                name, lastname, secondname, birthdayDate, login, password, registrationDate);
        if (result){
            errorLabel.setText("Успешно!");
            fhImage.setImage(fhHappy);
        }
        else {
            errorLabel.setText("Что-то пошло не так");
            fhImage.setImage(fhUpset);
        }
        return result;
    }
}
