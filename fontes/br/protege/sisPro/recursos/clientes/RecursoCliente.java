package br.protege.sisPro.recursos.clientes;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.modelo.feijao.Cliente;
import br.protege.sisPro.recursos.ModeloDeRecursoObtidoNoCouchDb;
import br.protege.sisPro.recursos.ModeloDeRecursoPostadoOuColocadoNoCouch;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/clientes/{identificador}")
public final class RecursoCliente {
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtml(@PathParam("identificador") String identificador) {
		return new ModeloDeRecursoObtidoNoCouchDb().obterHtml(Cliente.class, identificador, ArquivoHtml.CLIENTE, ArquivoHtml.MENU_CLIENTE, ArquivoHtml.CLIENTE_NÃO_ENCONTRADO);
	}
	
	@PUT
	@Produces(TipoDeMidia.JSON)
	@Consumes(TipoDeMidia.JSON)
	public Response colocarJson(@PathParam("identificador") String identificador, Cliente pessoa) {
		pessoa.fixar_id(identificador);
		return new ModeloDeRecursoPostadoOuColocadoNoCouch().colocarJson(pessoa);
	}
	
	public Cliente obterClasse(String identificador) {
		return new ModeloDeRecursoObtidoNoCouchDb().obterClasse(Cliente.class, identificador, ArquivoHtml.CLIENTE, ArquivoHtml.MENU_CLIENTE, ArquivoHtml.CLIENTE_NÃO_ENCONTRADO);
	}
}
