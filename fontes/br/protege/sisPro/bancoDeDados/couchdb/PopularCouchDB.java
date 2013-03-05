package br.protege.sisPro.bancoDeDados.couchdb;

public final class PopularCouchDB {
	
	public static void main(String[] args) {
		popularBancoDeDados();
	}

	private static void popularBancoDeDados() {
		ConectorDoCouchDb.fornecerConector().createDatabaseIfNotExists();
	}
}
