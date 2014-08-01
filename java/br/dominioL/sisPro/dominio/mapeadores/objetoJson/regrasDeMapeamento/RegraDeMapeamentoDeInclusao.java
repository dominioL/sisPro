package br.dominioL.sisPro.dominio.mapeadores.objetoJson.regrasDeMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeMapeamentoComCampoInexistente;

public class RegraDeMapeamentoDeInclusao extends RegraDeMapeamentoAbstrata {
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
