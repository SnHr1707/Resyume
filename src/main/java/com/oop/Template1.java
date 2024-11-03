package com.oop;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;

public abstract class Template1 implements Template{
    private ResumeAppUI ui;

    public Template1(ResumeAppUI ui) {
        this.ui = ui;
    }
    //method to generate document
    public void Create(Document document) {
        try {
            addHeaderSection(document);
            addAboutMeSection(document);
            addSkillsSection(document);
            addExperienceSection(document);
            addEducationSection(document);
            addCertificationsSection(document);
            addLanguagesSection(document);
            addProjectSection(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //formatting methods to input data
    private void addHeaderSection(Document document) throws DocumentException {
        Font nameFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        document.add(new Paragraph(ui.fullNameField.getText(), nameFont));

        Font headlineFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
        document.add(new Paragraph(ui.headlineField.getText(), headlineFont));

        Font contactFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        document.add(new Paragraph("Email: " + ui.emailField.getText() + " | Phone: " + ui.phoneField.getText(), contactFont));
        document.add(new Paragraph("Location: " + ui.locationField.getText(), contactFont));

        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addAboutMeSection(Document document) throws DocumentException {
        document.add(new Paragraph("About Me", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        document.add(new Paragraph(ui.aboutMeField.getText()));
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addSkillsSection(Document document) throws DocumentException {
        if(ui.skillsList.isEmpty()) return;
        document.add(new Paragraph("Skills", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        for (String skill : ui.skillsList) {
            document.add(new Paragraph("• " + skill));
        }
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addExperienceSection(Document document) throws DocumentException {
        if(ui.experienceList.isEmpty()) return;
        document.add(new Paragraph("Experience", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        for (Experience experience : ui.experienceList) {
            document.add(new Paragraph(experience.toString()));
        }
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addEducationSection(Document document) throws DocumentException {
        if(ui.educationList.isEmpty()) return;
        document.add(new Paragraph("Education", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        for (Education education : ui.educationList) {
            document.add(new Paragraph(education.toString()));
        }
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addCertificationsSection(Document document) throws DocumentException {
        if(ui.certificationsList.isEmpty()) return;
        document.add(new Paragraph("Certifications", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        for (String certification : ui.certificationsList) {
            document.add(new Paragraph("• " + certification));
        }
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addLanguagesSection(Document document) throws DocumentException {
        if(ui.languagesList.isEmpty()) return;
        document.add(new Paragraph("Languages", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        for (String language : ui.languagesList) {
            document.add(new Paragraph("• " + language));
        }
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }

    private void addProjectSection(Document document) throws DocumentException {
        if(ui.projectList.isEmpty()) return;
        document.add(new Paragraph("Projects", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
        for (Project project : ui.projectList) {
            document.add(new Paragraph("• " + project.toString()));
        }
        document.add(new Paragraph("\n"));
        document.add(new LineSeparator());
    }
}
