package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo() {
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.revisiones = new Revisiones();
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        Cliente clienteEncontrado = clientes.buscar(revision.getCliente());
        if (clienteEncontrado == null) {
            throw new OperationNotSupportedException("El cliente de la revisión no existe.");
        }
        Vehiculo vehiculoEncontrado = vehiculos.buscar(revision.getVehiculo());
        if (vehiculoEncontrado == null) {
            throw new OperationNotSupportedException("El vehículo de la revisión no existe.");
        }
        Revision revisionConClienteYVehiculoExistentes = new Revision(clienteEncontrado, vehiculoEncontrado, revision.getFechaInicio());
        revisiones.insertar(revisionConClienteYVehiculoExistentes);
    }

    public Cliente buscar(Cliente cliente) {
        return clientes.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return revisiones.buscar(revision);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precio) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision, precio);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> copia = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            copia.add(new Cliente(cliente));
        }
        return copia;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> copia = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            copia.add(new Revision(revision));
        }
        return copia;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> copia = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)) {
            copia.add(new Revision(revision));
        }
        return copia;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> copia = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)) {
            copia.add(new Revision(revision));
        }
        return copia;
    }

    public void terminar() {
        // No hace nada, pero es parte de la interfaz
    }
}
