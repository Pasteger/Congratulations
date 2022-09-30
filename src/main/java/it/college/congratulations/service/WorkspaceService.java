package it.college.congratulations.service;

import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.User;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.*;

public class WorkspaceService {
    private static final WorkspaceService workspaceService = new WorkspaceService();
    private WorkspaceService(){
        File normalImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\normal.gif");
        File surprisedImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\surprised.gif");
        File upsetImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\upset.gif");
        File happyImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\happy.gif");
        fhNormal = new Image(normalImg.toURI().toString());
        fhSurprised = new Image(surprisedImg.toURI().toString());
        fhUpset = new Image(upsetImg.toURI().toString());
        fhHappy = new Image(happyImg.toURI().toString());

        congratulationImagesPath = "src\\main\\resources\\it\\college\\congratulations\\image\\congratulations\\";
        random = new Random();
    }
    public static WorkspaceService getWorkspaceService(){return workspaceService;}
    private final DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private final Image fhNormal;
    private final Image fhSurprised;
    private final Image fhUpset;
    private final Image fhHappy;
    private final String congratulationImagesPath;

    private User user;

    private final Random random;

    public void setCongratulation(ImageView background, Label title){
        Calendar calendar = new GregorianCalendar();
        String month = (calendar.get(Calendar.MONTH) + 1) < 10 ?
                "0" + (calendar.get(Calendar.MONTH) + 1) : String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "." + month;
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

    private void unpackingCongratulation(ImageView background, Label title, String date, int year){
        try {
            List<String> congratulation = databaseHandler.getCongratulation(date);
            String message = congratulation.get(1);
            message = message.replace("user", user.getName());
            message = message.replace("year", String.valueOf(year));
            title.setText(message);

            String imagePath = congratulationImagesPath + congratulation.get(0);
            Image image = new Image(new File(imagePath).toURI().toString());
            background.setImage(image);
        }
        catch (Exception exception){
            title.setText("Приступим к работе " + user.getName());
        }
    }

    public void moveFH(ImageView fhImage){
        int x = 200 - random.nextInt(400);
        int y = 100 - random.nextInt(200);
        fhImage.setX(x);
        fhImage.setY(y);
        switch (random.nextInt(4)){
            case 0 -> fhImage.setImage(fhNormal);
            case 1 -> fhImage.setImage(fhSurprised);
            case 2 -> fhImage.setImage(fhUpset);
            case 3 -> fhImage.setImage(fhHappy);
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
}
