new Endereco();

function Endereco() {
	
	var $ = this;
	this.botãoSalvarEndereço = null;
	this.endereço = null;
	
	this.inicializar = function() {
		$.botãoSalvarEndereço = P.obterElementoPeloId("botãoSalvarEndereço");
		$.botãoSalvarEndereço.onclick = $.salvar;
		$.atualizarDadosDoEndereço(P.informaçõesJson);
	}
	
	this.salvar = function() {
		P.desabilitarBotão($.botãoSalvarEndereço);
		P.habilitarÍconeDeCarregamento();
		$.endereço.cep = P.obterValorDoCampo("cep");
		$.endereço.estado = P.obterValorDoCampo("estado");
		$.endereço.cidade = P.obterValorDoCampo("cidade");
		$.endereço.bairro = P.obterValorDoCampo("bairro");
		$.endereço.logradouro = P.obterValorDoCampo("logradouro");
		$.endereço.número = P.obterValorDoCampo("número");
		$.endereço.condomínio = P.obterValorDoCampo("condomínio");
		$.endereço.edifícil = P.obterValorDoCampo("edifícil");
		$.endereço.bloco = P.obterValorDoCampo("bloco");
		$.endereço.apartamento = P.obterValorDoCampo("apartamento");
		var requisiçãoHttp = new RequisicaoHttpJson($.endereço.uri, "PUT");
		requisiçãoHttp.sucesso = function(resposta) {
			$.atualizarDadosDoEndereço(resposta);
		};
		requisiçãoHttp.concluirRequisição = function(resposta) {
			P.desabilitarÍconeDeCarrregamento();
			P.habilitarBotão($.botãoSalvarEndereço);
		};
		requisiçãoHttp.enviarRequisição($.endereço);
	}
	
	this.atualizarDadosDoEndereço = function(dados) {
		$.endereço = dados.endereço;
		P.informaçõesJson.endereço = $.endereço;
		P.fixarValorDoCampo("cep", $.endereço.cep);
		P.fixarValorDoCampo("estado", $.endereço.estado);
		P.fixarValorDoCampo("cidade", $.endereço.cidade);
		P.fixarValorDoCampo("bairro", $.endereço.bairro);
		P.fixarValorDoCampo("logradouro", $.endereço.logradouro);
		P.fixarValorDoCampo("número", $.endereço.número);
		P.fixarValorDoCampo("condominio", $.endereço.condominio);
		P.fixarValorDoCampo("condominio", $.endereço.condominio);
		P.fixarValorDoCampo("edifícil", $.endereço.edifícil);
		P.fixarValorDoCampo("bloco", $.endereço.bloco);
		P.fixarValorDoCampo("apartamento", $.endereço.apartamento);
	}
	
	$.inicializar();
}