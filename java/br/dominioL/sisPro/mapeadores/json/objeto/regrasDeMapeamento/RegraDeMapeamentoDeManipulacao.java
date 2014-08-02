package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoNaoMapeado;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoMapeado;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome;

public abstract class RegraDeMapeamentoDeManipulacao extends RegraDeMapeamentoAbstrata {
	private String nomeDoNovoCampo;
	private String nomeDoCampo;
	private Mapa<Texto, Booleano> camposMapeados;

	public RegraDeMapeamentoDeManipulacao(String nomeDoCampo, String nomeDoClone, Mapa<Texto, Booleano> campos) {
		this.nomeDoCampo = nomeDoCampo;
		this.nomeDoNovoCampo = nomeDoClone;
		this.camposMapeados = campos;
	}

	@Override
	public final void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (!camposMapeados.contem(Texto.criar(nomeDoCampo))) {
			throw new ExcecaoDeManipulacaoDeCampoNaoMapeado();
		}
		if (camposMapeados.contem(Texto.criar(nomeDoNovoCampo))) {
			throw new ExcecaoDeManipulacaoDeCampoParaCampoMapeado();
		}
		if (mapeado.contem(nomeDoNovoCampo)) {
			throw new ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome();
		}
		if (mapeado.contem(nomeDoCampo)) {
			manipular(mapeado, nomeDoCampo, nomeDoNovoCampo);
		}
	}

	abstract void manipular(ObjetoJson mapeado, String nomeDoCampo, String nomeDoNovoCampo);
}
