package br.dominioL.sisPro.infraestrutura.dados.couchDb;


public interface MapeavelParaOCouchDb<T> {

	public RepositorioCouchDb<T> fornecerRepositorio();

}