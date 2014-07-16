package br.dominioL.sisPro.dados.couch.repositorios;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.TextoJson;
import br.dominioL.sisPro.dominio.entidades.Cliente;

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

//	public Mapeador fornecerMapeadorDeDocumentoParaEntidade() {
//		return Mapeador.criar()
//			.renomearCampoPara("id", "identificador")
//			.renomearCampoPara("rev", "revisao")
//			.someneteComOsCampos("nome", "identificador", "revisao")
//			.comTodosOsCamposMenos("ok")
//			.adicioanarCampo("uri", "/clientes")
//			.mapear(documento);
//	}
//
//	public MapeadorDeEntidadeParaDocumento fornecerMapeadorDeEntidadeParaDocumento() {
//		return Mapeador.criar()
//			.renomearCampoPara("identificador", "id")
//			.renomearCampoPara("revisao", "rev")
//			.someneteComOsCampos("nome", "identificador", "revisao")
//			.comTodosOsCamposMenos("ok")
//			.adicioanarCampo("tipo", "cliente")
//			.mapear(documento);
//	}

	@Override
	public void popular() {}
}
