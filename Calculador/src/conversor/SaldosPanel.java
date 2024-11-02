package conversor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.BigDecimal;

public class SaldosPanel extends JPanel {

    private JTextField pesosArgentinos;
    private JTextField aumentarARSField; // For increasing ARS
    private JTextField retirarARSField; // For withdrawing ARS
    private JTextField dolaresYanquis;
    private JTextField aumentarUSDField; // For increasing USD
    private JTextField retirarUSDField; // For withdrawing USD
    private JTextField resultARSField; // Result field for ARS
    private JTextField resultUSDField; // Result field for USD
    private ConversorMoneda conversorMoneda;
    private PasosPanel pasosPanel; // Reference to PasosPanel

    public SaldosPanel(ConversorMoneda conversorMoneda, PasosPanel pasosPanel) {
        this.conversorMoneda = conversorMoneda;
        this.pasosPanel = pasosPanel; // Initialize PasosPanel reference
        createUI();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Initialize text fields
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

        applyDocumentFilters();

        JButton aumentarARSPesoButton = new JButton("Aumentar ARS");
        JButton retirarARSPesoButton = new JButton("Retirar ARS");
        JButton aumentarUSDButton = new JButton("Aumentar USD");
        JButton retirarUSDButton = new JButton("Retirar USD");

        // Add action listeners for buttons
        aumentarARSPesoButton.addActionListener(e -> performOperation("aumentarARS"));
        retirarARSPesoButton.addActionListener(e -> performOperation("retirarARS"));
        aumentarUSDButton.addActionListener(e -> performOperation("aumentarUSD"));
        retirarUSDButton.addActionListener(e -> performOperation("retirarUSD"));

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
    }

    private void applyDocumentFilters() {
        // Apply document filters for all numeric fields
        ((AbstractDocument) pesosArgentinos.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) aumentarARSField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) retirarARSField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) dolaresYanquis.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) aumentarUSDField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) retirarUSDField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    private void performOperation(String operation) {
        try {
            BigDecimal result = BigDecimal.ZERO; // Default result initialization

            switch (operation) {
                case "aumentarARS":
                    String numARSStr = aumentarARSField.getText().replace(',', '.');
                    Number numARS = new BigDecimal(numARSStr);
                    result = conversorMoneda.aumentar(pesosArgentinos.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(pesosArgentinos.getText().replace(',', '.')), numARS);
                    resultARSField.setText(result.toString()); // Set result for ARS
                    pasosPanel.addPaso("Aumentar ARS: " + numARSStr); // Log step
                    break;
                case "retirarARS":
                    String numRetirarARSStr = retirarARSField.getText().replace(',', '.');
                    Number numRetirarARS = new BigDecimal(numRetirarARSStr);
                    result = conversorMoneda.retirar(pesosArgentinos.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(pesosArgentinos.getText().replace(',', '.')), numRetirarARS);
                    resultARSField.setText(result.toString()); // Set result for ARS
                    pasosPanel.addPaso("Retirar ARS: " + numRetirarARSStr); // Log step
                    break;
                case "aumentarUSD":
                    String numUSDStr = aumentarUSDField.getText().replace(',', '.');
                    Number numUSD = new BigDecimal(numUSDStr);
                    result = conversorMoneda.aumentar(dolaresYanquis.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(dolaresYanquis.getText().replace(',', '.')), numUSD);
                    resultUSDField.setText(result.toString()); // Set result for USD
                    pasosPanel.addPaso("Aumentar USD: " + numUSDStr); // Log step
                    break;
                case "retirarUSD":
                    String numRetirarUSDStr = retirarUSDField.getText().replace(',', '.');
                    Number numRetirarUSD = new BigDecimal(numRetirarUSDStr);
                    result = conversorMoneda.retirar(dolaresYanquis.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(dolaresYanquis.getText().replace(',', '.')), numRetirarUSD);
                    resultUSDField.setText(result.toString()); // Set result for USD
                    pasosPanel.addPaso("Retirar USD: " + numRetirarUSDStr); // Log step
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

    // DocumentFilter to restrict input to numeric characters
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
            return str.matches("[0-9,.]*"); // Allows digits, commas, and periods
        }
    }
}
