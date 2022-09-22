package it.college.congratulations.controller;

import it.college.congratulations.service.CalendarService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class CalendarController {
    CalendarService service = CalendarService.getCalendarService();
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private Label label6;
    @FXML private Label label7;
    @FXML private Label label8;
    @FXML private Label label9;
    @FXML private Label label10;
    @FXML private Label label11;
    @FXML private Label label12;
    @FXML private Label label13;
    @FXML private Label label14;
    @FXML private Label label15;
    @FXML private Label label16;
    @FXML private Label label17;
    @FXML private Label label18;
    @FXML private Label label19;
    @FXML private Label label20;
    @FXML private Label label21;
    @FXML private Label label22;
    @FXML private Label label23;
    @FXML private Label label24;
    @FXML private Label label25;
    @FXML private Label label26;
    @FXML private Label label27;
    @FXML private Label label28;
    @FXML private Label label29;
    @FXML private Label label30;
    @FXML private Label label31;
    @FXML private Label label32;
    @FXML private Label label33;
    @FXML private Label label34;
    @FXML private Label label35;
    @FXML private Label label36;

    @FXML private TextField findDateTextField;

    @FXML private ImageView nextMonthButton;
    @FXML private ImageView nextYearButton;
    @FXML private ImageView previousMonthButton;
    @FXML private ImageView previousYearButton;

    @FXML private Button todayButton;

    @FXML private Label yearLabel;
    @FXML private Label monthLabel;
    private final List<Label> labelList = new ArrayList<>();

    @FXML void initialize() {
        addLabelsToList();
        service.getMount(labelList, "CURRENT", yearLabel, monthLabel);

        previousMonthButton.setOnMouseClicked(mouseEvent ->
                service.getMount(labelList, "PREVIOUS_MONTH", yearLabel, monthLabel));
        previousYearButton.setOnMouseClicked(mouseEvent ->
                service.getMount(labelList, "PREVIOUS_YEAR", yearLabel, monthLabel));
        nextMonthButton.setOnMouseClicked(mouseEvent ->
                service.getMount(labelList, "NEXT_MONTH", yearLabel, monthLabel));
        nextYearButton.setOnMouseClicked(mouseEvent ->
                service.getMount(labelList, "NEXT_YEAR", yearLabel, monthLabel));

        findDateTextField.setOnAction(actionEvent -> {
            service.getMount(labelList, findDateTextField.getText(), yearLabel, monthLabel);
            findDateTextField.clear();
        });

        todayButton.setOnAction(actionEvent -> service.getMount(labelList, "CURRENT", yearLabel, monthLabel));
    }

    private void addLabelsToList(){
        labelList.add(label0);
        labelList.add(label1);
        labelList.add(label2);
        labelList.add(label3);
        labelList.add(label4);
        labelList.add(label5);
        labelList.add(label6);
        labelList.add(label7);
        labelList.add(label8);
        labelList.add(label9);
        labelList.add(label10);
        labelList.add(label11);
        labelList.add(label12);
        labelList.add(label13);
        labelList.add(label14);
        labelList.add(label15);
        labelList.add(label16);
        labelList.add(label17);
        labelList.add(label18);
        labelList.add(label19);
        labelList.add(label20);
        labelList.add(label21);
        labelList.add(label22);
        labelList.add(label23);
        labelList.add(label24);
        labelList.add(label25);
        labelList.add(label26);
        labelList.add(label27);
        labelList.add(label28);
        labelList.add(label29);
        labelList.add(label30);
        labelList.add(label31);
        labelList.add(label32);
        labelList.add(label33);
        labelList.add(label34);
        labelList.add(label35);
        labelList.add(label36);
    }
}
