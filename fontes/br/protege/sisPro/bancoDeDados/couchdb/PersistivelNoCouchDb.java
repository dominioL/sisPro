package br.protege.sisPro.bancoDeDados.couchdb;

import br.protege.sisPro.bancoDeDados.Persistivel;

public interface PersistivelNoCouchDb extends Persistivel {
	
	public String fornecer_id();
	
	public void fixar_id(String identificador);
	
	public String fornecer_rev();
	
	public void fixar_rev(String revisão);
}
