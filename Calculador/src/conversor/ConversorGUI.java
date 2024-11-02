package conversor;

/**
 *
 * @author Grupo16
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class ConversorGUI extends JFrame {
    private JTextField entradaA;
    private JTextField entradaB;
    private JTextField resultField;
    private ConversorMoneda conversorMoneda;

    public ConversorGUI() {
        conversorMoneda = new ConversorMoneda();
        createUI();
    }

    private void createUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        entradaA = new JTextField(10);
        entradaB = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        // Apply DocumentFilter to input fields
        ((AbstractDocument) entradaA.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) entradaB.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        // Buttons
        JButton sumButton = new JButton("Sumar");
        JButton subtractButton = new JButton("Restar");
        JButton multiplyButton = new JButton("Multiplicar");
        JButton divideButton = new JButton("Dividir");
        JButton clearButton = new JButton("Limpiar Campos");

        // Add action listeners for buttons
        sumButton.addActionListener(e -> performOperation("sumar", sumButton));
        subtractButton.addActionListener(e -> performOperation("restar", subtractButton));
        multiplyButton.addActionListener(e -> performOperation("multiplicar", multiplyButton));
        divideButton.addActionListener(e -> performOperation("dividir", divideButton));

        // Action listener for clear button
        clearButton.addActionListener(e -> clearFields());

        // Add components to the frame with GridBagLayout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Valor A:"), gbc);
        
        gbc.gridx = 1;
        add(entradaA, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Valor B:"), gbc);
        
        gbc.gridx = 1;
        add(entradaB, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Resultado:"), gbc);

        gbc.gridx = 1;
        add(resultField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(sumButton, gbc);

        gbc.gridx = 1;
        add(subtractButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(multiplyButton, gbc);

        gbc.gridx = 1;
        add(divideButton, gbc);

        // Adding the clear button across both columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(clearButton, gbc);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performOperation(String operation, JButton button) {
        try {
            button.setBackground(Color.ORANGE);
            
            // Convert commas to periods before parsing
            String num1Str = entradaA.getText().replace(',', '.');
            String num2Str = entradaB.getText().replace(',', '.');

            Number num1 = new BigDecimal(num1Str);
            Number num2 = new BigDecimal(num2Str);
            BigDecimal result;

            switch (operation) {
                case "sumar":
                    result = conversorMoneda.sumar(num1, num2);
                    break;
                case "restar":
                    result = conversorMoneda.restar(num1, num2);
                    break;
                case "multiplicar":
                    result = conversorMoneda.multiplicar(num1, num2);
                    break;
                case "dividir":
                    result = conversorMoneda.dividir(num1, num2);
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
        entradaA.setText("");
        entradaB.setText("");
        resultField.setText("");
        resetButtonColors(); // Reset button colors when fields are cleared
    }

    private void resetButtonColors() {
        // Reset all buttons to default color
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(null); // Default button background
            }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorGUI::new);
    }
}
