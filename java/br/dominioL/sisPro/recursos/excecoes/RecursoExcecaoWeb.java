package br.dominioL.sisPro.recursos.excecoes;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.sisPro.infraestrutura.http.CodigoDeEstado;

@Provider
public final class RecursoExcecaoWeb implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException excecao) {
		Numero codigoDeEstadoLancado = Numero.criar(excecao.getResponse().getStatus());
		CodigoDeEstado codigoDeEstado = CodigoDeEstado.fornecerCodigoDeEstado(codigoDeEstadoLancado);
		return codigoDeEstado.fornecerResposta().construir();
	}

}