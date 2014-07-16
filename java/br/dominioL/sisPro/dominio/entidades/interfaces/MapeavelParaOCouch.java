package br.dominioL.sisPro.dominio.entidades.interfaces;

import br.dominioL.sisPro.dados.couch.repositorios.RepositorioCouch;
import br.dominioL.sisPro.dominio.entidades.Entidade;

public interface MapeavelParaOCouch<T extends Entidade<T>> {
	public RepositorioCouch<T> fornecerRepositorio();
}
