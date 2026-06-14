package com.example.tareas11fx.controllers;

import com.example.tareas11fx.models.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class ProductosController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private ComboBox<String> cmbEstado;

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colEstado;
    @FXML private TableColumn<Producto, Void> colAcciones;

    @FXML private TextField txtBuscar;

    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    private Producto productoSeleccionado;

    @FXML
    public void initialize() {
        cmbCategoria.getItems().addAll("Calzado", "Ropa", "Accesorios", "Electrónica", "Hogar");
        cmbEstado.getItems().addAll("Activo", "Inactivo");

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        formatearColumnaPrecio();
        formatearColumnaEstado();
        configurarColumnaAcciones();
        cargarDatosPrueba();

        tablaProductos.setItems(listaProductos);

        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, anterior, actual) -> {
            if (actual != null) {
                cargarFormulario(actual);
            }
        });
    }

    private void cargarDatosPrueba() {
        listaProductos.add(new Producto("P001", "Zapato Deportivo", "Calzado", 35.00, 20, "Activo"));
        listaProductos.add(new Producto("P002", "Camisa Manga Larga", "Ropa", 25.00, 15, "Activo"));
        listaProductos.add(new Producto("P003", "Jean Clásico", "Ropa", 40.00, 10, "Activo"));
        listaProductos.add(new Producto("P004", "Gorra Deportiva", "Accesorios", 12.00, 30, "Activo"));
        listaProductos.add(new Producto("P005", "Cinturón de Cuero", "Accesorios", 18.00, 25, "Inactivo"));
    }

    private void formatearColumnaPrecio() {
        colPrecio.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : String.format("$%.2f", item));
            }
        });
    }

    private void formatearColumnaEstado() {
        colEstado.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label badge = new Label(item);
                    badge.getStyleClass().add(item.equals("Activo") ? "badge-activo" : "badge-inactivo");
                    setGraphic(badge);
                }
            }
        });
    }

    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar = new Button("✏");
            private final Button btnEliminar = new Button("🗑");
            private final HBox contenedor = new HBox(5, btnEditar, btnEliminar);

            {
                btnEditar.getStyleClass().add("btn-editar");
                btnEliminar.getStyleClass().add("btn-eliminar");

                btnEditar.setOnAction(e -> cargarFormulario(getTableView().getItems().get(getIndex())));
                btnEliminar.setOnAction(e -> eliminarProducto(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : contenedor);
            }
        });
    }

    private void cargarFormulario(Producto p) {
        productoSeleccionado = p;
        txtCodigo.setText(p.getCodigo());
        txtNombre.setText(p.getNombre());
        cmbCategoria.setValue(p.getCategoria());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cmbEstado.setValue(p.getEstado());
    }

    @FXML
    private void nuevoProducto() {
        limpiarFormulario();
    }

    @FXML
    private void guardarProducto() {
        if (!validarCampos()) return;

        Producto nuevo = new Producto(
                txtCodigo.getText().trim(),
                txtNombre.getText().trim(),
                cmbCategoria.getValue(),
                Double.parseDouble(txtPrecio.getText().trim()),
                Integer.parseInt(txtStock.getText().trim()),
                cmbEstado.getValue()
        );

        listaProductos.add(nuevo);
        limpiarFormulario();
    }

    @FXML
    private void actualizarProducto() {
        if (productoSeleccionado == null) {
            mostrarAlerta("Seleccione un producto de la tabla para actualizar");
            return;
        }
        if (!validarCampos()) return;

        productoSeleccionado.setCodigo(txtCodigo.getText().trim());
        productoSeleccionado.setNombre(txtNombre.getText().trim());
        productoSeleccionado.setCategoria(cmbCategoria.getValue());
        productoSeleccionado.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
        productoSeleccionado.setStock(Integer.parseInt(txtStock.getText().trim()));
        productoSeleccionado.setEstado(cmbEstado.getValue());

        tablaProductos.refresh();
        limpiarFormulario();
    }

    @FXML
    private void eliminarProductoSeleccionado() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un producto de la tabla para eliminar");
            return;
        }
        eliminarProducto(seleccionado);
    }

    private void eliminarProducto(Producto p) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Desea eliminar el producto " + p.getCodigo() + "?");

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                listaProductos.remove(p);
                limpiarFormulario();
            }
        });
    }

    @FXML
    private void limpiarFormulario() {
        txtCodigo.clear();
        txtNombre.clear();
        cmbCategoria.setValue(null);
        txtPrecio.clear();
        txtStock.clear();
        cmbEstado.setValue(null);
        productoSeleccionado = null;
        tablaProductos.getSelectionModel().clearSelection();
    }

    @FXML
    private void buscarProducto() {
        String filtro = txtBuscar.getText().trim().toLowerCase();
        if (filtro.isEmpty()) {
            tablaProductos.setItems(listaProductos);
            return;
        }
        ObservableList<Producto> filtrados = FXCollections.observableArrayList();
        for (Producto p : listaProductos) {
            if (p.getCodigo().toLowerCase().contains(filtro) || p.getNombre().toLowerCase().contains(filtro)) {
                filtrados.add(p);
            }
        }
        tablaProductos.setItems(filtrados);
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()
                || cmbCategoria.getValue() == null || txtPrecio.getText().trim().isEmpty()
                || txtStock.getText().trim().isEmpty() || cmbEstado.getValue() == null) {
            mostrarAlerta("Complete todos los campos del formulario");
            return false;
        }
        try {
            Double.parseDouble(txtPrecio.getText().trim());
            Integer.parseInt(txtStock.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta("Precio y Stock deben ser valores numéricos");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}