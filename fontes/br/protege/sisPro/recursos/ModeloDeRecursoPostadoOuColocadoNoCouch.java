package br.protege.sisPro.recursos;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.ektorp.UpdateConflictException;
import org.json.JSONObject;

import br.opp.pacoteHttp.baseHttp.cabecalho.CodigoDeEstado;
import br.protege.sisPro.bancoDeDados.couchdb.ConectorDoCouchDb;
import br.protege.sisPro.modelo.validacao.MensagemDeErro;
import br.protege.sisPro.modelo.validacao.Validador;
import br.protege.sisPro.utilidades.TipoDeMidia;

public final class ModeloDeRecursoPostadoOuColocadoNoCouch {
	
	public Response postarJson(RecursoAbstrato recurso) {
		lançarExeçãoDeErroDeValidaçãoSeNecessário(recurso, recurso.validarCadastro());
		try {
			ConectorDoCouchDb.fornecerConector().create(recurso);
		} catch (UpdateConflictException erro) {
			lançarExeçãoDeErroInernoNoCouchDb();
		}
		return fornecerResposta(CodigoDeEstado.HTTP201, recurso);
	}
	

	public Response colocarJson(RecursoAbstrato recurso) {
		lançarExeçãoDeErroDeValidaçãoSeNecessário(recurso, recurso.validarAtualização());
		try {
			ConectorDoCouchDb.fornecerConector().update(recurso);
		} catch (UpdateConflictException erro) {
			lançarExeçãoDeErroInernoNoCouchDb();
		}
		return fornecerResposta(CodigoDeEstado.HTTP200, recurso);
	}
	
	private Response fornecerResposta(CodigoDeEstado códgioDeEstado, RecursoAbstrato recurso) {
		JSONObject recipiente = new JSONObject();
		Response resposta = Response
				.status(códgioDeEstado.fornecerComoNúmero())
				.type(TipoDeMidia.JSON)
				.entity(recurso.fornecerComoRecursoJson(recipiente).toString().toString())
				.build();
		return resposta;
	}
	
	private void lançarExeçãoDeErroDeValidaçãoSeNecessário(RecursoAbstrato recurso, Validador validador) {
		if (validador.possuiErro()) {
			throw new WebApplicationException(Response
					.status(CodigoDeEstado.HTTP400.fornecerComoNúmero())
					.type(TipoDeMidia.JSON)
					.entity(validador.fornecerMensagemDeErro().fornecerComoJson().toString())
					.build());
		}
	}
	
	private void lançarExeçãoDeErroInernoNoCouchDb() {
		throw new WebApplicationException(Response
				.status(CodigoDeEstado.HTTP500.fornecerComoNúmero())
				.type(TipoDeMidia.JSON)
				.entity(new MensagemDeErro().fornecerMensagemDeFracassoComoJson().toString())
				.build());
	}
}
