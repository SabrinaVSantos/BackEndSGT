package br.com.sabrina.sgt.rest.gerador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.entidade.RelatorioOrientacao;
import br.com.sabrina.sgt.entidade.TCC;
import br.com.sabrina.sgt.gerador.Gerador20RegistroEncontroOrientacaoTCC;
import br.com.sabrina.sgt.gerador.dto.Dto20RegistroEncontroOrientacaoTCC;
import br.com.sabrina.sgt.service.TCCsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/registroencontroorientacaotcc")
public class Gerador20RegistroEncontroOrientacaoTCCController {
	@Autowired
	protected TCCsService tccService;
	
	@Autowired
	private Gerador20RegistroEncontroOrientacaoTCC gerador;
	
	@PostMapping(path = "/{idTCC}")
	public @ResponseBody byte[] gerarPreProjeto(@PathVariable("idTCC") Long idTCC, HttpServletResponse response) {
		try {
			TCC tcc = tccService.recuperarPorId(idTCC);
			Dto20RegistroEncontroOrientacaoTCC dto = new Dto20RegistroEncontroOrientacaoTCC();
			String nomes = tcc.getAluno1().getNome();
			if(tcc.getAluno2() != null) {
				nomes += " e " + tcc.getAluno2().getNome();
			}
			dto.setAluno(nomes);
			dto.setOrientador(tcc.getOrientador().getNome());
			dto.setCoorientador(tcc.getCoOrientador() == null? " ": tcc.getCoOrientador().getNome());
			dto.setTitulo(tcc.getTema());
			dto.setOrientacoes(new ArrayList<RelatorioOrientacao>(tcc.getOrientacoes()));
			
			File file = gerador.gera(dto);

            response.setContentType("application/pdf");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            
            return Files.readAllBytes(file.toPath());
            
		} catch (IOException e) {
			throw new RuntimeException("Erro ao gerar registro orientação", e);
		}
	}

}
