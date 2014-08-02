package br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public final class TratadorDeNaoMapeadosQueIgnora extends TratadorDeNaoMapeadosAbstrato implements TratadorDeNaoMapeados {
	public TratadorDeNaoMapeadosQueIgnora() {
		super(null);
	}

	@Override
	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado) {
		return mapeado;
	}
}
