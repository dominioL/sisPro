package br.dominioL.sisPro.infraestrutura.http;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum TipoDeMidia implements Igualavel<TipoDeMidia> {

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

	private static final Texto FORMATO = Texto.criar("%s/%s");
	private static final Texto FORMATO_TEXTUAL = Texto.criar("%s/%s;charset=utf-8");

	private final TipoGenericoDeMidia tipoGenerico;
	private final Texto tipo;

	private TipoDeMidia(TipoGenericoDeMidia tipoGenerico, String tipo) {
		this.tipoGenerico = tipoGenerico;
		this.tipo = Texto.criar(tipo);
	}

	public Texto comoTexto() {
		Texto formato = tipoGenerico.textual().avaliar() ? FORMATO_TEXTUAL : FORMATO;
		return formato.formatar(tipoGenerico, tipo);
	}

	public Texto comoTextoSemConjuntoDeCaracteres() {
		return FORMATO.formatar(tipoGenerico, tipo);
	}

	public Texto comoTextoGenerico() {
		return tipoGenerico.comoTextoGenerico();
	}

	@Override
	public Booleano igual(TipoDeMidia outro) {
		return Booleano.mesmo(this, outro);
	}

}