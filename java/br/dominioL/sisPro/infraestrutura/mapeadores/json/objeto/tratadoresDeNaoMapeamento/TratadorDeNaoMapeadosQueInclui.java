package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.tratadoresDeNaoMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.Par;

public final class TratadorDeNaoMapeadosQueInclui extends TratadorDeNaoMapeadosAbstrato implements TratadorDeNaoMapeados {

	public TratadorDeNaoMapeadosQueInclui(Mapa<Texto, Booleano> camposMapeados) {
		super(camposMapeados);
	}

	@Override
	public ObjetoJson tratar(ObjetoJson origem, ObjetoJson mapeado) {
		for (Par<IdentificadorJson, ValorJson> par : origem) {
			if (campoNaoFoiMapeado(par).avaliar() && campoNaoEstaMapeado(mapeado, par).avaliar()) {
				mapeado.inserir(par.fornecerChave().comoTexto(), par.fornecerValor());
			}
		}
		return mapeado;
	}

}