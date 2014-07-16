package br.dominioL.sisPro.testes.recursos;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.startsWith;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.SisPro;
import br.dominioL.sisPro.dominio.http.Atributo;
import br.dominioL.sisPro.dominio.http.CodigoDeEstado;
import br.dominioL.sisPro.dominio.http.TipoDeMidia;

public class TesteClientes {
	private ObjetoJson cliente;

	@Before
	public void criarFigurantes() {
		cliente = Json.criarObjeto();
		cliente.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		cliente.inserir(Json.criarIdentificador("telefones"), Json.criarLista());
		cliente.inserir(Json.criarIdentificador("enderecosEletronicos"), Json.criarLista());
	}

	@Test
	public void postarJson() {
		given()
			.port(7000)
			.contentType(TipoDeMidia.JSON.comoTexto())
			.content(cliente.comoTextoJson())
		.when()
			.post(SisPro.uri().caminho("/clientes").construirRelativo())
		.then()
			.statusCode(CodigoDeEstado.HTTP_201.comoNumero())
			.header(Atributo.LOCATION.comoTexto(), startsWith(SisPro.uri().caminho("/cliente/").construirAbsoluto()))
			.contentType(startsWith(TipoDeMidia.JSON.comoTextoSemCharset()))
			.content("", allOf(hasKey("identificador"), hasKey("revisao")));
	}
}
