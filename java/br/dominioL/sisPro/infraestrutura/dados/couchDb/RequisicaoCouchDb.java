package br.dominioL.sisPro.infraestrutura.dados.couchDb;

import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.ambiente.Ambiente;
import br.dominioL.sisPro.infraestrutura.http.ConstrutorDeUri;

public final class RequisicaoCouchDb {

	private static final Texto DESIGN = Texto.criar("_design");
	private static final Texto VIEW = Texto.criar("_view");

	private ObjetoJson documento;
	private ConstrutorDeUri construtorDeUri;

	private RequisicaoCouchDb(Ambiente<?> ambiente) {
		construtorDeUri = ambiente.construtorDeUriParaBancoDeDados();
	}

	public static RequisicaoCouchDb criar(Ambiente<?> ambiente) {
		return new RequisicaoCouchDb(ambiente);
	}

	private RequisicaoCouchDb(ConstrutorDeUri construtorDeUri) {
		documento = ConstrutorJson.deObjeto().construir();
		this.construtorDeUri = construtorDeUri;
	}

	public RequisicaoCouchDb comDocumento(ObjetoJson documento) {
		this.documento = documento;
		return this;
	}

	public RequisicaoCouchDb doRepositorio(Texto repositorio) {
		construtorDeUri.caminho(DESIGN).caminho(repositorio);
		return this;
	}

	public RequisicaoCouchDb daVisao(Texto visao) {
		construtorDeUri.caminho(VIEW).caminho(visao);
		return this;
	}

	public RequisicaoCouchDb comIdentificador(Texto identificador) {
		construtorDeUri.caminho(identificador);
		return this;
	}

	public ObjetoJson fornecerDocumento() {
		return documento;
	}

}
