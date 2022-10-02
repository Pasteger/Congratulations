package it.college.congratulations;

import javafx.scene.image.Image;
import java.io.File;

public class FaithfulHelper {
    public final Image NORMAL;
    public final Image SURPRISED;
    public final Image UPSET;
    public final Image HAPPY;

    public FaithfulHelper (){
        File normalImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\normal.gif");
        File surprisedImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\surprised.gif");
        File upsetImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\upset.gif");
        File happyImg = new File("src\\main\\resources\\it\\college\\congratulations\\image\\fh\\happy.gif");
        NORMAL = new Image(normalImg.toURI().toString());
        SURPRISED = new Image(surprisedImg.toURI().toString());
        UPSET = new Image(upsetImg.toURI().toString());
        HAPPY = new Image(happyImg.toURI().toString());
    }
}
