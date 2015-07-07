package br.dominioL.sisPro.recursos.raiz;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.dominioL.sisPro.infraestrutura.http.CodigoDeEstado;
import br.dominioL.sisPro.infraestrutura.http.TiposDeMidia;
import br.dominioL.sisPro.recursos.Recurso;

@Path("/")
public final class RecursoRaiz extends Recurso {

	@GET
	@Produces(TiposDeMidia.TEXTO)
	public Response obterTexto() {
		return CodigoDeEstado.HTTP_200.fornecerResposta().construir();
	}

}