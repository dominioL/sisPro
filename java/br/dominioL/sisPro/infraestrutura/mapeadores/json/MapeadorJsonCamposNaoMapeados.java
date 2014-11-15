package br.dominioL.sisPro.infraestrutura.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJsonCamposNaoMapeados<T extends ValorJson> {

	public MapeadorJsonCamposNaoMapeados<T> ignorarCamposNaoMapeados();

	public MapeadorJsonCamposNaoMapeados<T> incluirCamposNaoMapeados();

	public MapeadorJsonCamposNaoMapeados<T> impedirCamposNaoMapeados();

	public T mapear(T origem);

}
