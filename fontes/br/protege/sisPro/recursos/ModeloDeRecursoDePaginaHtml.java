package br.protege.sisPro.recursos;

import java.io.File;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import br.opp.pacoteHttp.baseHttp.cabecalho.CodigoDeEstado;
import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.estruturaInterna.Pasta;
import br.protege.sisPro.utilidades.ModeladorDePagina;
import br.protege.sisPro.utilidades.TipoDeMidia;

public final class ModeloDeRecursoDePaginaHtml {
	
	public Response obterHtml(String nomeDaPágina, String nomeDoMenu) {
		return obterHtml(nomeDaPágina, nomeDoMenu, CodigoDeEstado.HTTP200);
	}
	
	public Response obterHtml(String nomeDaPágina, String nomeDoMenu, JSONObject informaçãoJson) {
		ModeladorDePagina página = prepararPágina(nomeDaPágina, nomeDoMenu);
		página.fixarInformaçõesJson(informaçãoJson);
		return prepararResposta(página.fornecerPáginaMoldada(), CodigoDeEstado.HTTP200);
	} 
	
	public Response obterHtml(String nomeDaPágina, String nomeDoMenu, CodigoDeEstado códigoDeEstado) {
		ModeladorDePagina página = prepararPágina(nomeDaPágina, nomeDoMenu);
		return prepararResposta(página.fornecerPáginaMoldada(), códigoDeEstado);
	}
	
	private Response prepararResposta(Object entidade, CodigoDeEstado códigoDeEstado) {
		Response resposta = Response
				.status(códigoDeEstado.fornecerComoNúmero())
				.type(TipoDeMidia.HTML)
				.entity(entidade)
				.build();
		return resposta;
	}
	
	private ModeladorDePagina prepararPágina(String nomeDePágina, String nomeDoMenu) {
		File arquivoDaPágina = new File(Pasta.HTML+nomeDePágina);
		verificarSePáginaExiste(arquivoDaPágina);
		ModeladorDePagina página = ModeladorDePagina.fornecerMoldeDePáginaInicial();
		página.fixarConteúdo(nomeDePágina);
		página.fixarMenuTemático(nomeDoMenu);
		return página;
	}
	
	private void verificarSePáginaExiste(File arquivoDaPágina) {
		if (!arquivoDaPágina.exists()) {
			ModeladorDePagina modeladorDePágina = ModeladorDePagina.fornecerMoldeDePáginaInicial();
			modeladorDePágina.fixarConteúdo(ArquivoHtml.PAGINA_NAO_ENCONTRADA);
			throw new WebApplicationException(Response
					.status(CodigoDeEstado.HTTP404.fornecerComoNúmero())
					.type(TipoDeMidia.HTML)
					.entity(modeladorDePágina.fornecerPáginaMoldada())
					.build());
		}
	}
}
