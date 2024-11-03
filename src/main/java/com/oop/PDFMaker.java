package com.oop;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;

import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class PDFMaker {
    private ResumeAppUI ui;
    private PagePanel pdfPanel;
    private Template selectedTemplate;

    public PDFMaker(ResumeAppUI ui, JPanel pdfContainer) {
        this.ui = ui;
        this.pdfPanel = new PagePanel();
        pdfContainer.setLayout(new BorderLayout());
        pdfContainer.add(pdfPanel, BorderLayout.CENTER);
        //default select template 1
        this.selectedTemplate = createTemplate(1);
    }

    //template selector
    private Template createTemplate(int choice) {
        if (choice == 1) {
            return new temp1call(ui);
        } else if (choice == 2) {
            return new temp2call(ui);
        }
        return new temp1call(ui);
    }

    // Method to set the desired template (1 for Template1, 2 for Template2)
    public void setTemplate(int choice) {
        this.selectedTemplate = createTemplate(choice);
    }

    public void generatePDF() {
        try {
            Document document = new Document();
            File pdfFile = new File("resume.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // Use the selected template to create the PDF content
            selectedTemplate.Create(document);

            document.close();
            displayPDF(pdfFile); // Display PDF after generating
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayPDF(File pdfFile) {
        try {
            // Check if the PDF file exists
            if (!pdfFile.exists()) {
                System.out.println("PDF file does not exist: " + pdfFile.getAbsolutePath());
                return;
            }

            ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(pdfFile.toPath()));
            PDFFile pdf = new PDFFile(buffer);
            PDFPage page = pdf.getPage(0);
            pdfPanel.showPage(page);
            pdfPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadPDF() {
        try {
            Document document = new Document();
            File pdfFile = new File("resume.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            //create pdf
            selectedTemplate.Create(document);
            document.close();
            System.out.println("PDF downloaded to " + pdfFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//template calling classes
class temp1call extends Template1 {
    public temp1call(ResumeAppUI ui) {
        super(ui);
    }

    public void Create(Document document) {
        super.Create(document);
    }
}

// Concrete implementation for Template2
class temp2call extends Template2 {
    public temp2call(ResumeAppUI ui) {
        super(ui);
    }

    public void Create(Document document) {
        super.Create(document);
    }
}