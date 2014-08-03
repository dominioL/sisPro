package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.mapeadores.json.MapeadorJsonCamposNaoMapeados;

public class RegraDeMapeamentoDeInclusaoComMapeador extends RegraDeMapeamentoAbstrata {
	private String nome;
	private MapeadorJsonCamposNaoMapeados<ObjetoJson> mapeador;
	private RegraDeMapeamento regra;

	public RegraDeMapeamentoDeInclusaoComMapeador(String nome, MapeadorJsonCamposNaoMapeados<ObjetoJson> mapeador, RegraDeMapeamento regra) {
		this.nome = nome;
		this.mapeador = mapeador;
		this.regra = regra;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		regra.aplicar(origem, mapeado);
		if (mapeado.contem(nome)) {
			mapeado.substituir(nome, mapeador.mapear(mapeado.fornecer(nome).comoObjeto()));
		}
	}

}
