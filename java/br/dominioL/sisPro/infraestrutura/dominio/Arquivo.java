package br.dominioL.sisPro.infraestrutura.dominio;

import java.io.File;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum Arquivo {

	PRINCIPAL("principal", "html"),
	CSS("", "css"),
	HTML("", "html"),
	JS("", "js"),
	JSON("", "json"),
	JPG("", "jpg"),
	PNG("", "png");

	private static final Texto CAMINHO = Texto.criar("binarios");
	private static final Texto FORMATO = Texto.criar("%s/%s/%s.%s");

	private final Texto nome;
	private final Texto extensao;

	private Arquivo(String nome, String extensao) {
		this.nome = Texto.criar(nome);
		this.extensao = Texto.criar(extensao);
	}

	public final File fornecerArquivo(Texto nome) {
		return new File(FORMATO.formatar(CAMINHO, extensao, nome, extensao).valor());
	}
	
	public final File fornecerArquivo(String nome) {
		return new File(FORMATO.formatar(CAMINHO, extensao, nome, extensao).valor());
	}

	public final File fornecerArquivo() {
		return fornecerArquivo(nome);
	}

	public final Texto fornecerNome() {
		return nome;
	}

}
