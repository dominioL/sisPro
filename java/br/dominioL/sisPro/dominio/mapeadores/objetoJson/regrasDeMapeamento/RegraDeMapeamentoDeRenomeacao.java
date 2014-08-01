package br.dominioL.sisPro.dominio.mapeadores.objetoJson.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeRenomeacaoDeCampoNaoMapeado;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeRenomeacaoDeCampoParaCampoJaRenomeadoComMesmoNome;

public final class RegraDeMapeamentoDeRenomeacao extends RegraDeMapeamentoAbstrata {
	private String novoNome;
	private String nome;
	private Mapa<Texto, Booleano> camposMapeados;

	public RegraDeMapeamentoDeRenomeacao(String nome, String novoNome, Mapa<Texto, Booleano> campos) {
		this.nome = nome;
		this.novoNome = novoNome;
		this.camposMapeados = campos;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (!camposMapeados.contem(Texto.comValor(nome))) {
			throw new ExcecaoDeRenomeacaoDeCampoNaoMapeado();
		}
		if (camposMapeados.contem(Texto.comValor(novoNome))) {
			throw new ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado();
		}
		if (mapeado.contem(novoNome)) {
			throw new ExcecaoDeRenomeacaoDeCampoParaCampoJaRenomeadoComMesmoNome();
		}
		if (mapeado.contem(nome)) {
			mapeado.inserir(novoNome, mapeado.fornecer(nome));
			mapeado.remover(nome);
		}
	}
}
