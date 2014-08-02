package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoInexistente;

public final class RegraDeMapeamentoDeInclusao extends RegraDeMapeamentoAbstrata {
	private String nome;

	public RegraDeMapeamentoDeInclusao(String nome) {
		this.nome = nome;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (!origem.contem(nome)) {
			throw new ExcecaoDeMapeamentoComCampoInexistente();
		}
		mapeado.inserir(nome, origem.fornecer(nome));
	}
}
