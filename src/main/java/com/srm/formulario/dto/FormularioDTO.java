package com.srm.formulario.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.srm.formulario.domain.Formulario;
import com.srm.formulario.domain.enums.Riscos;

public class FormularioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres" )
	private String nomeCliente;
	
	
	private Double limiteCredito;
	
	private Integer taxaJuros;
	
	private Integer risco;
	
	public FormularioDTO() {
	}
	
	public FormularioDTO(Formulario obj) {
		this.id = obj.getId();
		this.nomeCliente = obj.getNomeCliente();
		this.limiteCredito = obj.getLimiteCredito();
		this.taxaJuros = obj.getTaxaJuros();
		this.risco = (obj.getRisco() == null) ? null : obj.getRisco().getCod();
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
	public void setRisco(Integer risco) {
		this.risco = risco;
	}

}
