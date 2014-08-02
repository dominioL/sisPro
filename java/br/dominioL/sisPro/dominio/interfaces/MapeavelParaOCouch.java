package br.dominioL.sisPro.dominio.interfaces;

import br.dominioL.sisPro.dados.couch.RepositorioCouch;
import br.dominioL.sisPro.dominio.Entidade;

public interface MapeavelParaOCouch<T extends Entidade<T>> {
	public RepositorioCouch<T> fornecerRepositorio();
}
