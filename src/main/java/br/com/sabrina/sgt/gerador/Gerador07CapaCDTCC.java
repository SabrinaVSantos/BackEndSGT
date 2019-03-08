package br.com.sabrina.sgt.gerador;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.sabrina.sgt.gerador.dto.Dto07CapaCDTCC;

@Component
public class Gerador07CapaCDTCC {

	public File gera(Dto07CapaCDTCC dto) {
		try {
			File templateOriginal = new ClassPathResource("static/07_CAPA_CD_TCC.docx").getFile();
			File template = new File("uploadarquivos/" + System.currentTimeMillis() + templateOriginal.getName());
			Files.copy(templateOriginal.toPath(), template.toPath());
			XWPFDocument document = new XWPFDocument(OPCPackage.open(template));
			
			int itensTabelaAlterados = 0;
			for (XWPFTable tbl : document.getTables()) {
				for (XWPFTableRow row : tbl.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								System.out.println(String.format("text=%s", text));
								if (text != null && text.contains("#")) {
									switch (itensTabelaAlterados) {
									case 0:
										text = text.replace("#", dto.getTitulo());
										break;
									case 1:
										text = text.replace("#", dto.getAluno());
										break;
									case 2:
										text = text.replace("#", dto.getOrientador());
										break;
									case 3:
										text = text.replace("#", dto.getCoorientador().equals(" ") ? " ": "Co-Orientador: ");
										break;
									case 4:
										text = text.replace("#", dto.getCoorientador());
										break;
									case 5:
										text = text.replace("#", dto.getAno());
										break;
									default:
										break;
									}
									System.out.println(String.format("text1=%s", text));
									r.setText(text, 0);
									itensTabelaAlterados++;
								}
							}
						}
					}
				}
			}

			File saida = new File("uploadarquivos/" + System.currentTimeMillis() + templateOriginal.getName());
			document.write(new FileOutputStream(saida));
			document.close();
			File pdf = ConversorPDF.convert(saida);
			template.delete();
			saida.delete();
			return pdf;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}