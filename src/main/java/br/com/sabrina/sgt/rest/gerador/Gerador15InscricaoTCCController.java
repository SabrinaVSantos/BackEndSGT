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
import br.com.sabrina.sgt.gerador.Gerador15InscricaoTCC;
import br.com.sabrina.sgt.gerador.dto.Dto15InscricaoTCC;
import br.com.sabrina.sgt.service.PreProjetosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/inscricaotcc")
public class Gerador15InscricaoTCCController {

	@Autowired
	protected PreProjetosService preProjetosService;
	
	@Autowired
	private Gerador15InscricaoTCC gerador;

	@PostMapping(path = "/{idPreProjeto}")
	public @ResponseBody byte[] gerarPreProjeto(@PathVariable("idPreProjeto") Long idPreProjeto,
			@RequestBody Dto15InscricaoTCC dto, HttpServletResponse response) {
		try {
			PreProjeto preProjeto = preProjetosService.recuperarPorId(idPreProjeto);
			dto.setAluno(preProjeto.getAluno1().getNome());
			Calendar data = Calendar.getInstance();
			if(dto.getData() != null) {
				data.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getData()));
			}
			dto.setDiaAssinatura(String.valueOf(data.get(Calendar.DAY_OF_MONTH)));
			dto.setMesAssinatura(new DateFormatSymbols().getMonths()[data.get(Calendar.MONTH)]);
			dto.setAnoAssinatura(String.valueOf(data.get(Calendar.YEAR)));
			
			File file = gerador.gera(dto);

            response.setContentType("application/pdf");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            
            return Files.readAllBytes(file.toPath());
            
		} catch (IOException | ParseException e) {
			throw new RuntimeException("Erro ao gerar ficha inscrição", e);
		}
	}
}
