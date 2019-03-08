package br.com.sabrina.sgt.entidade;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "relatorio")
public class RelatorioOrientacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "orientacao")
	private String orientacao;

	@Column(name = "data_orientacao")
	private Date data;
	
	@Transient
	private String dataFormatada;

	@Column(name = "id_tcc")
	private Long idTcc;

	public Long getIdTcc() {
		return idTcc;
	}

	public void setIdTcc(Long idTcc) {
		this.idTcc = idTcc;
	}

	public String getDataFormatada() {
		return dataFormatada == null ? new SimpleDateFormat("dd/MM/yyyy").format(data): dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
