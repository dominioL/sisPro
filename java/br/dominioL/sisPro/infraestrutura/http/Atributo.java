package br.dominioL.sisPro.infraestrutura.http;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum Atributo {

	CONTENT_TYPE("Content-Type"),
	ACCEPT("Accept"),
	ACCEPT_CHARSET("Accept-Charset"),
	ACCEPT_ENCODING("Accept-Encoding"),
	ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),
	ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),
	CONNECTION("Connection"),
	CONTENT_LENGTH("Content-Length"),
	COOKIE("Cookie"),
	COOKIE_2("Cookie2"),
	CONTENT_TRANSFER_ENCODING("Content-Transfer-Encoding"),
	DATE("Date"),
	EXPECT("Expect"),
	HOST("Host"),
	KEEP_ALIVE("Keep-Alive"),
	LOCATION("Location"),
	ORIGIN("Origin"),
	REFERER("Referer"),
	TE("TE"),
	TRAILER("Trailer"),
	TRANSFER_ENCODING("Transfer-Encoding"),
	UPGRADE("Upgrade"),
	USER_AGENT("User-Agent"),
	VIA("Via");

	private final Texto valor;

	private Atributo(String valor) {
		this.valor = Texto.criar(valor);
	}

	public Texto comoTexto() {
		return valor;
	}

}