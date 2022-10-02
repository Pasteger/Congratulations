package it.college.congratulations.service;

import it.college.congratulations.FaithfulHelper;
import it.college.congratulations.database.DatabaseHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistrationService {
    private static final RegistrationService REGISTRATION_SERVICE = new RegistrationService();
    private RegistrationService(){}
    public static RegistrationService getInstance(){return REGISTRATION_SERVICE;}

    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final FaithfulHelper FH = new FaithfulHelper();

    public boolean registration(String name, String lastname, String secondname, String birthdayDate,
                             String login, String password, Label errorLabel, ImageView fhImage){
        Calendar calendar = new GregorianCalendar();
        if (name.equals("") || lastname.equals("") || !birthdayDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}") ||
            login.equals("") || password.equals("")){
            errorLabel.setText("Что-то неправильно введено");
            fhImage.setImage(FH.SURPRISED);
            return false;
        }
        String[] birthday = birthdayDate.split("\\.");
        if (Integer.parseInt(birthday[0]) > 31 || Integer.parseInt(birthday[0]) < 1 ||
                Integer.parseInt(birthday[1]) > 12 || Integer.parseInt(birthday[1]) < 1 ||
                Integer.parseInt(birthday[2]) > calendar.get(Calendar.YEAR)){
            errorLabel.setText("Странная дата");
            fhImage.setImage(FH.SURPRISED);
            return false;
        }
        if (databaseHandler.findByLogin(login)){
            errorLabel.setText("Логин занят");
            fhImage.setImage(FH.UPSET);
            return false;
        }
        int month = calendar.get(Calendar.MONTH) + 1;
        String registrationDate =
                calendar.get(Calendar.DAY_OF_MONTH) +
                "." + (month > 9 ? month : "0" + month) + "." +
                calendar.get(Calendar.YEAR);

        boolean result = databaseHandler.registration(
                name, lastname, secondname, birthdayDate, login, password, registrationDate);
        if (result){
            errorLabel.setText("Успешно!");
            fhImage.setImage(FH.HAPPY);
        }
        else {
            errorLabel.setText("Что-то пошло не так");
            fhImage.setImage(FH.UPSET);
        }
        return result;
    }
}
