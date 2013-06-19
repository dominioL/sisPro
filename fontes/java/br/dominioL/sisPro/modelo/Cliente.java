package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.json.ObjetoJson;

public final class Cliente {
	private ObjetoJson dados;

	public Cliente(ObjetoJson dados) {
		this.dados = dados;
	}

	public Boolean validarDados() {
		return true;
		//TODO
	}

	public ObjetoJson fornecerComoJson() {
		return dados;
	}
}
