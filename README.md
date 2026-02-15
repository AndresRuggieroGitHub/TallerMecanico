# 🔧 Gestor de Taller Mecánico

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![Gradle](https://img.shields.io/badge/Build-Gradle-green)
![Swing](https://img.shields.io/badge/GUI-Swing-orange)
![JUnit](https://img.shields.io/badge/Test-JUnit%205-red)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

Aplicación Java para la gestión de un taller mecánico. Permite administrar clientes, vehículos y revisiones con una interfaz gráfica.


## 📋 Descripción

Sistema que facilita la gestión integral de un taller mecánico:
- Gestión de clientes (DNI, nombre, teléfono)
- Control de vehículos (marca, modelo, matrícula)
- Seguimiento de revisiones y cálculo de costos


## 🚀 Características principales

- Gestión completa de clientes, vehículos y revisiones
- Interfaz gráfica con Swing
- Validación de datos (DNI, teléfono, matrícula)
- Cálculo automático de costos de reparación


## 📦 Requisitos

- Java 11 o superior
- Gradle (incluye wrapper en el proyecto)


## ▶️ Instalación y uso

```bash
# Clonar el repositorio
git clone https://github.com/AndresRuggieroGitHub/TallerMecanico.git
cd TallerMecanico

# Compilar
./gradlew build

# Ejecutar
./gradlew run

# Tests
./gradlew test
```


## 📂 Estructura del proyecto

```
src/main/java/org/iesalandalus/programacion/tallermecanico/
├── modelo/
│   ├── Modelo.java
│   ├── dominio/ (Cliente, Vehiculo, Revision)
│   └── negocio/ (Clientes, Vehiculos, Revisiones)
└── vista/
    └── GestionTallerMecanico.java (GUI)
```


## 🧭 Cómo usar

1. **Clientes**: Agregar nombre, DNI y teléfono
2. **Vehículos**: Registrar marca, modelo y matrícula
3. **Revisiones**: Crear seguimiento de reparaciones

Los datos incluyen validaciones para DNI español, teléfono y matrícula.


## Tecnologías

- Java 11
- Gradle
- Swing (interfaz)
- JUnit 5 y Mockito (testing)


## 📄 Licencia

Este proyecto está bajo la licencia **MIT** — ver `LICENSE`.
