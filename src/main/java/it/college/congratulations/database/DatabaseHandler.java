package it.college.congratulations.database;

import java.sql.*;

public class DatabaseHandler extends Configs{

    /*public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }*/

    private static final DatabaseHandler databaseHandler = new DatabaseHandler();
    public static DatabaseHandler getDatabaseHandler(){
        return databaseHandler;
    }
    private DatabaseHandler () {
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
        if (!resultSet.next()){
            return "login not found";
        }
        request = "SELECT * FROM users where login = ? and password = ?";
        preparedStatement = dbConnection.prepareStatement(request);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? resultSet.getString("id") : "incorrect password";
    }
    public void registration() throws SQLException {
        String request = "INSERT INTO users (id, name, lastname, secondname, birthday_date, registration_date," +
                "role, login, password) VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = dbConnection.prepareStatement(request);
        preparedStatement.setLong(1, getNewId());
        preparedStatement.setString(2, "Дмитрий");
        preparedStatement.setString(3, "Рубин");
        preparedStatement.setString(4, "Андреевич");
        preparedStatement.setString(5, "17.02.2003");
        preparedStatement.setString(6, "22.09.2022");
        preparedStatement.setBoolean(7, false);
        preparedStatement.setString(8, "Dimyx");
        preparedStatement.setString(9, "789");

        preparedStatement.executeUpdate();
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
        } catch (Exception ignored){}
        return ++id;
    }
}
