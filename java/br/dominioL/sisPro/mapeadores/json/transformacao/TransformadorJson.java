package br.dominioL.sisPro.mapeadores.json.transformacao;

import br.dominioL.estruturados.json.ValorJson;

public interface TransformadorJson {
	public ValorJson transformar(ValorJson valor);
}
