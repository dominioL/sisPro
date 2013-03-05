package br.protege.sisPro.recursos;

import br.protege.sisPro.bancoDeDados.couchdb.PersistivelNoCouchDb;
import br.protege.sisPro.modelo.json.RepresentavelComoRecursoJson;
import br.protege.sisPro.modelo.validacao.Validavel;

public abstract class RecursoAbstrato implements PersistivelNoCouchDb, Validavel, RepresentavelComoRecursoJson {
	
}
