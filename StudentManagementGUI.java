import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class StudentManagementGUI extends JFrame
{
	
	    private StudentManagementSystem sms;
	    private JTable table;
	    private DefaultTableModel model;
	    private JTextField rollField, nameField, gradeField;

	    public StudentManagementGUI() {
	        sms = new StudentManagementSystem();

	        setTitle("Student Management System");
	        setSize(600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        // Input Panel
	        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
	        inputPanel.add(new JLabel("Roll Number:"));
	        rollField = new JTextField();
	        inputPanel.add(rollField);

	        inputPanel.add(new JLabel("Name:"));
	        nameField = new JTextField();
	        inputPanel.add(nameField);

	        inputPanel.add(new JLabel("Grade:"));
	        gradeField = new JTextField();
	        inputPanel.add(gradeField);

	        add(inputPanel, BorderLayout.NORTH);

	        // Table
	        model = new DefaultTableModel(new String[]{"Roll No", "Name", "Grade"}, 0);
	        table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);

	        // Buttons
	        JPanel buttonPanel = new JPanel();
	        JButton addBtn = new JButton("Add");
	        JButton editBtn = new JButton("Edit");
	        JButton deleteBtn = new JButton("Delete");
	        JButton searchBtn = new JButton("Search");
	        JButton displayBtn = new JButton("Display All");
	        JButton exitBtn = new JButton("Exit");

	        buttonPanel.add(addBtn);
	        buttonPanel.add(editBtn);
	        buttonPanel.add(deleteBtn);
	        buttonPanel.add(searchBtn);
	        buttonPanel.add(displayBtn);
	        buttonPanel.add(exitBtn);

	        add(buttonPanel, BorderLayout.SOUTH);

	        // Button Actions
	        addBtn.addActionListener(e -> addStudent());
	        editBtn.addActionListener(e -> editStudent());
	        deleteBtn.addActionListener(e -> deleteStudent());
	        searchBtn.addActionListener(e -> searchStudent());
	        displayBtn.addActionListener(e -> displayAllStudents());
	        exitBtn.addActionListener(e -> System.exit(0));

	        setVisible(true);
	    }

	    private void addStudent() {
	        try {
	            int rollNo = Integer.parseInt(rollField.getText().trim());
	            String name = nameField.getText().trim();
	            String grade = gradeField.getText().trim();

	            if (name.isEmpty() || grade.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
	                return;
	            }

	            sms.addStudent(new Student(rollNo, name, grade));
	            JOptionPane.showMessageDialog(this, "Student added successfully!");
	            clearFields();
	            displayAllStudents();
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Roll number must be an integer.");
	        }
	    }

	    private void editStudent() {
	        try {
	            int rollNo = Integer.parseInt(rollField.getText().trim());
	            Student s = sms.searchStudent(rollNo);

	            if (s != null) {
	                s.setName(nameField.getText().trim());
	                s.setGrade(gradeField.getText().trim());
	                JOptionPane.showMessageDialog(this, "Student updated successfully!");
	                displayAllStudents();
	            } else {
	                JOptionPane.showMessageDialog(this, "Student not found.");
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Enter a valid Roll Number.");
	        }
	    }

	    private void deleteStudent() {
	        try {
	            int rollNo = Integer.parseInt(rollField.getText().trim());
	            sms.removeStudent(rollNo);
	            JOptionPane.showMessageDialog(this, "Student removed.");
	            displayAllStudents();
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Enter a valid Roll Number.");
	        }
	    }

	    private void searchStudent() {
	        try {
	            int rollNo = Integer.parseInt(rollField.getText().trim());
	            Student s = sms.searchStudent(rollNo);

	            if (s != null) {
	                JOptionPane.showMessageDialog(this, s.toString());
	            } else {
	                JOptionPane.showMessageDialog(this, "Student not found.");
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Enter a valid Roll Number.");
	        }
	    }

	    private void displayAllStudents() {
	        model.setRowCount(0); // Clear table
	        for (Student s : sms.getAllStudents()) {
	            model.addRow(new Object[]{s.getRollNumber(), s.getName(), s.getGrade()});
	        }
	    }

	    private void clearFields() {
	        rollField.setText("");
	        nameField.setText("");
	        gradeField.setText("");
	    }

	    public static void main(String[] args) {
	        new StudentManagementGUI();
	    }
	
}
