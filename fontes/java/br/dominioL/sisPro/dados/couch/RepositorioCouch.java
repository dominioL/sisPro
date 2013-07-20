package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.ObjetoJson;

public interface RepositorioCouch {
	public RespostaCouch inserir(ObjetoJson documento);

	public RespostaCouch remover(String identificador);

	public void popular();
}
