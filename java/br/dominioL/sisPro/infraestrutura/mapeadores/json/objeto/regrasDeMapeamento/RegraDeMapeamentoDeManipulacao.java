package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoNaoMapeado;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoMapeado;

public abstract class RegraDeMapeamentoDeManipulacao extends RegraDeMapeamentoAbstrata {

	private Texto nomeDoNovoCampo;
	private Texto nomeDoCampo;
	private Mapa<Texto, Booleano> camposMapeados;

	public RegraDeMapeamentoDeManipulacao(Texto nomeDoCampo, Texto nomeDoClone, Mapa<Texto, Booleano> campos) {
		this.nomeDoCampo = nomeDoCampo;
		this.nomeDoNovoCampo = nomeDoClone;
		this.camposMapeados = campos;
	}

	@Override
	public final void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		assegureQueCampoFoiMapeado();
		assegureQueNovoCampoNaoFoiMapeado();
		assegureQueNovoCampoNaoFoiManipulado(mapeado);
		if (mapeado.contem(nomeDoCampo).avaliar()) {
			manipular(mapeado, nomeDoCampo, nomeDoNovoCampo);
		}
	}

	private void assegureQueNovoCampoNaoFoiManipulado(ObjetoJson mapeado) {
		if (mapeado.contem(nomeDoNovoCampo).avaliar()) {
			throw new ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome();
		}
	}

	private void assegureQueNovoCampoNaoFoiMapeado() {
		if (camposMapeados.contem(nomeDoNovoCampo).avaliar()) {
			throw new ExcecaoDeManipulacaoDeCampoParaCampoMapeado();
		}
	}

	private void assegureQueCampoFoiMapeado() {
		if (camposMapeados.contem(nomeDoCampo).negar().avaliar()) {
			throw new ExcecaoDeManipulacaoDeCampoNaoMapeado();
		}
	}

	abstract void manipular(ObjetoJson mapeado, Texto nomeDoCampo, Texto nomeDoNovoCampo);

}