package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        if (vehiculo == null) {
            throw new NullPointerException("El vehículo no puede ser nulo.");
        }
        if (fechaInicio == null) {
            throw new NullPointerException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.horas = 0;
        this.precioMaterial = 0;
    }

    public Revision(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }
        this.cliente = new Cliente(revision.cliente);
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (fechaFin == null) {
            throw new NullPointerException("La fecha de fin no puede ser nula.");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        this.fechaFin = fechaFin;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public float getPrecio() {
        float diasPrecio = 30.0f * ChronoUnit.DAYS.between(fechaInicio, estaCerrada() ? fechaFin : LocalDate.now());
        float horasPrecio = 10.0f * horas;
        return diasPrecio + horasPrecio + precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(cliente, revision.cliente) &&
                Objects.equals(vehiculo, revision.vehiculo) &&
                Objects.equals(fechaInicio, revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }

    @Override
    public String toString() {
        String cadenaCliente = cliente.toString();
        String cadenaVehiculo = vehiculo.toString();
        String cadenaFechaInicio = fechaInicio.format(FORMATO_FECHA);
        String cadenaFechaFin = estaCerrada() ? fechaFin.format(FORMATO_FECHA) : "";

        if (estaCerrada()) {
            return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",
                    cadenaCliente, cadenaVehiculo, cadenaFechaInicio, cadenaFechaFin, horas, precioMaterial, getPrecio());
        } else {
            return String.format("%s - %s: (%s - ), %d horas, %.2f € en material",
                    cadenaCliente, cadenaVehiculo, cadenaFechaInicio, horas, precioMaterial);
        }
    }
}
