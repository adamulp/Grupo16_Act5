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

        // Add the JScrollPane to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to add a step to the JTextArea
    public void addPaso(String paso) {
        String currentText = pasosTextArea.getText();
        String newStep = (currentText.isEmpty() ? "" : "\n") + (currentText.split("\n").length + 1) + ". " + paso;
        pasosTextArea.setText(newStep);
    }

    // Method to clear the JTextArea
    public void clearPasos() {
        pasosTextArea.setText("");
    }
}
