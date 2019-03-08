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

import br.com.sabrina.sgt.entidade.RelatorioOrientacao;
import br.com.sabrina.sgt.gerador.dto.Dto20RegistroEncontroOrientacaoTCC;

@Component
public class Gerador20RegistroEncontroOrientacaoTCC {

	public File gera(Dto20RegistroEncontroOrientacaoTCC dto) {
		try {
			File templateOriginal = new ClassPathResource("static/20_REGISTRO_ENCONTRO_ORIENTACAO_TCC.docx").getFile();
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
			int qtdOrientacoes = dto.getOrientacoes().size();
			for (XWPFTable tbl : document.getTables()) {
				for (XWPFTableRow row : tbl.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							RelatorioOrientacao orientacaoAtual = null;
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								System.out.println(String.format("text=%s", text));
								if (text != null && text.contains("#")) {
									String data = " ";
									String orientacao = " ";
									if(itensTabelaAlterados < qtdOrientacoes * 2) {
										orientacaoAtual = dto.getOrientacoes().get(itensTabelaAlterados/2);
										data = orientacaoAtual.getDataFormatada();
										orientacao = orientacaoAtual.getOrientacao();
									}
									
									if (itensTabelaAlterados %2 == 0) {
										text = text.replace("#", data);
									} else {
										text = text.replace("#", orientacao);
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