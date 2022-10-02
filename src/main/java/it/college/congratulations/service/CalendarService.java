package it.college.congratulations.service;

import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.Congratulation;
import javafx.scene.control.Label;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class CalendarService {
    private static final CalendarService CALENDAR_SERVICE = new CalendarService();
    private CalendarService() {}
    public static CalendarService getInstance() {
        return CALENDAR_SERVICE;
    }
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private final UpdateHolidayWindowService updateHolidayWindowService = UpdateHolidayWindowService.getInstance();
    private final Calendar calendar = new GregorianCalendar();
    private final int todayYear = calendar.get(Calendar.YEAR);
    private final int todayMonth = calendar.get(Calendar.MONTH);
    private int selectedYear = todayYear;
    private int selectedMonth = todayMonth;
    private List<Congratulation> congratulations;

    public void getMount(List<Label> labelList, String command, Label yearLabel, Label monthLabel){
        updateCongratulations();
        switch (command){
            case "CURRENT" -> setMount(labelList, yearLabel, monthLabel, todayYear, todayMonth);
            case "PREVIOUS_MONTH" -> setMount(labelList, yearLabel, monthLabel, selectedYear, --selectedMonth);
            case "PREVIOUS_YEAR" -> setMount(labelList, yearLabel, monthLabel, --selectedYear, selectedMonth);
            case "NEXT_MONTH" -> setMount(labelList, yearLabel, monthLabel, selectedYear, ++selectedMonth);
            case "NEXT_YEAR" -> setMount(labelList, yearLabel, monthLabel, ++selectedYear, selectedMonth);
            default -> {
                String[] monthAndYear = command.split("\\.");
                setMount(labelList, yearLabel, monthLabel, Integer.parseInt(monthAndYear[1]), Integer.parseInt(monthAndYear[0])-1);
            }
        }
    }

    public void getUpdateHolidayWindow(String day){
        String dayString = Integer.parseInt(day) > 9 ? day : "0" + day;
        String monthString = (selectedMonth + 1) > 9 ? "" + (selectedMonth + 1) : "0" + (selectedMonth + 1);
        String date =  dayString + "." + monthString;
        updateHolidayWindowService.setDate(date);
    }
    public void updateCongratulations(){
        congratulations = databaseHandler.getCongratulations();
    }

    private void setMount(List<Label> labelList, Label yearLabel, Label monthLabel, int year, int mount){
        labelList.forEach(label -> label.setStyle("-fx-background-color: #c6c6c6"));
        if (mount > Calendar.DECEMBER) {
            mount = Calendar.JANUARY;
        }
        if (mount < Calendar.JANUARY) {
            mount = Calendar.DECEMBER;
        }
        selectedYear = year;
        selectedMonth = mount;
        setYearAndMonthLabel(yearLabel, monthLabel);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, mount);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int coefficientOfDayOfWeek = -1;
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1 -> coefficientOfDayOfWeek = 6;
            case 2 -> coefficientOfDayOfWeek = 0;
            case 3 -> coefficientOfDayOfWeek = 1;
            case 4 -> coefficientOfDayOfWeek = 2;
            case 5 -> coefficientOfDayOfWeek = 3;
            case 6 -> coefficientOfDayOfWeek = 4;
            case 7 -> coefficientOfDayOfWeek = 5;
        }
        int daysOfMouth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        labelList.forEach(label -> label.setText(""));
        for (int day = 0; day < daysOfMouth; day++){
            Label label = labelList.get(day + coefficientOfDayOfWeek);
            int dayDate = day + 1;
            String dayDateString = dayDate > 9 ? "" + dayDate : "0" + dayDate;
            String monthString = (selectedMonth + 1) > 9 ? "" + (selectedMonth + 1) : "0" + (selectedMonth + 1);
            Optional<Congratulation> congratulationOptional = congratulations.stream().filter(congratulation ->
                    congratulation.getDate().equals(dayDateString + "." + monthString)).findFirst();
            if (congratulationOptional.isPresent()){
                label.setStyle("-fx-background-color: #ff6347");
            }
            label.setText(String.valueOf(dayDate));
        }
    }
    private void setYearAndMonthLabel(Label yearLabel, Label monthLabel){
        yearLabel.setText(String.valueOf(selectedYear));
        String month = "";
        switch (selectedMonth){
            case Calendar.JANUARY -> month = "Январь";
            case Calendar.FEBRUARY -> month = "Февраль";
            case Calendar.MARCH -> month = "Март";
            case Calendar.APRIL -> month = "Апрель";
            case Calendar.MAY -> month = "Май";
            case Calendar.JUNE -> month = "Июнь";
            case Calendar.JULY -> month = "Июль";
            case Calendar.AUGUST -> month = "Август";
            case Calendar.SEPTEMBER -> month = "Сентябрь";
            case Calendar.OCTOBER -> month = "Октябрь";
            case Calendar.NOVEMBER -> month = "Ноябрь";
            case Calendar.DECEMBER -> month = "Декабрь";
        }
        monthLabel.setText(month);
    }
}
