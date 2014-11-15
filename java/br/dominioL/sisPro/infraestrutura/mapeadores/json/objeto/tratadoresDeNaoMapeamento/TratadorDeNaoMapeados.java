package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public interface TratadorDeNaoMapeados {

	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado);

}
