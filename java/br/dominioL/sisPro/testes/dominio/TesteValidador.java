package br.dominioL.sisPro.testes.dominio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.dominio.Validador;

public final class TesteValidador {
	private ObjetoJson pessoa;

	@Before
	public void dadoQue() {
		pessoa = Json.criarObjeto();
	}

	@Test
	public void validarCampoComCampo() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		Validador validador = Validador.criar(pessoa)
			.comCampo("nome", Validador.NOME);
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void validarCampoSemCampo() {
		Validador validador = Validador.criar(pessoa)
			.comCampo("nome", Validador.NOME);
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void validarCampoObrigatorioComCampo() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		Validador validador = Validador.criar(pessoa)
			.comCampoObrigatorio("nome", Validador.NOME);
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void validarCampoObrigatorioSemCampo() {
		Validador validador = Validador.criar(pessoa)
			.comCampoObrigatorio("nome", Validador.NOME);
		Assert.assertFalse(validador.validar());
	}

	@Test
	public void naoPermitirOutrosCamposSemOutrosCampos() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		Validador validador = Validador.criar(pessoa)
			.comCampoObrigatorio("nome", Validador.NOME)
			.naoPermitirOutrosCampos();
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void naoPermitirOutrosCamposComOutrosCampos() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		pessoa.inserir(Json.criarIdentificador("outroCampo"), Json.criarTexto("qualquer"));
		Validador validador = Validador.criar(pessoa)
			.comCampoObrigatorio("nome", Validador.NOME)
			.naoPermitirOutrosCampos();
		Assert.assertFalse(validador.validar());
	}
}
