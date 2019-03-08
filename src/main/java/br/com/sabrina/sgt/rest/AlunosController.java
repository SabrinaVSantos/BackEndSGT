package br.com.sabrina.sgt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.entidade.Aluno;
import br.com.sabrina.sgt.service.AlunosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/alunos")
public class AlunosController {
	@Autowired
	protected AlunosService alunosService;
	
	@PostMapping	
	public Aluno salvar(@RequestBody Aluno aluno) {
		return alunosService.salvar(aluno);
	}
	
	@DeleteMapping(value = "/{id}")	
	public boolean apagar(@PathVariable("id") Long id) {
		return alunosService.apagar(id);
	}
	
	@GetMapping(value = "/{id}")
	public Aluno recuperar(@PathVariable("id") Long id) {
		return alunosService.recuperarPorId(id);
	}

	@GetMapping(value = "/pesquisa")
	public List<Aluno> pesquisa(@RequestParam("termo") String termo) {
		return alunosService.pesquisar(termo);
	}

	@GetMapping
	public List<Aluno> listarTodos() {
		return alunosService.listarTodos();
	}
}
