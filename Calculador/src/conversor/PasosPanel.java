package conversor;

import javax.swing.*;
import java.awt.*;

public class PasosPanel extends JPanel {

    private JTextArea pasosTextArea;

    public PasosPanel() {
        createUI();
    }

    private void createUI() {
        setLayout(new BorderLayout());

        // Initialize the JTextArea for recording steps
        pasosTextArea = new JTextArea();
        pasosTextArea.setEditable(false);
        pasosTextArea.setLineWrap(true);
        pasosTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(pasosTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set preferred size

        // Add the JScrollPane to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add a step to the JTextArea
    public void addPaso(String paso) {
        String currentText = pasosTextArea.getText();
        int stepNumber = currentText.isEmpty() ? 1 : currentText.split("\n").length + 1;
        String newStep = stepNumber + ". " + paso;

        pasosTextArea.setText(currentText.isEmpty() ? newStep : currentText + "\n" + newStep);
    }

    // Method to clear the JTextArea
    public void clearPasos() {
        pasosTextArea.setText("");
    }
}
