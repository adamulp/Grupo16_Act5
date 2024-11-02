package conversor;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class CotizacionPanel extends JPanel {

    private JTextField cotizarARSField; // Input for ARS to quote
    private JTextField cotizarUSDField; // Input for USD to quote
    private JTextField cotizacionARSUSDField; // Result for ARS/USD
    private JTextField cotizacionUSDARSField; // Result for USD/ARS
    private JTextField conversionARSField; // Input for ARS to convert
    private JTextField conversionUSDField; // Input for USD to convert
    private ConversorMoneda conversorMoneda;
    private PasosPanel pasosPanel; // Reference to PasosPanel

    public CotizacionPanel(ConversorMoneda conversorMoneda, PasosPanel pasosPanel) {
        this.conversorMoneda = conversorMoneda;
        this.pasosPanel = pasosPanel; // Initialize PasosPanel reference
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize text fields
        cotizarARSField = new JTextField(10);
        cotizarUSDField = new JTextField(10);
        cotizacionARSUSDField = new JTextField(10);
        cotizacionUSDARSField = new JTextField(10);
        conversionARSField = new JTextField(10);
        conversionUSDField = new JTextField(10);

        cotizacionARSUSDField.setEditable(false);
        cotizacionUSDARSField.setEditable(false);

        // Cotización button
        JButton cotizarButton = new JButton("Cotizar");
        cotizarButton.addActionListener(e -> performCotizacion());

        // Convert buttons
        JButton convertirUSDButton = new JButton("Convertir a USD");
        JButton convertirARSButton = new JButton("Convertir a ARS");

        convertirUSDButton.addActionListener(e -> performConversion("USD"));
        convertirARSButton.addActionListener(e -> performConversion("ARS"));

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cotización Inputs
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Cotizar ARS:"), gbc);
        gbc.gridx = 1;
        add(cotizarARSField, gbc);

        gbc.gridx = 2;
        add(cotizarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cotizar USD:"), gbc);
        gbc.gridx = 1;
        add(cotizarUSDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("ARS/USD:"), gbc);
        gbc.gridx = 1;
        add(cotizacionARSUSDField, gbc);

        gbc.gridx = 2;
        add(new JLabel("USD/ARS:"), gbc);
        gbc.gridx = 3;
        add(cotizacionUSDARSField, gbc);

        // Conversion Inputs
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Conversión a USD:"), gbc);
        gbc.gridx = 1;
        add(conversionARSField, gbc);
        gbc.gridx = 2;
        add(convertirUSDButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Conversión a ARS:"), gbc);
        gbc.gridx = 1;
        add(conversionUSDField, gbc);
        gbc.gridx = 2;
        add(convertirARSButton, gbc);
    }

    private void performCotizacion() {
        try {
            String cotizarARSStr = cotizarARSField.getText().replace(',', '.');
            String cotizarUSDStr = cotizarUSDField.getText().replace(',', '.');

            BigDecimal cotizarARS = new BigDecimal(cotizarARSStr);
            BigDecimal cotizarUSD = new BigDecimal(cotizarUSDStr);

            BigDecimal cotizacionARSUSD = cotizarARS.divide(cotizarUSD, BigDecimal.ROUND_HALF_UP); // ARS/USD
            BigDecimal cotizacionUSDARS = cotizarUSD.divide(cotizarARS, BigDecimal.ROUND_HALF_UP); // USD/ARS

            cotizacionARSUSDField.setText(cotizacionARSUSD.toString());
            cotizacionUSDARSField.setText(cotizacionUSDARS.toString());

            // Log the cotization action
            pasosPanel.addPaso("Cotizar: " + cotizarARSStr + " ARS con " + cotizarUSDStr + " USD");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para cotización.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performConversion(String currency) {
        try {
            BigDecimal result;

            // Perform conversion using the previously calculated cotización values
            if (currency.equals("USD")) {
                BigDecimal cotizacion = new BigDecimal(cotizacionARSUSDField.getText());
                BigDecimal amountToConvert = new BigDecimal(conversionARSField.getText());
                result = amountToConvert.multiply(cotizacion);
                JOptionPane.showMessageDialog(this, "Resultado: " + result.toString(), "Conversión a USD", JOptionPane.INFORMATION_MESSAGE);
                pasosPanel.addPaso("Convertir ARS a USD: " + amountToConvert + " * " + cotizacion); // Log step
            } else if (currency.equals("ARS")) {
                BigDecimal cotizacion = new BigDecimal(cotizacionUSDARSField.getText());
                BigDecimal amountToConvert = new BigDecimal(conversionUSDField.getText());
                result = amountToConvert.multiply(cotizacion);
                JOptionPane.showMessageDialog(this, "Resultado: " + result.toString(), "Conversión a ARS", JOptionPane.INFORMATION_MESSAGE);
                pasosPanel.addPaso("Convertir USD a ARS: " + amountToConvert + " * " + cotizacion); // Log step
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para la conversión.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
