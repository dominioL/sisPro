package br.dominioL.sisPro.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeTipo;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Par;
import br.dominioL.sisPro.dominio.Entidade;

public final class Validador {
	public static final String NOME = "^(\\p{L}{1,32}( \\p{L}{1,32})?){1,8}$";
	public static final String TELEFONE = "^[(]\\p{Digit}{2}[)] \\p{Digit}{4,5}-\\p{Digit}{4}$";
	public static final String EMAIL = "^[\\p{Lower}\\p{Digit}._-]{1,32}@[\\p{Lower}\\p{Digit}._-]{1,32}([.]\\p{Lower}{2,3}){1,2}$";
	public static final String CPF = "^\\p{Digit}{3}[.]\\p{Digit}{3}[.]\\p{Digit}{3}-\\p{Digit}{2}$";
	public static final String CNPJ = "^\\p{Digit}{2}[.]\\p{Digit}{3}[.]\\p{Digit}{3}/\\p{Digit}{4}-\\p{Digit}{2}$";
	public static final String IE = "^(\\p{Digit}{1,16}([./-]\\p{Digit}{1,16})?){1,8}$";

	private Boolean valido;
	private ObjetoJson dados;
	private ListaEncadeada<IdentificadorJson> camposValidados;

	private Validador(ObjetoJson dados) {
		this.dados = dados;
		valido = true;
		camposValidados = ListaEncadeada.criar();
	}

	public static Validador criar(ObjetoJson dados) {
		return new Validador(dados);
	}

	public static Validador criar(Entidade<?> entidade) {
		return new Validador(entidade.comoJson());
	}

	public Validador validarListaDeCampos(String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		camposValidados.inserirNoFim(Json.criarIdentificador(nomeDoCampo));
		if (valorJson != null) {
			validarListaDeCamposPadrao(valorJson, nomeDoCampo, validacao);
		}
		return this;
	}

	public Validador comListaDeCamposObrigatorio(String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		camposValidados.inserirNoFim(Json.criarIdentificador(nomeDoCampo));
		if (valorJson == null) {
			invalidarCampo(nomeDoCampo);
		} else {
			validarListaDeCamposPadrao(valorJson, nomeDoCampo, validacao);
		}
		return this;
	}

	public Validador comCampo(String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		camposValidados.inserirNoFim(Json.criarIdentificador(nomeDoCampo));
		if (valorJson != null) {
			validarCampoPadrao(valorJson, nomeDoCampo, validacao);
		}
		return this;
	}

	public Validador comCampoObrigatorio(String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		camposValidados.inserirNoFim(Json.criarIdentificador(nomeDoCampo));
		if (valorJson == null) {
			invalidarCampo(nomeDoCampo);
		} else {
			validarCampoPadrao(valorJson, nomeDoCampo, validacao);
		}
		return this;
	}

	public Validador naoPermitirOutrosCampos() {
		for (Par<IdentificadorJson, ValorJson> chaveValor : dados) {
			IdentificadorJson chave = chaveValor.fornecerChave();
			if (!camposValidados.contem(chave)) {
				invalidarCampo(chave.comoTexto());
			}
		}
		return this;
	}

	public Boolean validar() {
		return valido;
	}

	private void validarCampoPadrao(ValorJson valorJson, String nomeDoCampo, String validacao) {
		try {
			String valor = valorJson.comoTexto();
			Pattern padrao = Pattern.compile(validacao);
			Matcher validador = padrao.matcher(valor);
			if(!validador.matches()) {
				invalidarCampo(valor, nomeDoCampo);
			}
		} catch (ExcecaoJsonDeTipo excecao) {
			invalidarCampo(valorJson.comoTextoJson(), nomeDoCampo);
		}
	}

	private void validarListaDeCamposPadrao(ValorJson valorJson, String nomeDoCampo, String validacao) {
		try {
			ListaJson campos = valorJson.comoLista();
			for (ValorJson campo : campos) {
				validarCampoPadrao(campo, nomeDoCampo, validacao);
			}
		} catch (ExcecaoJsonDeTipo excecao) {
			invalidarCampo(valorJson.comoTextoJson(), nomeDoCampo);
		}
	}

	private void invalidarCampo(String valor, String nomeDoCampo) {
		valido = false;
		System.out.println(String.format("Campo %s inválido: %s", nomeDoCampo, valor));
	}

	private void invalidarCampo(String nomeDoCampo) {
		valido = false;
		System.out.println(String.format("Campo %s inválido.", nomeDoCampo));
	}
}
