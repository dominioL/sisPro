package br.protege.sisPro.recursos.enderecos;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.modelo.feijao.Cliente;
import br.protege.sisPro.modelo.feijao.Endereco;
import br.protege.sisPro.recursos.ModeloDeRecursoDePaginaHtml;
import br.protege.sisPro.recursos.ModeloDeRecursoPostadoOuColocadoEmColecaoNoCouch;
import br.protege.sisPro.recursos.clientes.RecursoCliente;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/enderecos")
public final class RecursoEnderecos {
	
	@GET
	@Produces(TipoDeMidia.HTML) 
	public Response obterHtml() {
		return new ModeloDeRecursoDePaginaHtml().obterHtml(ArquivoHtml.ENDERECOS, ArquivoHtml.MENU_ENDERECOS);
	}
	
	@POST
	@Consumes(TipoDeMidia.JSON)
	@Produces(TipoDeMidia.JSON) 
	public Response postarJson(Endereco endereço) {
		Cliente cliente = new RecursoCliente().obterClasse(endereço.fornecerIdentificadorDoCliente());
		List<String> identificadoresDosEndereços = cliente.fornecerIdentificadoresDosEndereços();
		if (identificadoresDosEndereços == null) {
			identificadoresDosEndereços = new LinkedList<String>();
			cliente.fixarIdentificadoresDosEndereços(identificadoresDosEndereços);
		}
		return new ModeloDeRecursoPostadoOuColocadoEmColecaoNoCouch().postarJson(endereço, cliente, identificadoresDosEndereços);
	}
}
