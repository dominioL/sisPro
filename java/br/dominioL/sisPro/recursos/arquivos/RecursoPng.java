package br.dominioL.sisPro.recursos.arquivos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.conexaoH.TiposDeMidia;
import br.dominioL.sisPro.dominio.interno.Arquivo;
import br.dominioL.sisPro.recursos.Recurso;
import br.dominioL.sisPro.recursos.abstratos.RecursoArquivo;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/png/{nome: .+}")
public final class RecursoPng extends Recurso {
	@GET
	@Produces(TiposDeMidia.PNG)
	public Response obterJpg(@PathParam("nome") String nome) {
		File arquivo = Arquivo.PNG.fornecerArquivo(nome);
		return new RecursoArquivo().obterArquivo(arquivo, CodigoDeEstado.HTTP_200, TipoDeMidia.PNG);
	}
}
