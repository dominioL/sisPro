package br.dominioL.sisPro.infraestrutura.dados.couchDb;

import br.dominioL.sisPro.infraestrutura.dominio.TipoDeDocumento;

public abstract class RepositorioCouchDb<T> {

	public final RespostaCouchDb inserir(T entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public final RespostaCouchDb colocar(T entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public final RespostaCouchDb obter(String identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	public final RespostaCouchDb remover(String identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract TipoDeDocumento fornecerTipo();

	public abstract void popular();

}
