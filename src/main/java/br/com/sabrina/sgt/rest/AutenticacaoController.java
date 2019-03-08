package br.com.sabrina.sgt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.sgt.dto.DtoAutenticacao;
import br.com.sabrina.sgt.entidade.Aluno;
import br.com.sabrina.sgt.entidade.PerfilUsuarioEnum;
import br.com.sabrina.sgt.entidade.Professor;
import br.com.sabrina.sgt.service.AlunosService;
import br.com.sabrina.sgt.service.ProfessoresService;

@RestController
@CrossOrigin("*")
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	@Autowired
	protected AlunosService alunosService;

	@Autowired
	protected ProfessoresService professoresService;

	@PostMapping
	public DtoAutenticacao autenticar(@RequestBody DtoAutenticacao dtoAutenticacao) {
		DtoAutenticacao dtoAutenticado = null;
		if(dtoAutenticacao.getLogin() == null || dtoAutenticacao.getSenha() == null) {
			throw new RuntimeException("Por favor informe login e senha.");
		}
		
		if(dtoAutenticacao.getLogin().length() == 14) {
			dtoAutenticacao.setPerfil(PerfilUsuarioEnum.ALUNO);
			dtoAutenticado = autenticaAluno(dtoAutenticacao, dtoAutenticado);
		} else {
			dtoAutenticacao.setPerfil(PerfilUsuarioEnum.PROFESSOR);
			dtoAutenticado = autenticaProfessor(dtoAutenticacao, dtoAutenticado);
		}
		
		if(dtoAutenticado == null) {
			throw new RuntimeException("Usuário inválido, verifique login e senha.");
		}
		return dtoAutenticado;
	}

	private DtoAutenticacao autenticaProfessor(DtoAutenticacao dtoAutenticacao, DtoAutenticacao dtoAutenticado) {
		Professor professor = professoresService.recuperarPorSiapeESenha(dtoAutenticacao.getLogin(),
				dtoAutenticacao.getSenha());
		if (professor != null) {
			dtoAutenticado = new DtoAutenticacao(professor.getId(), professor.getNome(), professor.getSiape(), professor.getSenha(),
					professor.getPerfil());
		}
		return dtoAutenticado;
	}

	private DtoAutenticacao autenticaAluno(DtoAutenticacao dtoAutenticacao, DtoAutenticacao dtoAutenticado) {
		Aluno aluno = alunosService.recuperarPorMatriculaESenha(Long.valueOf(dtoAutenticacao.getLogin()),
				dtoAutenticacao.getSenha());
		if (aluno != null) {
			dtoAutenticado = new DtoAutenticacao(aluno.getId(), aluno.getNome(), String.valueOf(aluno.getMatricula()), aluno.getSenha(),
					aluno.getPerfil());
		}
		return dtoAutenticado;
	}

}
