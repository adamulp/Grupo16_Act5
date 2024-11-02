package conversor;

import javax.swing.*;
import java.awt.*;

public class ConversorGUI extends JFrame {

    private ConversorMoneda conversorMoneda;
    private PasosPanel pasosPanel; // Declare the PasosPanel

    public ConversorGUI() {
        conversorMoneda = new ConversorMoneda();
        pasosPanel = new PasosPanel(); // Initialize the PasosPanel
        createUI();
    }

    private void createUI() {
        setTitle("Conversor de Moneda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the panels and pass pasosPanel to SaldosPanel
        JPanel saldoPanel = new SaldosPanel(conversorMoneda, pasosPanel);
        JPanel cotizacionPanel = new CotizacionPanel(conversorMoneda, pasosPanel);

        // Create a main panel to hold the saldo and cotizacion panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add SaldosPanel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(saldoPanel, gbc);

        // Add CotizacionPanel
        gbc.gridx = 1;
        mainPanel.add(cotizacionPanel, gbc);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        // Add the PasosPanel at the bottom of the frame
        add(pasosPanel, BorderLayout.SOUTH);

        setSize(1080, 450); // Adjusted size for better layout
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorGUI::new);
    }
}
