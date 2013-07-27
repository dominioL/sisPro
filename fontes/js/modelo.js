/*global AtributoHttp*/
/*global Classe*/
/*global Linda*/
/*global RequisicaoDocumento*/
/*global RequisicaoJson*/
/*global SisProControle*/
/*global SisProVisao*/
/*global TipoDeMidia*/
/*global TratadorDePagina*/

(function (global) {
	"use strict";

	var SisPro = Classe.criarSingleton({
		inicializar: function () {
			new TratadorDePagina().paraCarregamento(this.inicializarSistema.vincularEscopo(this));
		},

		inicializarSistema: function () {
			SisProVisao.instancia();
			SisProControle.instancia();
			this.carregarConteudo();
		},

		carregarConteudo: function () {
			var localizacao = Linda.localizacao;
			var pagina = String.concatenar("/html", localizacao.pathname, localizacao.search);
			this.carregarPagina(pagina);
		},

		carregarPagina: function (uriDaPagina) {
			this.iniciarAtualizacao();
			var requisicao = Requeridor.instancia.fornecerRequisicaoDePagina(uriDaPagina);
			requisicao.tratarSucesso = this.receberPagina.vincularEscopo(this);
			requisicao.enviarGet(true);
		},

		carregarScripts: function (pagina) {
			var scripts = pagina.firstChild.selecionarTodos("script");
			var cabecalho = Linda.selecionar("head");
			for (var indice = 0, tamanho = scripts.length; indice < tamanho; indice++) {
				var script = scripts[indice];
				var novoScript = Linda.criarElemento("script");
				novoScript.async = true;
				novoScript.type = script.type;
				novoScript.src = script.src;
				cabecalho.appendChild(novoScript);
				script.remove();
			}
		},

		receberPagina: function (pagina) {
			SisProVisao.instancia.carregarConteudo(pagina);
			this.carregarScripts(pagina);
			this.finalizarAtualizacao();
		},

		iniciarAtualizacao: function () {
			SisProVisao.instancia.iniciarAtualizacao();
			SisProControle.instancia.bloquearTodosBotoes();
		},

		finalizarAtualizacao: function () {
			SisProVisao.instancia.finalizarAtualizacao();
			SisProControle.instancia.desbloquearTodosBotoes();
		},

		adicionarPadraoDeValidacao: function (classeDoCampo, padraoDeValidacao) {
			var campo = SisProVisao.instancia.selecionarCampo(classeDoCampo);
			campo.setAttribute("pattern", padraoDeValidacao);
		},

		adicionarPadraoDeValidacaoDeTemplate: function (classeDoCampo, padraoDeValidacao) {
			var campo = SisProVisao.instancia.selecionarCampoDeTemplate(classeDoCampo);
			campo.setAttribute("pattern", padraoDeValidacao);
		},

		marcarCampoComoObrigatorio: function (classeDoCampo) {
			var campo = SisProVisao.instancia.selecionarCampo(classeDoCampo);
			campo.setAttribute("required", "required");
		},

		marcarCampoDeTemplateComoObrigatorio: function (classeDoCampo) {
			var campo = SisProVisao.instancia.selecionarCampoDeTemplate(classeDoCampo);
			campo.setAttribute("required", "required");
		},

		adicionarCampoAosDados: function (dados, classeDoCampo) {
			var campo = SisProVisao.instancia.selecionarCampo(classeDoCampo);
			if (!Linda.nulo(campo)) {
				var valor = campo.value;
				if (!valor.emBranco()) {
					dados[classeDoCampo] = valor;
				}
			}
		},

		adicionarCamposAosDados: function (dados, classeDoCampo, nomeDoAtributo) {
			dados[nomeDoAtributo] = [];
			var listaComDados = dados[nomeDoAtributo];
			var campos = SisProVisao.instancia.selecionarCampos(classeDoCampo);
			campos.paraCada(function (campo) {
				var valor = campo.value;
				if (!valor.emBranco()) {
					listaComDados.push(valor);
				}
			}, this);
		},

		incluirCampo: function (classeDoCampo, botao, validador, padrao) {
			var campo = SisProVisao.instancia.incluirCampo(classeDoCampo);
			SisProControle.instancia.bloquearBotao(botao);
			SisProControle.instancia.adicionarTratadorDeAlteracaoEmCampo(campo, validador);
			validador.adicionarCampo(campo, padrao);
		},

		adicionarCampo: function (classeDoCampo, validador, padrao) {
			var campo = SisProVisao.instancia.adicionarCampo(classeDoCampo);
			SisProControle.instancia.adicionarTratadorDeAlteracaoEmCampo(campo, validador);
			validador.adicionarCampo(campo, padrao);
		},

		iniciarCampo: function (classeDoCampo, validador, padrao) {
			var campo = SisProVisao.instancia.selecionarCampo(classeDoCampo);
			SisProControle.instancia.adicionarTratadorDeAlteracaoEmCampo(campo, validador);
			validador.adicionarCampoObrigatorio(campo, padrao);
		},

		validarFormulario: function (validador) {
			validador.validar();
		}
	});
	SisPro.instancia();

	var Requeridor = Classe.criarSingleton({
		fornecerRequisicaoDeSalvamento: function (uri) {
			var requisicao = new RequisicaoJson(uri);
			requisicao.fixarAtributoDeCabecalho(AtributoHttp.ACCEPT, TipoDeMidia.JSON.comoTexto());
			requisicao.fixarAtributoDeCabecalho(AtributoHttp.CONTENT_TYPE, TipoDeMidia.JSON.comoTexto());
			this.adicionarTratadores(requisicao);
			return requisicao;
		},

		fornecerRequisicaoDePagina: function (uri) {
			var requisicao = new RequisicaoDocumento(uri);
			requisicao.fixarAtributoDeCabecalho(AtributoHttp.ACCEPT, TipoDeMidia.HTML.comoTexto());
			this.adicionarTratadores(requisicao);
			return requisicao;
		},

		adicionarTratadores: function (requisicao) {
			requisicao.tratarInicio = this.tratarInicio.vincularEscopo(this);
			requisicao.tratarProgresso = this.tratarProgresso.vincularEscopo(this);
			requisicao.tratarTermino = this.tratarTermino.vincularEscopo(this);
			requisicao.tratarErro = this.tratarErro.vincularEscopo(this);
			requisicao.tratarAborto = this.tratarAborto.vincularEscopo(this);
			requisicao.tratarEstouroDeTempo = this.tratarEstouroDeTempo.vincularEscopo(this);
			requisicao.tratarResposta = this.tratarResposta.vincularEscopo(this);
			requisicao.tratarRedirecionamento = this.tratarRedirecionamento.vincularEscopo(this);
			requisicao.tratarSucesso = this.tratarSucesso.vincularEscopo(this);
			requisicao.tratarErroDoCliente = this.tratarErroDoCliente.vincularEscopo(this);
			requisicao.tratarErroDoServidor = this.tratarErroDoServidor.vincularEscopo(this);
		},

		tratarInicio: function () {
			//carregado, total, estampaDeTempo
		},

		tratarProgresso: function () {
			//carregado, total, estampaDeTempo
		},

		tratarTermino: function () {
			//carregado, total, estampaDeTempo
		},

		tratarErro: function () {
			var mensagem = "Erro: problema interno.";
			SisProVisao.instancia.mostrarMensagemDeErro(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarAborto: function () {
			var mensagem = "Erro: operação abortada.";
			SisProVisao.instancia.mostrarMensagemDeAviso(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarEstouroDeTempo: function () {
			var mensagem = "Erro: tempo limite atingido.";
			SisProVisao.instancia.mostrarMensagemDeAviso(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarResposta: function () {
			//resposta, codigoDeEstado, carregado, total, estampaDeTempo
		},

		tratarRedirecionamento: function (resposta, codigoDeEstado) {
			var mensagem = String.formatar("%@ - %@.", codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeInformacao(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarSucesso: function (resposta, codigoDeEstado) {
			var mensagem = String.formatar("%@ - %@.", codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeSucesso(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarErroDoCliente: function (resposta, codigoDeEstado) {
			var mensagem = String.formatar("%@ - %@.", codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeErro(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarErroDoServidor: function (resposta, codigoDeEstado) {
			var mensagem = String.formatar("%@ - %@.", codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeAviso(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		}
	});
	Requeridor.instancia();

	var Validador = Classe.criar({
		inicializar: function () {
			this.camposInvalidos = [];
			this.camposValidos = [];
			this.camposEmBranco = [];
			this.validacoes = [];
		},

		validar: function () {
			var valido = true;
			this.camposInvalidos.limpar();
			this.camposValidos.limpar();
			this.camposEmBranco.limpar();
			this.validacoes.paraCada(function (validacao) {
				var campo = validacao.campo;
				var padrao = validacao.padrao;
				var obrigatorio = validacao.obrigatorio;
				if (!Linda.nulo(campo)) {
					if (!this.validarCampo(campo, padrao, obrigatorio)) {
						valido = false;
					}
				}
			}, this);
			return valido;
		},

		adicionarCampo: function (campo, padrao) {
			var validacao = {};
			validacao.campo = campo;
			validacao.padrao = padrao;
			validacao.obrigatorio = false;
			this.validacoes.push(validacao);
		},

		adicionarCampoObrigatorio: function (campo, padrao) {
			var validacao = {};
			validacao.campo = campo;
			validacao.padrao = padrao;
			validacao.obrigatorio = true;
			this.validacoes.push(validacao);
		},

		adicionarCampos: function (campos, padrao) {
			campos.paraCada(function (campo) {
				this.adicionarCampo(campo, padrao);
			}, this);
		},

		validarCampo: function (campo, padrao, obrigatorio) {
			var valor = campo.value;
			if (valor.emBranco()) {
				this.camposEmBranco.push(campo);
				if (obrigatorio) {
					campo.setCustomValidity(Validador.CAMPO_EM_BRANCO);
					return false;
				}
				campo.setCustomValidity(Validador.CORRETO);
				return true;
			} else if (padrao.test(valor)) {
				this.camposValidos.push(campo);
				campo.setCustomValidity(Validador.CORRETO);
				return true;
			} else {
				this.camposInvalidos.push(campo);
				campo.setCustomValidity(Validador.CAMPO_INVALIDO);
				return false;
			}
		}
	});

	Validador.estender({
		NOME: /^([a-zA-Z\u00C0-\u00ff]{1,32}( [a-zA-Z\u00C0-\u00ff]{1,32})?){1,8}$/,
		TELEFONE: /^[(][0-9]{2}[)] [0-9]{4,5}-[0-9]{4}$/,
		EMAIL: /^[a-z0-9._-]{1,32}@([a-z0-9._-]|){1,32}([.][a-z]{2,3}){1,2}$/,
		CPF: /^[0-9]{3}[.][0-9]{3}[.][0-9]{3}-[0-9]{2}$/,
		CNPJ: /^[0-9]{2}[.][0-9]{3}[.][0-9]{3}\/[0-9]{4}-[0-9]{2}$/,
		IE: /^([0-9]{1,16}([\/.-][0-9]{1,16})?){1,8}$/,
		CAMPO_INVALIDO: "Campo inválido.",
		CAMPO_EM_BRANCO: "Campo em branco.",
		CORRETO: ""
	});

	global.SisPro = SisPro;
	global.Requeridor = Requeridor;
	global.Validador = Validador;
}(this));
