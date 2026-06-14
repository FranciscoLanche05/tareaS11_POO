module com.example.tareas11fx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.tareas11fx to javafx.fxml;
    exports com.example.tareas11fx;
    opens com.example.tareas11fx.controllers to javafx.fxml;
    exports com.example.tareas11fx.controllers;
    opens com.example.tareas11fx.models to javafx.base;
    exports com.example.tareas11fx.models;
}