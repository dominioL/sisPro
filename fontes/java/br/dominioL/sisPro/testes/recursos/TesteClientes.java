package br.dominioL.sisPro.testes.recursos;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

public class TesteClientes {
	private ObjetoJson cliente;

	@Before
	public void criarFigurantes() {
		cliente = Json.criarObjeto();
		cliente.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		cliente.inserir(Json.criarIdentificador("telefones"), Json.criarLista());
		cliente.inserir(Json.criarIdentificador("enderecoesEletronicos"), Json.criarLista());
	}

	@Test
	public void postarJson() {
		given()
			.port(7000)
			.contentType(TipoDeMidia.JSON.comoTexto())
			.content(cliente.comoTextoJson())
		.when()
			.post("/clientes")
		.then()
			.statusCode(CodigoDeEstado.HTTP_200.comoNumero())
			.contentType(TipoDeMidia.JSON.comoTexto())
			.assertThat()
				.content(hasKey("identificador"))
				.content(hasKey("revisao"));
	}
}
