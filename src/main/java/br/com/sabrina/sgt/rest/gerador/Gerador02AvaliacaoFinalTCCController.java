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

import br.com.sabrina.sgt.entidade.TCC;
import br.com.sabrina.sgt.gerador.Gerador02AvaliacaoFinalTCC;
import br.com.sabrina.sgt.gerador.dto.Dto02AvaliacaoFinalTCC;
import br.com.sabrina.sgt.service.TCCsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/avaliacaofinaltcc")
public class Gerador02AvaliacaoFinalTCCController {

	@Autowired
	protected TCCsService tccService;
	
	@Autowired
	private Gerador02AvaliacaoFinalTCC gerador;

	@PostMapping(path = "/{idTCC}")
	public @ResponseBody byte[] gera(@PathVariable("idTCC") Long idTCC, 
			@RequestBody Dto02AvaliacaoFinalTCC dto, HttpServletResponse response) {
		try {
			validaDto(dto);
			TCC tcc = tccService.recuperarPorId(idTCC);
			String nomes = tcc.getAluno1().getNome();
			if(tcc.getAluno2() != null) {
				nomes += " e " + tcc.getAluno2().getNome();
			}
			dto.setAluno(nomes);
			dto.setOrientador(tcc.getOrientador().getNome());
			dto.setCoorientador(tcc.getCoOrientador() == null? " ": tcc.getCoOrientador().getNome());
			dto.setMembroBanca1(tcc.getMembroBanca1() == null? " ": tcc.getMembroBanca1().getNome());
			dto.setMembroBanca2(tcc.getMembroBanca2() == null? " ": tcc.getMembroBanca2().getNome());
			dto.setTitulo(tcc.getTema());

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

	private void validaDto(Dto02AvaliacaoFinalTCC dto) {
		if(dto.getDataAssinatura() == null) {
			throw new RuntimeException("Campos obrigatórios inválidos!");
		}
	}


}
