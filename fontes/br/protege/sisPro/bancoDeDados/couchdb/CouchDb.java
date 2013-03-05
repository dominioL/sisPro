package br.protege.sisPro.bancoDeDados.couchdb;

import java.util.List;

import br.protege.sisPro.bancoDeDados.TipoBancoDeDados;

public final class CouchDb implements TipoBancoDeDados<PersistivelNoCouchDb> {
	
	private static CouchDb INSTÂNCIA;

	private CouchDb() {
		
	}
	
	public static CouchDb fornecerInstância() {
		return (INSTÂNCIA == null) ? (INSTÂNCIA = new CouchDb()) : INSTÂNCIA; 
	}

	@Override
	public boolean adicionar(PersistivelNoCouchDb dado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(PersistivelNoCouchDb dado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PersistivelNoCouchDb fornecer(PersistivelNoCouchDb dado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersistivelNoCouchDb> fornecerLista(PersistivelNoCouchDb dado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean salvar(PersistivelNoCouchDb dado) {
		// TODO Auto-generated method stub
		return false;
	}
}
