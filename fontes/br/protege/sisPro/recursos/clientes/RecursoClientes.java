package br.protege.sisPro.recursos.clientes;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.modelo.feijao.Cliente;
import br.protege.sisPro.recursos.ModeloDeRecursoDePaginaHtml;
import br.protege.sisPro.recursos.ModeloDeRecursoPostadoOuColocadoNoCouch;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/clientes")
public final class RecursoClientes {
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtml() {
		return new ModeloDeRecursoDePaginaHtml().obterHtml(ArquivoHtml.CLIENTES, ArquivoHtml.MENU_CLIENTES);
	}
	
	@POST
	@Produces(TipoDeMidia.JSON)
	@Consumes(TipoDeMidia.JSON)
	public Response postarJson(Cliente pessoa) {
		pessoa.fixarIdentificadoresDosEndereços(null);
		return new ModeloDeRecursoPostadoOuColocadoNoCouch().postarJson(pessoa);
	}
}
