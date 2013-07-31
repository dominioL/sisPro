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

	var Cadastro = Classe.criar({
		inicializar: function (uri) {
			this.validador = new Validador();
			this.formatador = new Formatador();
			this.uri = uri;
			this.campos = {
				obrigatorios: {},
				multiplos: {},
				unicos: {}
			};
			SisProControle.instancia.adicionarTratadorDeCadastro(this);
		},

		iniciarCampoObrigatorio: function (campo) {
			this.campos.obrigatorios[campo.nome] = campo;
			this.ativarCampoObrigatorio(campo.nome);
		},

		iniciarCampoUnico: function (campo) {
			this.campos.unicos[campo.nome] = campo;
			SisProControle.instancia.adicionarTratadorDeInclusaoDeCampo(this, campo);
		},

		iniciarCampoMultiplo: function (campo) {
			this.campos.multiplos[campo.nome] = campo;
			SisProControle.instancia.adicionarTratadorDeAdicaoDeCampo(this, campo);
		},

		ativarCampoObrigatorio: function (nome) {
			var campo = this.campos.obrigatorios[nome];
			var elemento = campo.fornecerElemento();
			var padrao = campo.padrao;
			this.validador.adicionarCampoObrigatorio(elemento, padrao);
			SisProControle.instancia.adicionarTratadorDeAlteracaoEmCampo(this, elemento);
			if (campo.possuiFormato()) {
				var formato = campo.formato;
				this.formatador.adicionarCampo(elemento, formato);
				SisProControle.instancia.adicionarTratadorDeDigitacaoEmCampo(this, elemento);
			}
			this.validador.validar();
		},

		ativarCampoUnico: function (nome) {
			var campo = this.campos.unicos[nome];
			var elemento = campo.incluirElemento();
			var padrao = campo.padrao;
			var mascara = campo.marcara;
			var botao = campo.fornecerBotao();
			this.validador.adicionarCampo(elemento, padrao);
			SisProControle.instancia.bloquearBotao(botao);
			SisProControle.instancia.adicionarTratadorDeAlteracaoEmCampo(this, elemento);
			if (campo.possuiFormato()) {
				var formato = campo.formato;
				this.formatador.adicionarCampo(elemento, formato);
				SisProControle.instancia.adicionarTratadorDeDigitacaoEmCampo(this, elemento);
			}
			this.validador.validar();
		},

		ativarCampoMultiplo: function (nome) {
			var campo = this.campos.multiplos[nome];
			var elemento = campo.adicionarElemento();
			var padrao = campo.padrao;
			var mascara = campo.marcara;
			this.validador.adicionarCampo(elemento, padrao);
			SisProControle.instancia.adicionarTratadorDeAlteracaoEmCampo(this, elemento);
			if (campo.possuiFormato()) {
				var formato = campo.formato;
				this.formatador.adicionarCampo(elemento, formato);
				SisProControle.instancia.adicionarTratadorDeDigitacaoEmCampo(this, elemento);
			}
			this.validador.validar();
		},

		validar: function () {
			this.validador.validar();
		},

		formatar: function () {
			this.formatador.formatar();
		},

		cadastrar: function () {
			SisPro.instancia.iniciarAtualizacao();
			this.dados = {};
			if (this.validador.validar()) {
				var requisicao = Requeridor.instancia.fornecerRequisicaoDeSalvamento(this.uri);
				this.cadastrarCamposObrigatorios();
				this.cadastrarCamposUnicos();
				this.cadastrarCamposMultiplos();
				requisicao.tratarSucesso = this.finalizarCadastroComSucesso.vincularEscopo(this);
				requisicao.enviarPost(JSON.stringify(this.dados), true);
			} else {
				SisProVisao.instancia.mostrarMensagemDeDadosInvalidos();
				SisPro.instancia.finalizarAtualizacao();
			}
		},

		adicionarCampoAosDados: function (campo) {
			var elemento = campo.fornecerElemento();
			if (!Linda.nulo(elemento)) {
				var valor = elemento.value;
				if (!valor.emBranco()) {
					this.dados[campo.nome] = valor;
				}
			}
		},

		adicionarCamposAosDados: function (campo) {
			this.dados[campo.nomeDeAtributo] = [];
			var listaComDados = this.dados[campo.nomeDeAtributo];
			var elementos = campo.fornecerElementos();
			elementos.paraCada(function (elemento) {
				var valor = elemento.value;
				if (!valor.emBranco()) {
					listaComDados.push(valor);
				}
			}, this);
		},

		cadastrarCamposObrigatorios: function () {
			this.campos.obrigatorios.paraCada(function (campo) {
				this.adicionarCampoAosDados(campo);
			}, this);
		},

		cadastrarCamposUnicos: function () {
			this.campos.unicos.paraCada(function (campo) {
				this.adicionarCampoAosDados(campo);
			}, this);
		},

		cadastrarCamposMultiplos: function () {
			this.campos.multiplos.paraCada(function (campo) {
				this.adicionarCamposAosDados(campo);
			}, this);
		},

		finalizarCadastroComSucesso: function () {
			//TODO
			SisProVisao.instancia.mostrarMensagemDeCadastroBemSucedido();
			SisProControle.instancia.bloquearBotao(SisProVisao.instancia.selecionarBotao("cadastrar"));
			SisPro.instancia.finalizarAtualizacao();
		}
	});

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
			var validacao = {campo: campo, padrao: padrao, obrigatorio: false};
			this.validacoes.push(validacao);
		},

		adicionarCampoObrigatorio: function (campo, padrao) {
			var validacao = {campo: campo, padrao: padrao, obrigatorio: true};
			this.validacoes.push(validacao);
		},

		validarCampo: function (campo, padrao, obrigatorio) {
			var valor = campo.value;
			if (valor.emBranco()) {
				return this.validarCampoEmBranco(campo, obrigatorio);
			} else if (padrao.test(valor)) {
				this.camposValidos.push(campo);
				campo.setCustomValidity(Validador.CORRETO);
				return true;
			} else {
				this.camposInvalidos.push(campo);
				campo.setCustomValidity(Validador.CAMPO_INVALIDO);
				return false;
			}
		},

		validarCampoEmBranco: function (campo, obrigatorio) {
			this.camposEmBranco.push(campo);
			if (obrigatorio) {
				campo.setCustomValidity(Validador.CAMPO_EM_BRANCO);
				return false;
			} else {
				campo.setCustomValidity(Validador.CORRETO);
				return true;
			}
		}
	});

	var Formatador = Classe.criar({
		inicializar: function () {
			this.formatacoes = [];
		},

		adicionarCampo: function (campo, formato) {
			var formatacao = {campo: campo, formato: formato};
			this.formatacoes.push(formatacao);
		},

		formatar: function () {
			this.formatacoes.paraCada(function (formatacao) {
				var valor = formatacao.campo.value;
				var valorFormatado = valor.formatarNumero(formatacao.formato);
				formatacao.campo.setAttribute("value", valorFormatado);
				formatacao.campo.value = valorFormatado;
				// TODO
				console.log(valor);
				console.log(valorFormatado);
			}, this);
		}
	});

	var Campo = Classe.criar({
		inicializar: function (nome) {
			this.nome = nome;
			this.elemento = null;
			this.elementos = [];
		},

		comPadrao: function (padrao) {
			this.padrao = padrao;
			return this;
		},

		comFormato: function (formato) {
			this.formato = formato;
			return this;
		},

		comBotao: function (botao) {
			this.botao = botao;
			return this;
		},

		comNomeDeAtributo: function (nomeDeAtributo) {
			this.nomeDeAtributo = nomeDeAtributo;
			return this;
		},

		possuiFormato: function () {
			return !Linda.nuloOuIndefinido(this.formato);
		},

		adicionarElemento: function () {
			this.elementos.push(SisProVisao.instancia.adicionarCampo(this.nome));
			return this.elementos.ultimo;
		},

		incluirElemento: function () {
			this.elemento = SisProVisao.instancia.incluirCampo(this.nome);
			return this.elemento;
		},

		fornecerElemento: function () {
			if (Linda.nuloOuIndefinido(this.elemento)) {
				this.elemento = SisProVisao.instancia.selecionarCampo(this.nome);
			}
			return this.elemento
		},

		fornecerElementos: function () {
			return this.elementos;
		},

		fornecerBotao: function () {
			return SisProVisao.instancia.selecionarBotao(this.botao);
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

	global.Cadastro = Cadastro;
	global.Campo = Campo;
	global.SisPro = SisPro;
	global.Validador = Validador;
}(this));
