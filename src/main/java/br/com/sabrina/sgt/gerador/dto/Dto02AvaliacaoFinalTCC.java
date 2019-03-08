package br.com.sabrina.sgt.gerador.dto;

public class Dto02AvaliacaoFinalTCC {
	private String aluno;
	private String titulo;
	private String orientador;
	private String coorientador;
	private String membroBanca1;
	private String membroBanca2;
	
	private String observacoes;
	private String dataAssinatura;
	private String dia;
	private String mes;
	private String ano;
	
	private Integer notaTrabalhoEscritoOrientador;
	private Integer notaTrabalhoEscritoCoOrientador;
	private Integer notaTrabalhoEscritoMembro1;
	private Integer notaTrabalhoEscritoMembro2;
	private Integer notaTrabalhoOralOrientador;
	private Integer notaTrabalhoOralCoOrientador;
	private Integer notaTrabalhoOralMembro1;
	private Integer notaTrabalhoOralMembro2;
	
	private Integer notaAvaliadorOrientador;
	private Integer notaAvaliadorCoOrientador;
	private Integer notaAvaliadorMembro1;
	private Integer notaAvaliadorMembro2;
	private Integer totalOrientador;
	private Integer totalCoOrientador;
	private Integer totalMembro1;
	private Integer totalMembro2;
	private Double notaFinal;
	
	public String getDataAssinatura() {
		return dataAssinatura;
	}
	public void setDataAssinatura(String dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
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
	public String getMembroBanca1() {
		return membroBanca1;
	}
	public void setMembroBanca1(String membroBanca1) {
		this.membroBanca1 = membroBanca1;
	}
	public String getMembroBanca2() {
		return membroBanca2;
	}
	public void setMembroBanca2(String membroBanca2) {
		this.membroBanca2 = membroBanca2;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
	public Integer getNotaTrabalhoEscritoOrientador() {
		return notaTrabalhoEscritoOrientador;
	}
	public void setNotaTrabalhoEscritoOrientador(Integer notaTrabalhoEscritoOrientador) {
		this.notaTrabalhoEscritoOrientador = notaTrabalhoEscritoOrientador;
	}
	public Integer getNotaTrabalhoEscritoCoOrientador() {
		return notaTrabalhoEscritoCoOrientador;
	}
	public void setNotaTrabalhoEscritoCoOrientador(Integer notaTrabalhoEscritoCoOrientador) {
		this.notaTrabalhoEscritoCoOrientador = notaTrabalhoEscritoCoOrientador;
	}
	public Integer getNotaTrabalhoEscritoMembro1() {
		return notaTrabalhoEscritoMembro1;
	}
	public void setNotaTrabalhoEscritoMembro1(Integer notaTrabalhoEscritoMembro1) {
		this.notaTrabalhoEscritoMembro1 = notaTrabalhoEscritoMembro1;
	}
	public Integer getNotaTrabalhoEscritoMembro2() {
		return notaTrabalhoEscritoMembro2;
	}
	public void setNotaTrabalhoEscritoMembro2(Integer notaTrabalhoEscritoMembro2) {
		this.notaTrabalhoEscritoMembro2 = notaTrabalhoEscritoMembro2;
	}
	public Integer getNotaTrabalhoOralOrientador() {
		return notaTrabalhoOralOrientador;
	}
	public void setNotaTrabalhoOralOrientador(Integer notaTrabalhoOralOrientador) {
		this.notaTrabalhoOralOrientador = notaTrabalhoOralOrientador;
	}
	public Integer getNotaTrabalhoOralCoOrientador() {
		return notaTrabalhoOralCoOrientador;
	}
	public void setNotaTrabalhoOralCoOrientador(Integer notaTrabalhoOralCoOrientador) {
		this.notaTrabalhoOralCoOrientador = notaTrabalhoOralCoOrientador;
	}
	public Integer getNotaTrabalhoOralMembro1() {
		return notaTrabalhoOralMembro1;
	}
	public void setNotaTrabalhoOralMembro1(Integer notaTrabalhoOralMembro1) {
		this.notaTrabalhoOralMembro1 = notaTrabalhoOralMembro1;
	}
	public Integer getNotaTrabalhoOralMembro2() {
		return notaTrabalhoOralMembro2;
	}
	public void setNotaTrabalhoOralMembro2(Integer notaTrabalhoOralMembro2) {
		this.notaTrabalhoOralMembro2 = notaTrabalhoOralMembro2;
	}
	public Integer getNotaAvaliadorOrientador() {
		notaAvaliadorOrientador = notaTrabalhoEscritoOrientador + notaTrabalhoOralOrientador;
		return notaAvaliadorOrientador;
	}
	public void setNotaAvaliadorOrientador(Integer notaAvaliadorOrientador) {
		this.notaAvaliadorOrientador = notaAvaliadorOrientador;
	}
	public Integer getNotaAvaliadorCoOrientador() {
		notaAvaliadorCoOrientador = notaTrabalhoEscritoCoOrientador + notaTrabalhoOralCoOrientador;
		return notaAvaliadorCoOrientador;
	}
	public void setNotaAvaliadorCoOrientador(Integer notaAvaliadorCoOrientador) {
		this.notaAvaliadorCoOrientador = notaAvaliadorCoOrientador;
	}
	public Integer getNotaAvaliadorMembro1() {
		notaAvaliadorMembro1 = notaTrabalhoEscritoMembro1 + notaTrabalhoOralMembro1;
		return notaAvaliadorMembro1;
	}
	public void setNotaAvaliadorMembro1(Integer notaAvaliadorMembro1) {
		this.notaAvaliadorMembro1 = notaAvaliadorMembro1;
	}
	public Integer getNotaAvaliadorMembro2() {
		notaAvaliadorMembro2 = notaTrabalhoEscritoMembro2 + notaTrabalhoOralMembro2;
		return notaAvaliadorMembro2;
	}
	public void setNotaAvaliadorMembro2(Integer notaAvaliadorMembro2) {
		this.notaAvaliadorMembro2 = notaAvaliadorMembro2;
	}
	public Integer getTotalOrientador() {
		totalOrientador = getNotaAvaliadorOrientador();
		return totalOrientador;
	}
	public void setTotalOrientador(Integer totalOrientador) {
		this.totalOrientador = totalOrientador;
	}
	public Integer getTotalCoOrientador() {
		totalCoOrientador = getNotaAvaliadorCoOrientador();
		return totalCoOrientador;
	}
	public void setTotalCoOrientador(Integer totalCoOrientador) {
		this.totalCoOrientador = totalCoOrientador;
	}
	public Integer getTotalMembro1() {
		totalMembro1 = getNotaAvaliadorMembro1();
		return totalMembro1;
	}
	public void setTotalMembro1(Integer totalMembro1) {
		this.totalMembro1 = totalMembro1;
	}
	public Integer getTotalMembro2() {
		totalMembro2 = getNotaAvaliadorMembro2();
		return totalMembro2;
	}
	public void setTotalMembro2(Integer totalMembro2) {
		this.totalMembro2 = totalMembro2;
	}
	public Double getNotaFinal() {
		notaFinal = (getTotalOrientador().doubleValue() + getTotalCoOrientador().doubleValue() + getTotalMembro1().doubleValue() + getTotalMembro2().doubleValue()) / 4;
		return notaFinal;
	}
	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}

	
}
