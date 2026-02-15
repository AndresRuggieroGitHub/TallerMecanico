package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Vehiculos {
    private List<Vehiculo> vehiculos;

    public Vehiculos() {
        this.vehiculos = new ArrayList<>();
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (buscar(vehiculo) != null) {
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        vehiculos.add(vehiculo);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede borrar un vehículo nulo.");
        }
        if (buscar(vehiculo) == null) {
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
        vehiculos.remove(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede buscar un vehículo nulo.");
        }
        for (Vehiculo v : vehiculos) {
            if (v.equals(vehiculo)) {
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(vehiculos);
    }
}
