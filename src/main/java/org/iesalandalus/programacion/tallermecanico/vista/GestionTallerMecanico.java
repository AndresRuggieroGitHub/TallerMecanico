package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionTallerMecanico extends JFrame {
    private Modelo modelo;
    private JTabbedPane tabPane;

    // Componentes para Clientes
    private JTable tablaClientes;
    private DefaultTableModel modeloTablaClientes;
    private JTextField txtNombreCliente, txtDniCliente, txtTelefonoCliente;

    // Componentes para Vehículos
    private JTable tablaVehiculos;
    private DefaultTableModel modeloTablaVehiculos;
    private JTextField txtMarcaVehiculo, txtModeloVehiculo, txtMatriculaVehiculo;

    // Componentes para Revisiones
    private JTable tablaRevisiones;
    private DefaultTableModel modeloTablaRevisiones;
    private JComboBox<Cliente> cbClienteRevision;
    private JComboBox<Vehiculo> cbVehiculoRevision;

    public GestionTallerMecanico() {
        modelo = new Modelo();
        
        setTitle("🔧 Gestor Taller Mecánico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Panel superior con header
        JPanel headerPanel = crearHeader();
        add(headerPanel, BorderLayout.NORTH);

        // TabPane
        tabPane = new JTabbedPane();
        tabPane.addTab("👥 Clientes", crearPanelClientes());
        tabPane.addTab("🚗 Vehículos", crearPanelVehiculos());
        tabPane.addTab("🔧 Revisiones", crearPanelRevisiones());
        add(tabPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(44, 62, 80));
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("🔧 GESTOR DE TALLER MECÁNICO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Gestión completa de clientes, vehículos y revisiones");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(236, 240, 241));

        JPanel textoPanel = new JPanel(new BorderLayout());
        textoPanel.setOpaque(false);
        textoPanel.add(titulo, BorderLayout.NORTH);
        textoPanel.add(subtitulo, BorderLayout.SOUTH);

        header.add(textoPanel, BorderLayout.WEST);
        return header;
    }

    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Formulario
        JPanel formulario = crearFormularioClientes();
        panel.add(formulario, BorderLayout.NORTH);

        // Tabla
        modeloTablaClientes = new DefaultTableModel(new String[]{"Nombre", "DNI", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloTablaClientes);
        tablaClientes.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        JPanel tablaPanelConBotones = new JPanel(new BorderLayout());
        tablaPanelConBotones.add(new JLabel("📊 Clientes Registrados"), BorderLayout.NORTH);
        tablaPanelConBotones.add(scrollPane, BorderLayout.CENTER);

        JButton btnEliminarCliente = new JButton("🗑️ Eliminar Cliente");
        btnEliminarCliente.setBackground(new Color(231, 76, 60));
        btnEliminarCliente.setForeground(Color.WHITE);
        btnEliminarCliente.addActionListener(e -> eliminarClienteSeleccionado());

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(btnEliminarCliente);
        tablaPanelConBotones.add(botonesPanel, BorderLayout.SOUTH);

        panel.add(tablaPanelConBotones, BorderLayout.CENTER);

        actualizarTablaClientes();
        return panel;
    }

    private JPanel crearFormularioClientes() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("📝 Nuevo Cliente"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        formulario.setBackground(new Color(236, 240, 241));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombreCliente = new JTextField(20);
        formulario.add(txtNombreCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formulario.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;
        txtDniCliente = new JTextField(20);
        formulario.add(txtDniCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formulario.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        txtTelefonoCliente = new JTextField(20);
        formulario.add(txtTelefonoCliente, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton btnAnadir = new JButton("➕ Añadir Cliente");
        btnAnadir.setBackground(new Color(39, 174, 96));
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.addActionListener(e -> anadirCliente());
        formulario.add(btnAnadir, gbc);

        return formulario;
    }

    private JPanel crearPanelVehiculos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formulario = crearFormularioVehiculos();
        panel.add(formulario, BorderLayout.NORTH);

        modeloTablaVehiculos = new DefaultTableModel(new String[]{"Marca", "Modelo", "Matrícula"}, 0);
        tablaVehiculos = new JTable(modeloTablaVehiculos);
        tablaVehiculos.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tablaVehiculos);

        JPanel tablaPanelConBotones = new JPanel(new BorderLayout());
        tablaPanelConBotones.add(new JLabel("📊 Vehículos Registrados"), BorderLayout.NORTH);
        tablaPanelConBotones.add(scrollPane, BorderLayout.CENTER);

        JButton btnEliminarVehiculo = new JButton("🗑️ Eliminar Vehículo");
        btnEliminarVehiculo.setBackground(new Color(231, 76, 60));
        btnEliminarVehiculo.setForeground(Color.WHITE);
        btnEliminarVehiculo.addActionListener(e -> eliminarVehiculoSeleccionado());

        JPanel botonesPanel = new JPanel();
        botonesPanel.add(btnEliminarVehiculo);
        tablaPanelConBotones.add(botonesPanel, BorderLayout.SOUTH);

        panel.add(tablaPanelConBotones, BorderLayout.CENTER);

        actualizarTablaVehiculos();
        return panel;
    }

    private JPanel crearFormularioVehiculos() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("📝 Nuevo Vehículo"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        formulario.setBackground(new Color(236, 240, 241));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulario.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1;
        txtMarcaVehiculo = new JTextField(20);
        formulario.add(txtMarcaVehiculo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formulario.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1;
        txtModeloVehiculo = new JTextField(20);
        formulario.add(txtModeloVehiculo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formulario.add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1;
        txtMatriculaVehiculo = new JTextField(20);
        formulario.add(txtMatriculaVehiculo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton btnAnadir = new JButton("➕ Añadir Vehículo");
        btnAnadir.setBackground(new Color(39, 174, 96));
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.addActionListener(e -> anadirVehiculo());
        formulario.add(btnAnadir, gbc);

        return formulario;
    }

    private JPanel crearPanelRevisiones() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formulario = crearFormularioRevisiones();
        panel.add(formulario, BorderLayout.NORTH);

        modeloTablaRevisiones = new DefaultTableModel(
                new String[]{"Cliente", "Vehículo", "Fecha Inicio", "Estado", "Precio"}, 0
        );
        tablaRevisiones = new JTable(modeloTablaRevisiones);
        tablaRevisiones.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tablaRevisiones);

        JPanel tablaPanelConBotones = new JPanel(new BorderLayout());
        tablaPanelConBotones.add(new JLabel("📊 Revisiones"), BorderLayout.NORTH);
        tablaPanelConBotones.add(scrollPane, BorderLayout.CENTER);

        panel.add(tablaPanelConBotones, BorderLayout.CENTER);

        actualizarTablaRevisiones();
        return panel;
    }

    private JPanel crearFormularioRevisiones() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("📝 Nueva Revisión"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        formulario.setBackground(new Color(236, 240, 241));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulario.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1;
        cbClienteRevision = new JComboBox<>(modelo.getClientes().toArray(new Cliente[0]));
        formulario.add(cbClienteRevision, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formulario.add(new JLabel("Vehículo:"), gbc);
        gbc.gridx = 1;
        cbVehiculoRevision = new JComboBox<>(modelo.getVehiculos().toArray(new Vehiculo[0]));
        formulario.add(cbVehiculoRevision, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton btnAnadir = new JButton("➕ Iniciar Revisión");
        btnAnadir.setBackground(new Color(39, 174, 96));
        btnAnadir.setForeground(Color.WHITE);
        btnAnadir.addActionListener(e -> anadirRevision());
        formulario.add(btnAnadir, gbc);

        return formulario;
    }

    private void anadirCliente() {
        try {
            String nombre = txtNombreCliente.getText().trim();
            String dni = txtDniCliente.getText().trim();
            String telefono = txtTelefonoCliente.getText().trim();

            if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor completa todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Cliente cliente = new Cliente(nombre, dni, telefono);
            modelo.insertar(cliente);
            actualizarTablaClientes();
            actualizarComboClientes();

            txtNombreCliente.setText("");
            txtDniCliente.setText("");
            txtTelefonoCliente.setText("");

            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void anadirVehiculo() {
        try {
            String marca = txtMarcaVehiculo.getText().trim();
            String modelo = txtModeloVehiculo.getText().trim();
            String matricula = txtMatriculaVehiculo.getText().trim();

            if (marca.isEmpty() || modelo.isEmpty() || matricula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor completa todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Vehiculo vehiculo = new Vehiculo(marca, modelo, matricula);
            this.modelo.insertar(vehiculo);
            actualizarTablaVehiculos();
            actualizarComboVehiculos();

            txtMarcaVehiculo.setText("");
            txtModeloVehiculo.setText("");
            txtMatriculaVehiculo.setText("");

            JOptionPane.showMessageDialog(this, "Vehículo agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void anadirRevision() {
        try {
            Cliente cliente = (Cliente) cbClienteRevision.getSelectedItem();
            Vehiculo vehiculo = (Vehiculo) cbVehiculoRevision.getSelectedItem();

            if (cliente == null || vehiculo == null) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona cliente y vehículo", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            LocalDate fechaInicio = LocalDate.now().minusDays(1);
            Revision revision = new Revision(cliente, vehiculo, fechaInicio);
            
            JOptionPane.showMessageDialog(this, "Revisión creada. Funcionalidad expandible en futuras versiones.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarClienteSeleccionado() {
        int fila = tablaClientes.getSelectedRow();
        if (fila >= 0) {
            try {
                Cliente cliente = modelo.getClientes().get(fila);
                modelo.borrar(cliente);
                actualizarTablaClientes();
                actualizarComboClientes();
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un cliente", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminarVehiculoSeleccionado() {
        int fila = tablaVehiculos.getSelectedRow();
        if (fila >= 0) {
            try {
                Vehiculo vehiculo = modelo.getVehiculos().get(fila);
                modelo.borrar(vehiculo);
                actualizarTablaVehiculos();
                actualizarComboVehiculos();
                JOptionPane.showMessageDialog(this, "Vehículo eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un vehículo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarTablaClientes() {
        modeloTablaClientes.setRowCount(0);
        for (Cliente cliente : modelo.getClientes()) {
            modeloTablaClientes.addRow(new Object[]{
                    cliente.getNombre(),
                    cliente.getDni(),
                    cliente.getTelefono()
            });
        }
    }

    private void actualizarTablaVehiculos() {
        modeloTablaVehiculos.setRowCount(0);
        for (Vehiculo vehiculo : modelo.getVehiculos()) {
            modeloTablaVehiculos.addRow(new Object[]{
                    vehiculo.marca(),
                    vehiculo.modelo(),
                    vehiculo.matricula()
            });
        }
    }

    private void actualizarTablaRevisiones() {
        modeloTablaRevisiones.setRowCount(0);
        for (Revision revision : modelo.getRevisiones()) {
            modeloTablaRevisiones.addRow(new Object[]{
                    revision.getCliente().getNombre(),
                    revision.getVehiculo().marca() + " " + revision.getVehiculo().modelo(),
                    revision.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    revision.estaCerrada() ? "✓ Cerrada" : "⏳ Abierta",
                    String.format("%.2f €", revision.getPrecio())
            });
        }
    }

    private void actualizarComboClientes() {
        cbClienteRevision.removeAllItems();
        for (Cliente cliente : modelo.getClientes()) {
            cbClienteRevision.addItem(cliente);
        }
    }

    private void actualizarComboVehiculos() {
        cbVehiculoRevision.removeAllItems();
        for (Vehiculo vehiculo : modelo.getVehiculos()) {
            cbVehiculoRevision.addItem(vehiculo);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionTallerMecanico());
    }
}
