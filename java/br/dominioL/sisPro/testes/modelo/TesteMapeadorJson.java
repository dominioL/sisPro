package br.dominioL.sisPro.testes.modelo;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.modelo.MapeadorObjetoJson;

public class TesteMapeadorJson {
	private ObjetoJson vazio;
	
	@Before
	public void cirarFiguracao() {
		vazio = Json.criarObjeto();
	}

	@Test
	public void criarMapeador() {
		MapeadorObjetoJson mapeador = MapeadorObjetoJson.criar();
		assertThat(MapeadorObjetoJson.criar(), not(is(sameInstance(mapeador))));
	}

	@Test
	public void mapearObjetoVazio() {
		MapeadorObjetoJson mapeador = MapeadorObjetoJson.criar();
		assertThat(mapeador.mapear(vazio), not(is(sameInstance(vazio))));
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}
}
