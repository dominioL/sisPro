package br.dominioL.sisPro.http;

import javax.ws.rs.Path;

public final class ConstrutorDeUri {
	private static final String PROTOCOLO_PADRAO = "http"; 
	private static final String ENDERECO_PADRAO = "localhost"; 
	private static final Integer PORTA_PADRAO = 80; 
	private static final String CAMINHO_PADRAO = ""; 

	private String protocolo;
	private String endereco;
	private Integer porta;
	private String caminho;

	private ConstrutorDeUri(String protocolo, String endereco, Integer porta, String caminho) {
		this.protocolo = protocolo;
		this.endereco = endereco;
		this.porta = porta;
		this.caminho = caminho;
	}

	public static ConstrutorDeUri criar() {
		return new ConstrutorDeUri(PROTOCOLO_PADRAO, ENDERECO_PADRAO, PORTA_PADRAO, CAMINHO_PADRAO);
	}

	public static ConstrutorDeUri criar(Class<?> classe) {
		String caminhoDoRecurso = obterCaminhoDeRecurso(classe);
		return new ConstrutorDeUri(PROTOCOLO_PADRAO, ENDERECO_PADRAO, PORTA_PADRAO, caminhoDoRecurso);
	}

	public ConstrutorDeUri protocolo(String protocolo) {
		this.protocolo = protocolo;
		return this;
	}

	public ConstrutorDeUri endereco(String endereco) {
		this.endereco = endereco;
		return this;
	}

	public ConstrutorDeUri porta(Integer porta) {
		this.porta = porta;
		return this;
	}

	public ConstrutorDeUri caminho(String novoCaminho) {
		caminho = String.format("%s/%s", caminho, novoCaminho);
		return this;
	}
	
	public ConstrutorDeUri caminho(Class<?> classe) {
		return caminho(obterCaminhoDeRecurso(classe));
	}

	public ConstrutorDeUri substituirParametro(String substituicao) {
		caminho = caminho.replaceFirst("\\{(\\p{Alpha})+\\}", substituicao);
		return this;
	}

	public String construirRelativo() {
		String caminhoFormatado = String.format("%s/", caminho);
		caminhoFormatado = caminhoFormatado.replaceAll("/{2,}", "/");
		return caminhoFormatado;
	}

	public String construirAbsoluto() {
		String caminhoFormatado = construirRelativo();
		return String.format("%s://%s:%s%s", protocolo, endereco, porta, caminhoFormatado);
	}
	
	private static String obterCaminhoDeRecurso(Class<?> classe) {
		String caminhoDoRecurso = classe.getAnnotation(Path.class).toString();
		caminhoDoRecurso = caminhoDoRecurso.replaceAll("@javax.ws.rs.Path\\(value=(.+?)\\)", "$1");
		return caminhoDoRecurso;
	}
}
