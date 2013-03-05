package br.protege.sisPro.recursos.enderecos;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.opp.pacoteHttp.baseHttp.cabecalho.CodigoDeEstado;
import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.modelo.feijao.Cliente;
import br.protege.sisPro.modelo.feijao.Endereco;
import br.protege.sisPro.modelo.validacao.MensagemDeErro;
import br.protege.sisPro.recursos.ModeloDeRecursoObtidoNoCouchDb;
import br.protege.sisPro.recursos.ModeloDeRecursoPostadoOuColocadoNoCouch;
import br.protege.sisPro.recursos.clientes.RecursoCliente;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/enderecos/{identificador}")
public final class RecursoEndereco {
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtml(@PathParam("identificador") String identificador) {
		return new ModeloDeRecursoObtidoNoCouchDb().obterHtml(Endereco.class, identificador, ArquivoHtml.ENDEREÇO, ArquivoHtml.MENU_ENDERECO, ArquivoHtml.ENDEREÇO_NÃO_ENCONTRADO);
	}
	
	@PUT
	@Consumes(TipoDeMidia.JSON)
	@Produces(TipoDeMidia.JSON)
	public Response colocarJson(Endereco endereço) {
		Cliente cliente = new RecursoCliente().obterClasse(endereço.fornecerIdentificadorDoCliente());
		if (!cliente.fornecerIdentificadoresDosEndereços().contains(endereço.fornecer_id())) {
			throw new WebApplicationException(Response
					.status(CodigoDeEstado.HTTP409.fornecerComoNúmero())
					.type(TipoDeMidia.JSON)
					.entity(new MensagemDeErro().fornecerMensagemDeRecursoPaiNãoModificável())
					.build());
		}
		return new ModeloDeRecursoPostadoOuColocadoNoCouch().colocarJson(endereço);
	}
}
