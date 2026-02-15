package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private List<Cliente> clientes;

    public Clientes() {
        this.clientes = new ArrayList<>();
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("No se puede insertar un cliente nulo.");
        }
        if (buscar(cliente) != null) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        clientes.add(cliente);
    }

    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("No se puede borrar un cliente nulo.");
        }
        if (buscar(cliente) == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        clientes.remove(cliente);
    }

    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No se puede buscar un cliente nulo.");
        }
        for (Cliente c : clientes) {
            if (c.equals(cliente)) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> get() {
        return new ArrayList<>(clientes);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        if (cliente == null) {
            throw new NullPointerException("No se puede modificar un cliente nulo.");
        }
        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        // En un escenario real, modificaríamos el cliente. Aquí solo validamos.
    }
}
