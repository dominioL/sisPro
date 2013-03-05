package br.protege.sisPro.recursos;

import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.opp.pacoteHttp.baseHttp.cabecalho.CodigoDeEstado;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/")
public final class RecursoRaiz {

//	GET: 	obter
//	PUT:	colocar
//	DELETE:	remover
//	POST:	postar
	
	@GET
	@Produces(TipoDeMidia.TXT)
	public Response obterTxt() {
		Response resposta = Response
				.status(CodigoDeEstado.HTTP200.fornecerComoNúmero())
				.entity("Bem vindo ao Sispro.")
				.build();
		
		return resposta;
	}
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtml() throws URISyntaxException {
		return Response.seeOther(UriBuilder.fromResource(RecursoInicio.class).build()).build();
	}
}
