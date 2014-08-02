package br.dominioL.sisPro.http;

import br.dominioL.estruturados.elemento.Igualavel;

public enum Metodo implements Igualavel<Metodo> {
	DELETE("DELETE"),
	GET("GET"),
	HEAD("HEAD"),
	POST("POST"),
	PUT("PUT");

	private String nome;

	private Metodo(String nome) {
		this.nome = nome;
	}

	public String comoTexto() {
		return nome;
	}

	@Override
	public Boolean igual(Metodo outro) {
		return (this == outro);
	}
}
