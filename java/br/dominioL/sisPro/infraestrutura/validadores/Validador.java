package br.dominioL.sisPro.infraestrutura.validadores;

import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class Validador {

	public static final Texto NOME = Texto.criar("^(\\p{L}{1,32}( \\p{L}{1,32})?){1,8}$");
	public static final Texto TELEFONE = Texto.criar("^[(]\\p{Digit}{2}[)] \\p{Digit}{4,5}-\\p{Digit}{4}$");
	public static final Texto EMAIL = Texto.criar("^[\\p{Lower}\\p{Digit}._-]{1,32}@[\\p{Lower}\\p{Digit}._-]{1,32}([.]\\p{Lower}{2,3}){1,2}$");
	public static final Texto CPF = Texto.criar("^\\p{Digit}{3}[.]\\p{Digit}{3}[.]\\p{Digit}{3}-\\p{Digit}{2}$");
	public static final Texto CNPJ = Texto.criar("^\\p{Digit}{2}[.]\\p{Digit}{3}[.]\\p{Digit}{3}/\\p{Digit}{4}-\\p{Digit}{2}$");
	public static final Texto IE = Texto.criar("^(\\p{Digit}{1,16}([./-]\\p{Digit}{1,16})?){1,8}$");

}