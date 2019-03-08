package br.com.sabrina.sgt.gerador.dto;

import java.util.List;

import br.com.sabrina.sgt.entidade.RelatorioOrientacao;

public class Dto20RegistroEncontroOrientacaoTCC {
	private String aluno;
	private String titulo;
	private String orientador;
	private String coorientador;
	private List<RelatorioOrientacao> orientacoes;
	
	public List<RelatorioOrientacao> getOrientacoes() {
		return orientacoes;
	}
	public void setOrientacoes(List<RelatorioOrientacao> orientacoes) {
		this.orientacoes = orientacoes;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getOrientador() {
		return orientador;
	}
	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}
	public String getCoorientador() {
		return coorientador;
	}
	public void setCoorientador(String coorientador) {
		this.coorientador = coorientador;
	}
	
	
}
