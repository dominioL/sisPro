package br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.Par;

public abstract class TratadorDeNaoMapeadosAbstrato implements TratadorDeNaoMapeados {
	private Mapa<Texto, Booleano> camposMapeados;

	public TratadorDeNaoMapeadosAbstrato(Mapa<Texto, Booleano> camposMapeados) {
		this.camposMapeados = camposMapeados;
	}

	protected Boolean campoNaoEstaMapeado(ObjetoJson mapeado, Par<IdentificadorJson, ValorJson> par) {
		return !mapeado.contem(par.fornecerChave().comoTexto());
	}

	protected Boolean campoNaoFoiMapeado(Par<IdentificadorJson, ValorJson> par) {
		return !camposMapeados.contem(Texto.comValor(par.fornecerChave().comoTexto()));
	}
}
