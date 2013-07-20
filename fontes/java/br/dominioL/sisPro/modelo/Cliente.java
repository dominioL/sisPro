package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.json.ObjetoJson;

public final class Cliente extends Entidade {
	public Cliente(ObjetoJson dados) {
		super(dados);
	}

	@Override
	public Boolean validar() {
		return true;
		//TODO
	}
}
