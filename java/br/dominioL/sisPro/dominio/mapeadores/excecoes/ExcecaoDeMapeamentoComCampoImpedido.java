package br.dominioL.sisPro.dominio.mapeadores.excecoes;

public class ExcecaoDeMapeamentoComCampoImpedido extends RuntimeException {
	private static final long serialVersionUID = -1188101786670037118L;
	
	public ExcecaoDeMapeamentoComCampoImpedido() {
		super("O campo existe na origem, mas não foi mapeado.");
	}
}
