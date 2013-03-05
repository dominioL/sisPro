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

@Path("/imagens")
public final class RecursoImagens {
	
	@GET
	@Path("{nomeDaImagem : .+[.png]$}")
	@Produces(TipoDeMidia.PNG)
	public Response obterPng(@PathParam("nomeDaImagem") String nomeDaImagem) {
		return obterImagem(nomeDaImagem, TipoDeMidia.PNG);
	}
	
	@GET
	@Path("{nomeDaImagem : .+[.gif]$}")
	@Produces(TipoDeMidia.GIF)
	public Response obterGif(@PathParam("nomeDaImagem") String nomeDaImagem) {
		return obterImagem(nomeDaImagem, TipoDeMidia.GIF);
	}
	
	private Response obterImagem(String nomeDaImagem, String tipoDeMídia) {
		File arquivoDaImagem = new File(Pasta.IMAGENS+nomeDaImagem);
		if (!arquivoDaImagem.exists()) {
			throw new WebApplicationException(CodigoDeEstado.HTTP404.fornecerComoNúmero());
		}
		Response resposta = Response
				.status(CodigoDeEstado.HTTP200.fornecerComoNúmero())
				.type(tipoDeMídia)
				.entity(arquivoDaImagem)
				.build();
		return resposta;
	}
}
