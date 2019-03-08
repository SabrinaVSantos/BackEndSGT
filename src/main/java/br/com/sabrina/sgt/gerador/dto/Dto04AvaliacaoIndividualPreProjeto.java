package br.com.sabrina.sgt.gerador.dto;

public class Dto04AvaliacaoIndividualPreProjeto {
	private Long idAvaliador;
	private Integer justificativa;
	private Integer referencial;
	private Integer metodologia;
	private Integer cronograma;
	private Integer notaFinal;
	private String dataAssinatura;
	private String tituloProjeto;
	private String nomeAluno;
	private String nomeAvaliador;
	private String dia;
	private String mes;
	private String ano;
	
	public Integer getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(Integer justificativa) {
		this.justificativa = justificativa;
	}
	public Integer getReferencial() {
		return referencial;
	}
	public void setReferencial(Integer referencial) {
		this.referencial = referencial;
	}
	public Integer getMetodologia() {
		return metodologia;
	}
	public void setMetodologia(Integer metodologia) {
		this.metodologia = metodologia;
	}
	public Integer getCronograma() {
		return cronograma;
	}
	public void setCronograma(Integer cronograma) {
		this.cronograma = cronograma;
	}
	public Long getIdAvaliador() {
		return idAvaliador;
	}
	public void setIdAvaliador(Long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}
	public Integer getNotaFinal() {
		this.notaFinal = justificativa + referencial + metodologia + cronograma;
		return this.notaFinal;
	}
	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}
	public String getDataAssinatura() {
		return dataAssinatura;
	}
	public void setDataAssinatura(String dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}
	public String getTituloProjeto() {
		return tituloProjeto;
	}
	public void setTituloProjeto(String tituloProjeto) {
		this.tituloProjeto = tituloProjeto;
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
