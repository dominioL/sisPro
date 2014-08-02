package br.dominioL.sisPro.recursos.clientes;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.dominioL.sisPro.http.CodigoDeEstado;
import br.dominioL.sisPro.http.TipoDeMidia;
import br.dominioL.sisPro.http.TiposDeMidia;
import br.dominioL.sisPro.recursos.Recurso;
import br.dominioL.sisPro.recursos.arquivos.Arquivo;

@Path("/cliente/{identificador}")
public final class RecursoCliente extends Recurso {
	@GET
	@Produces(TiposDeMidia.HTML)
	public Response obterHtml(@PathParam("identificador") String identificador) {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.HTML, Arquivo.PRINCIPAL.fornecerArquivo());
	}

	@GET
	@Produces(TiposDeMidia.JSON)
	public Response obterJson(@PathParam("identificador") String identificador) {
		// TODO
		return null;
	}

	@PUT
	@Produces(TiposDeMidia.JSON)
	@Consumes(TiposDeMidia.JSON)
	public Response colocarJson(@PathParam("identificador") String identificador, String dados) {
		// RepositorioDeClientes repositorio = RepositorioDeClientes.fornecerInstancia();
		// Cliente cliente = new Cliente();
		// return postarEntidadeJson(repositorio, cliente, dados);
		// TODO
		return null;
	}
}
