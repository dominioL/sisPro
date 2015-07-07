package br.dominioL.sisPro.infraestrutura.dominio;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum TipoDeDocumento {

	CLIENTE("cliente");

	private Texto tipo;

	private TipoDeDocumento(String tipo) {
		this.tipo = Texto.criar(tipo);
	}

	public Texto comoTexto() {
		return tipo;
	}

}