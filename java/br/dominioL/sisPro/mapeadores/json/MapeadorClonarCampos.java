package br.dominioL.sisPro.mapeadores.json;

public interface MapeadorClonarCampos<T> extends MapeadorAdicionarCampos<T> {
	public MapeadorClonarCampos<T> clonarCampo(String nome, String novoNome);
}
