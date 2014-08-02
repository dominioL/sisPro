package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public class RegraDeMapeamentoDeInclusaoOpcional extends RegraDeMapeamentoAbstrata {
	private String nome;

	public RegraDeMapeamentoDeInclusaoOpcional(String nome) {
		this.nome = nome;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (origem.contem(nome)) {
			mapeado.inserir(nome, origem.fornecer(nome));
		}
	}
}
