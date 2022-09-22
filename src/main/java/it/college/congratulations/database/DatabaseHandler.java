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

    public void signInUser() throws SQLException {
        String select = "SELECT * FROM users";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("role"));
            System.out.println();
        }
    }
}
