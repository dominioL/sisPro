package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;

public final class RegraDeMapeamentoDeClonagem extends RegraDeMapeamentoDeManipulacao {
	public RegraDeMapeamentoDeClonagem(String nome, String novoNome, Mapa<Texto, Booleano> camposMapeados) {
		super(nome, novoNome, camposMapeados);
	}

	@Override
	void manipular(ObjetoJson mapeado, String nomeDoCampo, String nomeDoNovoCampo) {
		mapeado.inserir(nomeDoNovoCampo, mapeado.fornecer(nomeDoCampo));
	}
}
