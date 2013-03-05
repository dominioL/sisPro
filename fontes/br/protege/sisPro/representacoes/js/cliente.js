new Cliente();

function Cliente() {
	
	var $ = this;
	this.botãoAdicionarTelefone = null;
	this.botãoAdicionarEndereçoEletrônico = null;
	this.botãoSalvarCliente = null;
	this.dadosDoFormulárioDeEdiçãoDeCliente = null;
	this.enlaceCadastroDeEndereço = null;
	this.cliente = null;
	
	this.inicializar = function () {
		$.cliente = P.informaçõesJson.cliente;
		$.botãoAdicionarEndereçoEletrônico = P.obterElementoPeloId("botãoAdicionarEndereçoEletrônico");
		$.botãoAdicionarTelefone = P.obterElementoPeloId("botãoAdicionarTelefone");
		$.botãoSalvarCliente = P.obterElementoPeloId("botãoSalvarCliente");
		$.dadosDoFormulárioDeEdiçãoDeCliente = P.obterElementoPeloId("dadosDoFormulárioDeEdiçãoDeCliente");
		$.enlaceCadastroDeEndereço = P.obterElementoPeloId("enlaceCadastroDeEndereço");
		$.botãoAdicionarEndereçoEletrônico.onclick = $.adicionarEndereçoEletrônico;
		$.botãoAdicionarTelefone.onclick = $.adicionarTelefone;
		$.enlaceCadastroDeEndereço.href = $.cliente.uriDoCadastroDeEndereço;
		if ($.cliente.tipo == "pessoaFisica") {
			P.tornarElementosInvisíveis("site");
			P.tornarElementosInvisíveis("cnpj");
			P.tornarElementosInvisíveis("inscriçãoEstadual");
			$.botãoSalvarCliente.onclick = $.salvarPessoaFisica;
			$.atualizarDadosDaPessoaFisica(P.informaçõesJson);
		}
		if ($.cliente.tipo == "pessoaJurídica") {
			P.tornarElementosInvisíveis("cpf");
			$.botãoSalvarCliente.onclick = $.salvarPessoaJurídica;
			$.atualizarDadosDaPessoaJurídica(P.informaçõesJson);
		}
	}
	
	this.atualizarDadosDoCliente = function(dados) {
		$.cliente = dados.cliente;
		P.informaçõesJson.cliente = $.cliente;
		P.fixarValorDoCampo("nome", $.cliente.nome);
		P.fixarValorDaColeçãoDeCampos("endereçoEletrônico", $.dadosDoFormulárioDeEdiçãoDeCliente, $.cliente.endereçosEletrônicos);
		P.fixarValorDaColeçãoDeCampos("telefone", $.dadosDoFormulárioDeEdiçãoDeCliente, $.cliente.telefones);
		P.fixarValorDoCampo("informaçõesExtras", $.cliente.informaçõesExtras);
	}
	
	this.atualizarDadosDaPessoaFisica = function(dados) {
		$.atualizarDadosDoCliente(dados);
		P.fixarValorDoCampo("cpf", $.cliente.cpf);
	}
	
	this.atualizarDadosDaPessoaJurídica = function(dados) {
		$.atualizarDadosDoCliente(dados);
		P.fixarValorDoCampo("site", $.cliente.site);
		P.fixarValorDoCampo("cnpj", $.cliente.cnpj);
		P.fixarValorDoCampo("inscriçãoEstadual", $.cliente.inscriçãoEstadual);
	}
	
	this.salvarCliente = function(funçãoAtualizadoraDeCliente) {
		P.habilitarÍconeDeCarregamento();
		P.desabilitarBotões([$.botãoAdicionarEndereçoEletrônico, $.botãoAdicionarTelefone, $.botãoSalvarCliente]);
		var requisiçãoHttpJson = new RequisicaoHttpJson($.cliente.uri, "PUT");
		requisiçãoHttpJson.sucesso = function(resposta) {
			funçãoAtualizadoraDeCliente(resposta);
		};
		requisiçãoHttpJson.concluirRequisição = function(resposta) {
			P.desabilitarÍconeDeCarrregamento();
			P.habilitarBotões([$.botãoAdicionarEndereçoEletrônico, $.botãoAdicionarTelefone, $.botãoSalvarCliente]);
		};
		requisiçãoHttpJson.enviarRequisição($.cliente);
	}
	
	this.salvarPessoaFisica = function() {
		$.cliente.nome = P.obterValorDoCampo("nome");
		$.cliente.endereçosEletrônicos = P.obterValoresDaColeçãoDeCampos("endereçoEletrônico");
		$.cliente.telefones = P.obterValoresDaColeçãoDeCampos("telefone");
		$.cliente.cpf = P.obterValorDoCampo("cpf");
		$.cliente.informaçõesExtras = P.obterValorDoCampo("informaçõesExtras");
		$.salvarCliente($.atualizarDadosDaPessoaFisica);
	}
	
	this.salvarPessoaJurídica = function() {
		$.cliente.nome = P.obterValorDoCampo("nome");
		$.cliente.site = P.obterValorDoCampo("site");
		$.cliente.endereçosEletrônicos = P.obterValoresDaColeçãoDeCampos("endereçoEletrônico");
		$.cliente.telefones = P.obterValoresDaColeçãoDeCampos("telefone");
		$.cliente.cnpj = P.obterValorDoCampo("cnpj");
		$.cliente.inscriçãoEstadual = P.obterValorDoCampo("inscriçãoEstadual");
		$.cliente.informaçõesExtras = P.obterValorDoCampo("informaçõesExtras");
		$.salvarCliente($.atualizarDadosDaPessoaJurídica);
	}
	
	this.adicionarTelefone = function () {
		P.clonarCampo("telefone", $.dadosDoFormulárioDeEdiçãoDeCliente);
	}
	
	this.adicionarEndereçoEletrônico = function() {
		P.clonarCampo("endereçoEletrônico", $.dadosDoFormulárioDeEdiçãoDeCliente);
	}
	
	$.inicializar();
}