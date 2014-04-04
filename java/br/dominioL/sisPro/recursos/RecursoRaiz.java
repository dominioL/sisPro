package br.dominioL.sisPro.recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.conexaoH.TiposDeMidia;

@Path("/")
public final class RecursoRaiz extends Recurso {
	@GET
	@Produces(TiposDeMidia.TEXTO)
	public Response obterTexto() {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.TEXTO, "SisPro");
	}

	@GET
	@Produces(TiposDeMidia.HTML)
	public Response obterHtml() {
		return Response.seeOther(UriBuilder.fromResource(RecursoInicio.class).build()).build();
	}
}
