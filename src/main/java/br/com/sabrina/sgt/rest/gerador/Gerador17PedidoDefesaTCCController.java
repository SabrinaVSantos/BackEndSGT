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
import br.com.sabrina.sgt.gerador.Gerador17PedidoDefesaTCC;
import br.com.sabrina.sgt.gerador.dto.Dto17PedidoDefesaTCC;
import br.com.sabrina.sgt.gerador.dto.DtoGeradorTCC;
import br.com.sabrina.sgt.service.TCCsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/pedidodefesatcc")
public class Gerador17PedidoDefesaTCCController {

	@Autowired
	protected TCCsService tccService;
	
	@Autowired
	private Gerador17PedidoDefesaTCC gerador;

	@PostMapping(path = "/{idTCC}")
	public @ResponseBody byte[] gera(@PathVariable("idTCC") Long idTCC, 
			@RequestBody DtoGeradorTCC dtoGerador, HttpServletResponse response) {
		try {
			validaDto(dtoGerador);
			TCC tcc = tccService.recuperarPorId(idTCC);
			Dto17PedidoDefesaTCC dto = new Dto17PedidoDefesaTCC();
			dto.setAluno(tcc.getAluno1().getNome());
			dto.setMatriculaAluno(tcc.getAluno1().getMatricula().toString());
			dto.setTitulo(tcc.getTema());
			dto.setOrientador(tcc.getOrientador().getNome());
			dto.setCoOrientador(tcc.getCoOrientador() == null? " ": tcc.getCoOrientador().getNome());
			dto.setMembro1(tcc.getMembroBanca1() == null? " ": tcc.getMembroBanca1().getNome());
			dto.setMembro2(tcc.getMembroBanca2() == null? " ": tcc.getMembroBanca2().getNome());
			dto.setHoraDefesa(dtoGerador.getHoraDefesa());
			dto.setLocalDefesa(dtoGerador.getLocalDefesa());
			dto.setSuplente(dtoGerador.getSuplente());
			Calendar data = Calendar.getInstance();
			data.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dtoGerador.getDataAssinatura()));
			dto.setDiaAssinatura(String.valueOf(data.get(Calendar.DAY_OF_MONTH)));
			dto.setMesAssinatura(new DateFormatSymbols().getMonths()[data.get(Calendar.MONTH)]);
			dto.setAnoAssinatura(String.valueOf(data.get(Calendar.YEAR)));
			data.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dtoGerador.getDataDefesa()));
			dto.setDiaDefesa(String.valueOf(data.get(Calendar.DAY_OF_MONTH)));
			dto.setMesDefesa(new DateFormatSymbols().getMonths()[data.get(Calendar.MONTH)]);
			dto.setAnoDefesa(String.valueOf(data.get(Calendar.YEAR)));

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

	private void validaDto(DtoGeradorTCC dto) {
		if(dto.getDataAssinatura() == null || dto.getDataDefesa() == null) {
			throw new RuntimeException("Campos obrigatórios inválidos!");
		}
	}

}
