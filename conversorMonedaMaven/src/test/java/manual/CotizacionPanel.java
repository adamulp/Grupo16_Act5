package manual;

import conversor.Conversor;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class CotizacionPanel extends JPanel {

    private JTextField cotizarARSField;
    private JTextField cotizarUSDField;
    private JTextField cotizacionARSUSDField;
    private JTextField cotizacionUSDARSField;
    private JTextField montoARS;
    private JTextField montoUSD;
    private Conversor conversorMoneda;
    private PasosPanel pasosPanel;

    public CotizacionPanel(Conversor conversorMoneda, 
            PasosPanel pasosPanel) {
        this.conversorMoneda = conversorMoneda;
        this.pasosPanel = pasosPanel;
        createUI();
    }

    public void clearFields() {
        cotizarARSField.setText("");
        cotizarUSDField.setText("");
        cotizacionARSUSDField.setText("");
        cotizacionUSDARSField.setText("");
        montoARS.setText("");
        montoUSD.setText("");

        resetButtonColors();
    }

    private void createUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Cotizar",
                SwingConstants.CENTER);
        
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JSeparator separator = new JSeparator();

        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(separator, BorderLayout.SOUTH);


        cotizarARSField = new JTextField(10);
        cotizarUSDField = new JTextField(10);
        cotizacionARSUSDField = new JTextField(10);
        cotizacionUSDARSField = new JTextField(10);
        montoARS = new JTextField(10);
        montoUSD = new JTextField(10);

        cotizacionARSUSDField.setEditable(false);
        cotizacionUSDARSField.setEditable(false);

        montoARS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                montoARS.selectAll();
            }
        });

        montoUSD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                montoUSD.selectAll();
            }
        });

        JButton cotizarButton = new JButton("Cotizar");
        cotizarButton.addActionListener(e -> {
            performCotizacion();
            cotizarButton.setBackground(Color.ORANGE);
        });

        JButton convertirUSDButton = new JButton("Convertir a USD");
        JButton convertirARSButton = new JButton("Convertir a ARS");

        convertirUSDButton.addActionListener(e -> {
            performConversion("USD");
            convertirUSDButton.setBackground(Color.ORANGE);
        });

        convertirARSButton.addActionListener(e -> {
            performConversion("ARS");
            convertirARSButton.setBackground(Color.ORANGE);
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("               ARS:"), gbc);
        gbc.gridx = 1;
        add(cotizarARSField, gbc);
        gbc.gridx = 2;
        add(cotizarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("               USD:"), gbc);
        gbc.gridx = 1;
        add(cotizarUSDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("               ARS/USD:"), gbc);
        gbc.gridx = 1;
        add(cotizacionARSUSDField, gbc);

        gbc.gridx = 2;
        add(new JLabel("                       USD/ARS:"), gbc);
        gbc.gridx = 3;
        add(cotizacionUSDARSField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Monto ARS:"), gbc);
        gbc.gridx = 1;
        add(montoARS, gbc);
        gbc.gridx = 2;
        add(convertirUSDButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Monto USD:"), gbc);
        gbc.gridx = 1;
        add(montoUSD, gbc);
        gbc.gridx = 2;
        add(convertirARSButton, gbc);
    }

    private void performCotizacion() {
        try {
            String cotizarARSStr = cotizarARSField.getText().
                    replace(',', '.');
            
            String cotizarUSDStr = cotizarUSDField.getText().
                    replace(',', '.');

            BigDecimal cotizarARS = new BigDecimal(cotizarARSStr);
            BigDecimal cotizarUSD = new BigDecimal(cotizarUSDStr);

            // Use ConversorMoneda methods
            BigDecimal cotizacionUSDARS = conversorMoneda.cotizar(
                    cotizarARS, cotizarUSD);
            
            BigDecimal cotizacionARSUSD = conversorMoneda.cotizar(
                    cotizarUSD, cotizarARS);
            
            cotizacionARSUSDField.setText(cotizacionARSUSD.toString());
            cotizacionUSDARSField.setText(cotizacionUSDARS.toString());

            pasosPanel.addPaso("Cotizar: "
                    + cotizarARSStr
                    + " ARS con "
                    + cotizarUSDStr
                    + " USD");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "Por favor, ingrese números válidos para cotización.", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performConversion(String currency) {
        try {
            BigDecimal result;

            if (currency.equals("USD")) {
                BigDecimal cotizacion = new BigDecimal(
                        cotizacionARSUSDField.getText());
                
                BigDecimal amountToConvert = new BigDecimal(
                        montoARS.getText());
                
                result = conversorMoneda.convertir(
                        amountToConvert, cotizacion);
                
                montoUSD.setText(result.toString());
                pasosPanel.addPaso("Convertir ARS a USD: "
                        + amountToConvert
                        + " * " + cotizacion);
                
            } else if (currency.equals("ARS")) {
                BigDecimal cotizacion = new BigDecimal(
                        cotizacionUSDARSField.getText());
                
                BigDecimal amountToConvert = new BigDecimal(
                        montoUSD.getText());
                
                result = conversorMoneda.convertir(
                        amountToConvert, cotizacion);
                
                montoARS.setText(result.toString());
                pasosPanel.addPaso("Convertir USD a ARS: "
                        + amountToConvert + " * " + cotizacion);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "Por favor, "
                            + "ingrese números válidos para la conversión.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(
                    this, "Error: "
                            + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: "
                            + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetButtonColors() {
        for (Component component : getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(null);
            }
        }
    }
}
