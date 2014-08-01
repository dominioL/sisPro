package br.dominioL.sisPro.dominio.mapeadores;

public interface MapeadorClonarCampos<T> extends MapeadorAdicionarCampos<T> {
	public MapeadorClonarCampos<T> clonarCampo(String nome, String novoNome);
}
