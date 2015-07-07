package br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes;

public final class ExcecaoDeManipulacaoDeCampoParaCampoMapeado extends RuntimeException {

	private static final long serialVersionUID = 5131784128249427573L;

	public ExcecaoDeManipulacaoDeCampoParaCampoMapeado() {
		super("O novo campo não pode ter sido mapeado para que possa ser renomeado, clonado ou adicionado.");
	}

}