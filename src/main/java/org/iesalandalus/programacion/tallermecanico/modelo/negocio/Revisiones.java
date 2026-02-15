package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Revisiones {
    private List<Revision> revisiones;

    public Revisiones() {
        this.revisiones = new ArrayList<>();
    }

    public void insertar(Revision revision) throws OperationNotSupportedException {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }
        if (!revision.estaCerrada()) {
            throw new OperationNotSupportedException("No se puede insertar una revisión abierta.");
        }
        if (buscar(revision) != null) {
            throw new OperationNotSupportedException("Ya existe una revisión con ese cliente, vehículo y fecha de inicio.");
        }
        revisiones.add(revision);
    }

    public void borrar(Revision revision) throws OperationNotSupportedException {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        if (buscar(revision) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión con ese cliente, vehículo y fecha de inicio.");
        }
        revisiones.remove(revision);
    }

    public Revision buscar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede buscar una revisión nula.");
        }
        for (Revision r : revisiones) {
            if (r.equals(revision)) {
                return r;
            }
        }
        return null;
    }

    public List<Revision> get() {
        return new ArrayList<>(revisiones);
    }

    public List<Revision> get(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision r : revisiones) {
            if (r.getCliente().equals(cliente)) {
                revisionesCliente.add(r);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("El vehículo no puede ser nulo.");
        }
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision r : revisiones) {
            if (r.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(r);
            }
        }
        return revisionesVehiculo;
    }

    public void anadirHoras(Revision revision, int horas) throws javax.naming.OperationNotSupportedException {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }
        try {
            revision.anadirHoras(horas);
        } catch (javax.naming.OperationNotSupportedException e) {
            throw e;
        }
    }

    public void anadirPrecioMaterial(Revision revision, float precio) throws javax.naming.OperationNotSupportedException {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }
        try {
            revision.anadirPrecioMaterial(precio);
        } catch (javax.naming.OperationNotSupportedException e) {
            throw e;
        }
    }

    public void cerrar(Revision revision, java.time.LocalDate fechaFin) throws javax.naming.OperationNotSupportedException {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }
        try {
            revision.cerrar(fechaFin);
        } catch (javax.naming.OperationNotSupportedException e) {
            throw e;
        }
    }
}
