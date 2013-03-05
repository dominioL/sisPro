new CadastroDeCliente();

function CadastroDeCliente() {
	
	var $ = this;
	this.botãoAdicionarTelefone = null;
	this.botãoAdicionarEndereçoEletrônico = null;
	this.botãoCadastrarCliente = null;
	this.dadosDoFomulárioDeCadastroDeCliente = null;
	this.uriDoCliente = null;
	this.tipo = "pessoa";

	this.inicializar = function() {
		$.botãoAdicionarTelefone = P.obterElementoPeloId("botãoAdicionarTelefone");
		$.botãoAdicionarEndereçoEletrônico = P.obterElementoPeloId("botãoAdicionarEndereçoEletrônico");
		$.dadosDoFomulárioDeCadastroDeCliente = P.obterElementoPeloId("dadosDoFomulárioDeCadastroDeCliente");
		$.uriDoCliente = P.obterElementoPeloId("uriDoCliente");
		if (($.botãoCadastrarCliente = P.obterElementoPeloId("botãoCadastrarPessoaFisica")) != undefined) {
			$.botãoCadastrarCliente.onclick = $.cadastrarPessoaFisica;
			$.tipo = "pessoaFisica";
		} else {
			$.botãoCadastrarCliente = P.obterElementoPeloId("botãoCadastrarPessoaJurídica");
			$.botãoCadastrarCliente.onclick = $.cadastrarPessoaJurídica;
			$.tipo = "pessoaJurídica";
		}
		$.botãoAdicionarTelefone.onclick = $.adicionarTelefone;
		$.botãoAdicionarEndereçoEletrônico.onclick = $.adicionarEndereçoEletrônico;
	}
	
	this.cadastrarCliente = function(clienteJson) {
		P.habilitarÍconeDeCarregamento();
		P.desabilitarBotões([$.botãoAdicionarEndereçoEletrônico, $.botãoAdicionarTelefone, $.botãoCadastrarCliente]);
		var requisiçãoHttpJson = new RequisicaoHttpJson(P.informaçõesJson.uriClientes, "POST");
		requisiçãoHttpJson.sucesso = function(resposta) {
			$.uriDoCliente.href = resposta.cliente.uri;
		};
		requisiçãoHttpJson.concluirRequisição = function() {
			P.desabilitarÍconeDeCarrregamento();
			P.habilitarBotões([$.botãoAdicionarEndereçoEletrônico, $.botãoAdicionarTelefone, $.botãoCadastrarCliente]);
		};
		requisiçãoHttpJson.enviarRequisição(clienteJson);
	}
	
	this.cadastrarPessoaFisica = function() {
		var pessoaFisica = {
			nome: P.obterValorDoCampo("nome"),
			endereçosEletrônicos: P.obterValoresDaColeçãoDeCampos("endereçoEletrônico"),
			telefones: P.obterValoresDaColeçãoDeCampos("telefone"),
			cpf: P.obterValorDoCampo("cpf"),
			informaçõesExtras: P.obterValorDoCampo("informaçõesExtras"),
			tipo: $.tipo
		};
		$.cadastrarCliente(pessoaFisica);
	} 
	
	this.cadastrarPessoaJurídica = function() {
		var pessoaJurídica = {
			nome: P.obterValorDoCampo("nome"),
			site: P.obterValorDoCampo("site"),
			endereçosEletrônicos: P.obterValoresDaColeçãoDeCampos("endereçoEletrônico"),
			telefones: P.obterValoresDaColeçãoDeCampos("telefone"),
			cnpj: P.obterValorDoCampo("cnpj"),
			inscriçãoEstadual: P.obterValorDoCampo("inscriçãoEstadual"),
			informaçõesExtras: P.obterValorDoCampo("informaçõesExtras"),
			tipo: $.tipo
		};
		$.cadastrarCliente(pessoaJurídica);
	}
	
	this.adicionarTelefone = function() {
		P.clonarCampo("telefone", dadosDoFomulárioDeCadastroDeCliente);
	}
	
	this.adicionarEndereçoEletrônico = function() {
		P.clonarCampo("endereçoEletrônico", dadosDoFomulárioDeCadastroDeCliente);
	}
	
	$.inicializar();
} 
