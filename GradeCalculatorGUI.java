import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculatorGUI extends JFrame implements ActionListener {

    private JTextField subjectCountField;
    private JPanel marksPanel;
    private JButton submitButton, calculateButton;
    private JTextArea resultArea;
    private JTextField[] marksFields;

    public GradeCalculatorGUI() {
        setTitle("Student Grade Calculator");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        JLabel label = new JLabel("Enter number of subjects: ");
        label.setFont(font);
        inputPanel.add(label);

        subjectCountField = new JTextField(5);
        subjectCountField.setFont(font);
        inputPanel.add(subjectCountField);

        submitButton = new JButton("Submit");
        submitButton.setFont(font);
        submitButton.addActionListener(this);
        inputPanel.add(submitButton);

        marksPanel = new JPanel();
        marksPanel.setLayout(new GridLayout(0, 2, 10, 10));
        marksPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Enter Subject Marks",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            font
        ));

        // Result Area
        resultArea = new JTextArea(6, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("📋 Result"));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Calculate Button
        calculateButton = new JButton("Calculate Grade");
        calculateButton.setFont(font);
        calculateButton.addActionListener(this);

        // Layout setup
        add(inputPanel, BorderLayout.NORTH);
        add(marksPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(calculateButton, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Clear previous marksPanel
            marksPanel.removeAll();
            resultArea.setText("");

            int count;
            try {
                count = Integer.parseInt(subjectCountField.getText());
                if (count <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of subjects.");
                return;
            }

            marksFields = new JTextField[count];
            for (int i = 0; i < count; i++) {
                JLabel label = new JLabel("Subject " + (i + 1) + " Marks:");
                label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                JTextField field = new JTextField(5);
                field.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                marksPanel.add(label);
                marksPanel.add(field);
                marksFields[i] = field;
            }

            marksPanel.revalidate();
            marksPanel.repaint();
        }

        if (e.getSource() == calculateButton) {
            if (marksFields == null || marksFields.length == 0) {
                JOptionPane.showMessageDialog(this, "Please submit the number of subjects first.");
                return;
            }

            int total = 0;
            int count = marksFields.length;

            for (int i = 0; i < count; i++) {
                try {
                    int mark = Integer.parseInt(marksFields[i].getText());
                    if (mark < 0 || mark > 100) throw new NumberFormatException();
                    total += mark;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "❌ Invalid marks in Subject " + (i + 1));
                    return;
                }
            }

            double average = (double) total / count;
            char grade;

            if (average >= 90) grade = 'A';
            else if (average >= 80) grade = 'B';
            else if (average >= 70) grade = 'C';
            else if (average >= 60) grade = 'D';
            else if (average >= 50) grade = 'E';
            else grade = 'F';

            resultArea.setText(
                "Total Marks       : " + total +
                "\n Average Percentage: " + String.format("%.2f", average) + "%" +
                "\n Grade             : " + grade
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeCalculatorGUI());
    }
}
