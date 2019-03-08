package br.com.sabrina.sgt.entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tcc")
public class TCC implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nota_tcc1")
	private String nota1;

	@Column(name = "nota_tcc2")
	private String nota2;

	@Column(name = "tema")
	private String tema;

	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno1;

	@ManyToOne
	@JoinColumn(name = "id_aluno2")
	private Aluno aluno2;

	@ManyToOne
	@JoinColumn(name = "id_professor_orientador")
	private Professor orientador;

	@ManyToOne
	@JoinColumn(name = "id_professor_coorientador")
	private Professor coOrientador;

	@ManyToOne
	@JoinColumn(name = "id_professor_membro1")
	private Professor membroBanca1;

	@ManyToOne
	@JoinColumn(name = "id_professor_membro2")
	private Professor membroBanca2;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_tcc")
	@OrderBy("data ASC")
    private Set<RelatorioOrientacao> orientacoes;
	
	@Transient
	private Long idAluno1;
	
	@Transient
	private Long idAluno2;

	@Transient
	private Long idOrientador;

	@Transient
	private Long idCoOrientador;

	@Transient
	private Long idMembroBanca1;

	@Transient
	private Long idMembroBanca2;

	public Long getIdMembroBanca1() {
		return membroBanca1 == null ? idMembroBanca1: membroBanca1.getId();
	}

	public void setIdMembroBanca1(Long idMembro1) {
		this.idMembroBanca1 = idMembro1;
	}

	public Long getIdMembroBanca2() {
		return membroBanca2 == null ? idMembroBanca2: membroBanca2.getId();
	}

	public void setIdMembroBanca2(Long idMembro2) {
		this.idMembroBanca2 = idMembro2;
	}

	public Professor getMembroBanca1() {
		return membroBanca1;
	}

	public void setMembroBanca1(Professor membroBanca1) {
		this.membroBanca1 = membroBanca1;
	}

	public Professor getMembroBanca2() {
		return membroBanca2;
	}

	public void setMembroBanca2(Professor membroBanca2) {
		this.membroBanca2 = membroBanca2;
	}

	public Set<RelatorioOrientacao> getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(Set<RelatorioOrientacao> orientacoes) {
		this.orientacoes = orientacoes;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNota1() {
		return nota1;
	}

	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}

	public String getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
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

}
