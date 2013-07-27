package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.excecoes.ExcecaoJsonDeTipo;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validador {
	public static final String NOME = "^(\\p{L}{1,32}( \\p{L}{1,32})?){1,8}$";
	public static final String TELEFONE = "^[(]\\p{Digit}{2}[)] \\p{Digit}{4,5}-\\p{Digit}{4}$";
	public static final String EMAIL = "^[\\p{Lower}\\p{Digit}._-]{1,32}@[\\p{Lower}\\p{Digit}._-]{1,32}([.]\\p{Lower}{2,3}){1,2}$";
	public static final String CPF = "^\\p{Digit}{3}[.]\\p{Digit}{3}[.]\\p{Digit}{3}-\\p{Digit}{2}$";
	public static final String CNPJ = "^\\p{Digit}{2}[.]\\p{Digit}{3}[.]\\p{Digit}{3}/\\p{Digit}{4}-\\p{Digit}{2}$";
	public static final String IE = "^(\\p{Digit}{1,16}([./-]\\p{Digit}{1,16})?){1,8}$";

	private Boolean valido;

	public Validador() {
		valido = true;
	}

	public void validarListaDeCampos(ObjetoJson dados, String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		if (valorJson != null) {
			validarListaDeCamposPadrao(valorJson, nomeDoCampo, validacao);
		}
	}

	public void validarListaDeCamposObrigatorio(ObjetoJson dados, String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		if (valorJson == null) {
			invalidarCampo(nomeDoCampo);
		} else {
			validarListaDeCamposPadrao(valorJson, nomeDoCampo, validacao);
		}
	}

	public void validarCampo(ObjetoJson dados, String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		if (valorJson != null) {
			validarCampoPadrao(valorJson, nomeDoCampo, validacao);
		}
	}

	public void validarCampoObrigatorio(ObjetoJson dados, String nomeDoCampo, String validacao) {
		ValorJson valorJson = dados.fornecer(nomeDoCampo);
		if (valorJson == null) {
			invalidarCampo(nomeDoCampo);
		} else {
			validarCampoPadrao(valorJson, nomeDoCampo, validacao);
		}
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
