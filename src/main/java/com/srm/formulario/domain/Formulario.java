package com.srm.formulario.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.srm.formulario.domain.enums.Riscos;


@Entity
public class Formulario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nomeCliente;
	private Double limiteCredito;
	private Integer taxaJuros;
	private Integer risco;
	
	public Formulario() {
	}
	
	public Formulario(Integer id, String nomeCliente, Double limiteCredito, Integer taxaJuros, Riscos risco) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.limiteCredito = limiteCredito;
		this.taxaJuros = taxaJuros;
		this.risco = ( risco == null) ? null : risco.getCod();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public Integer getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(Integer taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	
	public Riscos getRisco() {
		return Riscos.toEnum(risco);
	}

	public void setRisco(Riscos tipo) {
		this.risco = tipo.getCod();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formulario other = (Formulario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
