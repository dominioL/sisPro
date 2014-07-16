package br.dominioL.sisPro.dominio.http;

public enum TipoDeMidia {
	JS(TipoGenericoDeMidia.APLICACAO, "javascript"),
	JSON(TipoGenericoDeMidia.APLICACAO, "json"),
	PDF(TipoGenericoDeMidia.APLICACAO, "pdf"),
	XML(TipoGenericoDeMidia.APLICACAO, "xml"),
	ZIP(TipoGenericoDeMidia.APLICACAO, "zip"),
	MP3(TipoGenericoDeMidia.AUDIO, "mpeg"),
	GIF(TipoGenericoDeMidia.IMAGEM, "gif"),
	JPG(TipoGenericoDeMidia.IMAGEM, "jpeg"),
	PNG(TipoGenericoDeMidia.IMAGEM, "png"),
	SVG(TipoGenericoDeMidia.IMAGEM, "svg+xml"),
	FORMULARIO(TipoGenericoDeMidia.MULTIPARTE, "form-data"),
	CSS(TipoGenericoDeMidia.TEXTO, "css"),
	CSV(TipoGenericoDeMidia.TEXTO, "csv"),
	HTML(TipoGenericoDeMidia.TEXTO, "html"),
	TEXTO(TipoGenericoDeMidia.TEXTO, "plain"),
	MP4(TipoGenericoDeMidia.VIDEO, "mp4"),
	MPEG(TipoGenericoDeMidia.VIDEO, "mpeg"),
	OGG(TipoGenericoDeMidia.VIDEO, "ogg"),
	VORBIS(TipoGenericoDeMidia.VIDEO, "vorbis"),
	WEBM(TipoGenericoDeMidia.VIDEO, "webm");

	private static final String CONJUNTO_DE_CARACTERES = "utf-8";
	private final TipoGenericoDeMidia tipoGenerico;
	private final String tipo;

	private TipoDeMidia(TipoGenericoDeMidia tipoGenerico, String tipo) {
		this.tipoGenerico = tipoGenerico;
		this.tipo = tipo;
	}

	public String comoTexto() {
		String texto;
		if (tipoGenerico.equals(TipoGenericoDeMidia.APLICACAO) ||
			tipoGenerico.equals(TipoGenericoDeMidia.MULTIPARTE) ||
			tipoGenerico.equals(TipoGenericoDeMidia.TEXTO)) {
			texto = String.format("%s/%s;charset=%s", tipoGenerico.comoTexto(), tipo, CONJUNTO_DE_CARACTERES);
		} else {
			texto = String.format("%s/%s", tipoGenerico.comoTexto(), tipo);
		}
		return texto;
	}

	public String comoTextoSemCharset() {
		String texto = String.format("%s/%s", tipoGenerico.comoTexto(), tipo);
		return texto;
	}

	public String comoTextoGenerico() {
		return tipoGenerico.comoTextoGenerico();
	}
}
