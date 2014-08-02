package br.dominioL.sisPro.mapeadores.json.excecoes;

public class ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado extends RuntimeException {
	private static final long serialVersionUID = 5131784128249427573L;
	
	public ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado() {
		super("O novo campo não pode ter sido mapeado para que possa ser renomeado ou clonado.");
	}
}
