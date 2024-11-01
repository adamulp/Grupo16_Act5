package calculador;

/**
 *
 * @author Grupo16
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CalculadorGUI extends JFrame {
    private JTextField input1;
    private JTextField input2;
    private JTextField resultField;
    private Calculador calculador;

    public CalculadorGUI() {
        calculador = new Calculador();
        createUI();
    }

    private void createUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        input1 = new JTextField(10);
        input2 = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        JButton sumButton = new JButton("Sumar");
        JButton subtractButton = new JButton("Restar");
        JButton multiplyButton = new JButton("Multiplicar");
        JButton divideButton = new JButton("Dividir");
        JButton clearButton = new JButton("Limpiar Campos");

        sumButton.addActionListener(e -> performOperation("sumar"));
        subtractButton.addActionListener(e -> performOperation("restar"));
        multiplyButton.addActionListener(e -> performOperation("multiplicar"));
        divideButton.addActionListener(e -> performOperation("dividir"));

        clearButton.addActionListener(e -> clearFields());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Valor A:"), gbc);
        
        gbc.gridx = 1;
        add(input1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Valor B:"), gbc);
        
        gbc.gridx = 1;
        add(input2, gbc);
        
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

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(clearButton, gbc);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performOperation(String operation) {
        try {
            Number num1 = new BigDecimal(input1.getText());
            Number num2 = new BigDecimal(input2.getText());
            BigDecimal result;

            switch (operation) {
                case "sumar":
                    result = calculador.sumar(num1, num2);
                    break;
                case "restar":
                    result = calculador.restar(num1, num2);
                    break;
                case "multiplicar":
                    result = calculador.multiplicar(num1, num2);
                    break;
                case "dividir":
                    result = calculador.dividir(num1, num2);
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
        input1.setText("");
        input2.setText("");
        resultField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculadorGUI::new);
    }
}
