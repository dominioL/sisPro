var P = new Principal();
window.onload = P.carregarComponentes;

function Principal() {
	
	var $ = this;
	this.sucessoContâiner = null;
	this.erroContâiner = null;
	this.erroLista = null;
	this.erroItemDeLista = null;
	this.fracassoContâiner = null;
	this.íconeDeCarregamento = null;
	this.informaçõesJson = informaçõesJson;
	
	this.carregarComponentes = function() {
		console.log($.informaçõesJson);
		$.sucessoContâiner = $.obterElementoPeloId("sucessoContâiner");
		$.erroContâiner = $.obterElementoPeloId("erroContâiner");
		$.erroLista = $.obterElementoPeloId("erroLista");
		$.erroItemDeLista = $.obterElementoPelaClasse("erroItemDeLista");
		$.fracassoContâiner = $.obterElementoPeloId("fracassoContâiner");
		$.íconeDeCarregamento = $.obterElementoPeloId("íconeDeCarregamento");
	}
	
	this.tornarElementosInvisíveis = function(nomeDosElementos) {
		var campos = $.obterElementosPeloNome(nomeDosElementos);
		for (var indíce = 0; indíce < campos.length; indíce++) {
			$.tornarInvisível(campos.item(indíce));
		}
	}
	
	this.tornarVisível = function(elemento) {
		elemento.style.display = "block";
	}

	this.tornarInvisível = function(elemento) {
		elemento.style.display = "none";
	}
	
	this.habilitarÍconeDeCarregamento = function() {
		$.tornarVisível($.íconeDeCarregamento);
	}
	
	this.desabilitarÍconeDeCarrregamento = function() {
		$.tornarInvisível($.íconeDeCarregamento);
	}
	
	this.habilitarBotão = function(botão) {
		botão.disabled = false;
	}
	
	this.habilitarBotões = function(botões) {
		for (var indíce in botões) {
			$.habilitarBotão(botões[indíce]);
		}
	}
	
	this.desabilitarBotão = function(botão) {
		botão.disabled = true;
	}
	
	this.desabilitarBotões = function(botões) {
		for (var indíce in botões) {
			$.desabilitarBotão(botões[indíce]);
		}
	}
	
	this.fixarTextoAoElemento = function(elemento, texto) {
		elemento.innerHTML = texto;
	}
	
	this.obterElementoPeloId = function(idDoElemento) {
		return document.getElementById(idDoElemento);
	}
	
	this.obterElementoPelaClasse = function(classeDoElemento) {
		return document.getElementsByClassName(classeDoElemento)[0];
	}
	
	this.obterElementosPelaClasse = function(classeDoElemento) {
		return document.getElementsByClassName(classeDoElemento);
	}
	
	this.obterElementoPeloNome = function(nomeDoElemento) {
		return document.getElementsByName(nomeDoElemento)[0];
	}
	
	this.obterElementosPeloNome = function(nomeDoElemento) {
		return document.getElementsByName(nomeDoElemento);
	}
	
	this.clonarCampo = function(nomeDoCampo, contâinerDoCampo) {
		var campo = $.obterElementoPeloNome(nomeDoCampo);
		var campoClonado = campo.cloneNode();
		campoClonado.value = "";
		contâinerDoCampo.insertBefore(campoClonado, campo);
	}
	
	this.obterValorDoCampo = function(nomeDoCampo) {
		return $.obterElementoPeloNome(nomeDoCampo).value;
	}
	
	this.obterValoresDaColeçãoDeCampos = function(nomeDoCampo) {
		var coleçãoDeCampos = $.obterElementosPeloNome(nomeDoCampo);
		var campos = new Array();
		for (var posição = 0; posição < coleçãoDeCampos.length; posição++) {
			campos.push(coleçãoDeCampos[posição].value);
		}
		
		return campos;
	}
	
	this.fixarValorDoCampo = function(nomeDoCampo, valor) {
		if (valor != undefined) {
			$.obterElementoPeloNome(nomeDoCampo).value = valor;
		}
	}
	
	this.fixarValorDaColeçãoDeCampos = function(nomeDoCampo, elementoContâinerDoCampo, valores) {
		if (valores != undefined) {
			var quantidadeDeValores = valores.length;
			while ($.obterElementosPeloNome(nomeDoCampo).length > 1) {
				elementoContâinerDoCampo.removeChild($.obterElementoPeloNome(nomeDoCampo))
			}
			var elementoCampo = $.obterElementoPeloNome(nomeDoCampo);
			for (var indíce = 1; indíce < quantidadeDeValores; indíce++) {
				var elementoCampoClonado = elementoCampo.cloneNode();
				elementoCampoClonado.value = valores[indíce];
				elementoContâinerDoCampo.insertBefore(elementoCampoClonado, elementoCampo);
			}
			if (quantidadeDeValores > 0) {
				elementoCampo.value =  valores[0];
			} else {
				elementoCampo.value =  "";
			}
		}
	}
	
	this.mostrarMensagemDeAção = function(contâinerVisível, contâinersInvisíveis) {
		for (var indíce in contâinersInvisíveis) {
			$.tornarInvisível(contâinersInvisíveis[indíce]);
		}
		$.tornarVisível(contâinerVisível);
	}
	
	this.mostrarMensagemDeSucessoDeAção = function() {
		$.mostrarMensagemDeAção($.sucessoContâiner, [$.fracassoContâiner, $.erroContâiner]);
	}
	
	this.mostrarMensagemDeErroDeAção = function() {
		$.mostrarMensagemDeAção($.erroContâiner, [$.fracassoContâiner, $.sucessoContâiner]);
	}
	
	this.mostrarMensagemDeFracassoDeAção = function() {
		$.mostrarMensagemDeAção($.fracassoContâiner, [$.sucessoContâiner, $.erroContâiner]);
	}
	
	this.inserirElementosNaLista = function(elementoLista, elementosItemDaLista) {
		while (elementoLista.hasChildNodes()) {
			elementoLista.removeChild(elementoLista.lastChild);
		}
		for (var indíce in elementosItemDaLista) {
			elementoLista.appendChild(elementosItemDaLista[indíce]);
		}
	}
}

function RequisicaoHttpJson(uri, método) {
	
	var $ = this;
	this.requisiçãoHttpXml = new XMLHttpRequest();
	this.uri = uri;
	this.método = método;
	this.respostaDaÚltimaRequisição = null;
	
	this.sucesso = function(resposta) {
		
	} 
		
	this.fracasso = function(resposta) {
		
	}
	
	this.erro = function(resposta) {
		
	}
	
	this.concluirRequisição = function(resposta) {
		
	}
	
	this.enviarRequisição = function(dados) {
		$.requisiçãoHttpXml.open($.método, $.uri, true);
		$.requisiçãoHttpXml.onreadystatechange = $.tratarResposta;
		if (typeof(dados) == "object") {
			console.log(dados);
			dados = JSON.stringify(dados);
		}
		if (dados != undefined) {
			$.requisiçãoHttpXml.setRequestHeader("Content-Type", "application/json");
			$.requisiçãoHttpXml.setRequestHeader("Content-Encondig", "UTF-8");
		}
		$.requisiçãoHttpXml.setRequestHeader("Accept", "application/json");
		$.requisiçãoHttpXml.send(dados);
	}
	
	this.tratarResposta = function() {
		if ($.requisiçãoHttpXml.readyState == 4) {
			try {
				$.respostaDaÚltimaRequisição = JSON.parse($.requisiçãoHttpXml.responseText);
			} catch(exeção) {
				$.respostaDaÚltimaRequisição = $.requisiçãoHttpXml.response;
			}
			var códigoDeEstado = $.requisiçãoHttpXml.status;
			if (códigoDeEstado >= 200 && códigoDeEstado < 300) {
				P.mostrarMensagemDeSucessoDeAção()
				$.sucesso($.respostaDaÚltimaRequisição);
			} else {
				var erro = $.respostaDaÚltimaRequisição.erro;
				if (erro != undefined && erro) {
					P.mostrarMensagemDeErroDeAção();
					var itensDeErro = [];
					for (var indíce in $.respostaDaÚltimaRequisição.mensagensDeErro) {
						var itemDeErro = P.erroItemDeLista.cloneNode();
						itemDeErro.innerHTML = $.respostaDaÚltimaRequisição.mensagensDeErro[indíce];
						itensDeErro.push(itemDeErro);
					}
					P.inserirElementosNaLista(erroLista, itensDeErro);
					$.erro($.respostaDaÚltimaRequisição);
				} else {
					P.mostrarMensagemDeFracassoDeAção();
					$.fracasso($.respostaDaÚltimaRequisição);
				}
			}
			$.concluirRequisição($.respostaDaÚltimaRequisição);
		}
	}
	
	this.fornecerRespostaDaÚltimaRequisição = function() {
		return $.respostaDaÚltimaRequisição;
	}
}