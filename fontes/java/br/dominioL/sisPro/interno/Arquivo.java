package br.dominioL.sisPro.interno;

import java.io.File;

public enum Arquivo {
	CSS("", "css"),
	LIMPO("limpo", "css"),
	HTML("", "html"),
	INICIO("inicio", "html"),
	PRINCIPAL("principal", "html"),
	JS("", "js"),
	LINDA("linda", "js"),
	JPG("", "jpg"),
	PNG("", "png");

	private static final String CAMINHO = "binarios";
	private final String nome;
	private final String extensao;

	private Arquivo(String nome, String extensao) {
		this.nome = nome;
		this.extensao = extensao;
	}

	public final File fornecerArquivo(String nome) {
		return new File(String.format("%s/%s/%s.%s", CAMINHO, extensao, nome, extensao));
	}

	public final File fornecerArquivo() {
		return fornecerArquivo(nome);
	}

	public final String fornecerNome() {
		return nome;
	}
}
