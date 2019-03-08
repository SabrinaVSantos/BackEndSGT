package br.com.sabrina.sgt.rest.gerador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.entidade.TCC;
import br.com.sabrina.sgt.gerador.Gerador08CapaTCC;
import br.com.sabrina.sgt.gerador.dto.Dto08CapaTCC;
import br.com.sabrina.sgt.gerador.dto.DtoGeradorTCC;
import br.com.sabrina.sgt.service.TCCsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/capatcc")
public class Gerador08CapaTCCController {

	@Autowired
	protected TCCsService tccService;
	
	@Autowired
	private Gerador08CapaTCC gerador;

	@PostMapping(path = "/{idTCC}")
	public @ResponseBody byte[] gera(@PathVariable("idTCC") Long idTCC, 
			@RequestBody DtoGeradorTCC dtoGerador, HttpServletResponse response) {
		try {
			TCC tcc = tccService.recuperarPorId(idTCC);
			Dto08CapaTCC dto = new Dto08CapaTCC();
			String nomes = tcc.getAluno1().getNome();
			if(tcc.getAluno2() != null) {
				nomes += " e " + tcc.getAluno2().getNome();
			}
			dto.setAluno(nomes);
			dto.setTitulo(tcc.getTema());
			dto.setOrientador(tcc.getOrientador().getNome());
			dto.setCoorientador(tcc.getCoOrientador() == null? " ": tcc.getCoOrientador().getNome());
			dto.setAno(dtoGerador.getAnoDefesa());

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
