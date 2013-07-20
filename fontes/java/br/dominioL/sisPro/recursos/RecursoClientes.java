package br.dominioL.sisPro.recursos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.conexaoH.TiposDeMidia;

import br.dominioL.estruturados.json.Json;

import br.dominioL.sisPro.interno.Arquivo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/clientes")
public final class RecursoClientes extends Recurso {
	@GET
	@Path("/cadastro")
	@Produces(TiposDeMidia.HTML)
	public Response obterHtml() {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.HTML, Arquivo.PRINCIPAL.fornecerArquivo());
	}

	@POST
	@Produces(TiposDeMidia.JSON)
	@Consumes(TiposDeMidia.JSON)
	public Response postarJson(String dados) {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.JSON, Json.criarObjeto().fornecerComoTextoJson());
	}
}
