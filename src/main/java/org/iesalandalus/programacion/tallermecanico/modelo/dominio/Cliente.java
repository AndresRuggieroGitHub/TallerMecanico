package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {
    private String nombre;
    private String dni;
    private String telefono;

    private static final String REGEX_NOMBRE = "^[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*$";
    private static final String REGEX_DNI = "^\\d{8}[A-Z]$";
    private static final String REGEX_TELEFONO = "^\\d{9}$";
    private static final String[] LETRAS_DNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

    public Cliente(String nombre, String dni, String telefono) {
        validarNombre(nombre);
        validarDni(dni);
        validarTelefono(telefono);
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No es posible copiar un cliente nulo.");
        }
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.telefono = cliente.telefono;
    }

    private void validarNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser nulo.");
        }
        if (!Pattern.matches(REGEX_NOMBRE, nombre)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
    }

    private void validarDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("El DNI no puede ser nulo.");
        }
        if (!Pattern.matches(REGEX_DNI, dni)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        validarLetraDni(dni);
    }

    private void validarLetraDni(String dni) {
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        int indice = numero % 23;
        if (!LETRAS_DNI[indice].equals(String.valueOf(letra))) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("El teléfono no puede ser nulo.");
        }
        if (!Pattern.matches(REGEX_TELEFONO, telefono)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
    }

    public static Cliente get(String dni) {
        if (dni == null) {
            throw new NullPointerException("El DNI no puede ser nulo.");
        }
        if (!Pattern.matches(REGEX_DNI, dni)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        int indice = numero % 23;
        if (!LETRAS_DNI[indice].equals(String.valueOf(letra))) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        return new Cliente("Patricio Estrella", dni, "950111111");
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}
