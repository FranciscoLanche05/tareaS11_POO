# 🛒 MiTienda - Sistema de Ventas

## 📋 Descripción

MiTienda es una aplicación de escritorio desarrollada en JavaFX que simula un sistema de ventas con control de acceso por roles (Administrador, Vendedor, Cajero). Incluye un panel administrativo con menú lateral de navegación y un módulo completo de gestión de productos (CRUD).

## 🖼️ Captura del Dashboard

![Dashboard MiTienda](docs/dashboard.png)

## 🛠️ Tecnologías Utilizadas

- **JavaFX** (FXML)
- **CSS** para estilos personalizados
- **Java** (POO)

## ✅ Funcionalidades Implementadas

- Login con validación de Usuario, Contraseña y Rol
- Visualización de mensajes de error en pantalla de inicio de sesión
- Panel de administrador con menú lateral de navegación
- Cierre de sesión con retorno a la pantalla de login
- Módulo completo de **Productos**:
    - Registro de nuevos productos
    - Edición/actualización de productos
    - Eliminación de productos (con confirmación)
    - Limpieza del formulario
    - Listado en tabla con columnas: Código, Nombre, Categoría, Precio, Stock, Estado
    - Búsqueda de productos por código o nombre
    - Acciones rápidas (editar/eliminar) desde la tabla

## 📁 Estructura del Proyecto

src/main/java/com/example/demo/

├── Main.java

├── controllers/

│   ├── LoginController.java

│   ├── AdminController.java

│   └── ProductosController.java

└── models/

└── Producto.java
src/main/resources/com/example/demo/

├── login.fxml

├── admin-view.fxml

├── inicio-view.fxml

├── productos-view.fxml

└── styles/

├── login.css

└── admin.css

## ▶️ Instrucciones para Ejecutar el Proyecto

### Requisitos previos
- Java JDK 17 o superior
- JavaFX SDK 17 o superior
- IDE recomendado: IntelliJ IDEA / Eclipse / NetBeans con soporte JavaFX

### Pasos

1. Clonar el repositorio:
```bash
   git clone https://github.com/tu-usuario/mitienda.git
```

2. Abrir el proyecto en tu IDE.

3. Configurar el SDK de JavaFX en las librerías del proyecto.

4. Ejecutar la clase `Main.java`.

5. Iniciar sesión con las credenciales de prueba:
    - **Usuario:** admin
    - **Contraseña:** 1234
    - **Rol:** Administrador

## 🔑 Credenciales de Prueba

| Usuario | Contraseña | Rol            |
|---------|------------|----------------|
| admin   | 1234       | Administrador  |

## 👤 Autor

Proyecto desarrollado como tarea de Programación Orientada a Objetos.