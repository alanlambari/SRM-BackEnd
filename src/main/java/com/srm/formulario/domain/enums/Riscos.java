package com.srm.formulario.domain.enums;

public enum Riscos {
	
	RISCO_A (1, "A"),
	RISCO_B (2, "B"),
	RISCO_C (3, "C");
	
	private int cod;
	private String descricao;
	
	private Riscos(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Riscos toEnum (Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Riscos tc : Riscos.values()) {
			if(cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}