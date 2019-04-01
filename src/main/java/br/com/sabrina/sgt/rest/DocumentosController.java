package br.com.sabrina.sgt.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.sabrina.sgt.dao.AlunoRepository;
import br.com.sabrina.sgt.entidade.Documento;
import br.com.sabrina.sgt.entidade.PreProjeto;
import br.com.sabrina.sgt.entidade.TipoDocumentoEnum;
import br.com.sabrina.sgt.service.DocumentosService;
import br.com.sabrina.sgt.service.FileStorageService;
import br.com.sabrina.sgt.service.PreProjetosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/documentos")
public class DocumentosController {
	private static final Logger logger = LoggerFactory.getLogger(DocumentosController.class);

    @Autowired
	protected FileStorageService fileStorageService;
    
	@Autowired
	protected DocumentosService documentosService;

	@Autowired
	protected PreProjetosService preProjetosService;
	
	@Autowired
	protected AlunoRepository alunoRepository;

	//listar todos os documentos de um aluno
	@GetMapping("/{idAluno}")	
	public List<Documento> listarPorIdAluno(@PathVariable Long idAluno) {
		return documentosService.listarPorIdAluno(idAluno);
	}


	//salvar e fazer upload de documentos de um aluno
    @PostMapping("/{idAluno}")
    public boolean Salvar(@PathVariable Long idAluno,
    		@RequestParam("preProjeto") MultipartFile preProjeto, 
    		@RequestParam("fichaInscricao") MultipartFile fichaInscricao, 
    		@RequestParam("cartaAceite") MultipartFile cartaAceite, 
    		@RequestParam("historico") MultipartFile historico) {
    	documentosService.apagarTodosDocumentos(idAluno);
    	salvaArquivo(idAluno, fichaInscricao, TipoDocumentoEnum.FICHA_INSCRICAO);
    	salvaArquivo(idAluno, cartaAceite, TipoDocumentoEnum.CARTA_DE_ACEITE);
    	salvaArquivo(idAluno, historico, TipoDocumentoEnum.HISTORICO_ESCOLAR);
    	salvaPreProjeto(idAluno, preProjeto);

        return true;
    }
    
    private Documento salvaArquivo(Long idAluno, MultipartFile file, TipoDocumentoEnum tipoDocumento) {
    	if(file != null) {
	    	//salva arquivo em disco
	        String fileName = fileStorageService.storeFile(file);
	        
	        //salva documento em banco
	        Documento documento = new Documento();
	        documento.setTipo(tipoDocumento);
	        documento.setAnexo(fileName);
	        documento.setAluno(alunoRepository.findById(idAluno).get());
	        return documentosService.salvar(documento);
    	}
    	return null;
    }

    private PreProjeto salvaPreProjeto(Long idAluno, MultipartFile file) {
    	if(file != null) {
	    	//salva arquivo em disco
	        String fileName = fileStorageService.storeFile(file);
	        
	        //salva preProjeto em banco
	        PreProjeto preProjeto = preProjetosService.recuperarPorIdAluno(idAluno);
	        preProjeto.setAnexo(fileName);
	        return preProjetosService.salvar(preProjeto);
    	}
    	return null;
    }
    
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
    	Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
