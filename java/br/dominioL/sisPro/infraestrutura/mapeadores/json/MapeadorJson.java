package br.dominioL.sisPro.infraestrutura.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public abstract class MapeadorJson<T extends ValorJson> implements MapeadorJsonRenomearCampos<T> {

	public static final MapeadorJsonDeObjeto deObjeto() {
		return MapeadorJsonDeObjeto.criar();
	}

	public abstract MapeadorJson<T> comCampo(String nome);

	public abstract MapeadorJson<T> comCampoOpcional(String nome);

	public abstract MapeadorJson<T> comCampo(String nome, MapeadorJsonCamposNaoMapeados<T> mapeador);

	public abstract MapeadorJson<T> comCampoOpcional(String nome, MapeadorJsonCamposNaoMapeados<T> mapeador);

}