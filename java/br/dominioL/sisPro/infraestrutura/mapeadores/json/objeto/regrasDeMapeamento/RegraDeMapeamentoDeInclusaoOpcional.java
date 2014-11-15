package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;

public final class RegraDeMapeamentoDeInclusaoOpcional extends RegraDeMapeamentoAbstrata {

	private Texto nome;

	public RegraDeMapeamentoDeInclusaoOpcional(Texto nome) {
		this.nome = nome;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (origem.contem(nome).avaliar()) {
			mapeado.inserir(nome, origem.fornecer(nome));
		}
	}

}
