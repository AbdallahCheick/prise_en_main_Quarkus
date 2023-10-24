package org.acme;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class tesseract {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();

        try {
            tesseract.setDatapath("C:\\workspace\\eclipse\\2023-09\\tesseract\\Tess4J\\tessdata");

            // Le chemin de votre dossier tessdata
            // à l'intérieur du fichier extrait
            String text = tesseract.doOCR(new File("C:\\Users\\HP PAVILION 15\\Pictures\\Screenshots\\text.png"));

            // Le chemin de votre fichier image
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
