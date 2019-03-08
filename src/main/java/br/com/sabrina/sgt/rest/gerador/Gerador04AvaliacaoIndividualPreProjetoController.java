package br.com.sabrina.sgt.rest.gerador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.entidade.PreProjeto;
import br.com.sabrina.sgt.entidade.Professor;
import br.com.sabrina.sgt.gerador.Gerador04AvaliacaoIndividualPreProjeto;
import br.com.sabrina.sgt.gerador.dto.Dto04AvaliacaoIndividualPreProjeto;
import br.com.sabrina.sgt.service.PreProjetosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/avaliacaoindividualpreprojeto")
public class Gerador04AvaliacaoIndividualPreProjetoController {

	@Autowired
	protected PreProjetosService preProjetosService;
	
	@Autowired
	private Gerador04AvaliacaoIndividualPreProjeto gerador;

	@PostMapping(path = "/{idPreProjeto}")
	public @ResponseBody byte[] gerar(@PathVariable("idPreProjeto") Long idPreProjeto, 
			@RequestBody Dto04AvaliacaoIndividualPreProjeto dto, HttpServletResponse response) {
		
		try {
			validaDto(dto);
			PreProjeto preProjeto = preProjetosService.recuperarPorId(idPreProjeto);
			dto.setTituloProjeto(preProjeto.getTema());
			String nomes = preProjeto.getAluno1().getNome();
			if(preProjeto.getAluno2() != null) {
				nomes += " e " + preProjeto.getAluno2().getNome();
			}
			Professor avaliador = preProjeto.getAvaliador1().getId() == dto.getIdAvaliador() ? preProjeto.getAvaliador1(): preProjeto.getAvaliador2();
			dto.setNomeAluno(nomes);
			dto.setNomeAvaliador(avaliador.getNome());
			Calendar data = Calendar.getInstance();
			data.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataAssinatura()));
			dto.setDia(String.valueOf(data.get(Calendar.DAY_OF_MONTH)));
			dto.setMes(new DateFormatSymbols().getMonths()[data.get(Calendar.MONTH)]);
			dto.setAno(String.valueOf(data.get(Calendar.YEAR)));
			
			File file = gerador.gera(dto);

            response.setContentType("application/pdf");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            
            return Files.readAllBytes(file.toPath());
            
		} catch (IOException | ParseException e) {
			throw new RuntimeException("Erro ", e);
		}
	}

	private void validaDto(Dto04AvaliacaoIndividualPreProjeto dto) {
		if(dto.getDataAssinatura() == null) {
			throw new RuntimeException("Campos obrigatórios inválidos!");
		}
	}

}
