package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.ObjetoJson;

import br.dominioL.sisPro.modelo.Entidade;

public interface RepositorioCouch<T extends Entidade> {
	public RespostaCouch inserir(T entidade);

	public RespostaCouch remover(String identificador);

	public void popular();
}
