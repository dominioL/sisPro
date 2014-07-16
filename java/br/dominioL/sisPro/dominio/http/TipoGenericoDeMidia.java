package br.dominioL.sisPro.dominio.http;

public enum TipoGenericoDeMidia {
	APLICACAO("application"),
	AUDIO("audio"),
	IMAGEM("image"),
	MENSAGEM("message"),
	MODELO("model"),
	MULTIPARTE("multipart"),
	TEXTO("text"),
	VIDEO("video");

	private final String tipo;

	private TipoGenericoDeMidia(String tipo) {
		this.tipo = tipo;
	}

	public String comoTexto() {
		return tipo;
	}

	public String comoTextoGenerico() {
		return String.format("%s/*", tipo);
	}
}
