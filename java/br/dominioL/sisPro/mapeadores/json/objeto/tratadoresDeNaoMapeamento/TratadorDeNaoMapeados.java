package br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public interface TratadorDeNaoMapeados {
	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado);
}
