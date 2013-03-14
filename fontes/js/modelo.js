(function (global) {
	"use strict";
	
	var SisPro = new PrototipoUnico({
		inicializarUnico: function () {
			new TratadorDePagina().paraCarregada(this.inicializarSistema.vincularEscopo(this));
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
		
		carregarScripts: function () {
			var scripts = Linda.selecionarTodos("section.conteudo script");
			var cabecalho = Linda.selecionar("head");
			for (var indice = 0; indice < scripts.length; indice++) {
				var script = scripts[indice];
				var novoScript = Linda.criarElemento("script");
				novoScript.type = script.type;
				novoScript.src = script.src;
				cabecalho.appendChild(novoScript);
				script.remove();
			}
		},
		
		receberPagina: function (pagina) {
			SisProVisao.instancia.carregarConteudo(pagina);
			this.carregarScripts();
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
		
		incluirCampoEmConteudo: function (classeDoBotao, classeDoCampo) {
			var botao = Linda.selecionar(String.formatar("section.conteudo button.%@", classeDoBotao));
			var template = Linda.selecionar(String.formatar("section.conteudo template.%@", classeDoCampo));
			var formulario  = Linda.selecionar("section.conteudo > form");
			SisProControle.instancia.bloquearBotao(botao);
			formulario.appendChild(template.content.cloneNode(true));
			template.remove();
		},
		
		adicionarCampoEmConteudo: function (classeDoCampo) {
			var template = Linda.selecionar(String.formatar("section.conteudo template.%@", classeDoCampo));
			var formulario  = Linda.selecionar("section.conteudo > form");
			formulario.appendChild(template.content.cloneNode(true));
		}
	});
	SisPro.instancia();
	
	var Requeridor = new PrototipoUnico({
		inicializarUnico: function () {
			
		},
		
		fornecerRequisicaoDePagina: function (uri) {
			var requisicao = new RequisicaoJson(uri);
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
			requisicao.fixarAtributoDeCabecalho("Accept", "text/html");
			return requisicao;
		},
		
		tratarInicio: function (carregado, total, estampaDeTempo) {},
		
		tratarProgresso: function (carregado, total, estampaDeTempo) {},
		
		tratarTermino: function (carregado, total, estampaDeTempo) {},
		
		tratarErro: function (carregado, total, estampaDeTempo) {},
		
		tratarAborto: function (carregado, total, estampaDeTempo) {},
		
		tratarEstouroDeTempo: function (carregado, total, estampaDeTempo) {},
		
		tratarResposta: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {},
		
		tratarRedirecionamento: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeInformacao(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},
		
		tratarSucesso: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeSucesso(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},
		
		tratarErroDoCliente: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeErro(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		},
		
		tratarErroDoServidor: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeAviso(mensagem);
			SisPro.instancia.finalizarAtualizacao();
		}
	});
	Requeridor.instancia();
	
	global.SisPro = SisPro;
}(this));
