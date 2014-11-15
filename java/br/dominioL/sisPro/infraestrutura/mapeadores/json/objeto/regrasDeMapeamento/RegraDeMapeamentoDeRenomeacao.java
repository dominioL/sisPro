package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;

public final class RegraDeMapeamentoDeRenomeacao extends RegraDeMapeamentoDeManipulacao {

	public RegraDeMapeamentoDeRenomeacao(Texto nome, Texto novoNome, Mapa<Texto, Booleano> camposMapeados) {
		super(nome, novoNome, camposMapeados);
	}

	@Override
	void manipular(ObjetoJson mapeado, Texto nomeDoCampo, Texto nomeDoNovoCampo) {
		mapeado.inserir(nomeDoNovoCampo, mapeado.fornecer(nomeDoCampo));
		mapeado.remover(nomeDoCampo);
	}

}
