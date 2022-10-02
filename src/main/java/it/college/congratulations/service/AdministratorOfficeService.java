package it.college.congratulations.service;

import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.User;
import javafx.collections.ObservableList;

public class AdministratorOfficeService {
    private final DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private static final AdministratorOfficeService ADMINISTRATOR_OFFICE_SERVICE = new AdministratorOfficeService();
    private AdministratorOfficeService(){}
    public static AdministratorOfficeService getInstance(){return ADMINISTRATOR_OFFICE_SERVICE;}

    public ObservableList<User> getUsers(){
        return databaseHandler.getUsers();
    }
}
