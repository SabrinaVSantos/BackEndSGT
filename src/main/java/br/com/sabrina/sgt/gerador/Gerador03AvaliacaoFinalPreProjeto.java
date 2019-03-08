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

import br.com.sabrina.sgt.gerador.dto.Dto03AvaliacaoFinalPreProjeto;

@Component
public class Gerador03AvaliacaoFinalPreProjeto {

	public File gera(Dto03AvaliacaoFinalPreProjeto dto) {
		try {
			File templateOriginal = new ClassPathResource("static/03_AVALIACAO_FINAL_PREPROJETO.docx").getFile();
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
							text = text.replace("#", dto.getAluno());
							break;
						case 1:
							text = text.replace("#", dto.getTitulo());
							break;
						case 2:
							text = text.replace("#", dto.getOrientador());
							break;
						case 3:
							text = text.replace("#", dto.getCoorientador());
							break;
						case 4:
							text = text.replace("#", dto.getMembroBanca1());
							break;
						case 5:
							text = text.replace("#", dto.getMembroBanca2());
							break;
						case 6:
							text = text.replace("#", dto.getObservacoesNota());
							break;
						case 7:
							text = text.replace("#", dto.getAprovado());
							break;
						case 8:
							text = text.replace("#", dto.getNaoAprovado());
							break;
						case 9:
							text = text.replace("#", dto.getObservacoesNaoAceito());
							break;
						case 10:
							text = text.replace("#", dto.getCoordenador());
							break;
						case 11:
							text = text.replace("#", dto.getMembroBanca1());
							break;
						case 12:
							text = text.replace("#", dto.getMembroBanca2());
							break;
						case 13:
							text = text.replace("#", dto.getDia());
							break;
						case 14:
							text = text.replace("#", dto.getMes());
							break;
						case 15:
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
										text = text.replace("#", dto.getNotaAvaliador1());
										break;
									case 1:
										text = text.replace("#", dto.getNotaAvaliador2());
										break;
									case 2:
										text = text.replace("#", dto.getNotaFinal());
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