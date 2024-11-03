package com.oop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ResumeAppUI extends JFrame {
    // Personal detail fields
    public JTextField fullNameField, headlineField, emailField, phoneField, locationField;
    public JTextArea aboutMeField;

    // Panels for storing sections
    public JPanel skillsPanel, educationPanel, experiencePanel, languagesPanel, certificationsPanel, projectPanel;

    // Data lists to be accessed by PDFMaker
    public List<String> skillsList = new ArrayList<>();
    public List<String> languagesList = new ArrayList<>();
    public List<String> certificationsList = new ArrayList<>();
    public List<Education> educationList = new ArrayList<>();
    public List<Experience> experienceList = new ArrayList<>();
    public List<Project> projectList = new ArrayList<>();

    private PDFMaker pdfMaker;

    public ResumeAppUI() {
        initComponents();
    }


    private void initComponents() {
        setTitle("Resume Builder");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel - details entry
        JPanel leftContentPanel = new JPanel();
        leftContentPanel.setLayout(new BoxLayout(leftContentPanel, BoxLayout.Y_AXIS));
        leftContentPanel.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        addPersonalDetailFields(leftContentPanel);
        JScrollPane leftPanel = new JScrollPane(leftContentPanel);

        // Center part (PDF display part)
        JPanel middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(BorderFactory.createTitledBorder("PDF Preview"));
        JPanel pdfViewerPanel = new JPanel(new BorderLayout());
        middlePanel.add(pdfViewerPanel, BorderLayout.CENTER);

        // Download PDF button at the top of the center
        JButton downloadPDFButton = new JButton("Download PDF");
        downloadPDFButton.addActionListener(e -> {
            if (validateInputs()) {
                if (pdfMaker == null) {
                    pdfMaker = new PDFMaker(this, pdfViewerPanel);
                }
                pdfMaker.downloadPDF();
            }
        });
        middlePanel.add(downloadPDFButton, BorderLayout.NORTH);

        // Generate PDF button at the bottom of the center
        JButton generatePDFButton = new JButton("Generate PDF");
        generatePDFButton.addActionListener(e -> {
            if (validateInputs()) {
                if (pdfMaker == null) {
                    pdfMaker = new PDFMaker(this, pdfViewerPanel);
                }
                pdfMaker.generatePDF();
            }
        });
        middlePanel.add(generatePDFButton, BorderLayout.SOUTH);

        // Right panel - template and font
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        JPanel templatesPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        templatesPanel.setBorder(BorderFactory.createTitledBorder("Select Template"));

        // Template selection buttons
        JButton template1Button = new JButton("Template 1");
        template1Button.addActionListener(e -> {
            if (pdfMaker == null) {
                pdfMaker = new PDFMaker(this, pdfViewerPanel);
            }
            pdfMaker.setTemplate(1); // Set to Template 1
        });
        templatesPanel.add(template1Button);

        JButton template2Button = new JButton("Template 2");
        template2Button.addActionListener(e -> {
            if (pdfMaker == null) {
                pdfMaker = new PDFMaker(this, pdfViewerPanel);
            }
            pdfMaker.setTemplate(2); // Set to Template 2
        });
        templatesPanel.add(template2Button);

        // Add placeholders for more templates if needed
        JButton template3Button = new JButton("Template 3");
        template3Button.addActionListener(e -> {
            if (pdfMaker == null) {
                pdfMaker = new PDFMaker(this, pdfViewerPanel);
            }
            pdfMaker.setTemplate(1); // Default to Template 1 for other templates
        });
        templatesPanel.add(template3Button);

        // Add the template panel and font selection panel to the right panel
        JPanel fontSelectionPanel = new JPanel();
        fontSelectionPanel.setBorder(BorderFactory.createTitledBorder("Font Selection"));
        addFontOptions(fontSelectionPanel);

        rightPanel.add(templatesPanel, BorderLayout.CENTER);
        rightPanel.add(fontSelectionPanel, BorderLayout.SOUTH);

        // Add panels to the main layout
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    // Validation method
    private boolean validateInputs() {
        // Validate mobile number
        String mobileNumber = phoneField.getText().trim();
        if (mobileNumber.length() != 10 || !mobileNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit mobile number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate email
        String email = emailField.getText().trim();
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!Pattern.matches(emailPattern, email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void addPersonalDetailFields(JPanel leftPanel) {
        // Single-line fields
        fullNameField = new JTextField(20);
        headlineField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        locationField = new JTextField(20);

        aboutMeField = new JTextArea(5, 20);
        aboutMeField.setLineWrap(true);
        aboutMeField.setWrapStyleWord(true);

        leftPanel.add(new JLabel("Full Name:"));
        leftPanel.add(fullNameField);
        leftPanel.add(new JLabel("Headline:"));
        leftPanel.add(headlineField);
        leftPanel.add(new JLabel("Email ID:"));
        leftPanel.add(emailField);
        leftPanel.add(new JLabel("Phone No.:"));
        leftPanel.add(phoneField);
        leftPanel.add(new JLabel("Location:"));
        leftPanel.add(locationField);
        leftPanel.add(new JLabel("About Me:"));
        leftPanel.add(new JScrollPane(aboutMeField));

        skillsPanel = new JPanel();
        educationPanel = new JPanel();
        experiencePanel = new JPanel();
        languagesPanel = new JPanel();
        certificationsPanel = new JPanel();
        projectPanel = new JPanel();

        addInteractiveComponent(leftPanel, skillsPanel, "Skills");
        addInteractiveComponent(leftPanel, educationPanel, "Education");
        addInteractiveComponent(leftPanel, experiencePanel, "Experience");
        addInteractiveComponent(leftPanel, languagesPanel, "Languages");
        addInteractiveComponent(leftPanel, certificationsPanel, "Certifications");
        addInteractiveComponent(leftPanel, projectPanel, "Projects");
    }

    private void addInteractiveComponent(JPanel parent, JPanel panel, String title) {
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add " + title);
        addButton.addActionListener(e -> {
            switch (title) {
                case "Skills":
                    addSkillPopup(panel);
                    break;
                case "Education":
                    addEducationPopup(panel);
                    break;
                case "Experience":
                    addExperiencePopup(panel);
                    break;
                case "Languages":
                    addLanguagePopup(panel);
                    break;
                case "Certifications":
                    addCertificationPopup(panel);
                    break;
                case "Projects":
                    addProjectsPopup(panel);
                    break;
            }
        });

        panel.add(addButton);
        parent.add(panel);
    }

    private void addSkillPopup(JPanel panel) {
        String skill = JOptionPane.showInputDialog(this, "Enter Skill Name:");
        if (skill != null && !skill.isEmpty()) {
            skillsList.add(skill);
            addEntryToPanel(panel, skill);
        }
    }

    private void addProjectsPopup(JPanel panel) {
        JTextField projectField = new JTextField(10);
        JTextField linkField = new JTextField(10);
        Object[] message = {"Name:", projectField, "Link:", linkField};
        int option = JOptionPane.showConfirmDialog(this, message, "Add Project", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Project project = new Project(projectField.getText(), linkField.getText());
            projectList.add(project);
            addEntryToPanel(panel, project.toString());
        }
    }

    private void addEducationPopup(JPanel panel) {
        JTextField instituteField = new JTextField(10);
        JTextField degreeField = new JTextField(10);
        JTextField startYearField = new JTextField(5);
        JTextField endYearField = new JTextField(5);
        Object[] message = {"Institute:", instituteField, "Degree:", degreeField, "Start Year:", startYearField, "End Year:", endYearField};
        int option = JOptionPane.showConfirmDialog(this, message, "Add Education", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Education education = new Education(degreeField.getText(), instituteField.getText(), startYearField.getText(), endYearField.getText());
            educationList.add(education);
            addEntryToPanel(panel, education.toString());
        }
    }

    private void addExperiencePopup(JPanel panel) {
        JTextField orgField = new JTextField(10);
        JTextField roleField = new JTextField(10);
        JTextField startDateField = new JTextField(5);
        JTextField endDateField = new JTextField(5);
        Object[] message = {"Organization:", orgField, "Role:", roleField, "Start Date:", startDateField, "End Date:", endDateField};
        int option = JOptionPane.showConfirmDialog(this, message, "Add Experience", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Experience experience = new Experience(roleField.getText(), orgField.getText(), startDateField.getText(), endDateField.getText());
            experienceList.add(experience);
            addEntryToPanel(panel, experience.toString());
        }
    }

    private void addLanguagePopup(JPanel panel) {
        String language = JOptionPane.showInputDialog(this, "Enter Language:");
        if (language != null && !language.isEmpty()) {
            languagesList.add(language);
            addEntryToPanel(panel, language);
        }
    }

    private void addCertificationPopup(JPanel panel) {
        JTextField authorityField = new JTextField(10);
        JTextField topicField = new JTextField(10);
        JTextField dateField = new JTextField(5);
        JTextField linkField = new JTextField(10);
        Object[] message = {"Authority:", authorityField, "Topic:", topicField, "Date Received:", dateField, "Link:", linkField};
        int option = JOptionPane.showConfirmDialog(this, message, "Add Certification", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String certification = topicField.getText() + " - " + authorityField.getText();
            certificationsList.add(certification);
            addEntryToPanel(panel, certification);
        }
    }

    private void addEntryToPanel(JPanel panel, String entry) {
        JPanel entryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        entryPanel.add(new JLabel(entry));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String panelTitle = ((javax.swing.border.TitledBorder) panel.getBorder()).getTitle();
            switch (panelTitle) {
                case "Skills":
                    skillsList.remove(entry);
                    break;
                case "Languages":
                    languagesList.remove(entry);
                    break;
                case "Certifications":
                    certificationsList.remove(entry);
                    break;
                case "Education":
                    educationList.removeIf(education -> education.toString().equals(entry));
                    break;
                case "Experience":
                    experienceList.removeIf(experience -> experience.toString().equals(entry));
                    break;
                case "Projects":
                    projectList.removeIf(project -> project.toString().equals(entry));
                    break;
            }
            panel.remove(entryPanel);
            panel.revalidate();
            panel.repaint();
        });
        entryPanel.add(deleteButton);
        panel.add(entryPanel);
        panel.revalidate();
        panel.repaint();
    }

    private void addTemplateImages(JPanel templatesPanel) {
        for (int i = 1; i <= 6; i++) {
            JLabel template = new JLabel("Template " + i, SwingConstants.CENTER);
            template.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            templatesPanel.add(template);
        }
    }

    private void addFontOptions(JPanel fontSelectionPanel) {
        JLabel fontLabel = new JLabel("Font:");
        JComboBox<String> fontComboBox = new JComboBox<>(new String[]{"Arial", "Verdana", "Times New Roman"});
        fontSelectionPanel.add(fontLabel);
        fontSelectionPanel.add(fontComboBox);
    }

    // Getter methods for PDFMaker to access lists
    public List<String> getSkillsList() {
        return skillsList;
    }

    public List<String> getLanguagesList() {
        return languagesList;
    }

    public List<String> getCertificationsList() {
        return certificationsList;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResumeAppUI().setVisible(true));
    }
}