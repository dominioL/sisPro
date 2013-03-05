package br.protege.sisPro.recursos;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.ektorp.DocumentNotFoundException;
import org.json.JSONObject;

import br.opp.pacoteHttp.baseHttp.cabecalho.CodigoDeEstado;
import br.protege.sisPro.bancoDeDados.couchdb.ConectorDoCouchDb;
import br.protege.sisPro.estruturaInterna.ArquivoHtml;

public final class ModeloDeRecursoObtidoNoCouchDb {
	
	public Response obterHtml(Class<? extends RecursoAbstrato> classeDoRecurso, String identificadorDoRecursoNoCouchDb, String nomeDaPágina, String nomeDoMenu, String nomeDaPáginaDeRecursoNãoEncontrado) {
		RecursoAbstrato recurso = obter(classeDoRecurso, identificadorDoRecursoNoCouchDb, nomeDaPágina, nomeDoMenu, nomeDaPáginaDeRecursoNãoEncontrado);
		return new ModeloDeRecursoDePaginaHtml().obterHtml(nomeDaPágina, nomeDoMenu, recurso.fornecerComoRecursoJson(new JSONObject()));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends RecursoAbstrato> T obterClasse(Class<T> classeDoRecurso, String identificadorDoRecursoNoCouchDb, String nomeDaPágina, String nomeDoMenu, String nomeDaPáginaDeRecursoNãoEncontrado) {
		RecursoAbstrato recurso = obter(classeDoRecurso, identificadorDoRecursoNoCouchDb, nomeDaPágina, nomeDoMenu, nomeDaPáginaDeRecursoNãoEncontrado);
		return (T) recurso;
	}
	
	private RecursoAbstrato obter(Class<? extends RecursoAbstrato> classeDoRecurso, String identificadorDoRecursoNoCouchDb, String nomeDaPágina, String nomeDoMenu, String nomeDaPáginaDeRecursoNãoEncontrado) {
		RecursoAbstrato recurso = null;
		try {
			recurso = ConectorDoCouchDb.fornecerConector().get(classeDoRecurso, identificadorDoRecursoNoCouchDb);
		} catch (DocumentNotFoundException erro) {
			throw new WebApplicationException(new ModeloDeRecursoDePaginaHtml().obterHtml(nomeDaPáginaDeRecursoNãoEncontrado, ArquivoHtml.MENU_PADRÃO, CodigoDeEstado.HTTP404));		
		} catch (RuntimeException erro) {
			throw new WebApplicationException(new ModeloDeRecursoDePaginaHtml().obterHtml(ArquivoHtml.ERRO_INTERNO, ArquivoHtml.MENU_PADRÃO, CodigoDeEstado.HTTP500));
		}
		return recurso;
	}
}
