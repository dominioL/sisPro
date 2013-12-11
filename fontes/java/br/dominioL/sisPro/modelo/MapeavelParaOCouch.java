package br.dominioL.sisPro.modelo;

import br.dominioL.sisPro.dados.couch.RepositorioCouch;

public interface MapeavelParaOCouch<T extends Entidade<T>> {
	public RepositorioCouch<T> fornecerRepositorio();
}
