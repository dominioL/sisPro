package br.dominioL.sisPro.recursos.abstratos;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.dominioL.sisPro.http.CodigoDeEstado;

@Provider
public final class RecursoExcecaoJava implements ExceptionMapper<Exception> {
	@Override
	public Response toResponse(Exception excecao) {
		return CodigoDeEstado.HTTP_500.fornecerResposta();
	}
}
