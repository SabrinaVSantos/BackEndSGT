package br.com.sabrina.sgt.gerador;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.sabrina.sgt.gerador.dto.Dto11DeclaracaoOrientadorCorrecaoTCC;

@Component
public class Gerador11DeclaracaoOrientadorCorrecaoTCC {

	public File gera(Dto11DeclaracaoOrientadorCorrecaoTCC dto) {
		try {
			File templateOriginal = new ClassPathResource("static/11_DECLARACAO_ORIENTADOR_CORRECAO_TCC.docx").getFile();
			File template = new File("uploadarquivos/" + System.currentTimeMillis() + templateOriginal.getName());
			Files.copy(templateOriginal.toPath(), template.toPath());
			XWPFDocument document = new XWPFDocument(OPCPackage.open(template));
			int itensAlterados = 0;
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun r : paragraph.getRuns()) {
					String text = r.getText(0);
					System.out.println(String.format("text=%s", text));
					if (text != null && text.contains("#")) {
						switch (itensAlterados) {
						case 0:
							text = text.replace("#", dto.getOrientador());
							break;
						case 1:
							text = text.replace("#", dto.getMatriculaSiape());
							break;
						case 2:
							text = text.replace("#", dto.getAluno());
							break;
						case 3:
							text = text.replace("#", dto.getMatriculaAluno());
							break;
						case 4:
							text = text.replace("#", dto.getTitulo());
							break;
						case 5:
							text = text.replace("#", dto.getOrientador());
							break;
						case 6:
							text = text.replace("#", dto.getDia());
							break;
						case 7:
							text = text.replace("#", dto.getMes());
							break;
						case 8:
							text = text.replace("#", dto.getAno());
							break;
						default:
							break;
						}
						System.out.println(String.format("text1=%s", text));
						r.setText(text, 0);
						itensAlterados++;
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