package br.com.sabrina.sgt.gerador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ConversorPDF {

    public static File convert(File docFile) {
        try {
            InputStream doc = new FileInputStream(docFile);
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            File pdf = new File(docFile.getPath().replaceAll("docx", "pdf"));
            OutputStream out = new FileOutputStream(pdf);
            PdfConverter.getInstance().convert(document, out, options);
            return pdf;
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Erro ao gerar pdf do arquivo %s", docFile.getName()));
        }
    }
}