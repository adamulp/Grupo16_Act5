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
        setLayout(new GridLayout(5, 2));

        // Input fields
        input1 = new JTextField();
        input2 = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        // Buttons
        JButton sumButton = new JButton("Sumar");
        JButton subtractButton = new JButton("Restar");
        JButton multiplyButton = new JButton("Multiplicar");
        JButton divideButton = new JButton("Dividir");

        // Add action listeners for buttons
        sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("sumar");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("restar");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("multiplicar");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("dividir");
            }
        });

        // Add components to the frame
        add(new JLabel("Número 1:"));
        add(input1);
        add(new JLabel("Número 2:"));
        add(input2);
        add(new JLabel("Resultado:"));
        add(resultField);
        add(sumButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);

        setSize(300, 200);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadorGUI());
    }
}

