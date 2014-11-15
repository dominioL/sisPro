package br.dominioL.sisPro.infraestrutura.http;

import javax.ws.rs.Path;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public final class ConstrutorDeUri {

	private static final Texto PROTOCOLO_PADRAO = Texto.criar("http");
	private static final Texto ENDERECO_PADRAO = Texto.criar("localhost");
	private static final Numero PORTA_PADRAO = Numero.criar(80);
	private static final Texto CAMINHO_BASE_PADRAO = Texto.criar();
	private static final Texto FORMATO_ABSOLUTO = Texto.criar("%s://%s:%s%s");
	private static final Texto FORMATO_RELATIVO = Texto.criar("%s/");
	private static final Texto FORMATO_FRAGMENTO = Texto.criar("%s/%s");
	private static final Texto ER_DUAS_BARRAS = Texto.criar("/{2,}");
	private static final Texto ER_PARAMETRO = Texto.criar("\\{(\\p{Alpha})+\\}");
	private static final Texto UMA_BARRA = Texto.criar("/");

	private Texto protocolo;
	private Texto endereco;
	private Numero porta;
	private Texto caminho;

	private ConstrutorDeUri(Texto protocolo, Texto endereco, Numero porta, Texto caminho) {
		this.protocolo = protocolo;
		this.endereco = endereco;
		this.porta = porta;
		this.caminho = caminho;
	}

	public static ConstrutorDeUri criar() {
		return new ConstrutorDeUri(PROTOCOLO_PADRAO, ENDERECO_PADRAO, PORTA_PADRAO, CAMINHO_BASE_PADRAO);
	}

	public static ConstrutorDeUri criar(Class<?> classe) {
		Texto caminhoDoRecurso = obterCaminhoDeRecurso(classe);
		return new ConstrutorDeUri(PROTOCOLO_PADRAO, ENDERECO_PADRAO, PORTA_PADRAO, caminhoDoRecurso);
	}

	public ConstrutorDeUri protocolo(Texto protocolo) {
		this.protocolo = protocolo;
		return this;
	}

	public ConstrutorDeUri endereco(Texto endereco) {
		this.endereco = endereco;
		return this;
	}

	public ConstrutorDeUri porta(Numero porta) {
		this.porta = porta;
		return this;
	}

	public ConstrutorDeUri caminho(Texto novoCaminho) {
		caminho = FORMATO_FRAGMENTO.formatar(caminho, novoCaminho);
		return this;
	}

	public ConstrutorDeUri caminho(Class<?> classe) {
		return caminho(obterCaminhoDeRecurso(classe));
	}

	public ConstrutorDeUri substituirParametro(Texto substituicao) {
		caminho = caminho.substituirPrimeiro(ER_PARAMETRO, substituicao);
		return this;
	}

	public Texto construirRelativo() {
		Texto caminhoFormatado = FORMATO_RELATIVO.formatar(caminho);
		caminhoFormatado = caminhoFormatado.substituirTodos(ER_DUAS_BARRAS, UMA_BARRA);
		return caminhoFormatado;
	}

	public Texto construirAbsoluto() {
		Texto caminhoFormatado = construirRelativo();
		return FORMATO_ABSOLUTO.formatar(protocolo, endereco, porta, caminhoFormatado);
	}

	private static Texto obterCaminhoDeRecurso(Class<?> classe) {
		return Texto.criar(classe.getAnnotation(Path.class).value());
	}

}
