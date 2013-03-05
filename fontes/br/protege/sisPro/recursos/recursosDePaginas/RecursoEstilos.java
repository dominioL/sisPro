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

@Path("/estilos/{nomeDoEstilo}")
public final class RecursoEstilos {
	
	private static final String EXTENSÃO_CSS = ".css";
	
	@GET
	@Produces(TipoDeMidia.CSS)
	public Response obterCss(@PathParam("nomeDoEstilo") String nomeDoEstilo) {
		File arquivoDoEstilo = new File(Pasta.CSS+nomeDoEstilo+EXTENSÃO_CSS);
		if (!arquivoDoEstilo.exists()) {
			throw new WebApplicationException(CodigoDeEstado.HTTP404.fornecerComoNúmero());
		}
		Response resposta = Response
				.status(CodigoDeEstado.HTTP200.fornecerComoNúmero())
				.type(TipoDeMidia.CSS)
				.entity(arquivoDoEstilo)
				.build();
		return resposta;
	}
}
