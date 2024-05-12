/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class pdftodocx {
    public static void convertPdfToDocx(File inputPdfFile, File outputDocxFile) throws IOException {
        // Load the PDF document
        PDDocument document = PDDocument.load(new FileInputStream(inputPdfFile));

        // Create a new Word document
        XWPFDocument wordDocument = new XWPFDocument();

        // Extract text from the PDF document
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        // Split the text into lines
        String[] lines = text.split("\\r?\\n");

        // Add each line as a new paragraph in the Word document
        for (String line : lines) {
            XWPFParagraph paragraph = wordDocument.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(line);
        }

        // Save the Word document
        FileOutputStream out = new FileOutputStream(outputDocxFile);
        wordDocument.write(out);
        out.close();

        // Close the PDF document
        document.close();
    }
}
