# 🔧 Gestor de Taller Mecánico

**Aplicación Java con interfaz gráfica para la gestión integral de un taller mecánico.**

## 📋 Descripción

Sistema de gestión para talleres mecánicos que permite administrar:
- **Clientes**: registrar, modificar, buscar y eliminar clientes con información de contacto
- **Vehículos**: control de marca, modelo y matrícula de todos los coches en el taller
- **Revisiones**: seguimiento de trabajos de revisión, costos y tiempos de reparación

## ✨ Características

### 🎯 Funcionalidades principales
- ✅ Gestión completa de clientes (CRUD)
- ✅ Administración de vehículos
- ✅ Seguimiento de revisiones y reparaciones
- ✅ Cálculo automático de costos
- ✅ Interfaz gráfica intuitiva (Swing)
- ✅ Validación de datos (DNI, teléfono, matrícula)

### 🖥️ Interfaz Gráfica
La aplicación cuenta con una interfaz moderna con 3 pestañas principales:
- **👥 Clientes**: Agregar y gestionar clientes
- **🚗 Vehículos**: Registro y control de vehículos
- **🔧 Revisiones**: Seguimiento de trabajos en el taller

## 🛠️ Tecnologías utilizadas

- **Java 11+**
- **Gradle** (gestor de dependencias y compilación)
- **Swing** (interfaz gráfica)
- **JUnit 5** (testing)
- **Mockito** (mocking para tests)

## 📦 Requisitos

- **Java 11** o superior
- **Gradle 6.0** o superior (opcional, incluye wrapper)

## 🚀 Instalación y Ejecución

### Clonar el repositorio
```bash
git clone https://github.com/AndresRuggieroGitHub/TallerMecanico.git
cd TallerMecanico
```

### Compilar
```bash
./gradlew build
```

### Ejecutar la aplicación
```bash
./gradlew run
```

### Ejecutar los tests
```bash
./gradlew test
```

## 📂 Estructura del proyecto

```
src/
├── main/java/org/iesalandalus/programacion/tallermecanico/
│   ├── modelo/
│   │   ├── Modelo.java                    # Coordinador principal
│   │   ├── dominio/
│   │   │   ├── Cliente.java              # Entidad de cliente
│   │   │   ├── Vehiculo.java             # Entidad de vehículo
│   │   │   └── Revision.java             # Entidad de revisión
│   │   └── negocio/
│   │       ├── Clientes.java             # Gestión de clientes
│   │       ├── Vehiculos.java            # Gestión de vehículos
│   │       └── Revisiones.java           # Gestión de revisiones
│   └── vista/
│       └── GestionTallerMecanico.java    # Interfaz gráfica (GUI)
└── test/java/                             # Tests unitarios
```

## 💡 Cómo usar

1. **Agregar un cliente**:
   - Rellena los campos (Nombre, DNI, Teléfono)
   - Haz clic en "➕ Añadir Cliente"

2. **Agregar un vehículo**:
   - Introduce Marca, Modelo y Matrícula
   - Pulsa "➕ Añadir Vehículo"

3. **Crear una revisión**:
   - Selecciona cliente y vehículo
   - Haz clic en "➕ Iniciar Revisión"

4. **Eliminar registros**:
   - Selecciona la fila en la tabla
   - Pulsa el botón de eliminar

## 📝 Validaciones implementadas

- **DNI**: Formato válido español (8 números + letra correcta)
- **Teléfono**: 9 dígitos numéricos
- **Nombre cliente**: Palabras con mayúscula inicial
- **Matrícula vehículo**: 4 números + 3 letras (formato español)

## 📊 Cálculo de costos

El precio de una revisión se calcula como:
```
Precio = (Horas × 10 €) + (Días × 30 €) + Precio del material
```

Donde:
- **Horas**: Tiempo dedicado a la reparación
- **Días**: Días que el vehículo permanece en el taller
- **Material**: Coste de piezas y suministros utilizados

## 🔗 Información del proyecto

- **Profesor**: José Ramón Jiménez Reyes
- **Rama principal**: `master`
- **Rama de desarrollo**: `sprint_inicial`
- **Estado**: En desarrollo (Sprint 1)

## 📄 Licencia

Este proyecto es parte de una tarea educativa.

---

**Última actualización**: Febrero 2026