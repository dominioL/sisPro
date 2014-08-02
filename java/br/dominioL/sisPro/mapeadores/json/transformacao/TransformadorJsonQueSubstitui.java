package br.dominioL.sisPro.mapeadores.json.transformacao;

import br.dominioL.estruturados.json.ValorJson;

public final class TransformadorJsonQueSubstitui implements TransformadorJson {
	private ValorJson novoValor;

	public TransformadorJsonQueSubstitui(ValorJson novoValor) {
		this.novoValor = novoValor;
	}

	@Override
	public ValorJson transformar(ValorJson valor) {
		return novoValor;
	}
}
