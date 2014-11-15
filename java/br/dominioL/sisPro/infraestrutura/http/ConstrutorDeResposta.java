package br.dominioL.sisPro.infraestrutura.http;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public class ConstrutorDeResposta {

	private ResponseBuilder resposta;

	private ConstrutorDeResposta(CodigoDeEstado codigoDeEstado) {
		resposta = Response.status(codigoDeEstado.comoNumero().inteiro());
	}

	public static ConstrutorDeResposta codigoDeEstado(CodigoDeEstado codigoDeEstado) {
		return new ConstrutorDeResposta(codigoDeEstado);
	}

	public ConstrutorDeResposta tipoDeMidia(TipoDeMidia tipoDeMidia) {
		resposta.type(tipoDeMidia.comoTexto().valor());
		return this;
	}

	public ConstrutorDeResposta entidade(Object entidade) {
		resposta.entity(entidade);
		return this;
	}

	public ConstrutorDeResposta localizacao(Texto uri) {
		resposta.header(Atributo.LOCATION.comoTexto().valor(), uri.valor());
		return this;
	}

	public Response construir() {
		return resposta.build();
	}

}
