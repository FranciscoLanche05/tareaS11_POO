package com.example.tareas11fx.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML private StackPane contentArea;
    @FXML private Label lblTituloModulo;
    @FXML private Label lblBreadcrumb;

    @FXML
    public void initialize() {
        mostrarProductos();
    }

    @FXML
    private void mostrarInicio() {
        cargarVista("/com/example/tareas11fx/inicio-view.fxml", "Inicio", "Inicio");
    }

    @FXML
    private void mostrarProductos() {
        cargarVista("/com/example/tareas11fx/productos-view.fxml", "Productos", "Inicio / Productos");
    }

    @FXML
    private void mostrarCategorias() {
        cargarPlaceholder("Categorías");
    }

    @FXML
    private void mostrarClientes() {
        cargarPlaceholder("Clientes");
    }

    @FXML
    private void mostrarVentas() {
        cargarPlaceholder("Ventas");
    }

    @FXML
    private void mostrarReportes() {
        cargarPlaceholder("Reportes");
    }

    @FXML
    private void mostrarUsuarios() {
        cargarPlaceholder("Usuarios");
    }

    @FXML
    private void cerrarSesion() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/tareas11fx/login.fxml"));
            Stage stage = (Stage) contentArea.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 600));
            stage.setTitle("MiTienda - Iniciar Sesión");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVista(String fxml, String titulo, String breadcrumb) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(fxml));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(vista);
            lblTituloModulo.setText(titulo);
            lblBreadcrumb.setText(breadcrumb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarPlaceholder(String nombreModulo) {
        contentArea.getChildren().clear();
        Label lbl = new Label("Módulo de " + nombreModulo + " en construcción");
        lbl.getStyleClass().add("placeholder-label");
        contentArea.getChildren().add(lbl);
        lblTituloModulo.setText(nombreModulo);
        lblBreadcrumb.setText("Inicio / " + nombreModulo);
    }
}