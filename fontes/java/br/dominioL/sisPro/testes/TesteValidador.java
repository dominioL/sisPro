package br.dominioL.sisPro.testes;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

import br.dominioL.sisPro.modelo.Validador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public final class TesteValidador {
	private ObjetoJson pessoa;

	@Before
	public void dadoQue() {
		pessoa = Json.criarObjeto();
	}

	@Test
	public void validarCampoComCampo() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		Validador validador = new Validador(pessoa)
			.validarCampo("nome", Validador.NOME);
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void validarCampoSemCampo() {
		Validador validador = new Validador(pessoa)
			.validarCampo("nome", Validador.NOME);
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void validarCampoObrigatorioComCampo() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		Validador validador = new Validador(pessoa)
			.validarCampoObrigatorio("nome", Validador.NOME);
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void validarCampoObrigatorioSemCampo() {
		Validador validador = new Validador(pessoa)
			.validarCampoObrigatorio("nome", Validador.NOME);
		Assert.assertFalse(validador.validar());
	}

	@Test
	public void naoPermitirOutrosCamposSemOutrosCampos() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		Validador validador = new Validador(pessoa)
			.validarCampoObrigatorio("nome", Validador.NOME)
			.naoPermitirOutrosCampos();
		Assert.assertTrue(validador.validar());
	}

	@Test
	public void naoPermitirOutrosCamposComOutrosCampos() {
		pessoa.inserir(Json.criarIdentificador("nome"), Json.criarTexto("Lucas Pereira"));
		pessoa.inserir(Json.criarIdentificador("outroCampo"), Json.criarTexto("qualquer"));
		Validador validador = new Validador(pessoa)
			.validarCampoObrigatorio("nome", Validador.NOME)
			.naoPermitirOutrosCampos();
		Assert.assertFalse(validador.validar());
	}
}
