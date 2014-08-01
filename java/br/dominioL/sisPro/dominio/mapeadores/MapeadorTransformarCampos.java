 package br.dominioL.sisPro.dominio.mapeadores;


public interface MapeadorTransformarCampos<T> extends MapeadorCamposNaoMapeados<T> {
	public MapeadorTransformarCampos<T> transformarCampo(String nome, TransformadorDeMapeamento<T> transformador);
}
