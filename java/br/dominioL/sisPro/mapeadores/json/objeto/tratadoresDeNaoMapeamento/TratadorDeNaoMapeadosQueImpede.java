package br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.Par;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoImpedido;

public class TratadorDeNaoMapeadosQueImpede extends TratadorDeNaoMapeadosAbstrato implements TratadorDeNaoMapeados {
	public TratadorDeNaoMapeadosQueImpede(Mapa<Texto, Booleano> camposMapeados) {
		super(camposMapeados);
	}

	@Override
	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado) {
		for (Par<IdentificadorJson, ValorJson> par : origem) {
			if (campoNaoFoiMapeado(par) && campoNaoEstaMapeado(mapeado, par)) {
				throw new ExcecaoDeMapeamentoComCampoImpedido();
			}
		}
		return mapeado;
	}
}
