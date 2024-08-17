Overview
The Health Monitoring System is a Java-based application designed to help patients, especially those in rural and suburban areas, keep track of their medical reports efficiently. The application provides an easy-to-use interface for managing patient records and their corresponding medical reports, reducing the need for frequent checkups by maintaining detailed and organized records.

Features
Home Page:

Displays a welcome message with the logo "Mercury HealthCare" in a large, bold, blue, italic font.
Provides options to navigate to the following sections:
Add Patient
Add Report
View Report
Add Patient Page:

Allows users to input patient details:
Patient ID
Patient Name
Age
Gender
Address
Submits the patient information and displays a confirmation message when the patient is added successfully.
Add Report Page:

Allows users to input and submit medical report details for a specific patient:
Patient ID (to specify which patient the report is for)
Visit Number
Admission Date
Doctor
Reason for Admission
Principal Diagnosis
Tests
Medication
Displays a confirmation message when the report is added successfully.
View Report Page:

Allows users to view all reports for a specific patient by entering either:
Patient ID
Patient Name
Displays all reports in a tabular format if the patient is found; otherwise, shows an error message.
Technologies Used
Java Swing/AWT: Used for creating the graphical user interface.
GridBagLayout: Utilized for precise placement and alignment of components.
