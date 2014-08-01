package br.dominioL.sisPro.dominio.mapeadores.objetoJson.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public class TratadorDeNaoMapeadosQueIgnora extends TratadorDeNaoMapeadosAbstrato implements TratadorDeNaoMapeados {
	public TratadorDeNaoMapeadosQueIgnora() {
		super(null);
	}

	@Override
	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado) {
		return mapeado;
	}
}
