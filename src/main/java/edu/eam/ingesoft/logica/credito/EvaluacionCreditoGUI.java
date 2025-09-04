package edu.eam.ingesoft.logica.credito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class EvaluacionCreditoGUI extends JFrame {
    
    private JTextField txtNombre;
    private JTextField txtIngresos;
    private JTextField txtCreditosActivos;
    private JTextField txtPuntaje;
    private JTextField txtMontoSolicitado;
    private JCheckBox chkCodedor;
    private JTextField txtTasaAnual;
    private JTextField txtPlazoMeses;
    private JTextArea txtResultado;
    private JButton btnSimular;
    private JButton btnLimpiar;
    
    public EvaluacionCreditoGUI() {
        setTitle("FinAurora - Evaluación de Crédito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelSuperior = crearPanelDatos();
        JPanel panelCentral = crearPanelSimulacion();
        JPanel panelInferior = crearPanelResultados();
        JPanel panelBotones = crearPanelBotones();
        
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        add(panelBotones, BorderLayout.EAST);
        
        configurarEventos();
        
        pack();
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(700, 600));
    }
    
    private JPanel crearPanelDatos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Solicitante"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre del solicitante:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Ingresos mensuales ($):"), gbc);
        gbc.gridx = 1;
        txtIngresos = new JTextField(20);
        panel.add(txtIngresos, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Número de créditos activos:"), gbc);
        gbc.gridx = 1;
        txtCreditosActivos = new JTextField(20);
        panel.add(txtCreditosActivos, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Puntaje de crédito (0-1000):"), gbc);
        gbc.gridx = 1;
        txtPuntaje = new JTextField(20);
        panel.add(txtPuntaje, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Monto solicitado ($):"), gbc);
        gbc.gridx = 1;
        txtMontoSolicitado = new JTextField(20);
        panel.add(txtMontoSolicitado, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("¿Tiene codeudor?"), gbc);
        gbc.gridx = 1;
        chkCodedor = new JCheckBox();
        panel.add(chkCodedor, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelSimulacion() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Parámetros de Simulación"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tasa Nominal Anual (%):"), gbc);
        gbc.gridx = 1;
        txtTasaAnual = new JTextField(20);
        panel.add(txtTasaAnual, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Plazo (meses):"), gbc);
        gbc.gridx = 1;
        txtPlazoMeses = new JTextField(20);
        panel.add(txtPlazoMeses, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Resultado de la Evaluación"));
        
        txtResultado = new JTextArea(10, 40);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtResultado);
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnSimular = new JButton("Simular");
        btnSimular.setBackground(new Color(0, 123, 255));
        btnSimular.setForeground(Color.WHITE);
        btnSimular.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(108, 117, 125));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
        
        panel.add(btnSimular);
        panel.add(btnLimpiar);
        
        return panel;
    }
    
    private void configurarEventos() {
        btnSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simularCredito();
            }
        });
        
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    
    private void simularCredito() {
        try {
            String nombre = txtNombre.getText();
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del solicitante", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double ingresos = Double.parseDouble(txtIngresos.getText());
            int creditosActivos = Integer.parseInt(txtCreditosActivos.getText());
            int puntaje = Integer.parseInt(txtPuntaje.getText());
            double monto = Double.parseDouble(txtMontoSolicitado.getText());
            boolean tieneCodedor = chkCodedor.isSelected();
            double tasaAnual = Double.parseDouble(txtTasaAnual.getText());
            int plazoMeses = Integer.parseInt(txtPlazoMeses.getText());
            
            if (puntaje < 0 || puntaje > 1000) {
                JOptionPane.showMessageDialog(this, "El puntaje debe estar entre 0 y 1000", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (ingresos <= 0 || monto <= 0 || tasaAnual < 0 || plazoMeses <= 0) {
                JOptionPane.showMessageDialog(this, "Los valores numéricos deben ser positivos", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            EvaluacionCredito evaluacion = new EvaluacionCredito(nombre, ingresos, 
                creditosActivos, puntaje, monto, tieneCodedor);
            
            double cuotaMensual = evaluacion.calcularCuotaMensual(tasaAnual, plazoMeses);
            boolean aprobado = evaluacion.evaluarAprobacion(tasaAnual, plazoMeses);
            
            DecimalFormat df = new DecimalFormat("#,###.00");
            
            StringBuilder resultado = new StringBuilder();
            resultado.append("========================================\n");
            resultado.append("       RESULTADO DE LA EVALUACIÓN      \n");
            resultado.append("========================================\n\n");
            resultado.append("Solicitante: ").append(nombre).append("\n");
            resultado.append("Puntaje de crédito: ").append(puntaje).append("\n");
            resultado.append("Ingresos mensuales: $").append(df.format(ingresos)).append("\n");
            resultado.append("Monto solicitado: $").append(df.format(monto)).append("\n");
            resultado.append("Plazo: ").append(plazoMeses).append(" meses\n");
            resultado.append("Tasa anual: ").append(tasaAnual).append("%\n");
            resultado.append("Tasa mensual: ").append(String.format("%.2f", tasaAnual/12)).append("%\n\n");
            resultado.append("----------------------------------------\n");
            resultado.append("CUOTA MENSUAL CALCULADA: $").append(df.format(cuotaMensual)).append("\n");
            resultado.append("Porcentaje de ingresos: ").append(String.format("%.1f", (cuotaMensual/ingresos)*100)).append("%\n");
            resultado.append("----------------------------------------\n\n");
            
            if (aprobado) {
                resultado.append("ESTADO: APROBADO\n");
                resultado.append("El crédito ha sido aprobado exitosamente.\n");
            } else {
                resultado.append("ESTADO: RECHAZADO\n");
                resultado.append("El crédito no cumple con las políticas de aprobación.\n");
            }
            
            resultado.append("\n========================================");
            
            txtResultado.setText(resultado.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar la evaluación: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtIngresos.setText("");
        txtCreditosActivos.setText("");
        txtPuntaje.setText("");
        txtMontoSolicitado.setText("");
        chkCodedor.setSelected(false);
        txtTasaAnual.setText("");
        txtPlazoMeses.setText("");
        txtResultado.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EvaluacionCreditoGUI().setVisible(true);
            }
        });
    }
}