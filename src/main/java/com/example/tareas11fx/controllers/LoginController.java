package com.example.tareas11fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private ComboBox<String> cmbRol;
    @FXML private Label lblError;

    @FXML
    public void initialize() {
        cmbRol.getItems().addAll("Administrador", "Vendedor", "Cajero");
        lblError.setVisible(false);
    }

    @FXML
    private void onIngresar() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();
        String rol = cmbRol.getValue();

        if (usuario.isEmpty() || contrasena.isEmpty() || rol == null) {
            mostrarError("Por favor complete todos los campos");
            return;
        }

        if (usuario.equals("admin") && contrasena.equals("1234") && rol.equals("Administrador")) {
            abrirDashboard();
        } else {
            mostrarError("Usuario, contraseña o rol incorrectos");
        }
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
    }

    private void abrirDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/admin-view.fxml"));
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root, 1100, 650));
            stage.setTitle("MiTienda - Panel Administrador");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("Error al cargar el panel de administrador");
        }
    }
}