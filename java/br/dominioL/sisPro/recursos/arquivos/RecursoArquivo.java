package br.dominioL.sisPro.recursos.arquivos;

import java.io.File;

import javax.ws.rs.core.Response;

import br.dominioL.sisPro.infraestrutura.http.CodigoDeEstado;
import br.dominioL.sisPro.infraestrutura.http.ConstrutorDeResposta;
import br.dominioL.sisPro.infraestrutura.http.TipoDeMidia;

public final class RecursoArquivo {

	public Response obter(File arquivo, TipoDeMidia tipoDeMidia) {
		if (!arquivo.exists()) {
			return CodigoDeEstado.HTTP_404.fornecerResposta().construir();
		}
		return ConstrutorDeResposta
				.codigoDeEstado(CodigoDeEstado.HTTP_200)
				.tipoDeMidia(tipoDeMidia)
				.entidade(arquivo)
				.construir();
	}

}