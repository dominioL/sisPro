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
			// TODO
			var tratador = new TratadorDePagina();
			tratador.paraCarregamento(this.inicializarSistema.vincularEscopo(this))
			tratador.paraAlteracaoNoHistorico(this.carregarConteudo.vincularEscopo(this));
			this.reescritor = new Reescritor();
			this.reescritor.comBase("/html");
			this.reescritor.comReescrita("/clientes/cadastro", "/cadastroDeCliente");
			this.reescritor.comReescrita("/cliente/!alfa+", "/cliente");
			this.reescritor.comReescrita("/cliente/!alfa+/endereco/!alfa+", "/endereco");
			this.reescritor.comReescrita("/cliente/!alfa+/endereco/!alfa+/orcamento/!alfa+", "/orcamento");
		},

		inicializarSistema: function () {
			SisProVisao.instancia();
			SisProControle.instancia();
			// this.carregarConteudo();
		},

		carregarConteudo: function () {
			this.limparConteudo();
			var localizacao = Linda.localizacao;
			var caminho = localizacao.pathname;
			var busca = localizacao.search;
			var pagina = this.reescritor.reescrever(caminho, busca);
			this.carregarPagina(pagina);
		},

		carregarConteudoDinamicamente: function (uri) {
			var historico = Linda.historico;
			historico.pushState(null, Linda.documento.title, uri);
			this.carregarConteudo();
		},

		adicionarConteudo: function (pagina) {
			this.carregarPagina(pagina);
		},

		limparConteudo: function () {
			SisProVisao.instancia.limparConteudo();
		},

		carregarPagina: function (uriDaPagina) {
			this.iniciarAtualizacao();
			var requisicao = Requeridor.instancia.fornecerRequisicaoDePagina(uriDaPagina);
			requisicao.tratarSucesso = this.receberPagina.vincularEscopo(this);
			requisicao.enviarGet(true);
		},

		carregarScripts: function (pagina) {
			// TODO
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

	var Requeridor = Classe.criarSingleton({
		fornecerRequisicaoDeSalvamento: function (uri) {
			var requisicao = new RequisicaoJson(uri);
			requisicao.fixarAtributoDeCabecalho(AtributoHttp.ACCEPT, TipoDeMidia.JSON.comoTexto());
			requisicao.fixarAtributoDeCabecalho(AtributoHttp.CONTENT_TYPE, TipoDeMidia.JSON.comoTexto());
			this.adicionarTratadores(requisicao);
			return requisicao;
		},

		fornecerRequisicaoDePedidoJson: function (uri) {
			var requisicao = new RequisicaoJson(uri);
			requisicao.fixarAtributoDeCabecalho(AtributoHttp.ACCEPT, TipoDeMidia.JSON.comoTexto());
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
			// TODO
			//carregado, total, estampaDeTempo
		},

		tratarProgresso: function () {
			// TODO
			//carregado, total, estampaDeTempo
		},

		tratarTermino: function () {
			// TODO
			//carregado, total, estampaDeTempo
		},

		tratarErro: function () {
			SisProVisao.instancia.mostrarMensagemDeErro(Mensagens.ERRO);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarAborto: function () {
			SisProVisao.instancia.mostrarMensagemDeAviso(Mensagens.OPERACAO_ABORTADA);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarEstouroDeTempo: function () {
			SisProVisao.instancia.mostrarMensagemDeAviso(Mensagens.ESTOURO_DE_TEMPO);
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarResposta: function () {
			// TODO
			//resposta, codigoDeEstado, carregado, total, estampaDeTempo
		},

		tratarRedirecionamento: function (resposta, codigoDeEstado) {
			SisProVisao.instancia.mostrarMensagemDeInformacao(codigoDeEstado.comoTextoFormatado());
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarSucesso: function (resposta, codigoDeEstado) {
			SisProVisao.instancia.mostrarMensagemDeSucesso(codigoDeEstado.comoTextoFormatado());
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarErroDoCliente: function (resposta, codigoDeEstado) {
			SisProVisao.instancia.mostrarMensagemDeErro(codigoDeEstado.comoTextoFormatado());
			SisPro.instancia.finalizarAtualizacao();
		},

		tratarErroDoServidor: function (resposta, codigoDeEstado) {
			SisProVisao.instancia.mostrarMensagemDeAviso(codigoDeEstado.comoTextoFormatado());
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
				SisProVisao.instancia.mostrarMensagemDeErro(Mensagens.DADOS_INVALIDOS);
				SisPro.instancia.finalizarAtualizacao();
			}
		},

		adicionarCampoAosDados: function (campo) {
			var elemento = campo.fornecerElemento();
			if (!Linda.nulo(elemento)) {
				var valor = SisProVisao.instancia.obterValor(elemento);
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
				var valor = SisProVisao.instancia.obterValor(elemento);
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
			SisProVisao.instancia.mostrarMensagemDeSucesso(Mensagens.CADASTRO_BEM_SUCEDIDO);
			SisProControle.instancia.bloquearBotao(SisProVisao.instancia.selecionarBotao("cadastrar"));
			SisPro.instancia.finalizarAtualizacao();
			SisPro.instancia.carregarConteudoDinamicamente("/cliente/lucas");
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
				this.camposEmBranco.push(campo);
				var validacao = obrigatorio ? Validador.CAMPO_EM_BRANCO : Validador.CORRETO;
				SisProVisao.instancia.fixarValidacaoEmCampo(campo, validacao);
				return !obrigatorio;
			} else if (padrao.test(valor)) {
				this.camposValidos.push(campo);
				SisProVisao.instancia.fixarValidacaoEmCampo(campo, Validador.CORRETO);
				return true;
			} else {
				this.camposInvalidos.push(campo);
				SisProVisao.instancia.fixarValidacaoEmCampo(campo, Validador.CAMPO_INVALIDO);
				return false;
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
				var campo = formatacao.campo;
				var valor = SisProVisao.instancia.obterValor(campo);
				var valorFormatado = valor.formatarNumero(formatacao.formato);
				SisProVisao.instancia.fixarValor(campo, valorFormatado);
			}, this);
		}
	});

	var Reescritor = Classe.criar({
		inicializar: function () {
			this.reescritas = [];
			this.uriBase = "";
		},

		comUriBase: function (uriBase) {
			this.uriBase = uriBase;
		},

		comReescrita: function (padrao, reescrita) {
			padrao = padrao.replace(/!alfa/g, "[0-9a-zA-Z]");
			padrao = padrao.replace(/!texto/g, "[a-zA-Z]");
			padrao = padrao.replace(/!numero/g, "[0-9]");
			this.reescritas.push([padrao, reescrita]);
		},

		reescrever: function (caminho, parametros) {
			var novoCaminho = caminho;
			parametros = (Linda.instanciaDe(parametros, String) ? parametros : "";
			this.reescritas.paraCada(function (reescrita) {
				var combina = caminho.match(reescrita.primeiro);
				if (!Linda.nulo(combina) && combina.quantidadeIgual(1) && combina.primeiro === caminho) {
					novoCaminho = reescrita.ultimo;
				}
			}, this);
			return String.concatenar(this.uriBase, novoCaminho, parametros);
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
			return this.elemento;
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

	SisPro.instancia();
	global.Cadastro = Cadastro;
	global.Campo = Campo;
	global.SisPro = SisPro;
	global.Requeridor = Requeridor;
	global.Validador = Validador;
}(this));
