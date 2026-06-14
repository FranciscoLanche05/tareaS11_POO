module com.example.tareas11fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tareas11fx to javafx.fxml;
    exports com.example.tareas11fx;
}