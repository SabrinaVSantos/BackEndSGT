package br.com.sabrina.sgt.rest.gerador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.gerador.Gerador14InformeMatricula;
import br.com.sabrina.sgt.gerador.dto.Dto14InformeMatricula;

@RestController
@CrossOrigin("*")
@RequestMapping("/gerador/informematricula")
public class Gerador14InformeMatriculaController {

	@Autowired
	private Gerador14InformeMatricula gerador;
	
	@PostMapping
	public @ResponseBody byte[] gerarPreProjeto(@RequestBody Dto14InformeMatricula dto, HttpServletResponse response) {
		try {
			Calendar data = Calendar.getInstance();
			data.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getData()));
			dto.setData(new SimpleDateFormat("dd/MM/yyyy").format(data.getTime()));
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

}
