package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.TextoJson;

import br.dominioL.sisPro.modelo.Cliente;

public final class RepositorioDeClientes extends RepositorioCouch<Cliente> {
	private static RepositorioDeClientes INSTANCIA;
	private static final String TIPO = "cliente";

	private RepositorioDeClientes() {}

	public static RepositorioDeClientes fornecerInstancia() {
		return (INSTANCIA == null) ? (INSTANCIA = new RepositorioDeClientes()) : INSTANCIA;
	}

	@Override
	public TextoJson fornecerTipo() {
		return Json.criarTexto(TIPO);
	}

	@Override
	public void popular() {}
}
