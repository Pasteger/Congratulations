package it.college.congratulations.service;

import it.college.congratulations.database.entity.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.Random;

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

        random = new Random();
    }
    public static WorkspaceService getWorkspaceService(){return workspaceService;}
    private final Image fhNormal;
    private final Image fhSurprised;
    private final Image fhUpset;
    private final Image fhHappy;

    private User user;

    private final Random random;

    public String getTextForTitle(ImageView background){
        return "Приступим к работе, " + user.getName();
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
