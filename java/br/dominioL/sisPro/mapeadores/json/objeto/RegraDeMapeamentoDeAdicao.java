package br.dominioL.sisPro.mapeadores.json.objeto;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoMapeado;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoAbstrata;

public class RegraDeMapeamentoDeAdicao extends RegraDeMapeamentoAbstrata {
	private String nome;
	private ValorJson valor;
	private Mapa<Texto, Booleano> camposMapeados;

	public RegraDeMapeamentoDeAdicao(String nome, ValorJson valor, Mapa<Texto, Booleano> camposMapeados) {
		this.nome = nome;
		this.valor = valor;
		this.camposMapeados = camposMapeados;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		Texto chave = Texto.criar(nome);
		if (camposMapeados.contem(chave) && camposMapeados.fornecer(chave).falso()) {
			throw new ExcecaoDeManipulacaoDeCampoParaCampoMapeado();
		}
		if (!mapeado.contem(nome)) {
			mapeado.inserir(nome, valor);
		}
	}
}
