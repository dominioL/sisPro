package br.protege.sisPro.recursos;

import java.util.List;

import javax.ws.rs.core.Response;

public final class ModeloDeRecursoPostadoOuColocadoEmColecaoNoCouch {
	
	public Response postarJson(RecursoAbstrato recurso, RecursoAbstrato recursoColeção, List<String> coleção) {
		Response resposta = new ModeloDeRecursoPostadoOuColocadoNoCouch().postarJson(recurso);
		coleção.add(recurso.fornecer_id());
		new ModeloDeRecursoPostadoOuColocadoNoCouch().colocarJson(recursoColeção);
		return resposta;
	}

	public Response colocarJson(RecursoAbstrato recurso, RecursoAbstrato recursoColeção, List<String> coleção) {
		coleção.add(recurso.fornecer_id());
		Response resposta = new ModeloDeRecursoPostadoOuColocadoNoCouch().colocarJson(recurso);
		new ModeloDeRecursoPostadoOuColocadoNoCouch().colocarJson(recursoColeção);
		return resposta;
	}
}
