package br.com.sabrina.sgt.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pre_projeto")
public class PreProjeto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tema")
	private String tema;

	@Column(name = "anexo")
	private String anexo;

	@Column(name = "estado_avaliacao")
	@Enumerated(EnumType.STRING)
	private EstadoAvaliacaoEnum estadoAvaliacao = EstadoAvaliacaoEnum.CADASTRADO;

	@ManyToOne
	@JoinColumn(name = "id_professor_orientador")
	private Professor orientador;

	@ManyToOne
	@JoinColumn(name = "id_professor_coorientador")
	private Professor coOrientador;

	@ManyToOne
	@JoinColumn(name = "id_professor_avaliador1")
	private Professor avaliador1;

	@ManyToOne
	@JoinColumn(name = "id_professor_avaliador2")
	private Professor avaliador2;

	@ManyToOne
	@JoinColumn(name = "id_aluno1")
	private Aluno aluno1;

	@ManyToOne
	@JoinColumn(name = "id_aluno2")
	private Aluno aluno2;

	@Column(name = "observacao1")
	private String observacao1;

	@Column(name = "observacao2")
	private String observacao2;

	@Column(name = "observacao_aprovacao")
	private String observacaoAprovacao;
	
	@Column(name = "aprovado")
	private boolean aprovado;
	
	@Column(name = "nota1")
	private Integer nota1;

	@Column(name = "nota2")
	private Integer nota2;

	@Column(name = "notafinal")
	private Integer notaFinal;

	@Transient
	private Long idAluno1;
	
	@Transient
	private Long idAluno2;

	@Transient
	private Long idOrientador;

	@Transient
	private Long idCoOrientador;

	@Transient
	private Long idAvaliador1;

	@Transient
	private Long idAvaliador2;
	
	@Transient
	private String arquivoPreProjeto;

	@Transient
	private String arquivoFichaInscricao;

	@Transient
	private String arquivoHistorico;

	@Transient
	private String arquivoCartaAceite;

	public String getObservacaoAprovacao() {
		return observacaoAprovacao;
	}

	public void setObservacaoAprovacao(String observacaoAprovacao) {
		this.observacaoAprovacao = observacaoAprovacao;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getArquivoPreProjeto() {
		return arquivoPreProjeto;
	}

	public void setArquivoPreProjeto(String arquivoPreProjeto) {
		this.arquivoPreProjeto = arquivoPreProjeto;
	}

	public String getArquivoFichaInscricao() {
		return arquivoFichaInscricao;
	}

	public void setArquivoFichaInscricao(String arquivoFichaInscricao) {
		this.arquivoFichaInscricao = arquivoFichaInscricao;
	}

	public String getArquivoHistorico() {
		return arquivoHistorico;
	}

	public void setArquivoHistorico(String arquivoHistorico) {
		this.arquivoHistorico = arquivoHistorico;
	}

	public String getArquivoCartaAceite() {
		return arquivoCartaAceite;
	}

	public void setArquivoCartaAceite(String arquivoCartaAceite) {
		this.arquivoCartaAceite = arquivoCartaAceite;
	}

	public Long getIdOrientador() {
		return orientador == null ? idOrientador: orientador.getId();
	}

	public void setIdOrientador(Long idOrientador) {
		this.idOrientador = idOrientador;
	}

	public Long getIdCoOrientador() {
		return coOrientador == null ? idCoOrientador: coOrientador.getId();
	}

	public void setIdCoOrientador(Long idCoOrientador) {
		this.idCoOrientador = idCoOrientador;
	}

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public Professor getCoOrientador() {
		return coOrientador;
	}

	public void setCoOrientador(Professor coOrientador) {
		this.coOrientador = coOrientador;
	}

	public Long getIdAluno1() {
		return aluno1 == null ? idAluno1: aluno1.getId();
	}

	public void setIdAluno1(Long idAluno1) {
		this.idAluno1 = idAluno1;
	}

	public Long getIdAluno2() {
		return aluno2 == null ? idAluno2 : aluno2.getId();
	}

	public void setIdAluno2(Long idAluno2) {
		this.idAluno2 = idAluno2;
	}

	public Long getIdAvaliador1() {
		return avaliador1 == null? idAvaliador1: avaliador1.getId();
	}

	public void setIdAvaliador1(Long idAvaliador1) {
		this.idAvaliador1 = idAvaliador1;
	}

	public Long getIdAvaliador2() {
		return avaliador2 == null? idAvaliador2: avaliador2.getId();
	}

	public void setIdAvaliador2(Long idAvaliador2) {
		this.idAvaliador2 = idAvaliador2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public EstadoAvaliacaoEnum getEstadoAvaliacao() {
		return estadoAvaliacao;
	}

	public void setEstadoAvaliacao(EstadoAvaliacaoEnum estadoAvaliacao) {
		this.estadoAvaliacao = estadoAvaliacao;
	}

	public Professor getAvaliador1() {
		return avaliador1;
	}

	public void setAvaliador1(Professor avaliador1) {
		this.avaliador1 = avaliador1;
	}

	public Professor getAvaliador2() {
		return avaliador2;
	}

	public void setAvaliador2(Professor avaliador2) {
		this.avaliador2 = avaliador2;
	}

	public Aluno getAluno1() {
		return aluno1;
	}

	public void setAluno1(Aluno aluno1) {
		this.aluno1 = aluno1;
	}

	public Aluno getAluno2() {
		return aluno2;
	}

	public void setAluno2(Aluno aluno2) {
		this.aluno2 = aluno2;
	}

	public String getObservacao1() {
		return observacao1;
	}

	public void setObservacao1(String observacao1) {
		this.observacao1 = observacao1;
	}

	public String getObservacao2() {
		return observacao2;
	}

	public void setObservacao2(String observacao2) {
		this.observacao2 = observacao2;
	}

	public Integer getNota1() {
		return nota1;
	}

	public void setNota1(Integer nota1) {
		this.nota1 = nota1;
	}

	public Integer getNota2() {
		return nota2;
	}

	public void setNota2(Integer nota2) {
		this.nota2 = nota2;
	}

	public Integer getNotaFinal() {
		return nota1 != null && nota2 != null ? (nota1 + nota2) / 2: null;
	}

	public void setNotaFinal(Integer notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	
}
