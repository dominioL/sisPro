package br.protege.sisPro.recursos.recursosDePaginas;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.opp.pacoteHttp.baseHttp.cabecalho.CodigoDeEstado;
import br.protege.sisPro.estruturaInterna.Pasta;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/scripts/{nomeDoScript}")
public final class RecursoScripts {
	
	private static final String EXTENSÃO_JS = ".js";
	
	@GET
	@Produces(TipoDeMidia.JS)
	public Response obterJs(@PathParam("nomeDoScript") String nomeDoScript) {
		File arquivoDoEstilo = new File(Pasta.JS+nomeDoScript+EXTENSÃO_JS);
		if (!arquivoDoEstilo.exists()) {
			throw new WebApplicationException(CodigoDeEstado.HTTP404.fornecerComoNúmero());
		}
		Response resposta = Response
				.status(CodigoDeEstado.HTTP200.fornecerComoNúmero())
				.type(TipoDeMidia.JS)
				.entity(arquivoDoEstilo)
				.build();
		return resposta;
	}
}
