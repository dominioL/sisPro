package br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes;

public final class ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado extends RuntimeException {

	private static final long serialVersionUID = -8514024131178075118L;

	public ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado() {
		super("O campo deve ser mapeado ou renomeado ou clonado ou adicionado para que possa ser transformado.");
	}

}
