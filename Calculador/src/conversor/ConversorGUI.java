package conversor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.BigDecimal;

public class ConversorGUI extends JFrame {

    private JTextField pesosArgentinos;
    private JTextField dolaresYanquis;
    private JTextField resultField;
    private JTextField aumentarARSField; // For increasing ARS
    private JTextField aumentarUSDField; // For increasing USD
    private JTextField retirarARSField; // For withdrawing ARS
    private JTextField retirarUSDField; // For withdrawing USD
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

        resultField = new JTextField(10);
        resultField.setEditable(false);

        applyDocumentFilters();

        JButton aumentarARSPesoButton = new JButton("Aumentar ARS");
        JButton retirarARSPesoButton = new JButton("Retirar ARS");
        JButton aumentarUSDButton = new JButton("Aumentar USD");
        JButton retirarUSDButton = new JButton("Retirar USD");
        JButton convertirButton = new JButton("Convertir");
        JButton cotizarButton = new JButton("Cotizar");
        JButton clearButton = new JButton("Limpiar Campos");

        aumentarARSPesoButton.addActionListener(e -> performOperation("aumentarARS", aumentarARSPesoButton));
        retirarARSPesoButton.addActionListener(e -> performOperation("retirarARS", retirarARSPesoButton));
        aumentarUSDButton.addActionListener(e -> performOperation("aumentarUSD", aumentarUSDButton));
        retirarUSDButton.addActionListener(e -> performOperation("retirarUSD", retirarUSDButton));
        convertirButton.addActionListener(e -> performOperation("convertir", convertirButton));
        cotizarButton.addActionListener(e -> performOperation("cotizar", cotizarButton));

        clearButton.addActionListener(e -> clearFields());

        gbc.fill = GridBagConstraints.HORIZONTAL;

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

        gbc.gridx = 3;
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


        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Resultado:"), gbc);

        gbc.gridx = 1;
        add(resultField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        add(convertirButton, gbc);

        gbc.gridx = 1;
        add(cotizarButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 6;
        add(clearButton, gbc);

        setSize(800, 350);
        setLocationRelativeTo(null);
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
                    break;
                case "retirarARS":
                    String numRetirarARSStr = retirarARSField.getText().replace(',', '.');
                    Number numRetirarARS = new BigDecimal(numRetirarARSStr);
                    result = conversorMoneda.retirar(pesosArgentinos.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(pesosArgentinos.getText().replace(',', '.')), numRetirarARS);
                    break;
                case "aumentarUSD":
                    String numUSDStr = aumentarUSDField.getText().replace(',', '.');
                    Number numUSD = new BigDecimal(numUSDStr);
                    result = conversorMoneda.aumentar(dolaresYanquis.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(dolaresYanquis.getText().replace(',', '.')), numUSD);
                    break;
                case "retirarUSD":
                    String numRetirarUSDStr = retirarUSDField.getText().replace(',', '.');
                    Number numRetirarUSD = new BigDecimal(numRetirarUSDStr);
                    result = conversorMoneda.retirar(dolaresYanquis.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(dolaresYanquis.getText().replace(',', '.')), numRetirarUSD);
                    break;
                case "convertir":
                    // Implement conversion logic here if needed
                    break;
                case "cotizar":
                    // Implement quoting logic here if needed
                    break;
                default:
                    throw new UnsupportedOperationException("Operación no soportada");
            }

            resultField.setText(result.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        pesosArgentinos.setText("");
        dolaresYanquis.setText("");
        resultField.setText("");
        aumentarARSField.setText("");
        aumentarUSDField.setText("");
        retirarARSField.setText("");
        retirarUSDField.setText("");
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
