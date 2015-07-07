package br.dominioL.sisPro.ambiente;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class ConfiguracoesSisPro {

	public static final Texto PROTOCOLO = Texto.criar("http");

	public static final Texto ENDERECO = Texto.criar("localhost");

	public static final Numero PORTA = Numero.criar(7000);

	public static final Texto CAMINHO_BASE = Texto.criar("/");

	public static final Texto BANCO_NOME = Texto.criar("sispro");

	public static final Texto BANCO_PROTOCOLO = Texto.criar("http");

	public static final Texto BANCO_ENDERECO = Texto.criar("localhost");

	public static final Numero BANCO_PORTA = Numero.criar(5489);

	public static final Texto BANCO_CAMINHO_BASE = Texto.criar("/");

}