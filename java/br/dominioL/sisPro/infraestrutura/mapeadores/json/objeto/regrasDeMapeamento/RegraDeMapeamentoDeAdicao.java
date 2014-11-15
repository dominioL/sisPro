package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoMapeado;

public final class RegraDeMapeamentoDeAdicao extends RegraDeMapeamentoAbstrata {

	private Texto nome;
	private ValorJson valor;
	private Mapa<Texto, Booleano> camposMapeados;

	public RegraDeMapeamentoDeAdicao(Texto nome, ValorJson valor, Mapa<Texto, Booleano> camposMapeados) {
		this.nome = nome;
		this.valor = valor;
		this.camposMapeados = camposMapeados;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (camposMapeados.contem(nome).avaliar() && camposMapeados.fornecer(nome).negar().avaliar()) {
			throw new ExcecaoDeManipulacaoDeCampoParaCampoMapeado();
		}
		if (mapeado.contem(nome).negar().avaliar()) {
			mapeado.inserir(nome, valor);
		}
	}
}
