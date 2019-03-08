package br.com.sabrina.sgt.dto;

import br.com.sabrina.sgt.entidade.PerfilUsuarioEnum;

public class DtoAutenticacao {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private PerfilUsuarioEnum perfil;
	
	public DtoAutenticacao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtoAutenticacao(Long id, String nome, String login, String senha, PerfilUsuarioEnum perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public PerfilUsuarioEnum getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuarioEnum perfil) {
		this.perfil = perfil;
	}
	
	
}
