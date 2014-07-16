package br.dominioL.sisPro.recursos;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.dominioL.sisPro.dominio.Criador;
import br.dominioL.sisPro.dominio.entidades.Cliente;
import br.dominioL.sisPro.dominio.http.CodigoDeEstado;
import br.dominioL.sisPro.dominio.http.TipoDeMidia;
import br.dominioL.sisPro.dominio.http.TiposDeMidia;
import br.dominioL.sisPro.dominio.interno.Arquivo;

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
		Cliente cliente = new Cliente();
		Criador<Cliente> criador = Criador.criar(cliente, RecursoCliente.class);
		criador.fixarDados(dados);
		criador.validar();
		criador.salvar();
		return criador.fornecerResposta();
	}
}
