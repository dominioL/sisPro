package br.dominioL.sisPro.infraestrutura.http;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum Metodo implements Igualavel<Metodo> {

	DELETE("DELETE"),
	GET("GET"),
	HEAD("HEAD"),
	POST("POST"),
	PUT("PUT");

	private Texto nome;

	private Metodo(String nome) {
		this.nome = Texto.criar(nome);
	}

	public Texto comoTexto() {
		return nome;
	}

	@Override
	public Booleano igual(Metodo outro) {
		return Booleano.mesmo(this, outro);
	}

}