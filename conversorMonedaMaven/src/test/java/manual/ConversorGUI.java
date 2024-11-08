package manual;

import conversor.ConversorMoneda;
import javax.swing.*;
import java.awt.*;

public class ConversorGUI extends JFrame {

    private ConversorMoneda conversorMoneda;
    private PasosPanel pasosPanel;
    private SaldosPanel saldoPanel;
    private CotizacionPanel cotizacionPanel;

    public ConversorGUI() {
        conversorMoneda = new ConversorMoneda();
        pasosPanel = new PasosPanel();
        saldoPanel = new SaldosPanel(conversorMoneda, pasosPanel);
        cotizacionPanel = new CotizacionPanel(conversorMoneda, pasosPanel);
        createUI();
    }

    private void createUI() {
        setTitle("Conversor de Moneda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(saldoPanel, gbc);

        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(cotizacionPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        add(pasosPanel, BorderLayout.SOUTH);

        JButton clearButton = new JButton("Limpiar Campos");
        clearButton.addActionListener(e -> clearFields());

        add(clearButton, BorderLayout.NORTH);

        setSize(1200, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void clearFields() {
        pasosPanel.clearPasos();
        cotizacionPanel.clearFields();
        saldoPanel.clearFields();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorGUI::new);
    }
}
