package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public class Vehiculo {
    private String marca;
    private String modelo;
    private String matricula;

    private static final String REGEX_MARCA = "^[A-Z][a-zA-Z\\-]*[a-zA-Z0-9]$";
    private static final String REGEX_MATRICULA = "^\\d{4}[A-Z]{3}$";

    public Vehiculo(String marca, String modelo, String matricula) {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    private void validarMarca(String marca) {
        if (marca == null) {
            throw new NullPointerException("La marca no puede ser nula.");
        }
        if (!Pattern.matches(REGEX_MARCA, marca)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    private void validarModelo(String modelo) {
        if (modelo == null) {
            throw new NullPointerException("El modelo no puede ser nulo.");
        }
        if (modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    private void validarMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("La matrícula no puede ser nula.");
        }
        if (!Pattern.matches(REGEX_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public static Vehiculo get(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("La matrícula no puede ser nula.");
        }
        if (!Pattern.matches(REGEX_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return new Vehiculo("Renault", "Megane", matricula);
    }

    public String marca() {
        return marca;
    }

    public String modelo() {
        return modelo;
    }

    public String matricula() {
        return matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }
}
