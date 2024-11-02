package conversor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.BigDecimal;

public class ConversorGUI extends JFrame {

    private JTextField pesosArgentinos;
    private JTextField dolaresYanquis;
    private JTextField resultARSField; // Result field for ARS
    private JTextField resultUSDField; // Result field for USD
    private JTextField aumentarARSField; // For increasing ARS
    private JTextField aumentarUSDField; // For increasing USD
    private JTextField retirarARSField; // For withdrawing ARS
    private JTextField retirarUSDField; // For withdrawing USD
    
    // Fields for Cotización
    private JTextField cotizarARSField; // Input for ARS to quote
    private JTextField cotizarUSDField; // Input for USD to quote
    private JTextField cotizacionARSUSDField; // Result for ARS/USD
    private JTextField cotizacionUSDARSField; // Result for USD/ARS

    private ConversorMoneda conversorMoneda;

    public ConversorGUI() {
        conversorMoneda = new ConversorMoneda();
        createUI();
    }

    private void createUI() {
        setTitle("Conversor de Moneda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        pesosArgentinos = new JTextField(10);
        aumentarARSField = new JTextField(10);
        retirarARSField = new JTextField(10);

        dolaresYanquis = new JTextField(10);
        aumentarUSDField = new JTextField(10);
        retirarUSDField = new JTextField(10);

        resultARSField = new JTextField(10);
        resultUSDField = new JTextField(10);
        resultARSField.setEditable(false);
        resultUSDField.setEditable(false);

        // Cotización fields
        cotizarARSField = new JTextField(10);
        cotizarUSDField = new JTextField(10);
        cotizacionARSUSDField = new JTextField(10);
        cotizacionUSDARSField = new JTextField(10);
        cotizacionARSUSDField.setEditable(false);
        cotizacionUSDARSField.setEditable(false);

        applyDocumentFilters();

        JButton aumentarARSPesoButton = new JButton("Aumentar ARS");
        JButton retirarARSPesoButton = new JButton("Retirar ARS");
        JButton aumentarUSDButton = new JButton("Aumentar USD");
        JButton retirarUSDButton = new JButton("Retirar USD");
        JButton cotizarButton = new JButton("Cotizar");
        JButton clearButton = new JButton("Limpiar Campos");

        JButton convertirUSDButton = new JButton("Convertir a USD");
        JButton convertirARSButton = new JButton("Convertir a ARS");

        aumentarARSPesoButton.addActionListener(e -> performOperation("aumentarARS", aumentarARSPesoButton));
        retirarARSPesoButton.addActionListener(e -> performOperation("retirarARS", retirarARSPesoButton));
        aumentarUSDButton.addActionListener(e -> performOperation("aumentarUSD", aumentarUSDButton));
        retirarUSDButton.addActionListener(e -> performOperation("retirarUSD", retirarUSDButton));
        convertirUSDButton.addActionListener(e -> performConversion("USD"));
        convertirARSButton.addActionListener(e -> performConversion("ARS"));
        cotizarButton.addActionListener(e -> performCotizacion());

        clearButton.addActionListener(e -> clearFields());

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ARS Inputs
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ARS:"), gbc);

        gbc.gridx = 1;
        add(pesosArgentinos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(aumentarARSPesoButton, gbc);

        gbc.gridx = 1;
        add(aumentarARSField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(retirarARSPesoButton, gbc);

        gbc.gridx = 1;
        add(retirarARSField, gbc);

        // USD Inputs
        gbc.gridx = 3; // Move to the next column for USD
        gbc.gridy = 0;
        add(new JLabel("USD:"), gbc);

        gbc.gridx = 4;
        add(dolaresYanquis, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        add(aumentarUSDButton, gbc);

        gbc.gridx = 4;
        add(aumentarUSDField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        add(retirarUSDButton, gbc);

        gbc.gridx = 4;
        add(retirarUSDField, gbc);

        // Result Fields
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Saldo ARS:"), gbc);
        gbc.gridx = 1;
        add(resultARSField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        add(new JLabel("Saldo USD:"), gbc);
        gbc.gridx = 4;
        add(resultUSDField, gbc);

        // Cotización Fields
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Cotizar ARS:"), gbc);
        gbc.gridx = 1;
        add(cotizarARSField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        add(new JLabel("Cotizar USD:"), gbc);
        gbc.gridx = 4;
        add(cotizarUSDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(cotizarButton, gbc);

        gbc.gridx = 1;
        add(new JLabel("ARS/USD:"), gbc);
        gbc.gridx = 2;
        add(cotizacionARSUSDField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        add(new JLabel("USD/ARS:"), gbc);
        gbc.gridx = 4;
        add(cotizacionUSDARSField, gbc);

        // Adding a gap for better spacing
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 5; // Span across multiple columns
        gbc.insets = new Insets(10, 0, 10, 0); // Add space above and below
        add(new JLabel("Conversiónes:"), gbc);

        // Conversion buttons
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Reset to default
        gbc.gridx = 0;
        add(convertirUSDButton, gbc);

        gbc.gridx = 1;
        add(convertirARSButton, gbc);

        // Clear Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 5; // Make it span across all columns
        add(clearButton, gbc);

        setSize(900, 450); // Adjusted size for better layout
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void applyDocumentFilters() {
        // Apply document filters for all numeric fields
        ((AbstractDocument) pesosArgentinos.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) dolaresYanquis.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) aumentarARSField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) aumentarUSDField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) retirarARSField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) retirarUSDField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) cotizarARSField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) cotizarUSDField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    private void performOperation(String operation, JButton button) {
        try {
            button.setBackground(Color.ORANGE);

            BigDecimal result = BigDecimal.ZERO; // Default result initialization

            switch (operation) {
                case "aumentarARS":
                    String numARSStr = aumentarARSField.getText().replace(',', '.');
                    Number numARS = new BigDecimal(numARSStr);
                    result = conversorMoneda.aumentar(pesosArgentinos.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(pesosArgentinos.getText().replace(',', '.')), numARS);
                    resultARSField.setText(result.toString()); // Set result for ARS
                    break;
                case "retirarARS":
                    String numRetirarARSStr = retirarARSField.getText().replace(',', '.');
                    Number numRetirarARS = new BigDecimal(numRetirarARSStr);
                    result = conversorMoneda.retirar(pesosArgentinos.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(pesosArgentinos.getText().replace(',', '.')), numRetirarARS);
                    resultARSField.setText(result.toString()); // Set result for ARS
                    break;
                case "aumentarUSD":
                    String numUSDStr = aumentarUSDField.getText().replace(',', '.');
                    Number numUSD = new BigDecimal(numUSDStr);
                    result = conversorMoneda.aumentar(dolaresYanquis.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(dolaresYanquis.getText().replace(',', '.')), numUSD);
                    resultUSDField.setText(result.toString()); // Set result for USD
                    break;
                case "retirarUSD":
                    String numRetirarUSDStr = retirarUSDField.getText().replace(',', '.');
                    Number numRetirarUSD = new BigDecimal(numRetirarUSDStr);
                    result = conversorMoneda.retirar(dolaresYanquis.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(dolaresYanquis.getText().replace(',', '.')), numRetirarUSD);
                    resultUSDField.setText(result.toString()); // Set result for USD
                    break;
                case "convertir":
                    // Implement conversion logic here if needed
                    break;
                default:
                    throw new UnsupportedOperationException("Operación no soportada");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performCotizacion() {
        try {
            String cotizarARSStr = cotizarARSField.getText().replace(',', '.');
            String cotizarUSDStr = cotizarUSDField.getText().replace(',', '.');

            BigDecimal cotizarARS = new BigDecimal(cotizarARSStr);
            BigDecimal cotizarUSD = new BigDecimal(cotizarUSDStr);

            // Example of how to calculate cotizations (you may need to implement the actual logic)
            BigDecimal cotizacionARSUSD = cotizarARS.divide(cotizarUSD, BigDecimal.ROUND_HALF_UP); // ARS/USD
            BigDecimal cotizacionUSDARS = cotizarUSD.divide(cotizarARS, BigDecimal.ROUND_HALF_UP); // USD/ARS

            cotizacionARSUSDField.setText(cotizacionARSUSD.toString());
            cotizacionUSDARSField.setText(cotizacionUSDARS.toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para cotización.", "Error", JOptionPane.ERROR_MESSAGE);
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
                BigDecimal amountToConvert = new BigDecimal(pesosArgentinos.getText());
                result = amountToConvert.multiply(cotizacion);
                resultUSDField.setText(result.toString());
            } else if (currency.equals("ARS")) {
                BigDecimal cotizacion = new BigDecimal(cotizacionUSDARSField.getText());
                BigDecimal amountToConvert = new BigDecimal(dolaresYanquis.getText());
                result = amountToConvert.multiply(cotizacion);
                resultARSField.setText(result.toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para la conversión.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        pesosArgentinos.setText("");
        dolaresYanquis.setText("");
        resultARSField.setText("");
        resultUSDField.setText("");
        aumentarARSField.setText("");
        aumentarUSDField.setText("");
        retirarARSField.setText("");
        retirarUSDField.setText("");
        cotizarARSField.setText("");
        cotizarUSDField.setText("");
        cotizacionARSUSDField.setText("");
        cotizacionUSDARSField.setText("");
        resetButtonColors();
    }

    private void resetButtonColors() {
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(null);
            }
        }
    }

    private class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isNumeric(string)) {
                super.insertString(fb, offset, string.replace(',', '.'), attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (isNumeric(string)) {
                super.replace(fb, offset, length, string.replace(',', '.'), attr);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private boolean isNumeric(String str) {
            return str.matches("[0-9,.]*");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorGUI::new);
    }
}
