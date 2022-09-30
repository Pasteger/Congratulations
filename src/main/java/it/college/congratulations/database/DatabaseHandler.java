package it.college.congratulations.database;

import it.college.congratulations.database.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends Configs {

    /*public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }*/

    private static final DatabaseHandler databaseHandler = new DatabaseHandler();

    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    private DatabaseHandler() {
        String connectionString = "jdbc:postgresql://localhost:5432/congratulations";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            dbConnection = DriverManager.getConnection(connectionString, "postgres", "890123890123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final Connection dbConnection;

    public String authorization(String login, String password) throws SQLException {
        String request = "SELECT * FROM users where login = ?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return "login not found";
        }
        String request2 = "SELECT * FROM users where login = ? and password = ?";
        preparedStatement = dbConnection.prepareStatement(request2);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? resultSet.getString("id") : "incorrect password";
    }

    public User findUserById(Long id){
        try {
            String request = "SELECT * FROM users where id = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User();
            user.setId(id);
            user.setName(resultSet.getString("name"));
            user.setLastname(resultSet.getString("lastname"));
            user.setSecondname(resultSet.getString("secondname"));
            user.setBirthdayDate(resultSet.getString("birthday_date"));
            user.setRegistrationDate(resultSet.getString("registration_date"));
            user.setRole(resultSet.getBoolean("role"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registration(String name, String lastname, String secondname, String birthdayDate,
                             String login, String password, String registrationDate) {
        String request = "INSERT INTO users (id, name, lastname, secondname, birthday_date, registration_date," +
                "role, login, password) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            preparedStatement.setLong(1, getNewId());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, secondname);
            preparedStatement.setString(5, birthdayDate);
            preparedStatement.setString(6, registrationDate);
            preparedStatement.setBoolean(7, false);
            preparedStatement.setString(8, login);
            preparedStatement.setString(9, password);

            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    private long getNewId() throws SQLException {
        String request = "SELECT * FROM users";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
        ResultSet resultSet = preparedStatement.executeQuery();
        long id = 0;
        try {
            while (resultSet.next()) {
                long thisId = Long.parseLong(resultSet.getString("id"));
                if (id < thisId) {
                    id = thisId;
                }
            }
        } catch (Exception ignored) {
        }
        return ++id;
    }

    public boolean findByLogin(String login) {
        String request = "SELECT * FROM users WHERE login = ?";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        String request = "SELECT * FROM users";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastname(resultSet.getString("lastname"));
                user.setSecondname(resultSet.getString("secondname"));
                user.setBirthdayDate(resultSet.getString("birthday_date"));
                user.setRegistrationDate(resultSet.getString("registration_date"));
                user.setRole(resultSet.getBoolean("role"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<String> getCongratulation(String date){
        List<String> congratulation = new ArrayList<>();
        String request = "SELECT * FROM congratulations WHERE congratulations.date = ?";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            congratulation.add(resultSet.getString("image"));
            congratulation.add(resultSet.getString("message"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return congratulation;
    }
}
