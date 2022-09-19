module it.college.congratulations {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens it.college.congratulations to javafx.fxml;
    exports it.college.congratulations;
    exports it.college.congratulations.controller;
    opens it.college.congratulations.controller to javafx.fxml;
}