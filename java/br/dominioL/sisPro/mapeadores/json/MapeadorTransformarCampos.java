 package br.dominioL.sisPro.mapeadores.json;


public interface MapeadorTransformarCampos<T> extends MapeadorCamposNaoMapeados<T> {
	public MapeadorTransformarCampos<T> transformarCampo(String nome, TransformadorDeMapeamento<T> transformador);
}
