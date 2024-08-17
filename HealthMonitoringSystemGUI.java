import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Patient {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private List<Report> reports;

    public Patient(String id, String name, int age, String gender, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.reports = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void addReport(Report report) {
        reports.add(report);
    }
}

class Report {
    private String visitNumber;
    private String admissionDate;
    private String doctor;
    private String reasonForAdmission;
    private String principalDiagnosis;
    private String tests;
    private String medication;

    public Report(String visitNumber, String admissionDate, String doctor, String reasonForAdmission,
                  String principalDiagnosis, String tests, String medication) {
        this.visitNumber = visitNumber;
        this.admissionDate = admissionDate;
        this.doctor = doctor;
        this.reasonForAdmission = reasonForAdmission;
        this.principalDiagnosis = principalDiagnosis;
        this.tests = tests;
        this.medication = medication;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-15s %-20s %-20s %-20s %-20s",
                visitNumber, admissionDate, doctor, reasonForAdmission, principalDiagnosis, tests, medication);
    }
}

public class HealthMonitoringSystemGUI extends JFrame {
    private HashMap<String, Patient> patients = new HashMap<>();

    public HealthMonitoringSystemGUI() {
        setTitle("Mercury HealthCare System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel homePanel = createHomePanel();
        JPanel addPatientPanel = createAddPatientPanel();
        JPanel addReportPanel = createAddReportPanel();
        JPanel viewReportPanel = createViewReportPanel();

        add(homePanel, "Home");
        add(addPatientPanel, "AddPatient");
        add(addReportPanel, "AddReport");
        add(viewReportPanel, "ViewReport");

        showPanel("Home");
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Mercury HealthCare", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
        titleLabel.setForeground(Color.BLUE);
        panel.add(titleLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton addPatientButton = new JButton("Add Patient");
        JButton addReportButton = new JButton("Add Report");
        JButton viewReportButton = new JButton("View Report");

        addPatientButton.addActionListener(e -> showPanel("AddPatient"));
        addReportButton.addActionListener(e -> showPanel("AddReport"));
        viewReportButton.addActionListener(e -> showPanel("ViewReport"));

        buttonPanel.add(addPatientButton);
        buttonPanel.add(addReportButton);
        buttonPanel.add(viewReportButton);

        // Centering the buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(buttonPanel);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddPatientPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel idLabel = new JLabel("Patient ID:");
        JTextField idField = new JTextField(20);

        JLabel nameLabel = new JLabel("Patient Name:");
        JTextField nameField = new JTextField(20);

        JLabel ageLabel = new JLabel("Patient Age:");
        JTextField ageField = new JTextField(20);

        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField(20);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(20);

        JButton submitButton = new JButton("Add Patient");
        JLabel resultLabel = new JLabel();

        // Back to Home Button
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> showPanel("Home"));

        submitButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String address = addressField.getText();

            if (patients.containsKey(id)) {
                resultLabel.setText("Patient ID already exists.");
            } else {
                Patient patient = new Patient(id, name, age, gender, address);
                patients.put(id, patient);
                resultLabel.setText("Patient added successfully.");
            }
        });

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(idLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(ageLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(genderLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(genderField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(addressLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(submitButton, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(resultLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        panel.add(backButton, gbc);

        return panel;
    }

    private JPanel createAddReportPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel idLabel = new JLabel("Patient ID:");
        JTextField idField = new JTextField(20);
    
        JLabel visitNumberLabel = new JLabel("Visit Number:");
        JTextField visitNumberField = new JTextField(20);
    
        JLabel admissionDateLabel = new JLabel("Admission Date (dd/mm/yyyy):");
        JTextField admissionDateField = new JTextField(20);
    
        JLabel doctorLabel = new JLabel("Doctor:");
        JTextField doctorField = new JTextField(20);
    
        JLabel reasonLabel = new JLabel("Reason for Admission:");
        JTextField reasonField = new JTextField(20);
    
        JLabel diagnosisLabel = new JLabel("Principal Diagnosis:");
        JTextField diagnosisField = new JTextField(20);
    
        JLabel testsLabel = new JLabel("Tests:");
        JTextField testsField = new JTextField(20);
    
        JLabel medicationLabel = new JLabel("Medication:");
        JTextField medicationField = new JTextField(20);
    
        JButton submitButton = new JButton("Add Report");
        JLabel resultLabel = new JLabel();
    
        // Back to Home Button
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> showPanel("Home"));
    
        submitButton.addActionListener(e -> {
            String patientId = idField.getText();  // Get Patient ID from the input field
            if (patients.containsKey(patientId)) {
                Patient patient = patients.get(patientId);
                String visitNumber = visitNumberField.getText();
                String admissionDate = admissionDateField.getText();
                String doctor = doctorField.getText();
                String reason = reasonField.getText();
                String diagnosis = diagnosisField.getText();
                String tests = testsField.getText();
                String medication = medicationField.getText();
    
                Report report = new Report(visitNumber, admissionDate, doctor, reason, diagnosis, tests, medication);
                patient.addReport(report);
                resultLabel.setText("Report added successfully.");
            } else {
                resultLabel.setText("Patient ID not found.");
            }
        });
    
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(idLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(idField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(visitNumberLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(visitNumberField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(admissionDateLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(admissionDateField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(doctorLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(doctorField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(reasonLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(reasonField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(diagnosisLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(diagnosisField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(testsLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        panel.add(testsField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(medicationLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 7;
        panel.add(medicationField, gbc);
    
        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(submitButton, gbc);
        gbc.gridx = 1; gbc.gridy = 8;
        panel.add(resultLabel, gbc);
    
        gbc.gridx = 1; gbc.gridy = 9;
        panel.add(backButton, gbc);
    
        return panel;
    }
    

    private JPanel createViewReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Patient ID:");
        JTextField idField = new JTextField(20);

        JLabel nameLabel = new JLabel("Patient Name:");
        JTextField nameField = new JTextField(20);

        JButton viewButton = new JButton("View Reports");
        JTable reportTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(reportTable);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(idLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        inputPanel.add(nameField, gbc);

        // Back to Home Button
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> showPanel("Home"));

        inputPanel.add(backButton, gbc); // Adding the back button to the input panel

        viewButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            Patient patient = null;

            if (!id.isEmpty()) {
                patient = patients.get(id);
            } else if (!name.isEmpty()) {
                for (Patient p : patients.values()) {
                    if (p.getName().equalsIgnoreCase(name)) {
                        patient = p;
                        break;
                    }
                }
            }

            if (patient != null) {
                String[] columnNames = {"Visit No", "Admission Date", "Doctor", "Reason", "Diagnosis", "Tests", "Medication"};
                Object[][] data = new Object[patient.getReports().size()][7];
                int i = 0;
                for (Report report : patient.getReports()) {
                    data[i][0] = report.toString();
                    i++;
                }
                reportTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found.");
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(viewButton, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HealthMonitoringSystemGUI gui = new HealthMonitoringSystemGUI();
            gui.setVisible(true);
        });
    }
}
