package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJson<T extends ValorJson> extends MapeadorJsonRenomearCampos<T> {
	public MapeadorJson<T> comCampo(String nome);

	public MapeadorJson<T> comCampoOpcional(String nome);

	public MapeadorJson<T> comCampoColecao(String nome, MapeadorJsonCamposNaoMapeados<T> mapeador);

	public MapeadorJson<T> comCampoColecaoOpcional(String nome, MapeadorJsonCamposNaoMapeados<T> mapeador);

	public MapeadorJson<T> comCampo(String nome, MapeadorJsonCamposNaoMapeados<T> mapeador);

	public MapeadorJson<T> comCampoOpcional(String nome, MapeadorJsonCamposNaoMapeados<T> mapeador);
}
