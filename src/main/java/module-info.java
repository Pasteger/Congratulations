module it.college.congratulations {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires org.postgresql.jdbc;

    opens it.college.congratulations to javafx.fxml;
    exports it.college.congratulations;
    exports it.college.congratulations.controller;
    opens it.college.congratulations.controller to javafx.fxml;
    exports it.college.congratulations.database.entity;
    opens it.college.congratulations.database.entity to javafx.fxml;
}