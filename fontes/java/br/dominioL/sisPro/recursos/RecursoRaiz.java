package br.dominioL.sisPro.recursos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.conexaoH.TiposDeMidia;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public final class RecursoRaiz {
	@GET
	@Produces(TiposDeMidia.TEXTO)
	public Response fornecerTexto() {
		return Response
			.status(CodigoDeEstado.HTTP_200.comoNumero())
			.type(TipoDeMidia.TEXTO.comoTexto())
			.entity("SisPro")
			.build();
	}
}
