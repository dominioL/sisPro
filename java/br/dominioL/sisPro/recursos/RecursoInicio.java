package br.dominioL.sisPro.recursos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.conexaoH.TiposDeMidia;
import br.dominioL.sisPro.dominio.interno.Arquivo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/inicio")
public final class RecursoInicio extends Recurso {
	@GET
	@Produces(TiposDeMidia.HTML)
	public Response obterHtml() {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.HTML, Arquivo.PRINCIPAL.fornecerArquivo());
	}
}
