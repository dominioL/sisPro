package br.dominioL.sisPro.dominio.mapeadores.objetoJson.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public interface TratadorDeNaoMapeados {
	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado);
}
