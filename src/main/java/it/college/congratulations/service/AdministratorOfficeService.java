package it.college.congratulations.service;

import it.college.congratulations.database.DatabaseHandler;
import it.college.congratulations.database.entity.User;
import javafx.collections.ObservableList;

public class AdministratorOfficeService {
    DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private static final AdministratorOfficeService administratorOfficeService = new AdministratorOfficeService();
    private AdministratorOfficeService(){}
    public static AdministratorOfficeService getAdministratorOfficeService(){return administratorOfficeService;}

    public ObservableList<User> getUsers(){
        return databaseHandler.getUsers();
    }
}