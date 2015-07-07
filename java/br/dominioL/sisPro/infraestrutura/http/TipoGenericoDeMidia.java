package br.dominioL.sisPro.infraestrutura.http;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum TipoGenericoDeMidia implements Igualavel<TipoGenericoDeMidia> {

	APLICACAO("application", true),
	AUDIO("audio", false),
	IMAGEM("image", false),
	MENSAGEM("message", false),
	MODELO("model", false),
	MULTIPARTE("multipart", true),
	TEXTO("text", true),
	VIDEO("video", false);

	private static final Texto FORMATO = Texto.criar("%s/*");

	private final Texto tipo;
	private final Booleano textual;

	private TipoGenericoDeMidia(String tipo, Boolean textual) {
		this.tipo = Texto.criar(tipo);
		this.textual = Booleano.criar(textual);
	}

	public Texto comoTexto() {
		return tipo;
	}

	public Texto comoTextoGenerico() {
		return FORMATO.formatar(tipo);
	}

	public Booleano textual() {
		return textual;
	}

	@Override
	public Booleano igual(TipoGenericoDeMidia outro) {
		return Booleano.mesmo(this, outro);
	}

}