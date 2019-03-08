package br.com.sabrina.sgt.gerador.dto;

public class Dto05AvaliacaoIndividualTCC {
	private Long idAvaliador;
	private String dataAssinatura;
	private Integer introducao;
	private Integer referencialTeorico;
	private Integer desenvolvimentoAvaliacao;
	private Integer conclusoesReferenciasBibliograficas;
	private Integer notaFinalTrabalhoEscrito;
	private Integer estruturaOrdenacao;
	private Integer clarezaFluencia;
	private Integer dominioTema;
	private Integer observanciaTempo;
	private Integer notaFinalTrabalhoOral;
	private String nomeAluno;
	private String nomeAvaliador;
	private String dia;
	private String mes;
	private String ano;
	
	public String getDataAssinatura() {
		return dataAssinatura;
	}
	public void setDataAssinatura(String dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}
	public Integer getIntroducao() {
		return introducao;
	}
	public void setIntroducao(Integer introducao) {
		this.introducao = introducao;
	}
	public Integer getReferencialTeorico() {
		return referencialTeorico;
	}
	public void setReferencialTeorico(Integer referencialTeorico) {
		this.referencialTeorico = referencialTeorico;
	}
	public Integer getDesenvolvimentoAvaliacao() {
		return desenvolvimentoAvaliacao;
	}
	public void setDesenvolvimentoAvaliacao(Integer desenvolvimentoAvaliacao) {
		this.desenvolvimentoAvaliacao = desenvolvimentoAvaliacao;
	}
	public Integer getConclusoesReferenciasBibliograficas() {
		return conclusoesReferenciasBibliograficas;
	}
	public void setConclusoesReferenciasBibliograficas(Integer conclusoesReferenciasBibliograficas) {
		this.conclusoesReferenciasBibliograficas = conclusoesReferenciasBibliograficas;
	}
	public Integer getNotaFinalTrabalhoEscrito() {
		notaFinalTrabalhoEscrito = introducao + referencialTeorico + desenvolvimentoAvaliacao + conclusoesReferenciasBibliograficas;
		return notaFinalTrabalhoEscrito;
	}
	public void setNotaFinalTrabalhoEscrito(Integer notaFinalTrabalhoEscrito) {
		this.notaFinalTrabalhoEscrito = notaFinalTrabalhoEscrito;
	}
	public Integer getEstruturaOrdenacao() {
		return estruturaOrdenacao;
	}
	public void setEstruturaOrdenacao(Integer estruturaOrdenacao) {
		this.estruturaOrdenacao = estruturaOrdenacao;
	}
	public Integer getClarezaFluencia() {
		return clarezaFluencia;
	}
	public void setClarezaFluencia(Integer clarezaFluencia) {
		this.clarezaFluencia = clarezaFluencia;
	}
	public Integer getDominioTema() {
		return dominioTema;
	}
	public void setDominioTema(Integer dominioTema) {
		this.dominioTema = dominioTema;
	}
	public Integer getObservanciaTempo() {
		return observanciaTempo;
	}
	public void setObservanciaTempo(Integer observanciaTempo) {
		this.observanciaTempo = observanciaTempo;
	}
	public Integer getNotaFinalTrabalhoOral() {
		notaFinalTrabalhoOral = estruturaOrdenacao + clarezaFluencia + dominioTema + observanciaTempo; 
		return notaFinalTrabalhoOral;
	}
	public void setNotaFinalTrabalhoOral(Integer notaFinalTrabalhoOral) {
		this.notaFinalTrabalhoOral = notaFinalTrabalhoOral;
	}
	public Long getIdAvaliador() {
		return idAvaliador;
	}
	public void setIdAvaliador(Long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getNomeAvaliador() {
		return nomeAvaliador;
	}
	public void setNomeAvaliador(String nomeAvaliador) {
		this.nomeAvaliador = nomeAvaliador;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
		
}
