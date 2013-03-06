package br.dominioL.sisPro.recursos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.conexaoH.TiposDeMidia;

import br.dominioL.sisPro.interno.Arquivo;
import br.dominioL.sisPro.recursos.abstratos.RecursoArquivo;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/js/{nome}")
public final class RecursoJs extends Recurso {
	@GET
	@Produces(TiposDeMidia.JS)
	public Response obterCss(@PathParam("nome") String nome) {
		File arquivo = Arquivo.JS.fornecerArquivo(nome);
		return new RecursoArquivo().obterArquivo(arquivo, CodigoDeEstado.HTTP_200, TipoDeMidia.JS);
	}
}
