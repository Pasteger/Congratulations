package it.college.congratulations.service;

import it.college.congratulations.FaithfulHelper;
import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.Congratulation;
import it.college.congratulations.database.entity.User;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.*;

public class WorkspaceService {
    private static final WorkspaceService WORKSPACE_SERVICE = new WorkspaceService();
    private WorkspaceService(){
        congratulationImagesPath = "src\\main\\resources\\it\\college\\congratulations\\image\\congratulations\\";
        random = new Random();
    }
    public static WorkspaceService getInstance(){return WORKSPACE_SERVICE;}
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final FaithfulHelper FH = new FaithfulHelper();
    private final String congratulationImagesPath;

    private User user;

    private final Random random;

    public void setCongratulation(ImageView background, Label title){
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayString = day > 9 ? "" + day : "0" + day;
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthString = month > 9 ? "" + month : "0" + month;
        String date =  dayString + "." + monthString;
        int year = 0;
        String userBirthdayDate = user.getBirthdayDate().substring(0, 5);
        String userRegistrationDate = user.getRegistrationDate().substring(0, 5);

        //Первой датой проверяется дата рождения, потому что
        //У дня рождения приоритет выше других праздников
        if (date.equals(userBirthdayDate)){
            int yearBirthday = Integer.parseInt(user.getBirthdayDate().substring(6));
            year = calendar.get(Calendar.YEAR) - yearBirthday;
            if (year == 0) {
                return;
            }
            unpackingCongratulation(background, title, "birthday", year);
        }
        //Проверяется не дата регистрации для выполнения любых действий, потому что
        //У любого праздника приоритет больше, чем у даты регистрации
        else if (!date.equals(userRegistrationDate)){
            unpackingCongratulation(background, title, date, year);
        }
        //Последней возможной датой остаётся дата регистрации
        //И у неё самый низкий приоритет поздравления
        else {
            int yearRegistration = Integer.parseInt(user.getRegistrationDate().substring(6));
            year = calendar.get(Calendar.YEAR) - yearRegistration;
            if (year == 0) {
                return;
            }
            unpackingCongratulation(background, title, "registration_anniversary", year);
        }
    }

    public void moveFH(ImageView fhImage){
        int x = 200 - random.nextInt(400);
        int y = 100 - random.nextInt(200);
        fhImage.setX(x);
        fhImage.setY(y);
        switch (random.nextInt(4)){
            case 0 -> fhImage.setImage(FH.NORMAL);
            case 1 -> fhImage.setImage(FH.SURPRISED);
            case 2 -> fhImage.setImage(FH.UPSET);
            case 3 -> fhImage.setImage(FH.HAPPY);
        }
    }

    public void resetUser(){
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void unpackingCongratulation(ImageView background, Label title, String date, int year){
        try {
            Congratulation congratulation = databaseHandler.getCongratulation(date);
            String message = congratulation.getMessage();
            message = message.replace("user", user.getName());
            message = message.replace("year", String.valueOf(year));
            title.setText(message);

            String imagePath = congratulationImagesPath + congratulation.getImage();
            Image image = new Image(new File(imagePath).toURI().toString());
            background.setImage(image);
        }
        catch (Exception exception){
            title.setText("Приступим к работе " + user.getName());
        }
    }
}
