package br.com.sabrina.sgt.rest.gerador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.entidade.PreProjeto;
import br.com.sabrina.sgt.entidade.Professor;
import br.com.sabrina.sgt.gerador.Gerador03AvaliacaoFinalPreProjeto;
import br.com.sabrina.sgt.gerador.dto.Dto03AvaliacaoFinalPreProjeto;
import br.com.sabrina.sgt.service.PreProjetosService;
import br.com.sabrina.sgt.service.ProfessoresService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/avaliacaofinalpreprojeto")
public class Gerador03AvaliacaoFinalPreProjetoController {

	@Autowired
	protected PreProjetosService preProjetosService;

	@Autowired
	protected ProfessoresService professoresService;
	
	@Autowired
	private Gerador03AvaliacaoFinalPreProjeto gerador;
	
	@PostMapping(path = "/{idPreProjeto}")
	public @ResponseBody byte[] gerar(@PathVariable("idPreProjeto") Long idPreProjeto, HttpServletResponse response) {
		try {
			PreProjeto preProjeto = preProjetosService.recuperarPorId(idPreProjeto);
			Dto03AvaliacaoFinalPreProjeto dto = new Dto03AvaliacaoFinalPreProjeto();
			String nomes = preProjeto.getAluno1().getNome();
			if(preProjeto.getAluno2() != null) {
				nomes += " e " + preProjeto.getAluno2().getNome();
			}
			dto.setAluno(nomes);
			dto.setTitulo(preProjeto.getTema());
			dto.setOrientador(preProjeto.getOrientador().getNome());
			dto.setCoorientador(preProjeto.getCoOrientador() == null ? " ": preProjeto.getCoOrientador().getNome());
			dto.setMembroBanca1(preProjeto.getAvaliador1() == null ? " ": preProjeto.getAvaliador1().getNome());
			dto.setMembroBanca2(preProjeto.getAvaliador2() == null ? " ": preProjeto.getAvaliador2().getNome());
			dto.setNotaAvaliador1(preProjeto.getNota1() == null ? " ": preProjeto.getNota1().toString());
			dto.setNotaAvaliador2(preProjeto.getNota2() == null ? " ": preProjeto.getNota2().toString());
			dto.setNotaFinal(preProjeto.getNotaFinal() == null ? " ": preProjeto.getNotaFinal().toString());
			String observacoesNota = " ";
			if(preProjeto.getObservacao1() != null) {
				observacoesNota += preProjeto.getObservacao1(); 
			}
			if(preProjeto.getObservacao2() != null) {
				observacoesNota += preProjeto.getObservacao2(); 
			}
			dto.setObservacoesNota(observacoesNota);
			dto.setObservacoesNaoAceito(preProjeto.getObservacaoAprovacao()  == null ? " ": preProjeto.getObservacaoAprovacao());
			dto.setAprovado(preProjeto.isAprovado() ? "X": " ");
			dto.setNaoAprovado(preProjeto.isAprovado() ? " ": "X");
			
			Calendar data = Calendar.getInstance();
			dto.setDia(String.valueOf(data.get(Calendar.DAY_OF_MONTH)));
			dto.setMes(new DateFormatSymbols().getMonths()[data.get(Calendar.MONTH)]);
			dto.setAno(String.valueOf(data.get(Calendar.YEAR)));
			
			Professor coordenador = professoresService.recuperarCoordenador();
			dto.setCoordenador(coordenador == null ? " ": coordenador.getNome());
			
			File file = gerador.gera(dto);

            response.setContentType("application/pdf");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            
            return Files.readAllBytes(file.toPath());
            
		} catch (IOException e) {
			throw new RuntimeException("Erro ", e);
		}
	}

}
