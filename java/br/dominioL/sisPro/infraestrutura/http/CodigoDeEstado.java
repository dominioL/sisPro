package br.dominioL.sisPro.infraestrutura.http;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;

public enum CodigoDeEstado implements Igualavel<CodigoDeEstado> {

	HTTP_100(100, "Continuar", "Continue"),
	HTTP_101(101, "Trocando protocolos", "Switching Protocols"),
	HTTP_200(200, "Certo", "OK"),
	HTTP_201(201, "Criado", "Created"),
	HTTP_202(202, "Aceito", "Accepted"),
	HTTP_203(203, "Informações não autorizadas", "Non-Authoritative Information"),
	HTTP_204(204, "Sem conteúdo", "No Content"),
	HTTP_205(205, "Conteúdo reiniciado", "Reset Content"),
	HTTP_206(206, "Conteúdo parcial", "Partial Content"),
	HTTP_300(300, "Múltiplas escolhas", "Multiple Choices"),
	HTTP_301(301, "Movido permanentemente", "Moved Permanently"),
	HTTP_302(302, "Encontrado", "Found"),
	HTTP_303(303, "Olhar outro", "See Other"),
	HTTP_304(304, "Não modificado", "Not Modified"),
	HTTP_305(305, "Usar procurador", "Use Proxy"),
	HTTP_306(306, "", ""),
	HTTP_307(307, "Redirecionado temporariamente", "Temporary Redirect"),
	HTTP_400(400, "Requisição ruim", "Bad Request"),
	HTTP_401(401, "Não autorizado", "Unauthorized"),
	HTTP_402(402, "Pagamento requerido", "Payment Required"),
	HTTP_403(403, "Proibido", "Forbidden"),
	HTTP_404(404, "Não encontrado", "Not Found"),
	HTTP_405(405, "Método não permitido", "Method Not Allowed"),
	HTTP_406(406, "Não aceitável", "Not Acceptable"),
	HTTP_407(407, "Autenticação do procurador requerida", "Proxy Authentication Required"),
	HTTP_408(408, "Estouro de tempo", "Request Time-out"),
	HTTP_409(409, "Conflito", "Conflict"),
	HTTP_410(410, "Desaparecido", "Gone"),
	HTTP_411(411, "Tamanho requerido", "Length Required"),
	HTTP_412(412, "Pré-condição não satisfeita", "Precondition Failed"),
	HTTP_413(413, "Entidade muito grande", "Request Entity Too Large"),
	HTTP_414(414, "URI muito longa", "Request-URI Too Large"),
	HTTP_415(415, "Tipo de mídia não suportado", "Unsupported Media Type"),
	HTTP_416(416, "Intervalo não satisfatório", "Requested range not satisfiable"),
	HTTP_417(417, "Expectativa não satisfeita", "Expectation Failed"),
	HTTP_500(500, "Erro interno no servidor", "Internal Server Error"),
	HTTP_501(501, "Não implementado", "Not Implemented"),
	HTTP_502(502, "Problema no portão de acesso", "Bad Gateway"),
	HTTP_503(503, "Serviço indisponível", "Service Unavailable"),
	HTTP_504(504, "Estouro de tempo do portão de acesso", "Gateway Time-out"),
	HTTP_505(505, "Versão do protocolo não suportada", "HTTP Version not supported");

	private static final Texto FORMATO = Texto.criar("%d - %s");
	private static final Numero CODIGO_INFORMACIONAL = Numero.criar(100);
	private static final Numero CODIGO_SUCESSO = Numero.criar(200);
	private static final Numero CODIGO_REDIRECIONAMENTO = Numero.criar(300);
	private static final Numero CODIGO_ERRO_DO_CLIENTE = Numero.criar(400);
	private static final Numero CODIGO_ERRO_DO_SERVIDOR = Numero.criar(500);

	private final Texto mensagemPortugues;
	private final Texto mensagemIngles;
	private final Numero codigo;

	private CodigoDeEstado(Integer codigo, String mensagemPortugues, String mensagemIngles) {
		this.codigo = Numero.criar(codigo);
		this.mensagemPortugues = Texto.criar(mensagemPortugues);
		this.mensagemIngles = Texto.criar(mensagemIngles);
	}

	public Texto comoTexto() {
		return mensagemPortugues;
	}

	public Texto comoTextoIngles() {
		return mensagemIngles;
	}

	public Numero comoNumero() {
		return codigo;
	}

	public Texto comoTextoFormatado() {
		return FORMATO.formatar(codigo, mensagemPortugues);
	}

	public Booleano informacional() {
		Numero codigo = comoNumero();
		return codigo.maiorOuIgualQue(CODIGO_INFORMACIONAL).e(codigo.menorQue(CODIGO_SUCESSO));
	}

	public Booleano sucesso() {
		Numero codigo = comoNumero();
		return codigo.maiorOuIgualQue(CODIGO_SUCESSO).e(codigo.menorQue(CODIGO_REDIRECIONAMENTO));
	}

	public Booleano redirecionamento() {
		Numero codigo = comoNumero();
		return codigo.maiorOuIgualQue(CODIGO_REDIRECIONAMENTO).e(codigo.menorQue(CODIGO_ERRO_DO_CLIENTE));
	}

	public Booleano erroDoCliente() {
		Numero codigo = comoNumero();
		return codigo.maiorOuIgualQue(CODIGO_ERRO_DO_CLIENTE).e(codigo.menorQue(CODIGO_ERRO_DO_SERVIDOR));
	}

	public Booleano erroDoServidor() {
		Numero codigo = comoNumero();
		return codigo.maiorOuIgualQue(CODIGO_ERRO_DO_SERVIDOR);
	}

	@Override
	public Booleano igual(CodigoDeEstado outro) {
		return Booleano.mesmo(this, outro);
	}

	public ConstrutorDeResposta fornecerResposta() {
		return ConstrutorDeResposta
				.codigoDeEstado(this)
				.tipoDeMidia(TipoDeMidia.TEXTO)
				.entidade(comoTextoFormatado().valor());
	}

	public static CodigoDeEstado fornecerCodigoDeEstado(Numero codigo) {
		for (CodigoDeEstado codigoDeEstado : CodigoDeEstado.values()) {
			if (codigoDeEstado.codigo.igual(codigo).avaliar()) {
				return codigoDeEstado;
			}
		}
		return HTTP_500;
	}

}