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

import br.com.sabrina.sgt.gerador.dto.Dto15InscricaoTCC;

@Component
public class Gerador15InscricaoTCC {

	public File gera(Dto15InscricaoTCC dtoTCCFichaInscricao) {
		try {
			File templateOriginal = new ClassPathResource("static/15_INSCRICAO_TCC_DO_ALUNO.docx").getFile();
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
							text = text.replace("#", dtoTCCFichaInscricao.getAluno());
							break;
						case 1:
							text = text.replace("#", dtoTCCFichaInscricao.getCpf());
							break;
						case 2:
							text = text.replace("#", dtoTCCFichaInscricao.getRg());
							break;
						case 3:
							text = text.replace("#", dtoTCCFichaInscricao.getOrgaoExpedidor());
							break;
						case 4:
							text = text.replace("#", dtoTCCFichaInscricao.getEmail());
							break;
						case 5:
							text = text.replace("#", dtoTCCFichaInscricao.getTelefone());
							break;
						case 6:
							text = text.replace("#", dtoTCCFichaInscricao.getCelular());
							break;
						case 7:
							text = text.replace("#", dtoTCCFichaInscricao.getMatricula());
							break;
						case 8:
							text = text.replace("#", dtoTCCFichaInscricao.getPeriodo());
							break;
						case 9:
							text = text.replace("#", dtoTCCFichaInscricao.getNumeroDisciplinasCursadasAprovadas());
							break;
						case 10:
							text = text.replace("#", dtoTCCFichaInscricao.getTitulo());
							break;
						case 11:
							text = text.replace("#", dtoTCCFichaInscricao.getOrientador());
							break;
						case 12:
							text = text.replace("#", dtoTCCFichaInscricao.getCoorientador());
							break;
						case 13:
							text = text.replace("#", dtoTCCFichaInscricao.getAluno2());
							break;
						case 14:
							text = text.replace("#", dtoTCCFichaInscricao.getCpf2());
							break;
						case 15:
							text = text.replace("#", dtoTCCFichaInscricao.getRg2());
							break;
						case 16:
							text = text.replace("#", dtoTCCFichaInscricao.getOrgaoExpedidor2());
							break;
						case 17:
							text = text.replace("#", dtoTCCFichaInscricao.getEmail2());
							break;
						case 18:
							text = text.replace("#", dtoTCCFichaInscricao.getTelefone2());
							break;
						case 19:
							text = text.replace("#", dtoTCCFichaInscricao.getCelular2());
							break;
						case 20:
							text = text.replace("#", dtoTCCFichaInscricao.getMatricula2());
							break;
						case 21:
							text = text.replace("#", dtoTCCFichaInscricao.getPeriodo2());
							break;
						case 22:
							text = text.replace("#", dtoTCCFichaInscricao.getNumeroDisciplinasCursadasAprovadas2());
							break;
						case 23:
							text = text.replace("#", dtoTCCFichaInscricao.getAluno());
							break;
						case 24:
							text = text.replace("#", dtoTCCFichaInscricao.getOrientador());
							break;
						case 25:
							text = text.replace("#", dtoTCCFichaInscricao.getCoorientador());
							break;
						case 26:
							text = text.replace("#", dtoTCCFichaInscricao.getDiaAssinatura());
							break;
						case 27:
							text = text.replace("#", dtoTCCFichaInscricao.getMesAssinatura());
							break;
						case 28:
							text = text.replace("#", dtoTCCFichaInscricao.getAnoAssinatura());
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