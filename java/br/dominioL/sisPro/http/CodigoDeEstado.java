package br.dominioL.sisPro.http;

import javax.ws.rs.core.Response;

import br.dominioL.estruturados.elemento.Igualavel;

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
	HTTP_502(502, "Portão de acesso ruim", "Bad Gateway"),
	HTTP_503(503, "Serviço indisponível", "Service Unavailable"),
	HTTP_504(504, "Estouro de tempo do portão de acesso", "Gateway Time-out"),
	HTTP_505(505, "Versão do protocolo não suportada", "HTTP Version not supported");

	private static final Integer CODIGO_INFORMACIONAL = 100;
	private static final Integer CODIGO_SUCESSO = 200;
	private static final Integer CODIGO_REDIRECIONAMENTO = 300;
	private static final Integer CODIGO_ERRO_DO_CLIENTE = 400;
	private static final Integer CODIGO_ERRO_DO_SERVIDOR = 500;
	private final Integer codigo;
	private final String mensagemPortugues;
	private final String mensagemIngles;

	private CodigoDeEstado(Integer codigo, String mensagemPortugues, String mensagemIngles) {
		this.codigo = codigo;
		this.mensagemPortugues = mensagemPortugues;
		this.mensagemIngles = mensagemIngles;
	}

	public String comoTexto() {
		return mensagemPortugues;
	}

	public String comoTextoIngles() {
		return mensagemIngles;
	}

	public Integer comoNumero() {
		return codigo;
	}

	public String comoTextoFormatado() {
		return String.format("%d - %s.", codigo, mensagemPortugues);
	}

	public Boolean informacional() {
		Integer codigo = comoNumero();
		return (codigo >= CODIGO_INFORMACIONAL && codigo < CODIGO_SUCESSO);
	}

	public Boolean sucesso() {
		Integer codigo = comoNumero();
		return (codigo >= CODIGO_SUCESSO && codigo < CODIGO_REDIRECIONAMENTO);
	}

	public Boolean redirecionamento() {
		Integer codigo = comoNumero();
		return (codigo >= CODIGO_REDIRECIONAMENTO && codigo < CODIGO_ERRO_DO_CLIENTE);
	}

	public Boolean erroDoCliente() {
		Integer codigo = comoNumero();
		return (codigo >= CODIGO_ERRO_DO_CLIENTE && codigo < CODIGO_ERRO_DO_SERVIDOR);
	}

	public Boolean erroDoServidor() {
		Integer codigo = comoNumero();
		return (codigo >= CODIGO_ERRO_DO_SERVIDOR);
	}

	@Override
	public Boolean igual(CodigoDeEstado outro) {
		return (this.codigo.equals(outro.codigo));
	}

	public Response fornecerResposta(TipoDeMidia tipoDeMidia, Object entidade, String localizacao) {
		return Response
			.status(comoNumero())
			.type(tipoDeMidia.comoTexto())
			.entity(entidade)
			.header(Atributo.LOCATION.comoTexto(), localizacao)
			.build();
	}

	public Response fornecerResposta(TipoDeMidia tipoDeMidia, Object entidade) {
		return Response
			.status(comoNumero())
			.type(tipoDeMidia.comoTexto())
			.entity(entidade)
			.build();
	}

	public Response fornecerResposta() {
		return fornecerResposta(TipoDeMidia.TEXTO, comoTextoFormatado());
	}

	public static CodigoDeEstado fornecerCodigoDeEstado(Integer codigo) {
		for (CodigoDeEstado codigoDeEstado : CodigoDeEstado.values()) {
			if (codigoDeEstado.codigo.equals(codigo)) {
				return codigoDeEstado;
			}
		}
		return HTTP_500;
	}
}
