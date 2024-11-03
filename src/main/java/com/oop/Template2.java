package com.oop;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;

public abstract class Template2 implements Template {
    private ResumeAppUI ui;

    public Template2(ResumeAppUI ui) {
        this.ui = ui;
    }

    public void Create(Document document) {
        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            //left side cell adding
            PdfPCell leftCell = new PdfPCell();
            leftCell.addElement(leftSideDetails());
            leftCell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(leftCell);

            //right side cell adding
            PdfPCell rightCell = new PdfPCell();
            rightCell.addElement(rightSideDetails());
            rightCell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(rightCell);

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //left side cell details adding
    private Paragraph leftSideDetails() {
        Paragraph personalDetails = new Paragraph();
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        //adding of details one by one
        personalDetails.add(new Paragraph(ui.fullNameField.getText(), boldFont));
        personalDetails.add(new Paragraph("Phone: " + ui.phoneField.getText(),smallFont));
        personalDetails.add(new Paragraph("Email: " + ui.emailField.getText(),smallFont));
        personalDetails.add(new Paragraph("Location: " + ui.locationField.getText(),smallFont));

        personalDetails.add(new Paragraph("\n"));
        personalDetails.add(new LineSeparator());
        personalDetails.add(new Paragraph("Skills", boldFont));
        if(ui.skillsList.isEmpty()) return personalDetails;
        for (String skill : ui.skillsList) {
            personalDetails.add(new Paragraph("• " + skill));
        }
        return personalDetails;
    }

    //right side cell details adding
    private Paragraph rightSideDetails() throws DocumentException {
        Paragraph mainContent = new Paragraph();
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

        mainContent.add(new Paragraph("About Me", boldFont));
        mainContent.add(new Paragraph(ui.aboutMeField.getText()));
        mainContent.add(new Paragraph("\n"));
        mainContent.add(new LineSeparator());

        if(!ui.educationList.isEmpty()){
            mainContent.add(new Paragraph("Education", boldFont));
            for (Education education : ui.educationList) {
                mainContent.add(new Paragraph(education.toString()));
            }
            mainContent.add(new Paragraph("\n"));
            mainContent.add(new LineSeparator());
        }
        
        if(!ui.experienceList.isEmpty()){
            mainContent.add(new Paragraph("Experience", boldFont));
            for (Experience experience : ui.experienceList) {
                mainContent.add(new Paragraph(experience.toString()));
            }
            mainContent.add(new Paragraph("\n"));
            mainContent.add(new LineSeparator());
        }

        if(!ui.languagesList.isEmpty()){
            mainContent.add(new Paragraph("Languages", boldFont));
            for (String language : ui.languagesList) {
                mainContent.add(new Paragraph("• " + language));
            }
            mainContent.add(new Paragraph("\n"));
            mainContent.add(new LineSeparator());
        }
        
        if(!ui.certificationsList.isEmpty()){
            mainContent.add(new Paragraph("Certifications", boldFont));
            for (String certification : ui.certificationsList) {
                mainContent.add(new Paragraph("• " + certification));
            }  
            mainContent.add(new Paragraph("\n"));
            mainContent.add(new LineSeparator());
        }
        
        if(!ui.projectList.isEmpty()){
            mainContent.add(new Paragraph("Projects", boldFont));
            for (Project project : ui.projectList) {
                mainContent.add(new Paragraph("• " + project));
            }
            mainContent.add(new Paragraph("\n"));
        }
        return mainContent;
    }
}
