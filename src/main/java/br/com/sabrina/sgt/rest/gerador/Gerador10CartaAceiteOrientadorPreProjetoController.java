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
import br.com.sabrina.sgt.gerador.Gerador10CartaAceiteOrientadorPreProjeto;
import br.com.sabrina.sgt.gerador.dto.Dto10CartaAceiteOrientadorPreProjeto;
import br.com.sabrina.sgt.service.PreProjetosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/cartaaceiteorientadorpreprojeto")
public class Gerador10CartaAceiteOrientadorPreProjetoController {

	@Autowired
	protected PreProjetosService preProjetosService;
	
	@Autowired
	private Gerador10CartaAceiteOrientadorPreProjeto gerador;

	@PostMapping(path = "/{idPreProjeto}")
	public @ResponseBody byte[] gerar(@PathVariable("idPreProjeto") Long idPreProjeto, 
			@RequestBody Dto10CartaAceiteOrientadorPreProjeto dto, HttpServletResponse response) {
		try {
			validaDto(dto);
			PreProjeto preProjeto = preProjetosService.recuperarPorId(idPreProjeto);
			Calendar data = Calendar.getInstance();
			data.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataAssinatura()));
			dto.setDia(String.valueOf(data.get(Calendar.DAY_OF_MONTH)));
			dto.setMes(new DateFormatSymbols().getMonths()[data.get(Calendar.MONTH)]);
			dto.setAno(String.valueOf(data.get(Calendar.YEAR)));
			dto.setTitulo(preProjeto.getTema());
			String nomes = preProjeto.getAluno1().getNome();
			if(preProjeto.getAluno2() != null) {
				nomes += " e " + preProjeto.getAluno2().getNome();
			}
			String matriculas = preProjeto.getAluno1().getMatricula().toString();
			if(preProjeto.getAluno2() != null) {
				nomes += " e " + preProjeto.getAluno2().getMatricula().toString();
			}
			dto.setAluno(nomes);
			dto.setMatriculaAluno(matriculas);
			dto.setOrientador(preProjeto.getOrientador().getNome());
			dto.setMatriculaSiape(preProjeto.getOrientador().getSiape());
			
			File file = gerador.gera(dto);

            response.setContentType("application/pdf");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            
            return Files.readAllBytes(file.toPath());
            
		} catch (IOException | ParseException e) {
			throw new RuntimeException("Erro ao gerar carta aceite orientador", e);
		}
	}

	private void validaDto(Dto10CartaAceiteOrientadorPreProjeto dto) {
		if(dto.getDataAssinatura() == null) {
			throw new RuntimeException("Campos obrigatórios inválidos!");
		}
	}

}
