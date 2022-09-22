package it.college.congratulations.service;

import javafx.scene.control.Label;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarService {
    private final Calendar calendar = new GregorianCalendar();
    private final int todayYear = calendar.get(Calendar.YEAR);
    private final int todayMonth = calendar.get(Calendar.MONTH);
    private int selectedYear = todayYear;
    private int selectedMonth = todayMonth;
    private static final CalendarService calendarService = new CalendarService();
    private CalendarService() {}
    public static CalendarService getCalendarService() {
        return calendarService;
    }
    public void getMount(List<Label> labelList, String command, Label yearLabel, Label monthLabel){
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

    private void setMount(List<Label> labelList, Label yearLabel, Label monthLabel, int year, int mount){
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
            label.setText(String.valueOf(day + 1));
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
