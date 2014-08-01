package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.dominio.http.ConstrutorDeUri;

public final class RequisicaoCouch {
	private static final String DESIGN = "_design";
	private static final String VIEW = "_view";
	private ObjetoJson documento;
	private ConstrutorDeUri construtorDeUri;

	private RequisicaoCouch() {
		documento = Json.criarObjeto();
		construtorDeUri = Couch.fornecerInstancia().fornecerConstrutorDeUri();
	}

	public static RequisicaoCouch criar() {
		return new RequisicaoCouch();
	}

	public RequisicaoCouch comDocumento(ObjetoJson documento) {
		this.documento = documento;
		return this;
	}

	public RequisicaoCouch doRepositorio(String repositorio) {
		construtorDeUri.caminho(DESIGN).caminho(repositorio);
		return this;
	}

	public RequisicaoCouch daVisao(String visao) {
		construtorDeUri.caminho(VIEW).caminho(visao);
		return this;
	}

	public RequisicaoCouch comIdentificador(String identificador) {
		construtorDeUri.caminho(identificador);
		return this;
	}

	public ObjetoJson fornecerDocumento() {
		return documento;
	}

	public ConstrutorDeUri fornecerConstrutorDeUri() {
		return construtorDeUri;
	}
}