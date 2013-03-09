(function (global) {
	"use strict";
	
	var SisPro = new PrototipoUnico({
		inicializarUnico: function () {
			new TratadorDePagina().paraCarregada(this.inicializarSistema.vincularEscopo(this));
		},
		
		inicializarSistema: function () {
			SisProVisao.instancia();
			this.carregarConteudo();
		},
		
		carregarConteudo: function () {
			var localizacao = Linda.localizacao;
			var pagina = String.concatenar("/html", localizacao.pathname, localizacao.search);
			this.carregarPagina(pagina);
		},
		
		carregarPagina: function (uriDaPagina) {
			SisProVisao.instancia.iniciarAtualizacao();
			var requisicao = Requeridor.instancia.fornecerRequisicaoDePagina(uriDaPagina);
			requisicao.tratarSucesso = this.receberPagina.vincularEscopo(this);
			requisicao.enviarGet(true);
		},
		
		receberPagina: function (pagina) {
			SisProVisao.instancia.carregarConteudo(pagina);
			SisProVisao.instancia.finalizarAtualizacao();
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
		},
		
		tratarSucesso: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeSucesso(mensagem);
		},
		
		tratarErroDoCliente: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeErro(mensagem);
		},
		
		tratarErroDoServidor: function (resposta, codigoDeEstado, carregado, total, estampaDeTempo) {
			var mensagem = String.concatenarComEspaco(codigoDeEstado.comoNumero(), codigoDeEstado.comoTexto());
			SisProVisao.instancia.mostrarMensagemDeAviso(mensagem);
		}
	});
	Requeridor.instancia();
	
	global.SisPro = SisPro;
}(this));
